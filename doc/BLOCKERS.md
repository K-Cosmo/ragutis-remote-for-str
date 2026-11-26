# Blockers

This document records **current unresolved release blockers**, not completed releases or the general backlog. Authoritative release disposition: [Release gates](RELEASE_GATES.md). Sequencing: [Planned](PLANNED.md).

## Current release blockers

- **GitHub:** no known blocking gate for the already published scoped 0.3.6 release.
- **Play closed-test acceptance:** automatic 0.3.6 delivery owner-confirmed; general owner functional acceptance confirmed; installed version/signing identity and separately itemized device checks remain undocumented. Console-side artifact provenance is not independently reconciled.
- **Play production:** backup/WebView/Data safety review, actual Play device compatibility and current tester eligibility/production-access approval remain open. No production rollout authorized.

See [Release gates](RELEASE_GATES.md) for the authoritative disposition and [Planned](PLANNED.md) for sequencing.

## Non-blocking follow-ups

- [STR-027](https://github.com/K-Cosmo/ragutis-remote-for-str/issues/7): deferred negative-path hardware and permissions remain **NOT TESTED**; positive GitHub 0.3.4→0.3.6 emulator path is now verified within its scope.
- [STR-024](GIT_HISTORY_PROVENANCE.md): residual historical GitHub-managed PR refs/caches need a separate privacy-maintenance review.
- Transient Chromium/WebView, Bluetooth-permission and upstream API warnings are only defects if a reproducible user-visible failure is established. Do not add speculative permissions or workarounds to silence logs.

Detailed tasks belong in [Backlog](BACKLOG.md); security policies in [Security](SECURITY.md).
