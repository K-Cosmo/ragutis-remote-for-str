# Source merges #26 → #21 → #25 — 2026-11-23

## Authorization and merge method
The project owner explicitly approved **the three GitHub source merges** following a prior review of the tested 0.3.6 debug candidate. This approval **did not authorize** signing, publishing a GitHub APK, uploading a new AAB, changing the Google Play closed track or Play production rollout.

GitHub REST returned `merged=true` for each PR after transitioning approved drafts to ready-for-review. All used **normal merge commits**, not squash/rebase, preserving the staged #21→#25 commit history. #25 was explicitly retargeted to `main` after #21 merged; it was checked to be conflict-free before merge.

| Order | PR / scope | PR HEAD (pre-merge) | Merge commit on main |
|---|---|---|---|
| 1 | [#26](https://github.com/K-Cosmo/ragutis-remote-for-str/pull/26) — AGENTS router / AI guardrails | `ca4b443c2d22a8f9ad0e95faf934f69914254d02` | `13ad2e1689c509d1bb521622ec99a88f33efddf4` |
| 2 | [#21](https://github.com/K-Cosmo/ragutis-remote-for-str/pull/21) — 0.3.5 GitHub/Play flavors, privacy, QA | `485890509eb7eab7953b365517ff335f2f9a575e` | `0c83810d63452974165623cfdc4c4929e5f90415` |
| 3 | [#25](https://github.com/K-Cosmo/ragutis-remote-for-str/pull/25) — 0.3.6/code17 speaker fixes and QA | `21ec06027e6c1383e18fcee52a041516091bc171` | **`2bcdf023e67eb6f06a7d37f9add3a233711938c7`** |

GitHub's `main` branch HEAD was explicitly re-fetched and matched `2bcdf023e67eb6f06a7d37f9add3a233711938c7`. All three PRs reported `state=closed`, `merged=true`, and expected merge commit SHAs. Each exact **PR HEAD** had previously passed GitHub Actions push/PR CI, and owner-device debug smoke (12 PASS / 0 FAIL / 2 N/A) remains pinned to source `f8f95d1e1929cc882daad77cdc7c209455a7c197` ([device QA](2026-11-22-ragutis-036-owner-debug-device-acceptance.md)).

## Separate post-merge CI evidence
- The first intermediate `main` workflow for #26 ([run 45200661318](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/45200661318)) **FAILED** during Gradle dependency resolution: Maven Central returned **HTTP 429 Too Many Requests** for artifacts including ASM 9.9, not a confirmed Kotlin/compiler/test error. Documentation consistency and certificate-parser checks had passed.
- After #21 and final #25 merges, independent `main` workflows were triggered ([run 45200779186](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/45200779186), [run 45200951905](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/45200951905)). At this dated capture they had not completed. **Do not mark final main CI PASS until the exact run completes successfully.** A dependency-server rate limit is a distinct environmental failure, not grounds to bypass the gate.

## Unchanged distribution and acceptance boundaries
- App source `main`: `versionName=0.3.6`, `versionCode=17`, appId `app.strremote.android`, minSdk26/targetSdk37. No public or beta `0.3.6` signed artifact was produced.
- Public **GitHub latest** remains [v0.3.4/code15](https://github.com/K-Cosmo/ragutis-remote-for-str/releases/tag/v0.3.4). Owner reports Play **closed-test v0.3.5/code16** is delivered and working, with matched app-signing certificate. No new Play rollout.
- Pending: exact final-main CI, original-key signed GitHub 0.3.6 update test, independently signed Play AAB and real 0.3.5→0.3.6 update, owner approval for each release channel; T12/T13 and the exceptional same-host/different-key F-034 hardware case remain untested.

See [Status](../../STATUS.md), [Release gates](../../RELEASE_GATES.md) and [Planned](../../PLANNED.md).

## Post-capture CI completion (2026-11-23)

After the above initial in-progress snapshot, GitHub Actions completed both later normal `main` builds successfully:
- [Post-#21 main CI run 45200779186](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/45200779186): **success** on `0c83810d63452974165623cfdc4c4929e5f90415`.
- [Post-#25 final main CI run 45200951905](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/45200951905): **success** on exact merged `main` `2bcdf023e67eb6f06a7d37f9add3a233711938c7`.

Thus **source merge + final post-merge Android/documentation CI PASS**. The earlier #26 failure remains a dated infrastructure/dependency-download **HTTP 429**, not a hidden PASS or reproducible code defect. These successful builds do not confer signed APK, AAB or Play release approval.
