# Planned work

This document describes **sequence and dependencies**, not release history or a second backlog. Current state: [Status](STATUS.md). Individual work items and priorities: [Backlog](BACKLOG.md). Verification requirements: [Release gates](RELEASE_GATES.md). Completed changes: [Changelog](CHANGELOG.md).

## Next sequence

1. Complete the focused Play-delivered 0.3.6 post-update checks in [Release gates](RELEASE_GATES.md); preserve installed data and the saved GitHub AVD baseline.
2. Reconcile Play artifact provenance, actual backup payload, embedded STR traffic and Data safety declarations; confirm current tester eligibility before any production-access application.
3. Prioritize remaining work in [Backlog](BACKLOG.md), including the deferred hardware cases; no new release or production rollout implied.

## Separate follow-ups

- Publish the requested [STR Show-and-tell](https://github.com/JRpersonal/streborn/discussions/categories/show-and-tell) discussion, without implying endorsement by Bose or transferring support ownership to STR.
- Complete the explicitly deferred [negative-path and future-release tests](https://github.com/K-Cosmo/ragutis-remote-for-str/issues/7); new hardware claims require new evidence.
- Address residual historical GitHub references, additional model compatibility and isolated Gradle maintenance only when separately prioritized; see [Backlog](BACKLOG.md).

Do not reintroduce the old private STR CSS/DOM workaround. The upstream maintainer accepted the shipped native inset solution; see [D-047](DECISIONS.md), [API contracts](API_CONTRACTS.md) and [Status](STATUS.md).
