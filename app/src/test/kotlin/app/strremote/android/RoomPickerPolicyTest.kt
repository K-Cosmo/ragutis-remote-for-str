package app.strremote.android

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertThrows
import org.junit.Test

class RoomPickerPolicyTest {
    @Test fun selectedRowFollowsExistingSavedAssignment() {
        assertEquals(0, RoomPickerPolicy.selectedIndex(null))
        RoomPickerPolicy.presets.forEachIndexed { i, preset ->
            assertEquals(i + 1, RoomPickerPolicy.selectedIndex(RoomAssignment(preset = preset)))
        }
        assertEquals(7, RoomPickerPolicy.selectedIndex(RoomAssignment(customName = "Office")))
        assertEquals(0, RoomPickerPolicy.selectedIndex(RoomAssignment()))
    }

    @Test fun presetRowsRetainStableStoredRoomIdentity() {
        assertNull(RoomPickerPolicy.assignmentAt(0))
        RoomPickerPolicy.presets.forEachIndexed { i, preset ->
            assertEquals(RoomAssignment(preset = preset), RoomPickerPolicy.assignmentAt(i + 1))
        }
    }

    @Test fun customTextEntryCannotBeConfusedWithNoRoom() {
        assertThrows(IllegalArgumentException::class.java) {
            RoomPickerPolicy.assignmentAt(RoomPickerPolicy.CUSTOM_INDEX)
        }
        assertThrows(IllegalArgumentException::class.java) {
            RoomPickerPolicy.assignmentAt(-1)
        }
    }
}
