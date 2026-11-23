# Ragutis v0.3.5/code16 — merged GitHub/Play release manifest acceptance

**Date:** 2026-11-03
**Source:** Owner-provided PowerShell inspection of locally built Gradle manifests after `:app:processGithubReleaseMainManifest` and `:app:processPlayReleaseMainManifest` returned **BUILD SUCCESSFUL** (4 actionable tasks: 1 executed / 3 cached). Source is the feature branch of [draft PR #21](https://github.com/K-Cosmo/ragutis-remote-for-str/pull/21). This is *owner-reported local generated-file evidence*; no generated manifest was attached or independently opened.

- `app/build/intermediates/merged_manifest/githubRelease/processGithubReleaseMainManifest/AndroidManifest.xml`
- `app/build/intermediates/merged_manifest/playRelease/processPlayReleaseMainManifest/AndroidManifest.xml`

| Property | githubRelease | playRelease | Result |
|---|---|---|---|
| `package` | `app.strremote.android` | `app.strremote.android` | MATCH / PASS |
| `android:allowBackup` | `true` | `true` | MATCH / review backup exposure separately |
| `android:usesCleartextTraffic` | `true` | `true` | MATCH / needed for local HTTP STR/Bose |
| `android:networkSecurityConfig` | `@xml/network_security_config` | same | MATCH |
| Permissions | same set of 9 | same | MATCH |
| `BoseSetupProbeActivity` | `exported=false` | same | MATCH |
| `MainActivity` | `exported=true` | same | MATCH (launcher activity) |

**Owner-reported permissions identical in both variants:** `INTERNET`, `ACCESS_NETWORK_STATE`, `CHANGE_NETWORK_STATE`, `ACCESS_COARSE_LOCATION` (maxSdk 32), `ACCESS_FINE_LOCATION` (maxSdk 32), `ACCESS_WIFI_STATE`, `CHANGE_WIFI_MULTICAST_STATE`, `NEARBY_WIFI_DEVICES`, `ACCESS_LOCAL_NETWORK`. No additional permissions/activities appeared in the owner's output.

**Gate decision:** PASS for **merged release manifest consistency, package name, permissions, backup/cleartext attributes, and activity export states**. This is a narrow gate: does **not** establish packaged signed-AAB manifest, Google Play privacy/Data safety completion or Android 17 hardware acceptance.

## Source-followup: backup and networking

- `PreferencesStore.kt` saves active and known STR endpoints into `SharedPreferences("str_remote")`: LAN host/port, name, model, endpoint `key`, saved speaker entries and custom room labels, last reachability. `MainActivity.kt` also stores an update-check timestamp in `SharedPreferences("str_remote_update_check")`. With `allowBackup=true` and no explicit extraction XML at the conventional resource paths, these may be eligible for OS-managed cloud backup/device transfer; **do not describe local metadata as guaranteed never to leave the device**.
- `BoseSetupProbeActivity.kt` processes Wi-Fi SSID/password temporarily for direct LAN HTTP provisioning; code clears the input and transport payload; no Wi-Fi password persistence is identified in the inspected storage classes. `PendingBoseStore.kt` explicitly describes a debug-PoC-only persistence path for IDs/host/room (must not infer this is enabled in production without confirming call paths).
- The `network_security_config.xml` has `base-config cleartextTrafficPermitted=true`; unencrypted HTTP is deliberately needed for STR and original Bose devices, and it is wider than a static host-specific rule because LAN addresses vary.
- Android 17 / API 37 runtime permission: `MainActivity.requiredLocalNetworkPermission()` selects `ACCESS_LOCAL_NETWORK` at API >=37; `BoseSetupProbeActivity.requiredPermissions()` also includes it >=37. Presence and *code path* reviewed; **Android 17 real-device granted/denied behavior is not verified**. Reference [Android Local Network Permission](https://developer.android.com/privacy-and-security/local-network-permission).
- [Android Auto Backup](https://developer.android.com/identity/data/autobackup) documents that without rules the OS may back up most eligible app data, with rules/transfer behavior varying by Android version. Decide whether to preserve normal speaker/room migration via backups or introduce selective `data-extraction-rules` / legacy `fullBackupContent` rules; do not silently disable backup and risk user state loss.
- [Play Data safety](https://support.google.com/googleplay/android-developer/answer/10787469?hl=en) requires reviewing what data is transmitted, including controlled WebView activity and ephemeral flows, before claiming "no collection", "no sharing" or "encrypted in transit." The app's local HTTP and embedded STR WebView are separate from HTTPS public links. If only internal testing is used, Google notes a temporary Data safety form exemption for apps **exclusively** in internal testing; later tracks require the proper form.

**Remain OPEN:** Android system-backup transfer scope decision, actual signed AAB contents, WebView network assessment, Play data safety declarations, original-signer production-device QA and Play-delivered 0.3.4→0.3.5 speaker/room preservation. No application code changed, no AAB signed/uploaded, no PR merge or rollout.

[Release gates](../../RELEASE_GATES.md) · [Play runbook](../../PLAY_STORE_PREPARATION.md).
