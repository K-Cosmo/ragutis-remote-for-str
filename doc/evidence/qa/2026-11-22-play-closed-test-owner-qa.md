# Play closed-test delivery and in-place update — 2026-11-22

**Evidence type:** Owner-reported real-device acceptance in the project conversation. No direct access to Play Console, APK bytes, Android package logs or signing certificates in this session.

## Accepted for the tested device
- The Google Play version is available to the owner's Google account in the closed-test process; this does **not** mean public Play production.
- Google Play automatically updated an existing GitHub installation in place without uninstalling.
- **All saved speakers and room assignments survived** and STR playback control still works.
- The installed Play app's **Google Play update link and privacy-policy link both work**.

## Confirmed owner decisions
- Keep Android system backup enabled (`android:allowBackup="true"`) so speaker/room settings may be recoverable. No new permission or storage feature is required. Actual Auto Backup/restore behavior and Play Data safety disclosures remain separate audit subjects.
- **Fewer than 12 testers** are enrolled in the closed Play test; exact count and opt-in dates were not supplied. For relevant newly created personal developer accounts, at least 12 continuously opted-in testers for 14 days are required for production access.

## Evidence limits and remaining gates
- The owner ran `adb shell dumpsys package app.strremote.android` and reported `versionCode=16 minSdk=32 targetSdk=37` and `versionName=0.3.5`. The versionCode, versionName and targetSdk match the previously inspected 0.3.5/code16 AAB metadata. The installed APK reports minSdk 32 whereas Gradle and the compiled AAB manifest specify minSdk 26; Google's bundletool supports per-SDK generated APK variants and injection of variant-level minSdk into split APK manifests. This is a plausible packaging explanation, **not yet independently verified for this installed APK**. Validate Play compatibility for API 26 / Android 8 before declaring the supported device range verified. Official reference: https://developer.android.com/tools/bundletool .
- The installed signing certificate SHA-256 and byte-for-byte link to the separately documented 0.3.5/code16 signed AAB remain unverified.
- No successful **Android cloud-backup restoration** has been tested; an in-place store update is a different operation.
- No v0.3.6/code17 signed build, real-device regression pass, Play update or publication is implied.
- Existing published GitHub latest remains v0.3.4/code15. No PR merge or new Play/GitHub release is authorized.

See [Status](../../STATUS.md), [Release gates](../../RELEASE_GATES.md) and [Play preparation](../../PLAY_STORE_PREPARATION.md).
