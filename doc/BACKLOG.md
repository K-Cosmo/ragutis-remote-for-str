# Backlog

This register contains **unfinished or explicitly deferred work**. Completed release changes live in [Changelog](CHANGELOG.md), accepted release evidence in [Release gates](RELEASE_GATES.md), and current release status in [Status](STATUS.md). Execution order and dependencies: [Planned](PLANNED.md).

| ID | Work item | Priority | State / next gate |
|---|---|---:|---|
| PLAY-001 | Confirm live Play Console app record, app-signing and separate upload fingerprints | P0 | OPEN — verify in the Console before upload |
| PLAY-002 | Validate the in-app privacy link, merged manifest, backups, STR WebView traffic and Data safety | P0 | PARTIAL — public policy corrected, source staged and [6/6 owner-reported debug-device QA](evidence/qa/2026-11-03-ragutis-0.3.5-debug-device-qa.md) PASS; **production-signed/Play QA, PR merge and audits open** |
| PLAY-006 | Channel-safe update UX for GitHub and Google Play variants | P0 | SOURCE + CI + OWNER-REPORTED BOTH DEBUG FLAVORS PASS; **merged release manifest, original-signer and real Play-delivered upgrade QA OPEN**. [Play runbook](PLAY_STORE_PREPARATION.md#google-play-channel-ux-source-implemented-p0-qa-before-distribution) |
| PLAY-003 | Produce and verify first signed Play internal-test AAB | P0 | PENDING — code16/0.3.5 staged in draft PR #21; separate upload key and approval required |
| PLAY-004 | Internal Play GitHub-to-Play in-place update, original signer and data preservation | P0 | PENDING — no track upload/test yet; owner authorization required |
| PLAY-005 | Store listing/media, real closed testers, test period and production-access application | P1 | PENDING — see [Play Store preparation](PLAY_STORE_PREPARATION.md) |
| STR-023 | Publish STR upstream Show-and-tell thread and maintain independent GitHub Issues/voluntary email support | P1 | OPEN — maintainer requested thread; no published discussion verified |
| STR-027 | [Deferred negative-path QA and future positive version alert](https://github.com/K-Cosmo/ragutis-remote-for-str/issues/7) | P1 | DEFERRED, NOT TESTED; do not infer coverage |
| STR-024 | Review residual GitHub-managed old PR refs/caches after historical address cleanup | P1 | PARTIALLY RESOLVED — see [Git history provenance](GIT_HISTORY_PROVENANCE.md) |
| STR-012 | Additional STR remote/control compatibility across other hardware | P2 | BACKLOG; separate from Bose provisioning |
| STR-026 | Bose original-firmware Wi-Fi provisioning on unverified models/firmware/router combinations | P2 | BACKLOG; community reports are not certification |
| STR-014 / BUILD-0008 | Evaluate Gradle 9.8.x as isolated maintenance | P2 | DEFERRED — no toolchain change authorized |
| STR-006 | Evaluate upstream STR authentication changes if introduced | P2 | CONDITIONAL |

## Closed upstream integration items

**STR-022 / STR-025:** The maintainer accepted the native inset/WebView embedding solution and current Ragutis naming on 2026-11-03; the official [STR Community Apps FAQ](https://st-reborn.de/de/#faq-q48) publicly lists the canonical project. A future CSS hook is **not required**, and no undocumented STR selector contract is assumed. These items are no longer open development blockers. The independent Play policy/declaration checks remain in PLAY-002.

Historical decisions and completed STR-001…038 implementation work are preserved under [Decisions](DECISIONS.md), [Changelog](CHANGELOG.md) and [dated QA](evidence/qa/README.md), not repeated as an active-task list.
