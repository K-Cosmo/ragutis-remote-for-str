# Google Play signing certificates — owner-reported PASS (2026-11-03)

**App:** Ragutis Remote for STR; `app.strremote.android`.

| Certificate | SHA-256 | Owner-reported result |
|---|---|---|
| Published GitHub APK v0.3.4; original local PKCS12 alias `key0` | `3CC2E7272D8C2D1433A57C4462DF39ADFE5E8C1931A4A924710C0E4087AA1E32` | Original certificate confirmed |
| Google Play current **in-use app-signing** certificate | `3CC2E7272D8C2D1433A57C4462DF39ADFE5E8C1931A4A924710C0E4087AA1E32` | **PASS — matches original APK** |
| Local separate `remote-str-upload` keystore | `41BFFC7105A5345DF3F2C1C65CBB52595DB5048C4C80A24564C2863331FA7791` | Local keytool PASS |
| Google Play current **registered upload** certificate | `41BFFC7105A5345DF3F2C1C65CBB52595DB5048C4C80A24564C2863331FA7791` | **PASS — matches upload key** |

**PLAY-001 CLOSED.** The owner provided both current Play Console SHA-256 values after correcting/diagnosing previous signer import attempts. The earlier Console screenshot showing `F2DCDD5F…`, quantum-ready classical `D0FDE60F…` and PQC `803406BF…`, and the rejected PEPK ZIP are historical troubleshooting; the precise import sequence is not independently verified. The new matching values supersede those earlier readings. Do not infer Android APK delivery or actual installed upgrade was tested.

**Still OPEN:** review actual `playRelease` merged manifest, backups, WebView and Data safety; original-signer production 0.3.5 device smoke on separate test device; signed local AAB creation and independent upload-certificate verification; owner approval for draft PR merge and **separately** for Play test-track upload; Play-delivered GitHub 0.3.4/code15 → Play 0.3.5/code16 in-place update preserving stored speakers/rooms. Preserve baseline v0.3.4 device. No Play AAB upload or release has been authorized by this report.

[Release gates](../../RELEASE_GATES.md) · [Play runbook](../../PLAY_STORE_PREPARATION.md).
