package app.strremote.android

/**
 * Stable order and mapping for the native room picker. A room is presentation
 * metadata only; it must not affect STR routing or speaker identity.
 */
internal object RoomPickerPolicy {
    const val NONE_INDEX = 0
    const val CUSTOM_INDEX = 7

    val presets: List<RoomPreset> = listOf(
        RoomPreset.LIVING_ROOM, RoomPreset.BEDROOM, RoomPreset.KITCHEN,
        RoomPreset.BATHROOM, RoomPreset.KIDS_ROOM, RoomPreset.GARDEN
    )

    fun selectedIndex(current: RoomAssignment?): Int {
        if (current == null) return NONE_INDEX
        if (!current.customName.isNullOrBlank()) return CUSTOM_INDEX
        val preset = current.preset ?: return NONE_INDEX
        val index = presets.indexOf(preset)
        return if (index >= 0) index + 1 else NONE_INDEX
    }

    /** Custom is a separate text-entry action; it is intentionally rejected. */
    fun assignmentAt(index: Int): RoomAssignment? {
        require(index in NONE_INDEX until CUSTOM_INDEX)
        return if (index == NONE_INDEX) null
            else RoomAssignment(preset = presets[index - 1])
    }
}
