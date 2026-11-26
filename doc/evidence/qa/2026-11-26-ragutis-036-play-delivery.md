# 0.3.6 Play AAB and closed-test delivery — 2026-11-26

Owner-reported evidence from local PowerShell and Play Console; raw AAB and Console not independently inspected.

- Exact source: `2bcdf023e67eb6f06a7d37f9add3a233711938c7`; playRelease build and lint completed.
- Unsigned AAB: 1,163,012 bytes; SHA-256 `1959AADD4E598B632572A55BC97A4C9092E956DF32CDA7CD0C67D407E6DAB1C0`; required ZIP entries 4/4 PASS.
- bundletool 1.18.3 packaged manifest: app.strremote.android, version 0.3.6/code17, min26/target37; allowBackup=true, usesCleartextTraffic=true, networkSecurityConfig unchanged; nine permissions unchanged, both location maxSdk32, NEARBY_WIFI_DEVICES flag 0x00010000 (neverForLocation), only MainActivity exported among activities. PASS. Numeric flag parser corrected; Protobuf Unsafe warning nonfatal.
- Signed AAB: 1,168,165 bytes; SHA-256 `7101791CE83231F419BE48D6A788FF4D50B2C0F09C69CA55ED002147DBFD601A`. jarsigner strict verification and bundletool validate PASS; keytool -printcert -jarfile ExitCode 0, registered upload certificate SHA-256 `41BFFC7105A5345DF3F2C1C65CBB52595DB5048C4C80A24564C2863331FA7791`. Earlier keytool invocation failed; simplified invocation succeeded. No rebuild or re-sign needed.
- Owner confirms Play Console completed and app automatically updated via store. Scope: existing closed test, not public production. Installed code/name, APK signer and post-update retention/control/link checks not yet separately reported. Exact Console asset hash not independently inspected.
- Owner confirmed GitHub 0.3.6 AVD update baseline snapshot saved. No full Bose/negative-path retest claimed.

Documentation review follows GOVERNANCE.md semantic ownership; no app/build changes. Future publication blocks should be noninteractive after explicit owner authorization, retaining automatic integrity gates and necessary secure password entry.
