# Ragutis 0.3.6 — owner-local signed GitHub APK build and certificate check (2026-11-23)

## Reported candidate identity
- **Source commit:** `2bcdf023e67eb6f06a7d37f9add3a233711938c7` (exact merged `main` source, post-merge Android CI PASS).
- **Build configuration (repo):** production `applicationId=app.strremote.android`, `versionName=0.3.6`, `versionCode=17`; configured `minSdk=26`, `targetSdk=37`. These configuration values are **not proof of inspecting the actual signed APK manifest**.
- **Build system reported:** Gradle 9.7.1, Kotlin 2.4.0, Android Studio JetBrains JBR Java 25.0.4, Windows 11; GitHub `githubRelease` flavor.
- **Builder:** repository `tools/build-signed-release.ps1` on a separate **detached Git worktree** fixed to the exact source commit. The owner's first local attempt failed because the detached worktree lacked a Gradle SDK location; the owner set `ANDROID_HOME` / `ANDROID_SDK_ROOT` locally and reran successfully.
- **Keystore:** original **GitHub app-signing key alias `key0`**, NOT the separate Play upload key. Private keystore path/password not recorded.
- **Signed release artifact:** `Ragutis-Remote-for-SoundTouch-Reborn-0.3.6.apk` (private owner-local `dist`); the raw APK is **not** uploaded or independently checked.
- **Reported SHA-256:** `cf1d33fcd2f29210fc9250619b53f169a64add60334fe49e96e2d5c4dd0527b3`. This hash identifies the *owner-reported* artifact but does not independently prove its bytes, installed package contents, or future published release identity.

## Log-based verification results
| Check | Evidence | Result |
|---|---|---|
| Exact source and version | Script reports source `2bcdf023e67eb6f06a7d37f9add3a233711938c7`, version 0.3.6/code17 | **PASS (owner log)** |
| Gradle compilation, tests and lint | `BUILD SUCCESSFUL`, 99 actionable tasks: 88 executed, 10 from cache, 1 up-to-date; both Github debug/release lint reports written | **PASS (owner log)** |
| Pre-signing zipalign | `Verification successful` | **PASS (owner log)** |
| APK sign/verify | `apksigner verify --verbose --print-certs`: one signer, v2 **true**, v3 **true** | **PASS (owner log)** |
| Signing certificate continuity | v3 signer certificate SHA-256 `3cc2e7272d8c2d1433a57c4462df39adfe5e8c1931a4a924710c0e4087aa1e32`, matches original GitHub and the Play in-use app-signing certificate | **PASS (owner log)** |
| Signed APK zipalign check | Second post-sign check `Verification successful` | **PASS (owner log)** |
| SHA-256 | Final script output `cf1d33fcd2f29210fc9250619b53f169a64add60334fe49e96e2d5c4dd0527b3` | **CAPTURED (owner log)** |

## Nonblocking diagnostic notes
- Android Build Tools 37 `apksigner` printed Java restricted native-access warnings from Conscrypt; the sign, certificate and alignment checks **completed successfully**.
- Kotlin reported **`MainActivity.kt:2287:28 Condition is always 'true'`**. This is a nonfatal compiler warning, **not** acceptance of the underlying expression as correct; review as a separately scoped quality follow-up rather than silently mutate already-signed release source.
- Initial Gradle SDK detection failure in the detached worktree was resolved **only in the local build environment** by setting SDK environment variables. No production source, keystore, app package or signing configuration changed.

## Open gates and protection of the Play baseline
- Inspect *actual* APK package/versionCode/SDK via owner-local Android tools and verify the file checksum, preferably before device install.
- **Real signed in-place GitHub v0.3.4/code15 → v0.3.6/code17 update with persisted speaker and room data: NOT TESTED.**
- Owner's existing Play **closed-test 0.3.5/code16** installation is the reference for the genuine future Play 0.3.5→0.3.6 update. Do **not** overwrite it with the GitHub v0.3.6 local candidate while that Play upgrade gate is pending.
- Separate Play AAB signed with registered upload key, actual Play delivery, manifest/Play data safety and explicit GitHub/Play publication approvals remain open.
- T12 (original firmware Bose), T13 (manual IP), and F-034 same-IP/different-key physical integration edge remain untested/partial.

**Provenance qualification:** this record is reconstructed from the owner's submitted `0.3.6-build-ok-1.txt` local build log; the assistant did not independently inspect APK bytes, Windows keystore or physical installation. No raw log containing personal account directory paths or hardware identifiers is committed to the repository.

See [Release gates](../../RELEASE_GATES.md), [Status](../../STATUS.md) and [0.3.6 debug QA](2026-11-22-ragutis-036-owner-debug-device-acceptance.md).
