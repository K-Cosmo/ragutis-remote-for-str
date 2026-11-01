package app.strremote.android

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class BoseSetupModelTest {
    @Test fun modelPickerOrderIsNumericThenOtherAndUsesUnadornedLabels() {
        assertEquals(
            listOf(BoseSetupModel.SOUND_TOUCH_10, BoseSetupModel.SOUND_TOUCH_20_III,
                BoseSetupModel.OTHER_SOUND_TOUCH),
            BoseSetupModel.displayOrder
        )
        assertEquals(
            listOf(R.string.bose_model_10_option, R.string.bose_model_20_option,
                R.string.bose_model_other_option),
            BoseSetupModel.displayOrder.map { it.selectionLabelRes }
        )
        assertEquals(BoseSetupModel.SOUND_TOUCH_20_III,
            BoseSetupModel.fromId(BoseSetupModel.SOUND_TOUCH_20_III.name))
    }

    @Test fun freshWizardRequiresConsciousModelSelection() {
        assertNull(BoseSetupModel.fromId(null))
        assertNull(BoseSetupModel.fromId(""))
        assertNull(BoseSetupModel.fromId("not-supported"))
        assertEquals(0, BoseSetupModel.pickerIndex(null))
        assertNull(BoseSetupModel.pickerModel(0))
        assertNull(BoseSetupModel.pickerModel(-1))
        assertNull(BoseSetupModel.pickerModel(4))
        assertTrue(BoseSetupModel.SOUND_TOUCH_20_III.protocolVerifiedOnDevice)
    }

    @Test fun chosenModelSurvivesExplicitIdRoundTripAndSpinnerOrder() {
        BoseSetupModel.displayOrder.forEachIndexed { index, model ->
            assertEquals(index + 1, BoseSetupModel.pickerIndex(model))
            assertEquals(model, BoseSetupModel.pickerModel(index + 1))
            assertEquals(model, BoseSetupModel.fromId(model.name))
        }
    }

    @Test fun soundTouch10HasVerifiedTestWifiProfile() {
        assertEquals(BoseSetupModel.SOUND_TOUCH_10,
            BoseSetupModel.fromId(BoseSetupModel.SOUND_TOUCH_10.name))
        assertTrue(BoseSetupModel.SOUND_TOUCH_10.protocolVerifiedOnDevice)
        assertEquals(R.string.bose_model_10_hint, BoseSetupModel.SOUND_TOUCH_10.setupHintRes)
        assertEquals(R.string.bose_model_10_option, BoseSetupModel.SOUND_TOUCH_10.selectionLabelRes)
    }

    @Test fun onlyExplicitlyImplementedModelsAreSelectable() {
        assertEquals(3, BoseSetupModel.entries.size)
        assertEquals(BoseSetupModel.OTHER_SOUND_TOUCH,
            BoseSetupModel.fromId("OTHER_SOUND_TOUCH"))
        assertEquals(R.string.bose_model_other_hint, BoseSetupModel.OTHER_SOUND_TOUCH.setupHintRes)
        assertEquals(false, BoseSetupModel.OTHER_SOUND_TOUCH.protocolVerifiedOnDevice)
    }
}
