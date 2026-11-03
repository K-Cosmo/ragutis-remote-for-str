package app.strremote.android

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/** Run on BOTH githubDebug and playDebug variants. */
class DistributionChannelTest {
    @Test fun githubUpdaterIsEnabledOnlyForGithubFlavor() {
        val flavor = BuildConfig.FLAVOR
        assertTrue("Unexpected distribution flavor: $flavor", flavor == "github" || flavor == "play")
        assertEquals(flavor == "github", BuildConfig.GITHUB_UPDATES)
    }

    @Test fun debugAppIdIsIsolatedFromProduction() {
        assertEquals("app.strremote.android.poc", BuildConfig.APPLICATION_ID)
    }
}
