# Planned work

This document describes **sequence and dependencies**, not release history or a second backlog. Current state: [Status](STATUS.md). Individual work items and priorities: [Backlog](BACKLOG.md). Verification requirements: [Release gates](RELEASE_GATES.md). Completed changes: [Changelog](CHANGELOG.md).

## Next sequence — verify and distribute merged 0.3.6 source

1. Decide whether to physically test or expressly defer **T12 Bose-original-firmware setup**, **T13 manual IP** and **F-034 same-host/different-key integration**. The recorded owner debug-device scope is 12 PASS / 0 FAIL / 2 N/A; do not convert N/A to PASS.
2. **Owner-reported local build complete:** original-key GitHub v0.3.6/code17 APK built, verified and signed with checksum `cf1d33fcd2f29210fc9250619b53f169a64add60334fe49e96e2d5c4dd0527b3` on exact `main` source `2bcdf023e67eb6f06a7d37f9add3a233711938c7` ([QA](evidence/qa/2026-11-23-ragutis-036-local-github-signed-apk.md)). Next: independently inspect the binary manifest/checksum and perform an actual **in-place signed update** from public GitHub v0.3.4/code15 on a separate compatible device, retaining rooms/speakers and playback. **Do not overwrite the owner's current Play 0.3.5/code16 baseline** needed for the later genuine Play upgrade test. No GitHub publication without distinct owner authorization.
3. Independently authorize, sign (registered **upload** key), inspect and distribute the v0.3.6/code17 **Play closed-test AAB**, then verify a true Play-managed v0.3.5/code16→v0.3.6/code17 in-place update with saved data and unchanged Play **app-signing** certificate. Do not roll out to Play production.
4. Continue genuine closed-tester recruitment and reconcile Android backup, embedded STR WebView and Play Data safety disclosures. The owner reported fewer than 12 enrolled closed testers; production permission remains separate.

## Historical 0.3.5 Play preparation (recorded 2026-11-03)

1. **PLAY-001 complete:** 2026-11-03 live Play certificates owner-confirmed PASS (original app signer `3CC2E727…`, upload signer `41BFFC71…`); no key changes necessary. [Evidence](evidence/qa/2026-11-03-play-signing-acceptance.md).
2. GitHub/Play merged release manifest **consistency owner-reported PASS** (2026-11-03, [evidence](evidence/qa/2026-11-03-merged-release-manifest-qa.md)); complete separate **original-signer production** smoke on a spare device; review [draft PR #21](https://github.com/K-Cosmo/ragutis-remote-for-str/pull/21), with **no merge without express owner approval**. Complete [PLAY-006](BACKLOG.md) device/manifest acceptance, public privacy URL, Android backup and truthful embedded-WebView/Data safety disclosures. The STR maintainer's accepted WebView integration and official Community Apps listing are [documented in Status](STATUS.md); Google Play's independent policy review remains open.
3. Owner reported successful unsigned Play AAB Gradle build (2026-11-03). ZIP structure 4/4 PASS (2026-11-03 owner report). `bundletool` packaged-manifest identity/version/SDK/permission/export checks PASS (2026-11-03; 1,160,743-byte AAB), [evidence](evidence/qa/2026-11-03-play-aab-bundletool-manifest-qa.md). Outstanding unsigned AAB checks PASS: SHA-256 `15BA556123614F926EF78AE163F43F89843157E18661187083FE57D1F997DA5E`, packed location maxSdk=32 and networkSecurityConfig confirmed by owner. **PLAY-003 owner-reported signed AAB PASS** (`jarsigner`, registered upload certificate, SHA-256 `7B94567B796D13A8747A2184DEE073C4FC7EFD0F89838414306D9DFC03EB33B8`; [evidence](evidence/qa/2026-11-03-play-aab-upload-signing-qa.md)). Signed AAB is now attached in the internal-release editor (not rolled out; [screenshot](evidence/qa/2026-11-03-play-console-internal-release-editor.md)); remaining work is to save draft, configure internal tester list, complete policy/production smoke and obtain explicit separate approval **before rollout**. Obtain explicit approval **before** uploading or starting an internal Play track.
4. Test a genuine Google-Play-delivered in-place update over GitHub 0.3.4/code15 with the original app-signing identity and retained speaker/room data.
5. Finalize DE/EN store text/media, organize real closed testers via STR community, and fulfill the applicable test/production-access gates. Production requires a separate decision.

Detailed procedures and policy checks belong only in [Play Store preparation](PLAY_STORE_PREPARATION.md) and [Tests](TESTS.md).

## Separate follow-ups

- Publish the requested [STR Show-and-tell](https://github.com/JRpersonal/streborn/discussions/categories/show-and-tell) discussion, without implying endorsement by Bose or transferring support ownership to STR.
- Complete the explicitly deferred [negative-path and future-release tests](https://github.com/K-Cosmo/ragutis-remote-for-str/issues/7); new hardware claims require new evidence.
- Address residual historical GitHub references, additional model compatibility and isolated Gradle maintenance only when separately prioritized; see [Backlog](BACKLOG.md).

Do not reintroduce the old private STR CSS/DOM workaround. The upstream maintainer accepted the shipped native inset solution; see [D-047](DECISIONS.md), [API contracts](API_CONTRACTS.md) and [Status](STATUS.md).
