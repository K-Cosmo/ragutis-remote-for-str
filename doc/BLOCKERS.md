# Blockers

This document records **current unresolved release blockers**, not completed releases or the general backlog. Authoritative release disposition: [Release gates](RELEASE_GATES.md). Sequencing: [Planned](PLANNED.md).

## Public GitHub v0.3.4

No known open gate blocks the **already published** signed release. Its accepted scope, original source and device-QA limitations are recorded in [Status](STATUS.md) and [Release gates](RELEASE_GATES.md). Deferred tests are not retroactive failures and are not considered passed.

## First Google Play test-track upload

The following gates remain **open** and must be checked before the corresponding Play action:

- **P0: actual app-signing certificate mismatch reported 2026-11-03.** The new Play app shows SHA-256 `F2DCDD5F…`, whereas v0.3.4 GitHub uses `3CC2E727…`. Local upload key correctly matches `41BFFC710…`; live Play upload registration still needs checking. **Do not upload any AAB** until original app-signing key configuration is corrected and both live certs/package are verified. [Runbook](PLAY_STORE_PREPARATION.md).
- Current `github`/`play` flavor routing (**PLAY-006**) has owner-reported **both debug variants PASS** ([QA record](evidence/qa/2026-11-03-ragutis-0.3.5-flavor-device-qa.md)); **merged release-manifest, original-signer production and actual Play-delivery verification remain open**.
- DE/EN in-app privacy link tested in the actual Play candidate, plus truthful backup, embedded-WebView, permissions and Data safety review.
- Signed AAB built from reviewed exact source and verified with the **separate** registered upload key.
- Explicit owner approval for the Play upload and rollout.

The STR maintainer's acceptance of the native embedding and the official Community Apps listing are now confirmed; see [Status](STATUS.md). These are no longer an outstanding **STR-owner permission** request. They do **not** replace Google's separate platform policy review or Bose trademark restrictions.

## Non-blocking follow-ups

- [STR-027](https://github.com/K-Cosmo/ragutis-remote-for-str/issues/7): deferred negative-path hardware, permissions and future positive-update tests remain **NOT TESTED**.
- [STR-024](GIT_HISTORY_PROVENANCE.md): residual historical GitHub-managed PR refs/caches need a separate privacy-maintenance review.
- Transient Chromium/WebView, Bluetooth-permission and upstream API warnings are only defects if a reproducible user-visible failure is established. Do not add speculative permissions or workarounds to silence logs.

Detailed tasks belong in [Backlog](BACKLOG.md); security policies in [Security](SECURITY.md).
