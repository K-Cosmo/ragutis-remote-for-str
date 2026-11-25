# Ragutis 0.3.6 — signed GitHub emulator release smoke (2026-11-25)

## Scope and provenance

Owner-reported execution on the existing Pixel 9a AVD: Android 17 / API 37.1, Google APIs x86_64, 16 KB page-size system image (owner screenshot). No physical device identifiers or local paths recorded.

- Baseline: published GitHub 0.3.4/code15; expected public APK SHA-256 `7563764c09fe15f5bfe122a05d37964929cb905f7c1a92bf05e896775a5f17f7`. Owner reported installed code15/0.3.4, minSdk26, targetSdk37.
- Candidate: original-app-key signed GitHub 0.3.6/code17 from `2bcdf023e67eb6f06a7d37f9add3a233711938c7`, owner-reported local APK SHA-256 `cf1d33fcd2f29210fc9250619b53f169a64add60334fe49e96e2d5c4dd0527b3`. [Local signing and manifest evidence](2026-11-23-ragutis-036-local-github-signed-apk.md).
- Owner reports successful execution of all seven requested release-smoke cases, with installed package output `versionCode=17 minSdk=26 targetSdk=37` and `versionName=0.3.6`. The assistant did not independently inspect the emulator, APK bytes, installed certificate, or raw adb install transcript. Snapshot creation was suggested but is not confirmed.

## Results

| ID | Test | Result |
|---|---|---|
| R01 | Signed in-place 0.3.4 → 0.3.6 update | PASS |
| R02 | Saved speakers and room assignments retained | PASS |
| R03 | STR control works | PASS |
| R04 | App restart without errors/data loss | PASS |
| R05 | GitHub update check: no newer public release | PASS |
| R06 | Privacy link opens | PASS |
| R07 | WLAN wizard opens and can be cancelled | PASS |

**7 PASS / 0 FAIL** (owner-reported). The physical Play 0.3.5/code16 baseline was not used for this test. No public release or upload is authorized by this report.

## Coverage boundaries / change impact

Prior repository Git tree comparison: all versioned files under app/src/ are blob-identical between tested debug source f8f95d1e1929cc882daad77cdc7c209455a7c197 and candidate source 2bcdf023e67eb6f06a7d37f9add3a233711938c7. Dedicated Bose setup implementation and resources unchanged since 0.3.4; shared MainActivity, speaker identity and discovery logic did change. Pending Bose list removal and the full original-firmware setup handoff deserve separate integration consideration. This is evidence for scoped regression selection, not proof of binary identity or complete hardware coverage.

Physical debug QA remains 12 PASS / 0 FAIL / 2 N/A. T12 full Bose original-firmware setup, T13 manual IP physical test and F-034 exceptional identity integration are not converted into PASS by emulator smoke. Full wizard setup, physical production-signed upgrade, API26 runtime and positive online newer-release detection/download are not established here. Exact manual-IP vs discovery route was not reported. No blanket physical-test waiver is granted; scope/deferral decision remains subject to owner approval. Once a real newer release is published, verify positive online update flow using the previous-version AVD baseline.

See [Release gates](../../RELEASE_GATES.md).
