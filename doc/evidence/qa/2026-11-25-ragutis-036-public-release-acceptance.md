# Ragutis 0.3.6 — public GitHub release and online update QA (2026-11-25)

## Publication evidence

- Owner executed publication and reported SUCCESS. GitHub API independently confirmed latest stable release [v0.3.6](https://github.com/K-Cosmo/ragutis-remote-for-str/releases/tag/v0.3.6), ID 458483271, published 2026-11-25T18:24:42Z, draft=false, prerelease=false.
- Lightweight tag resolves directly to exact signed source `2bcdf023e67eb6f06a7d37f9add3a233711938c7`.
- APK asset ID 792639039, 1,209,989 bytes, GitHub SHA-256 `cf1d33fcd2f29210fc9250619b53f169a64add60334fe49e96e2d5c4dd0527b3`, matches owner-verified signed candidate.
- Checksum companion asset ID 792639037, 114 bytes, GitHub SHA-256 `1d73fa581fea1aac182715d75ca4feedd7021da08a04ced52fbd751c098a0f05`.
- Docs-only PR #27 merged at `f3ae7cbc7b8885a2fc1efc85e2f429446bfd50c0`; [post-merge CI PASS](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/45673618215). Tag not moved to documentation merge.

## Packaged APK permission comparison

Owner ran hash-checked `aapt2 dump permissions` on published 0.3.4 and local signed 0.3.6 and reported PASS: all nine permissions unchanged: INTERNET, ACCESS_NETWORK_STATE, CHANGE_NETWORK_STATE, ACCESS_COARSE_LOCATION (maxSdk32), ACCESS_FINE_LOCATION (maxSdk32), ACCESS_WIFI_STATE, CHANGE_WIFI_MULTICAST_STATE, NEARBY_WIFI_DEVICES (neverForLocation), ACCESS_LOCAL_NETWORK. Source manifest, security configuration and storage files were also blob-identical. This does not certify actual Play AAB, all manifest attributes or Play Data safety.

## Post-publication positive online update

| Step | Result and evidence |
|---|---|
| 0.3.6 offered to previous GitHub version | PASS, owner screenshot of update dialog |
| Correct public release page | PASS, screenshot shows tag v0.3.6 and source short SHA 2bcdf02 |
| APK download and native update prompt | PASS, screenshot of Android update dialog via Chrome |
| Android in-place update completion | PASS, screenshot of App updated |
| Retained speaker/room data and STR control | PASS, owner confirmation of final functional checks |

Owner explicitly requested closure of scoped GitHub acceptance after completing the checks. Earlier [signed AVD smoke](2026-11-25-ragutis-036-signed-emulator-qa.md) is 7/7 PASS; physical debug scope remains 12 PASS / 2 N/A. Screenshots are not committed because they show private LAN metadata. No independent hash of the browser-downloaded or installed APK, no raw post-update ADB transcript, and no proof of whether detection was automatic versus manually invoked. GitHub asset metadata digest is independently verified, not a second download hash. Snapshot creation is not confirmed.

Residual physical T12/T13/F-034 and pending-Bose integration coverage remains untested and scoped deferral remains valid. No Google Play 0.3.6 rollout or approval implied.
