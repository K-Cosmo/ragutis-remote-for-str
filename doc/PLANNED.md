# Planned work

This document describes **sequence and dependencies**, not release history or a second backlog. Current state: [Status](STATUS.md). Individual work items and priorities: [Backlog](BACKLOG.md). Verification requirements: [Release gates](RELEASE_GATES.md). Completed changes: [Changelog](CHANGELOG.md).

## Next sequence — Google Play internal test

1. **PLAY-001 complete:** 2026-11-03 live Play certificates owner-confirmed PASS (original app signer `3CC2E727…`, upload signer `41BFFC71…`); no key changes necessary. [Evidence](evidence/qa/2026-11-03-play-signing-acceptance.md).
2. After owner-reported PASS for both debug flavors, inspect merged GitHub/Play **release manifests** and complete separate **original-signer production** smoke on a spare device; review [draft PR #21](https://github.com/K-Cosmo/ragutis-remote-for-str/pull/21), with **no merge without express owner approval**. Complete [PLAY-006](BACKLOG.md) device/manifest acceptance, public privacy URL, Android backup and truthful embedded-WebView/Data safety disclosures. The STR maintainer's accepted WebView integration and official Community Apps listing are [documented in Status](STATUS.md); Google Play's independent policy review remains open.
3. Create and verify a locally signed AAB with the separate upload key. Obtain explicit approval **before** uploading or starting an internal Play track.
4. Test a genuine Google-Play-delivered in-place update over GitHub 0.3.4/code15 with the original app-signing identity and retained speaker/room data.
5. Finalize DE/EN store text/media, organize real closed testers via STR community, and fulfill the applicable test/production-access gates. Production requires a separate decision.

Detailed procedures and policy checks belong only in [Play Store preparation](PLAY_STORE_PREPARATION.md) and [Tests](TESTS.md).

## Separate follow-ups

- Publish the requested [STR Show-and-tell](https://github.com/JRpersonal/streborn/discussions/categories/show-and-tell) discussion, without implying endorsement by Bose or transferring support ownership to STR.
- Complete the explicitly deferred [negative-path and future-release tests](https://github.com/K-Cosmo/ragutis-remote-for-str/issues/7); new hardware claims require new evidence.
- Address residual historical GitHub references, additional model compatibility and isolated Gradle maintenance only when separately prioritized; see [Backlog](BACKLOG.md).

Do not reintroduce the old private STR CSS/DOM workaround. The upstream maintainer accepted the shipped native inset solution; see [D-047](DECISIONS.md), [API contracts](API_CONTRACTS.md) and [Status](STATUS.md).
