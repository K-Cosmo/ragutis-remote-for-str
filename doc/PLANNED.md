# Planned work

This document describes **sequence and dependencies**, not release history or a second backlog. Current state: [Status](STATUS.md). Individual work items and priorities: [Backlog](BACKLOG.md). Verification requirements: [Release gates](RELEASE_GATES.md). Completed changes: [Changelog](CHANGELOG.md).

## Next sequence — 0.3.6 GitHub + Play closed-test bugfix

1. **Done at tested source:** both-flavor CI PASS for `f8f95d1e1929cc882daad77cdc7c209455a7c197`; owner debug-device QA **12 PASS / 0 FAIL / 2 N/A** including all three original bug fixes, standard discovery and DE/EN/Play routing. [Exact-scoped evidence](evidence/qa/2026-11-22-ragutis-036-owner-debug-device-acceptance.md). Keep GitHub public v0.3.4/code15 and Google Play closed-test v0.3.5/code16 unchanged during review.
2. **Remaining before full signed release acceptance:** T12 original Bose setup and T13 manual-IP path were N/A; determine whether to test or explicitly defer them. The identical-IP/different-key F-034 hardware edge case is not physically tested. Prepare separately signed in-place GitHub/Play upgrade QA; never treat debug smoke as signed-release verification.
3. Merge PR #21 and the subsequent 0.3.6 bugfix only after independent review, CI and explicit owner merge approval.
4. Separately approve, sign, inspect and publish the original-signer GitHub APK and the Play upload-key-signed AAB for **v0.3.6/code17**. Update the **existing Play closed test**, not Play production; verify actual Play-delivered v0.3.5→0.3.6 update preserves devices and rooms.
5. Continue community beta testing and monitor feedback/Android Vitals. The owner reports **fewer than 12** opted-in testers; the applicable 12/14-day Play production gate remains open. Keep Android backup enabled for restoration while auditing its actual content and Play Data safety. [Owner QA](evidence/qa/2026-11-22-play-closed-test-owner-qa.md).

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
