# Play AAB compiled-manifest inspection — owner-reported PASS (2026-11-03)

**App and source:** `Ragutis Remote for STR`, current draft PR #21, `playRelease` flavor, Gradle `:app:bundlePlayRelease` already returned BUILD SUCCESSFUL. Exact local resulting output and version were reported by the owner.

## File metadata and tool

- File: `app-play-release.aab` (locally under `app/build/outputs/bundle/playRelease/`).
- Reported size: **1,160,743 bytes**, LastWriteTime **2026-11-03 20:07:05** (owner's local environment).
- Read with **`bundletool-all-1.18.3.jar`**, `dump manifest --module=base`, parsing the protobuf-backed manifest as XML.
- The Java runtime printed a `sun.misc.Unsafe::arrayBaseOffset` terminal deprecation warning originating from `com.google.protobuf.UnsafeUtil$MemoryAccessor`; bundletool **completed successfully and returned manifest fields**. No build failure evidenced by this warning.
- Owner subsequently reported AAB **SHA-256 file digest `15BA556123614F926EF78AE163F43F89843157E18661187083FE57D1F997DA5E`**. The earlier ZIP structure check 4/4 PASS is separately recorded [here](2026-11-03-play-aab-zip-structure-qa.md).

## Owner-reported packaged values

| Check | Reported value | Result |
|---|---|---|
| `package` | `app.strremote.android` | PASS |
| `versionCode` | `16` | PASS |
| `versionName` | `0.3.5` | PASS |
| `minSdkVersion` | `26` | PASS |
| `targetSdkVersion` | `37` | PASS |
| `android:allowBackup` | `true` | Expected, backup/data review OPEN |
| `android:usesCleartextTraffic` | `true` | Expected for STR/Bose LAN HTTP |
| `android.permission.INTERNET` | Present | PASS |
| `android.permission.ACCESS_NETWORK_STATE` | Present | PASS |
| `android.permission.CHANGE_NETWORK_STATE` | Present | PASS |
| `android.permission.ACCESS_COARSE_LOCATION` | Present, `maxSdkVersion=32` | PASS |
| `android.permission.ACCESS_FINE_LOCATION` | Present, `maxSdkVersion=32` | PASS |
| `android.permission.ACCESS_WIFI_STATE` | Present | PASS |
| `android.permission.CHANGE_WIFI_MULTICAST_STATE` | Present | PASS |
| `android.permission.NEARBY_WIFI_DEVICES` | Present | PASS |
| `android.permission.ACCESS_LOCAL_NETWORK` | Present | PASS |
| `app.strremote.android.BoseSetupProbeActivity` | `exported=false` | PASS |
| `app.strremote.android.MainActivity` | `exported=true` | PASS |

**Scoped gate:** **PASS** for the actual unsigned AAB's compiled **identity, version, SDK levels, nine permissions (presence), backup/cleartext flags and exported activity states**, based on owner-reported local bundletool output. Source/Gradle and earlier generated merged manifests are consistent. This is not the same as verification of every manifest attribute.

**Final exact checks passed (owner report, 2026-11-03):** AAB SHA-256 `15BA556123614F926EF78AE163F43F89843157E18661187083FE57D1F997DA5E`; compiled `android:networkSecurityConfig=@xml/network_security_config` and both coarse/fine location permissions `android:maxSdkVersion=32`. All scoped **unsigned AAB gates PASS**. Preserve examined bundle for local signing, rather than rebuilding without traceability. Nonfatal Java/Protobuf `sun.misc.Unsafe` deprecation warning persists.

**Still open:** actual Android backup contents and Play Data safety/STR WebView policy, original-signer production smoke test on another device, local sign and verification with **separate Play upload key**, and *separately approved* Play test-track upload and real GitHub 0.3.4/code15 → Play 0.3.5/code16 update. No merge, signing, Play upload or rollout occurred in this review.

[Release gates](../../RELEASE_GATES.md).
