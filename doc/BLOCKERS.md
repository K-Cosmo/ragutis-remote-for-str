# Blockers

This document records **current unresolved release blockers**, not completed releases or the general backlog. Authoritative release disposition: [Release gates](RELEASE_GATES.md). Sequencing: [Planned](PLANNED.md).

## Public GitHub v0.3.4

No known open gate blocks the **already published** signed release. Its accepted scope, original source and device-QA limitations are recorded in [Status](STATUS.md) and [Release gates](RELEASE_GATES.md). Deferred tests are not retroactive failures and are not considered passed.

## First Google Play test-track upload

The following gates remain **open** and must be checked before the corresponding Play action:

- **PLAY-001 resolved:** 2026-11-03 owner-reported live Play Console app-signing `3CC2E727…` and upload `41BFFC71…` both PASS; certificate enrollment is no longer a Play blocker. [Evidence](evidence/qa/2026-11-03-play-signing-acceptance.md). Remaining blockers below are separate.
- Current `github`/`play` flavor routing (**PLAY-006**) has owner-reported **both debug variants PASS** ([QA record](evidence/qa/2026-11-03-ragutis-0.3.5-flavor-device-qa.md)); **merged release-manifest consistency PASS; original-signer production and actual Play-delivery verification remain open**.
- DE/EN in-app privacy link tested in the actual Play candidate, plus truthful backup, embedded-WebView, permissions and Data safety review.
- **PLAY-003 resolved:** owner-reported signed AAB local verification and Play-registered upload cert match PASS. [Evidence](evidence/qa/2026-11-03-play-aab-upload-signing-qa.md). No Play upload; privacy, device and owner-approval gates remain separate.
- Signed AAB has already been **attached in Play Console's internal release editor** (owner screenshot); this precedes separate documented approval for that upload. **Actual track rollout remains on HOLD** pending app policy/device QA and explicit owner approval. [Screenshot record](evidence/qa/2026-11-03-play-console-internal-release-editor.md).

The STR maintainer's acceptance of the native embedding and the official Community Apps listing are now confirmed; see [Status](STATUS.md). These are no longer an outstanding **STR-owner permission** request. They do **not** replace Google's separate platform policy review or Bose trademark restrictions.

## Non-blocking follow-ups

- [STR-027](https://github.com/K-Cosmo/ragutis-remote-for-str/issues/7): deferred negative-path hardware, permissions and future positive-update tests remain **NOT TESTED**.
- [STR-024](GIT_HISTORY_PROVENANCE.md): residual historical GitHub-managed PR refs/caches need a separate privacy-maintenance review.
- Transient Chromium/WebView, Bluetooth-permission and upstream API warnings are only defects if a reproducible user-visible failure is established. Do not add speculative permissions or workarounds to silence logs.

Detailed tasks belong in [Backlog](BACKLOG.md); security policies in [Security](SECURITY.md).
