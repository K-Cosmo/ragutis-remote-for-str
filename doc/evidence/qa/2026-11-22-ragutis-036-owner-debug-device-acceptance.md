# Ragutis 0.3.6 — owner-reported debug-device functional acceptance (2026-11-22)

## Provenance and limits
- **Exact tested PR/source HEAD:** [PR #25](https://github.com/K-Cosmo/ragutis-remote-for-str/pull/25), `f8f95d1e1929cc882daad77cdc7c209455a7c197`; branch `fix/speaker-list-bugs-036`, Android app candidate `versionName=0.3.6` / `versionCode=17`.
- **Evidence source:** owner's submitted checklist after testing the GitHub/Play debug variants on an actual device. This is **owner-reported on-device QA**, not independent access to device logs, screenshots or APKs. The owner supplied no failure remarks.
- **Automated gates:** both push and pull-request GitHub Actions runs for that exact commit returned `success` ([push CI](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/45053384306), [PR CI](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/45053391742)); unit/build/lint/doc gates passed for that checked source.
- **Scope:** debug-installed application `.poc` and both distribution flavor behaviors; **not** signed `app.strremote.android` release, actual Play-delivered 0.3.6, independent original-signer update, all Android versions, or all combinations of endpoints.

## Test matrix — 12 PASS / 0 FAIL / 2 NOT APPLICABLE (NOT TESTED)

| ID | Owner's test | Result | Scope accepted |
|---|---|---|---|
| T01 | Newly discovered speaker without room | **PASS** | Delete action visible immediately |
| T02 | Delete / cancel | **PASS** | Cancel preserves row, confirm removes |
| T03 | Rediscovery | **PASS** | Hidden during current scan, visible on new scan |
| T04 | New reachable STR speaker | **PASS** | Automatically becomes online/green |
| T05 | New unreachable STR speaker | **PASS** | Automatically becomes offline/red after probe |
| T06 | Saved offline speaker | **PASS** | Correct offline/red state |
| T07 | Clear room from offline speaker | **PASS** | Remains offline/red — original critical regression |
| T08 | Assign other room while offline | **PASS** | Offline/red state persists across room changes |
| T09 | Return online | **PASS** | Correct recovery after successful scan/connection |
| T10 | Avoid duplicate rows | **PASS** | Repeated discovery and operations show no duplicates |
| T11 | More than one speaker | **PASS** | Operations remain isolated for normal multi-device use |
| T12 | Original-firmware Bose setup entry | **N/A — NOT TESTED** | No real-device verification of pending-Bose behavior |
| T13 | Manual IP connection | **N/A — NOT TESTED** | Manual-address path unverified in this round |
| T14 | DE/EN and Play distribution variant | **PASS** | Bilingual UI and debug Play update routing verified |

**Owner's remarks:** None.

## Identity finding F-034 — bounded acceptance
The normal multiple-speaker case T11 passed, along with delete/rediscovery T01–T03 and automatic reachability T04–T09. **This does not reproduce the same-IP/different-key edge case**, IP reuse with delayed callbacks or two keyed speakers competing for one initially unkeyed saved host. Focused JVM regression tests cover those policies, but keep the exceptional hardware/integration scenario open or separately risk-accepted.

## Release and rollout boundary
- **Functional 0.3.6 debug-device smoke: PASS for 12 tested cases.** T12/T13 remain explicitly untested, not silently accepted.
- Both CI runs for the tested exact source are green. A subsequent documentation-only QA commit changes the branch HEAD; require exact-head documentation/CI checks again without re-attributing new source behavior to a new device binary.
- No original-signer signed GitHub 0.3.6 APK, no Play-upload-key-signed 0.3.6 AAB, no in-place signed `0.3.4→0.3.6` or Play `0.3.5→0.3.6` upgrade tests are established.
- GitHub v0.3.4/code15 and Google Play closed-test v0.3.5/code16 remain unchanged.
- Neither a PR merge nor publication was approved by the owner through this test report.

See [Release gates](../../RELEASE_GATES.md), [Tests](../../TESTS.md), [Status](../../STATUS.md), and [F-034](../../FINDINGS.md).
