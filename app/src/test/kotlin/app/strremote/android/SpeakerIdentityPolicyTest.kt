package app.strremote.android

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SpeakerIdentityPolicyTest {
    private fun candidate(key: String, host: String = "192.0.2.15", service: String = "Speaker") =
        SpeakerCandidate(
            key = key, serviceName = service, friendlyName = "Speaker",
            host = host, advertisedPort = 8888, model = null, version = null
        )

    private fun endpoint(key: String?, host: String = "192.0.2.15") =
        SpeakerEndpoint(host = host, port = 8888, name = "Speaker", key = key)

    @Test fun differentDeviceIdsNeverMergeOnlyBecauseHostMatches() {
        val first = candidate("device-A")
        val second = candidate("device-B")
        val row = SpeakerRow(first, endpoint = null, probeFinished = false)
        assertFalse(speakerCandidateMatches(first, second))
        assertFalse(speakerRowMatchesCandidate(row, second))
        assertFalse(speakerRowMatchesEndpoint(row, endpoint("device-B")))
    }

    @Test fun savedManualEndpointCanAcquireFirstRealKeyButNotSecond() {
        val manual = endpoint(null)
        val initial = SpeakerRow(
            candidate = candidate("host:192.0.2.15"),
            endpoint = null, probeFinished = false, saved = SavedSpeaker(manual)
        )
        assertTrue(speakerRowMatchesCandidate(initial, candidate("device-A")))
        val discovered = initial.copy(candidate = candidate("device-A"))
        assertFalse(speakerRowMatchesCandidate(discovered, candidate("device-B")))
        assertFalse(speakerRowMatchesEndpoint(discovered, endpoint("device-B")))
        assertTrue(speakerRowMatchesEndpoint(discovered, endpoint("device-A")))
    }

    @Test fun sameDeviceIdCanFollowAddressChange() {
        val old = candidate("device-A")
        val new = candidate("device-A", host = "192.0.2.99")
        assertTrue(speakerCandidateMatches(old, new))
        assertTrue(speakerRowMatchesCandidate(SpeakerRow(old, null, false), new))
        assertTrue(speakerRowMatchesEndpoint(SpeakerRow(old, null, false), endpoint("device-A", "192.0.2.99")))
    }

    @Test fun syntheticDiscoveryKeyFallsBackToHost() {
        val legacy = candidate("Speaker@192.0.2.15")
        val otherMdnsService = candidate("Remote@192.0.2.15", service = "Remote")
        assertTrue(speakerCandidateMatches(legacy, otherMdnsService))
        assertEquals("host:192.0.2.15", speakerVisibilityKey(legacy))
        assertEquals("host:192.0.2.15", speakerVisibilityKey(otherMdnsService))
    }

    @Test fun hidingOneKeyedSpeakerDoesNotHideAnotherOnSameHost() {
        val first = candidate("device-A")
        val second = candidate("device-B")
        val hidden = setOf(speakerVisibilityKey(first))
        assertFalse(speakerVisibilityKey(second) in hidden)
        assertEquals("key:device-a", speakerVisibilityKey(first))
        assertEquals("key:device-b", speakerVisibilityKey(second))
        assertEquals("key:device-a", speakerVisibilityKey(endpoint("device-A")))
    }

    @Test fun unkeyedManualHostStillUsesHostSuppression() {
        val unkeyed = endpoint(null)
        assertEquals("host:192.0.2.15", speakerVisibilityKey(unkeyed))
        assertEquals("host:192.0.2.15", speakerVisibilityKey(candidate("host:192.0.2.15")))
    }

    @Test fun unkeyedSavedHostIsNotClaimedByTwoDifferentKeyedRows() {
        val manual = SavedSpeaker(endpoint(null))
        val claimed = SpeakerRow(candidate("device-A"), null, false, saved = manual)
        assertTrue(savedSpeakerClaimedByDifferentIdentity(
            listOf(claimed), manual, candidate("device-B")
        ))
        assertFalse(savedSpeakerClaimedByDifferentIdentity(
            listOf(claimed), manual, candidate("device-A")
        ))
        assertFalse(savedSpeakerClaimedByDifferentIdentity(
            listOf(SpeakerRow(candidate("host:192.0.2.15"), null, false, saved = manual)),
            manual, candidate("device-A")
        ))
    }

    @Test fun distinctKeysOnOneHostMayBothStartProbes() {
        val guard = DiscoveryCycleGuard()
        val first = speakerVisibilityKey(candidate("device-A"))
        val second = speakerVisibilityKey(candidate("device-B"))
        assertTrue(guard.claimProbe(first))
        assertFalse(guard.claimProbe(first))
        assertTrue(guard.claimProbe(second))
        guard.newGeneration()
        assertTrue(guard.claimProbe(first))
    }
}
