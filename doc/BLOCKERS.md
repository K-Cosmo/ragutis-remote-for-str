# Blockers

This document records **current unresolved release blockers**, not completed releases or the general backlog. Authoritative release disposition: [Release gates](RELEASE_GATES.md). Sequencing: [Planned](PLANNED.md).

## Public GitHub v0.3.4

No known open gate blocks the **already published** signed release. Its accepted scope, original source and device-QA limitations are recorded in [Status](STATUS.md) and [Release gates](RELEASE_GATES.md). Deferred tests are not retroactive failures and are not considered passed.

## Current Google Play closed-test and production gates (2026-11-22)

The owner reports active Google Play closed-test distribution and a successful automatic GitHub→Play upgrade with **saved speakers/rooms and STR control retained**; Play/privacy links work. The old editor-only status below is historical. [Owner QA](evidence/qa/2026-11-22-play-closed-test-owner-qa.md).

**Still open:** exact Play uploaded-AAB/source provenance (installed 0.3.5/code16 and its matching app-signing SHA-256 were owner-verified), embedded STR WebView / Android backup Data safety audit, post-merge `main` CI and signed-source-to-artifact verification, and production eligibility. The owner intentionally keeps Android backup enabled. **Fewer than 12 testers** are enrolled; applicable continuous 12-tester/14-day test and Play production approval are not yet satisfied. Any 0.3.6 GitHub/Play release requires distinct approval.

## 0.3.6 candidate — remaining release gates (2026-11-22)

**Complete in scoped owner QA:** all three original device-list bugs and normal discovery/room regressions PASS; `12 PASS / 0 FAIL / 2 N/A` on exact source `f8f95d1e1929cc882daad77cdc7c209455a7c197`. Both flavor CI workflows PASS. [Dated matrix](evidence/qa/2026-11-22-ragutis-036-owner-debug-device-acceptance.md).

**Still open:** T12 Bose-original-firmware setup and T13 manual-IP path are N/A (untested); F-034 special shared-host identity case lacks physical integration QA. Signed original-key GitHub update, Play-delivered 0.3.5→0.3.6 upgrade, exact AAB/build provenance and separate **release/rollout** authorizations remain open. Source PRs #26, #21 and #25 were already merged with owner approval; see [dated evidence](evidence/qa/2026-11-23-ragutis-source-merges.md). Final `main` CI still requires confirmation. This is not Play production eligibility.

## Historical first Google Play test-track upload (2026-11-03)

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
