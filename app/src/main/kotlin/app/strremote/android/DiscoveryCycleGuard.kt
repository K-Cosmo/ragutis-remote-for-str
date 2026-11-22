package app.strremote.android

/**
 * One bounded STR-discovery generation. mDNS announcements may be duplicated,
 * but neither in-flight nor completed endpoint probes may be restarted by them.
 * An explicit device key may share a host with another device during IP reuse.
 */
internal class DiscoveryCycleGuard {
    private val seenIdentities = mutableSetOf<String>()

    fun newGeneration() = seenIdentities.clear()

    fun claimProbe(identity: String): Boolean =
        identity.isNotBlank() && seenIdentities.add(identity)

    companion object {
        /** A discovery deadline is always terminal, even with stuck I/O. */
        fun shouldShowChecking(
            discoveryActive: Boolean,
            discoveryTimedOut: Boolean,
            pendingProbes: Boolean
        ): Boolean = discoveryActive && !discoveryTimedOut && pendingProbes
    }
}
