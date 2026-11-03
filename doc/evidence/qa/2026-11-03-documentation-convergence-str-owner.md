# Documentation convergence and STR upstream acceptance — 2026-11-03

## Scope and source classification

Documentation-only review based on the current public GitHub v0.3.4 source/docs, the dated 2026-11-03 **owner-provided** STR maintainer reply, and the independently reachable [STR Community Apps FAQ](https://st-reborn.de/de/#faq-q48). The private correspondence is summarized, not reproduced verbatim; no sender address, message headers or other identifying metadata are committed.

The maintainer confirmed the distinct Ragutis name, accepted the native safe-area handling (system bars/camera cutout handled without consuming keyboard/IME insets) and removal of the private CSS override. The official FAQ publicly lists Ragutis with the canonical repository link. No formal Bose trademark authorization or Google Play policy approval is inferred.

## Semantic review matrix

| Authority | Finding | Convergence action |
|---|---|---|
| Root README | Dated v0.3.4 feature/change narrative duplicated changelog | Keep product, install, safety, community entry points; link canonical history |
| `PLANNED.md` | Finished v0.3.3/0.3.4 release stories and duplicate backlog | Keep only upcoming sequence/dependencies with links |
| `BACKLOG.md` | Completed changes and stale STR-022/STR-025 open status | Keep unfinished/deferred rows; record upstream closure as a concise reference |
| `BLOCKERS.md` | Called v0.3.1 active and removed CSS override current | Distinguish accepted GitHub release from genuinely open Play gates |
| `ARCHITECTURE.md` | Historical candidate descriptions conflicted with shipped design | Describe current components and native WebView/inset implementation |
| `API_CONTRACTS.md` | Claimed native inset fix unreleased | State current native integration; proposed CSS variables not an active contract |
| `STATUS.md` | Duplicated 0.3.3 evidence narrative and lacked owner's latest confirmation | One 0.3.3 status record; authoritative current state and upstream evidence |
| `PLAY_STORE_PREPARATION.md` | Outdated policy wording and unresolved upstream consent/listing | Record corrected policy, drafted PR #21, verified official listing; keep Play review open |
| `RELEASE_GATES.md` | Earlier upstream permission request still open | Mark owner/listing evidence met; preserve independent Play privacy/QA gates |
| `TESTS.md` | 0.3.4 acceptance tests framed as pending | Preserve reusable regression criteria; link accepted dated test evidence |
| `GOVERNANCE.md` | No explicit per-document semantic ownership checklist | Require topic scope, precise state terms and links instead of duplicated text |
| `FINDINGS.md` | No current drift entry | Record F-033 and convergence evidence |

## Verification limits

- No application source/resources/build metadata, keystore, APK, AAB, Git tag, release or Play Console actions are changed by this PR.
- Current latest public GitHub v0.3.4 and its published hash are immutable.
- Draft PR #21 (0.3.5/code16 privacy link) remains unmerged; reconciliation against this docs baseline will be needed before its own acceptance.
- Independent Google Play policy/permissions/Data safety and distribution-channel updater review remain **OPEN**.
- Automated documentation consistency, Android CI and human semantic review results must be added by the PR checks; this note alone does not assert they passed.
