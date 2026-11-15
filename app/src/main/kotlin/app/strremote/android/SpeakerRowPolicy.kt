package app.strremote.android

/** Pure list-row decisions independent of room metadata and Android UI. */
internal enum class SpeakerRemovalAction { REMOVE_PENDING, REMOVE_SAVED, HIDE_DISCOVERED }

internal object SpeakerRowPolicy {
    fun removalAction(saved: Boolean, pendingBose: Boolean): SpeakerRemovalAction = when {
        pendingBose -> SpeakerRemovalAction.REMOVE_PENDING
        saved -> SpeakerRemovalAction.REMOVE_SAVED
        else -> SpeakerRemovalAction.HIDE_DISCOVERED
    }

    /** Null means that the current discovery probe has not settled. */
    fun reachability(
        hasWifiTransport: Boolean,
        hasEndpoint: Boolean,
        probeFinished: Boolean,
        lastReachable: Boolean?,
        saved: Boolean,
        pendingBose: Boolean
    ): Boolean? = when {
        pendingBose -> null
        !hasWifiTransport -> false
        !saved && hasEndpoint -> true
        !saved && probeFinished -> false
        !saved -> null
        probeFinished -> hasEndpoint
        else -> lastReachable
    }
}
