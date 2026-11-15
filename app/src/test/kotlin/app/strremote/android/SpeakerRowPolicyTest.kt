package app.strremote.android

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

class SpeakerRowPolicyTest {
    private val candidate = SpeakerCandidate(
        key = "device-1", serviceName = "Speaker", friendlyName = "Speaker",
        host = "192.0.2.15", advertisedPort = 8888,
        model = "SoundTouch 20 III", version = null
    )
    private val savedEndpoint = SpeakerEndpoint(
        host = "192.0.2.15", port = 8888, name = "Speaker", key = "device-1"
    )

    private fun reach(
        saved: Boolean = false, verified: Boolean = false, finished: Boolean = false,
        last: Boolean? = null, wifi: Boolean = true, pending: Boolean = false
    ): Boolean? = SpeakerRowPolicy.reachability(wifi, verified, finished, last, saved, pending)

    @Test fun unsavedSpeakersHaveRemoveActionWithoutRoom() {
        assertEquals(SpeakerRemovalAction.HIDE_DISCOVERED, SpeakerRowPolicy.removalAction(false, false))
        assertEquals(SpeakerRemovalAction.REMOVE_SAVED, SpeakerRowPolicy.removalAction(true, false))
        assertEquals(SpeakerRemovalAction.REMOVE_PENDING, SpeakerRowPolicy.removalAction(false, true))
    }

    @Test fun discoveredSpeakerIsNeutralWhileProbeRuns() { assertNull(reach()) }
    @Test fun discoveredSpeakerIsGreenAfterSuccess() { assertEquals(true, reach(verified = true, finished = true)) }
    @Test fun discoveredSpeakerIsRedAfterFailure() { assertEquals(false, reach(finished = true)) }
    @Test fun savedOfflineStaysOfflineOnRoomOnlyUpdate() {
        val originallySaved = SavedSpeaker(savedEndpoint, RoomAssignment(preset = RoomPreset.LIVING_ROOM))
        val offline = SpeakerRow(
            candidate = candidate, endpoint = null, probeFinished = true,
            saved = originallySaved, lastReachable = false
        )
        val changed = offline.withSavedMetadata(originallySaved.copy(room = null))
        assertNull(changed.endpoint)
        assertTrue(changed.probeFinished)
        assertEquals(false, changed.lastReachable)
        assertNull(changed.saved?.room)
        assertEquals(false, SpeakerRowPolicy.reachability(
            hasWifiTransport = true,
            hasEndpoint = changed.endpoint != null,
            probeFinished = changed.probeFinished,
            lastReachable = changed.lastReachable,
            saved = changed.saved != null,
            pendingBose = changed.pendingBose
        ))
    }

    @Test fun savedOfflineRemainsUnknownProbeAndOfflineCacheAfterDifferentRoom() {
        val cached = SavedSpeaker(savedEndpoint)
        val checking = SpeakerRow(candidate, endpoint = null, probeFinished = false, saved = cached, lastReachable = false)
        val changed = checking.withSavedMetadata(cached.copy(room = RoomAssignment(customName = "Office")))
        assertNull(changed.endpoint)
        assertFalse(changed.probeFinished)
        assertEquals(false, changed.lastReachable)
    }

    @Test fun roomMetadataUpdatePreservesVerifiedOnlineEndpoint() {
        val live = SpeakerRow(candidate, endpoint = savedEndpoint, probeFinished = true)
        val changed = live.withSavedMetadata(SavedSpeaker(savedEndpoint, RoomAssignment(preset = RoomPreset.KITCHEN)))
        assertSame(savedEndpoint, changed.endpoint)
        assertTrue(changed.probeFinished)
    }
    @Test fun savedOnlineRemainsOnlineAfterRoomUpdate() { assertEquals(true, reach(saved = true, verified = true, finished = true)) }
    @Test fun savedUnknownUsesLastKnownWhileChecking() { assertEquals(true, reach(saved = true, last = true)) }
    @Test fun wifiLossCannotAppearOnline() { assertEquals(false, reach(verified = true, wifi = false)) }
    @Test fun pendingBoseNeverClaimsStrOnline() { assertNull(reach(verified = true, finished = true, pending = true)) }
}
