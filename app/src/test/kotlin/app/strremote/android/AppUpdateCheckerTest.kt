package app.strremote.android

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class AppUpdateCheckerTest {
    @Test fun manualUpdateCheckDistinguishesFailureCurrentAndUpdateAvailable() {
        assertEquals(AppUpdateStatus.FAILED, updateStatusFor(null))
        assertEquals(AppUpdateStatus.CURRENT, updateStatusFor(AppUpdateCheckResult(null)))
        val available = AppUpdatePolicy.newerThanInstalled("v0.3.5", "0.3.4")
        assertEquals(
            AppUpdateStatus.UPDATE_AVAILABLE,
            updateStatusFor(AppUpdateCheckResult(available))
        )
    }

    @Test fun latestPublishedRagutisReleaseDoesNotPromptInstalled033() {
        val json = """{"tag_name":"v0.3.3","draft":false,"prerelease":false,
            "assets":[{"name":"Ragutis-Remote-for-SoundTouch-Reborn-0.3.3.apk"}]}"""
        assertNull(AppUpdateChecker.parsePublicRelease(json, "0.3.3"))
        assertEquals(
            "0.3.3", AppUpdateChecker.parsePublicRelease(json, "0.3.2")?.version
        )
    }
    @Test fun githubApiRedirectsRemainEnabledForRepositoryRename() {
        // GitHub serves 301/307 after repository renames. Older 0.3.2 APKs
        // disabled redirects and cannot be changed retrospectively.
        assertEquals(true, AppUpdateChecker.FOLLOW_REPOSITORY_REDIRECTS)
    }

    @Test fun checksCurrentCanonicalRagutisRepository() {
        assertEquals(
            "https://api.github.com/repos/K-Cosmo/ragutis-remote-for-str/releases/latest",
            AppUpdateChecker.API_URL
        )
    }

    @Test fun acceptsOfficialReleaseWithMatchingApk() {
        val json = """{"tag_name":"v0.4.0","draft":false,"prerelease":false,
            "assets":[{"name":"Remote-for-STR-0.4.0.apk"}]}"""
        assertEquals("0.4.0", AppUpdateChecker.parsePublicRelease(json, "0.3.0")?.version)
    }

    @Test fun acceptsRagutisApkNameAndRejectsMismatchedVersion() {
        val json = """{"tag_name":"v0.3.3","draft":false,"prerelease":false,
            "assets":[{"name":"Ragutis-Remote-for-SoundTouch-Reborn-0.3.3.apk"}]}"""
        assertEquals("0.3.3", AppUpdateChecker.parsePublicRelease(json, "0.3.2")?.version)
        assertNull(AppUpdateChecker.parsePublicRelease(
            """{"tag_name":"v0.3.3","draft":false,"prerelease":false,
            "assets":[{"name":"Ragutis-Remote-for-SoundTouch-Reborn-0.3.4.apk"}]}""", "0.3.2"))
    }

    @Test fun acceptsLegacyApkNameDuringBrandMigration() {
        val json = """{"tag_name":"v0.4.0","draft":false,"prerelease":false,
            "assets":[{"name":"STR-Remote-0.4.0.apk"}]}"""
        assertEquals("0.4.0", AppUpdateChecker.parsePublicRelease(json, "0.3.0")?.version)
    }

    @Test fun ignoresMissingApkOrPreRelease() {
        val noApk = """{"tag_name":"v0.4.0","draft":false,"prerelease":false,
            "assets":[{"name":"other.apk"}]}"""
        assertNull(AppUpdateChecker.parsePublicRelease(noApk, "0.3.0"))
        assertNull(AppUpdateChecker.parsePublicRelease(
            """{"tag_name":"v0.4.0","draft":false,"prerelease":true,
            "assets":[{"name":"STR-Remote-0.4.0.apk"}]}""", "0.3.0"))
        assertNull(AppUpdateChecker.parsePublicRelease(
            """{"tag_name":"v0.4.0","draft":true,"prerelease":false,
            "assets":[{"name":"STR-Remote-0.4.0.apk"}]}""", "0.3.0"))
    }

    @Test fun doesNotShowAlreadyInstalledRelease() {
        assertNull(AppUpdateChecker.parsePublicRelease(
            """{"tag_name":"v0.3.0","draft":false,"prerelease":false,
            "assets":[{"name":"STR-Remote-0.3.0.apk"}]}""", "0.3.0"))
    }
}
