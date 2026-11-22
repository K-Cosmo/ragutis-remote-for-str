package app.strremote.android

internal data class SpeakerCandidate(
    val key: String,
    val serviceName: String,
    val friendlyName: String,
    val host: String,
    val advertisedPort: Int,
    val model: String?,
    val version: String?
)

internal data class SpeakerEndpoint(
    val host: String,
    val port: Int,
    val name: String,
    val model: String? = null,
    val key: String? = null
) {
    val baseUrl: String
        get() = "http://${urlHost(host)}:$port/"

    private fun urlHost(value: String): String {
        if (!value.contains(':')) return value
        val escaped = value.replace("%", "%25")
        return if (escaped.startsWith("[") && escaped.endsWith("]")) escaped else "[$escaped]"
    }
}

internal enum class RoomPreset(val storageId: String) {
    LIVING_ROOM("living_room"),
    BEDROOM("bedroom"),
    KITCHEN("kitchen"),
    BATHROOM("bathroom"),
    KIDS_ROOM("kids_room"),
    GARDEN("garden");

    companion object {
        fun fromStorageId(value: String?): RoomPreset? =
            entries.firstOrNull { it.storageId == value }
    }
}

internal data class RoomAssignment(
    val preset: RoomPreset? = null,
    val customName: String? = null
)

internal data class SavedSpeaker(
    val endpoint: SpeakerEndpoint,
    val room: RoomAssignment? = null
)

/** UI row data, separate from persisted room metadata and the live HTTP probe. */
internal data class SpeakerRow(
    val candidate: SpeakerCandidate,
    val endpoint: SpeakerEndpoint?,
    val probeFinished: Boolean,
    val saved: SavedSpeaker? = null,
    val discovered: Boolean = true,
    val lastReachable: Boolean? = null,
    val pendingBose: Boolean = false,
    val pendingBoseVerified: Boolean = false,
    val pendingRoom: RoomAssignment? = null
) {
    /** Never upgrade a stored endpoint to a live endpoint merely because a room changed. */
    fun withSavedMetadata(updated: SavedSpeaker): SpeakerRow = copy(saved = updated)
}



// The discovery key may be a real device ID or a synthesized fallback
// (serviceName@host / host:host). Only real IDs may override host equality.
internal fun stableSpeakerKey(key: String?, host: String): String? {
    val id = key?.takeIf { it.isNotBlank() } ?: return null
    return id.takeUnless {
        it.equals("host:$host", ignoreCase = true) ||
            it.endsWith("@$host", ignoreCase = true)
    }
}

internal fun speakerCandidateMatches(first: SpeakerCandidate, second: SpeakerCandidate): Boolean =
    speakerIdentityMatches(
        stableSpeakerKey(first.key, first.host), first.host,
        stableSpeakerKey(second.key, second.host), second.host
    )

internal fun speakerCandidateMatchesEndpoint(
    candidate: SpeakerCandidate, endpoint: SpeakerEndpoint
): Boolean = speakerIdentityMatches(
    stableSpeakerKey(candidate.key, candidate.host), candidate.host,
    stableSpeakerKey(endpoint.key, endpoint.host), endpoint.host
)

private fun speakerIdentityMatches(
    firstKey: String?, firstHost: String, secondKey: String?, secondHost: String
): Boolean =
    if (firstKey != null && secondKey != null) firstKey == secondKey
    else firstHost.equals(secondHost, ignoreCase = true)

internal fun speakerVisibilityKey(candidate: SpeakerCandidate): String =
    stableSpeakerKey(candidate.key, candidate.host)?.let { "key:${it.lowercase()}" }
        ?: "host:${candidate.host.lowercase()}"

internal fun speakerVisibilityKey(endpoint: SpeakerEndpoint): String =
    stableSpeakerKey(endpoint.key, endpoint.host)?.let { "key:${it.lowercase()}" }
        ?: "host:${endpoint.host.lowercase()}"

internal fun speakerRowMatchesCandidate(row: SpeakerRow, candidate: SpeakerCandidate): Boolean {
    // A saved *unkeyed* endpoint may match by host, but it must not erase
    // a different real key already established by a discovery announcement.
    if (!speakerCandidateMatches(row.candidate, candidate)) return false
    val endpoint = row.endpoint ?: row.saved?.endpoint ?: return true
    return speakerCandidateMatchesEndpoint(candidate, endpoint)
}

internal fun speakerRowMatchesEndpoint(row: SpeakerRow, endpoint: SpeakerEndpoint): Boolean {
    if (!speakerCandidateMatchesEndpoint(row.candidate, endpoint)) return false
    val existing = row.endpoint ?: row.saved?.endpoint ?: return true
    return speakerMatches(existing, endpoint)
}

internal fun speakerIdentity(endpoint: SpeakerEndpoint): String =
    endpoint.key?.takeIf { it.isNotBlank() } ?: "host:${endpoint.host.lowercase()}"

internal fun speakerMatches(first: SpeakerEndpoint, second: SpeakerEndpoint): Boolean {
    val firstKey = first.key?.takeIf { it.isNotBlank() }
    val secondKey = second.key?.takeIf { it.isNotBlank() }
    if (firstKey != null && secondKey != null) return firstKey == secondKey
    return first.host.equals(second.host, ignoreCase = true)
}

internal fun hostForDisplay(host: String): String =
    if (host.contains(':')) "[$host]" else host
