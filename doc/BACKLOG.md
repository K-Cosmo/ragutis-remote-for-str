# Backlog

This register contains **unfinished or explicitly deferred work**. Completed release changes live in [Changelog](CHANGELOG.md), accepted release evidence in [Release gates](RELEASE_GATES.md), and current release status in [Status](STATUS.md). Execution order and dependencies: [Planned](PLANNED.md).

| ID | Work item | Priority | State / next gate |
|---|---|---:|---|
| PLAY-002 | Verify delivered privacy link, backups, STR WebView traffic and Data safety | P0 | PARTIAL — local AAB manifest PASS, delivered 0.3.6 link and audits open |
| PLAY-006 | Verify channel-safe update UX on delivered Play version | P0 | Implementation shipped; focused 0.3.6 Play link/routing check open |
| PLAY-004 | Verify installed Play upgrade identity and data preservation | P0 | 0.3.5 cross-channel PASS; 0.3.6 automatic delivery reported; installed signer/version and retention check open |
| PLAY-005 | Store listing/media, real closed testers, test period and production-access application | P1 | PENDING — see [Play Store preparation](PLAY_STORE_PREPARATION.md) |
| STR-023 | Maintain independent support and STR community discussion | P1 | Follow-up; discussion linked from public README |
| STR-039 | Device-list sorting by reachability, device name or room; ascending or descending | P2 | REQUEST RECORDED, separate feature; out of scope for 0.3.6 |
| STR-027 | [Deferred negative-path QA](https://github.com/K-Cosmo/ragutis-remote-for-str/issues/7) | P1 | Negative paths untested; positive GitHub AVD update complete |
| STR-024 | Review residual GitHub-managed old PR refs/caches after historical address cleanup | P1 | PARTIALLY RESOLVED — see [Git history provenance](GIT_HISTORY_PROVENANCE.md) |
| STR-012 | Additional STR remote/control compatibility across other hardware | P2 | BACKLOG; separate from Bose provisioning |
| STR-026 | Bose original-firmware Wi-Fi provisioning on unverified models/firmware/router combinations | P2 | BACKLOG; community reports are not certification |
| STR-014 / BUILD-0008 | Evaluate Gradle 9.8.x as isolated maintenance | P2 | DEFERRED — no toolchain change authorized |
| STR-006 | Evaluate upstream STR authentication changes if introduced | P2 | CONDITIONAL |

## Closed upstream integration items

**STR-022 / STR-025:** The maintainer accepted the native inset/WebView embedding solution and current Ragutis naming on 2026-11-03; the official [STR Community Apps FAQ](https://st-reborn.de/de/#faq-q48) publicly lists the canonical project. A future CSS hook is **not required**, and no undocumented STR selector contract is assumed. These items are no longer open development blockers. The independent Play policy/declaration checks remain in PLAY-002.

Historical decisions and completed STR-001…038 implementation work are preserved under [Decisions](DECISIONS.md), [Changelog](CHANGELOG.md) and [dated QA](evidence/qa/README.md), not repeated as an active-task list.
