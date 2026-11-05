# Play 0.3.5/code16 — owner-reported local signed AAB QA (2026-11-03)

**PLAY-003: PASS for local signing and artifact verification only.** This does not authorize or imply Play upload, application install, PR merge or public release.

| Verification | Owner-reported result |
|---|---|
| Input unsigned AAB | `app-play-release.aab`, prechecked in signing script against SHA-256 `15BA556123614F926EF78AE163F43F89843157E18661187083FE57D1F997DA5E` |
| Local Play upload keystore | `D:\Android\STR\keys\remote-str-upload.jks`, alias `remote-str-upload` |
| Signed output | `D:\Android\STR\dist\Ragutis-0.3.5-play-signed.aab` |
| Signed output size | 1,165,897 bytes |
| Signed output SHA-256 | `7B94567B796D13A8747A2184DEE073C4FC7EFD0F89838414306D9DFC03EB33B8` |
| `jarsigner` | `JAR signiert.` followed by `JAR verifiziert.` |
| `keytool -printcert -jarfile` | SHA-256 `41BFFC7105A5345DF3F2C1C65CBB52595DB5048C4C80A24564C2863331FA7791` — **matches the separately enrolled Play upload certificate exactly** |

`jarsigner` warned that the signer is self-signed, lacks a publicly trusted PKIX chain and has no timestamp (warning cites certificate expiry no earlier than 2054-03-18). These are nonfatal warnings for this local validation against the **specific Play-registered upload certificate**. Do not claim the certificate chains to a public CA or that Google has already accepted the file. No passwords or key material were provided or committed.

**Still OPEN:** signed production device smoke on a separate device, privacy/backup/STR WebView and Play policy/Data safety review, distinct owner permission for PR merge and Play upload, and real GitHub 0.3.4/code15 → Play 0.3.5/code16 in-place update keeping speaker/room data. Preserve a device with existing GitHub v0.3.4 for that test. [Release gates](../../RELEASE_GATES.md).
