# QA evidence

Store build logs, lint reports/summaries, relevant Logcat excerpts and concise real-device test records here. Evidence files must identify the source version/build and test date. Do not fabricate or backfill missing evidence.

## Current unreleased Play candidate — certificate, merged manifest and debug evidence

- [2026-11-03 actual unsigned Play AAB bundletool manifest inspection — owner-reported PASS](2026-11-03-play-aab-bundletool-manifest-qa.md) — identity, version, SDK, 9 permissions, activities; AAB SHA-256 and packed maxSdk/networkConfig still OPEN.

- [2026-11-03 unsigned Play AAB basic ZIP structure — owner-reported 4/4 PASS](2026-11-03-play-aab-zip-structure-qa.md) — packaged protobuf manifest and file SHA-256 not yet reported.

- [2026-11-03 githubRelease/playRelease merged-manifest comparison — owner-reported PASS](2026-11-03-merged-release-manifest-qa.md) — packages, permissions, cleartext/backup, activities; signed AAB/Data safety still open.

- [2026-11-03 Play certificate enrollment — both owner-reported live PASS](2026-11-03-play-signing-acceptance.md) — PLAY-001 closed; AAB/release/upgrade QA remains OPEN.

- [2026-11-03 Ragutis 0.3.5 GitHub/Play flavor QA — both owner-reported PASS](2026-11-03-ragutis-0.3.5-flavor-device-qa.md) — exact source and CI, expected channel behavior/content/links, with signed and Play-delivered cases still OPEN.

- [2026-11-03 Ragutis 0.3.5/code16 owner-reported 6/6 debug-device QA PASS](2026-11-03-ragutis-0.3.5-debug-device-qa.md) — exact tested source, bilingual privacy links, native navigation, STR/regression and isolation from public 0.3.4. Production-signed candidate, Play upgrade and policy gates remain open.

## Current signed build and public release evidence

- [2026-11-03 Ragutis 0.3.4 signed build and 6/6 device smoke PASS](2026-11-03-ragutis-0.3.4-local-signed-build.md) — original-signer/source/SHA-256 and six-case QA reported by owner; **public GitHub v0.3.4 released, tag/asset digest independently verified**; future positive update notification untested.

## Current signed release evidence

- [2026-11-01 Ragutis 0.3.3 local signed APK + owner-reported 7/7 device smoke PASS](2026-11-01-ragutis-0.3.3-signed-qa.md) — **public GitHub v0.3.3 stable release; public tag/assets/digest verified after distinct owner approval**.
