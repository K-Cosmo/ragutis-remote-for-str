package app.strremote.android

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class SpeakerRowPolicyTest {
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
        // Room assignment does not participate in this policy; it must not set hasEndpoint=true.
        assertEquals(false, reach(saved = true, finished = true, last = false))
        assertEquals(false, reach(saved = true, finished = false, last = false))
    }
    @Test fun savedOnlineRemainsOnlineAfterRoomUpdate() { assertEquals(true, reach(saved = true, verified = true, finished = true)) }
    @Test fun savedUnknownUsesLastKnownWhileChecking() { assertEquals(true, reach(saved = true, last = true)) }
    @Test fun wifiLossCannotAppearOnline() { assertEquals(false, reach(verified = true, wifi = false)) }
    @Test fun pendingBoseNeverClaimsStrOnline() { assertNull(reach(verified = true, finished = true, pending = true)) }
}
