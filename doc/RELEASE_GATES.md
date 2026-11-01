# Release gates

A release candidate may be marked final/ACCEPTED only when all applicable gates pass.

## 0.3.3 repository rename and release packaging — OPEN GATES

- [x] Owner renamed the repository to [K-Cosmo/ragutis-remote-for-str](https://github.com/K-Cosmo/ragutis-remote-for-str) on 2026-11-01. Previous repository remains the historical 0.3.2 hardcoded endpoint.
- [x] PR #13 **source merged** as `f821602e1b45172610a7735f6e0830de4bfdee79`, post-merge [CI 41315289069](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41315289069) PASS. No signed APK or release implied.
- [x] Legacy 0.3.2 update-check risk documented: fixed original endpoint with redirects disabled; can no longer promise notifications after GitHub rename. A manual new APK download is the fallback.
- [x] Canonical URL migration PR #14 merged to `main` as exact signed source `44bc6d0c518c9c0dbb01bf109589f3b5cbd29d65`. App updater/API/release/About/Issues URLs, tests and standard HTTPS redirects updated. [Final main CI 41321518107](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41321518107) PASS. Positive live redirected/next-version notification remains NOT TESTED.
- [x] **Owner-reported local signed APK build** from exact main `44bc6d0c518c9c0dbb01bf109589f3b5cbd29d65`, `versionName=0.3.3`, code 14, application ID `app.strremote.android`, completed `tools/build-signed-release.ps1`. Script enforces historical GitHub signer SHA-256, runs Gradle checks, `apksigner`, 16-KiB `zipalign` and generates SHA-256 companion. Owner-provided final output and digest recorded; original keystore/APK bytes not independently inspected.
- [x] **Owner-reported signed in-place 0.3.2→0.3.3 smoke PASS (2026-11-01)**: all 7 categories PASS — update/data, STR controls, room selection, WLAN wizard, feedback, DE/EN interface, canonical GitHub links. [Exact signed candidate QA record](evidence/qa/2026-11-01-ragutis-0.3.3-signed-qa.md). No uninstall/data-clear reported. Raw ADB logs, exact every-edge-case coverage and APK bytes not independently available; deferred negative tests remain NOT TESTED.
- [x] **Local APK filename and SHA-256 recorded (owner-local)**: `Ragutis-Remote-for-SoundTouch-Reborn-0.3.3.apk`; SHA-256 `045c5e8e6b521f92c398a3051deffd4b997bad78214b9337e149492e36d2de3f`; corresponding checksum companion generated locally. [Provenance](evidence/qa/2026-11-01-ragutis-0.3.3-signed-qa.md).
- [ ] **Separate explicit owner release approval**; only then create `v0.3.3` tag pointing to **exact signed source** `44bc6d0c518c9c0dbb01bf109589f3b5cbd29d65`, upload original signed APK + checksum and independently verify public assets and GitHub `releases/latest`. No publication authorized in the smoke-test message.
- [ ] Update status/README to `0.3.3` **only after** the real GitHub Release is confirmed. Do not claim Play/closed-test availability.

---

## 0.3.3 — RAGUTIS / NATIVE WEBVIEW INSETS (SIGNED QA ACCEPTED; UNRELEASED)

- [x] Code staged with full **Ragutis Remote for SoundTouch Reborn** identity, launcher/native toolbar **Ragutis STR**, existing `app.strremote.android` identity and original signer expectation.
- [x] Static code removes the private STR header CSS override; native root zeroes already-handled system bars/cutouts (API 30+) and consumes legacy system window insets (API 26–29).
- [x] Version staged `0.3.3` / `14`; new and two historical GitHub APK asset names are recognized by update checker; new basename used for future APK creation.
- [x] Candidate source `08ca3d5c3025fbce7df5130fa8961a00713fa954` GitHub CI: [push 41306285299](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41306285299) **PASS** and [PR 41306330468](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41306330468) **PASS**; documentation drift/anti-harvest and Android build/lint/unit tests PASS. These CI results do **not** replace on-device QA.
- [x] **Owner-reported first-round debug QA (2026-11-01):** seven requested categories PASS for the earlier Ragutis 0.3.3 debug build, including names, STR top/bottom insets, control use, navigation, keyboard/orientation/themes and DE/EN wizard. This does **not** verify the subsequent new feedback recipient/room picker, older API 26–29 devices or signed upgrade.
- [x] New recipient staged as reversible numeric codes and both opt-in email paths still use one `BoseCommunityFeedback.RECIPIENT`; new unit expectation avoids committing a full mailbox literal.
- [x] WLAN-style room cards implemented; existing selected preset/custom state is highlighted, accessibility labels localized, text-entry blank validation preserved, and mapping unit tests staged.
- [x] **New functional source head `37b93dc38e971d51d9c8d9af1b2e3aff0077b12d`** CI after recipient/room changes: [push 41310056025](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41310056025) **PASS**, [PR 41310061181](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41310061181) **PASS**; document consistency, Android build/lint, unit tests and debug artifact upload passed. UI/mailbox device validation remains separate.
- [x] SoundTouch wizard dropdown changed to 10, 20 III, Other (last), without „geprüft“/“verified” label suffix; model-specific verified and unverified notes render immediately beneath the selector. Saved enum names/IDs, model-specific setup instructions, verification flags and Bose protocol remain unchanged. Test explicitly covers display order and stable mapping.
- [x] Wizard now uses a **non-selectable placeholder** at dropdown index 0 with no automatic SoundTouch 20 III choice; continue/next is disabled and instructional card/verified notice are hidden until explicit selection. Existing chosen model ID can be restored for rotation/retry. Status notice has 14dp side inset for alignment.
**Evidence scope:** The owner's 2026-11-01 signed 0.3.3 release smoke report confirms **7/7 category-level PASS**, including the WLAN wizard, room selection, email feedback, UI/DE/EN and data preservation. The granular scenarios below that were not individually identified in the report (legacy Android devices, TalkBack, denial/failure states, every rotation/model permutation) remain open or deferred. Those are **not silently converted to PASS** and do not assert untested universal compatibility. See [signed QA](evidence/qa/2026-11-01-ragutis-0.3.3-signed-qa.md).

- [ ] **Latest wizard on-phone QA:** fresh opening shows placeholder **SoundTouch-Modell auswählen** (EN: Select SoundTouch model), no selected model and disabled Next, no model instruction card; opening then dismissing dropdown without selecting cannot progress; selecting each supported model enables Next and displays correct help; back/retry/rotation preserve *chosen* model. Check hint text alignment in DE/EN at normal and large font.
- [ ] **New wizard UI phone QA:** in DE and EN verify dropdown order, no redundant verified suffixes, checked hint for both tested models, brief unverified hint for Other and readable spacing before the instruction card. Confirm switching 10 ↔ 20 III ↔ Other updates hint and instructions, feedback button only for Other, and wizard pages remain usable without clipping.
- [ ] DE/EN on-device regression: About email and WLAN-model feedback open user-controlled mail drafts addressed to **new project mailbox**; version/Android fields, consent, no plaintext old/new mailbox in public source/docs.
- [ ] Room picker DE/EN phone QA: current assigned room highlighted, none clears, six presets persist, custom name and re-edit persist, cancellation leaves data untouched, long names/small screen accessible and scrollable, TalkBack label correct; pending Bose device and STR-connected device both work.
- [ ] Real device reports: native root + STR remote layout **no duplicate top/bottom padding**, normal upstream 16px header, no clipped header/buttons, functional scrolling, clicks, keyboard/IME and both light/dark modes, landscape, status/nav bars, API 26–29 and current API 30+ device.
- [x] **Owner-reported signed APK 0.3.2→0.3.3 in-place upgrade and data retention: PASS** as part of the seven-case release smoke report. Historical signer enforced by the owner's local build script; independent physical-APK verification not performed by assistant.
- [ ] DE/EN phone checks: launcher and toolbar **Ragutis STR**, full About product identity, independent/trademark notice, model-feedback version and both wizard languages.
- [ ] **Post-release/community gate (not required for signed APK acceptance):** owner reviews Show-and-tell post and asks upstream maintainer to update the Community Apps label only after a confirmed release. Any upstream CSS variables remain unshipped fallbacks, not an app release requirement.
- [x] **Owner explicitly authorizes merging PR #13** (2026-11-01) and plans an initial GitHub release of Ragutis 0.3.3 after code integration. This is approval to **merge source only**, not evidence of a signed release being built, installed, tested or published.
- [x] **Signed candidate build + core device smoke completed, owner-reported**: exact source, original-signer script, versionCode 14, local SHA-256 and 7/7 phone tests documented. **Not equivalent to permission to publish**; the remaining publication tag/assets/evidence gates and explicit owner approval are still OPEN. Google Play AAB/track rollout separately gated.

Do not reuse old Play-only PR #12 candidate without reconciling the new code, name and version. Historical 0.3.2 remains RELEASED / ACCEPTED.

---

## 0.3.2 — RELEASED / ACCEPTED (STR-028 / BUILD-0014)

- [x] Original signed/tested source `547fe4e52935bac03ddb0c3eb1f1ca4c7e517323`: Android build/lint/tests plus anti-harvest doc gate CI PASS ([push](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41203798614), [PR](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41203849504)).
- [x] All six signed-device test scenarios PASS (compact launcher; full independent-brand/About notice; About and SoundTouch email drafts; signed in-place 0.3.1→0.3.2 upgrade retaining devices/rooms; STR connection/controls and restart persistence).
- [x] Tester-executed signed APK integrity: exact original APK SHA-256 `19b0cf987351fde11ad76c9c61d5334784dbeb5466f98e5b5602ea91549e10ec` and companion checksum, `apksigner verify`, original certificate SHA-256 `3cc2e7272d8c2d1433a57c4462df39adfe5e8c1931a4a924710c0e4087aa1e32`, `zipalign -c -P 16 -v 4` PASS; recorded verifier output, not independently supplied APK bytes.
- [x] Owner explicitly approved 0.3.2 merge and publication; normal [PR #9 merge](https://github.com/K-Cosmo/ragutis-remote-for-str/pull/9) `d937365e6187fefe1d097aacb2d68b4ae8b393f6` completed and [post-merge CI](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41214563587) PASS.
- [x] Annotated Git `v0.3.2` tag object `a076305a65f9207c2df36acfedf5ba94c6e576a9` dereferenced on GitHub and targets the **exact signed source** `547fe4e52935bac03ddb0c3eb1f1ca4c7e517323`. Git tag itself is unsigned.
- [x] Public [v0.3.2 release](https://github.com/K-Cosmo/ragutis-remote-for-str/releases/tag/v0.3.2) verified (GitHub ID `430018074`, 2026-10-31T15:29:07Z, non-draft, non-prerelease, `releases/latest`).
- [x] GitHub APK asset ID `695738568`, 1,197,701 bytes: GitHub-reported digest `sha256:19b0cf987351fde11ad76c9c61d5334784dbeb5466f98e5b5602ea91549e10ec` exactly matches original signed APK.
- [x] Checksum file asset ID `695738567`, 92 bytes: separately published; user explicitly reports re-downloading and verifying public APK and checksum. The assistant independently checked GitHub asset metadata, not the downloaded file bytes.
- [x] Deferred negative paths and unverified Bose configurations remain under [STR-027](https://github.com/K-Cosmo/ragutis-remote-for-str/issues/7) as NOT TESTED, not implicitly accepted.

The 0.3.2 release is **ACCEPTED for the verified scope**. See [publication evidence](evidence/qa/2026-10-31-release-0.3.2.md) for tag/asset provenance and verification limitations.

---

## 0.3.1 — RELEASED / ACCEPTED (BUILD-0013, PR #6)

- [x] English WLAN wizard usability confirmed by user on actual Android device (2026-10-30); **language/UI gate only**
- [x] Model search for “30” and “SoundTouch”, readable suggestions and compact feedback page confirmed on phone
- [x] Recipient, subject and body of volunteer email draft confirmed on tested email client; decorative paragraph spacing unsupported there, cosmetic/deferred
- [x] Exact signed-source Android CI passed at `0e6bab9d9e1bcac19b542755b7a1774c69d2776a` ([push 41146044403](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41146044403), [PR 41146049240](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41146049240)); documentation-only candidate commit `67916ad816e77d1408c58238f01dc69bae1fb04f` passed [push 41188614827](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41188614827) and [PR 41188618925](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41188618925). final acceptance docs CI subsequently passed, and the normal merge also passed CI
- [x] User-confirmed signed-APK smartphone acceptance: **Remote for STR** launcher; info icon next to title on Devices only; playback, volume, presets and connection; app restart and saved speaker/room persistence
- [x] **Scoped deferral accepted by owner 2026-10-31:** live end-to-end positive update-checker notification cannot be exercised until a newer official stable APK release exists; deterministic version/asset/URL unit tests passed. No fake public Release for testing. Record a future-version production check as follow-up, **not PASS**
- [x] Rewrite normal branch/tag refs and remove six obsolete branches; preserve five signed releases. [ ] Follow up on GitHub-managed old PR refs/caches separately
- [x] **Explicit owner-approved 0.3.1 deferral (2026-10-31):** no-email-client fallback, rejected Wi-Fi permission, network failure/timeout, and untested SoundTouch/router/firmware combinations were outside the six positive phone checks. Keep these cases as **NOT TESTED**, scope supported claims to tested conditions and track follow-up. Existing code fallback is not evidence of real-phone behavior
- [x] Stage candidate Android `versionName=0.3.1` / `versionCode=12` without changing `applicationId`
- [x] User successfully ran the fail-closed local release script on clean source `0e6bab9d9e1bcac19b542755b7a1774c69d2776a`; resulting signed `Remote-for-STR-0.3.1.apk`, SHA-256 `8100334f067346c9987a68969bdebd9065455bfe73c21a0634f0dba42ddbd759`. Script requires original signing-certificate SHA-256, `apksigner verify` and 16 KiB zipalign before reporting Final artifact
- [x] **Tester-reported PASS on the exact signed APK:** 0.3.0 → 0.3.1 in-place upgrade without uninstall, saved speakers/rooms, STR remote playback/volume/presets, DE/EN wizard, optional email draft, About icon placement, app restart/persistence. All six presented device acceptance checks were explicitly confirmed on 2026-10-31
- [x] **Owner-approved merge completed** on 2026-10-31: final feature-head CI green ([push 41189139487](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41189139487), [PR 41189143522](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41189143522)), normal merge commit `ed7652783a9752e107449a572c5b6f2dff624922`, **no squash/rebase**; signed source remains ancestor
- [x] **Public release confirmed 2026-10-31:** owner published `v0.3.1` as GitHub Release ID `429849089` (latest, `draft=false`, `prerelease=false`); annotated tag object `dd0480df720d805b0c6b76fd20757b59d0ee5cfe` points to **exact signed source** `0e6bab9d9e1bcac19b542755b7a1774c69d2776a`; GitHub published APK (1,197,701 bytes) digest `sha256:8100334f067346c9987a68969bdebd9065455bfe73c21a0634f0dba42ddbd759` matches the tester's signed APK, companion checksum asset uploaded, public docs reconciled after release

The **signed 0.3.1 release, six specified handset scenarios, exact-source and post-merge CI, and public APK/hash publication are accepted**. Untested negative cases are expressly **deferred, not passed**. `v0.3.1` is the latest published release. Evidence: [2026-10-31 signed candidate](evidence/qa/2026-10-31-release-candidate-0.3.1.md) and [publication check](evidence/qa/2026-10-31-release-0.3.1.md). Later documentation-only commits do **not** change the signed source SHA or the APK bytes.

---

## 0.3.0 — ACCEPTED

### Product and security

- [x] Bose Wi-Fi wizard promoted into the production source set, not just `src/debug`
- [x] Android 10+ system-consented Bose setup WLAN; unchanged Android 8/9 STR remote availability
- [x] one state-changing provisioning POST with no automatic retries and bounded LAN verification
- [x] same-device verification before claims of WLAN join; Bose without STR is a distinct grey record
- [x] SoundTouch 10 clearly marked unverified/experimental; SoundTouch 20 III remains the reference
- [x] WLAN cleartext credential risk documented, password not persisted or logged
- [x] versionName 0.3.0 / versionCode 11; same production package ID
- [x] release source committed, production Debug and Release CI green on exact source revision (main run 41108758228)

### Signature and publication

- [x] build clean production source with the original 0.2.1 signing keystore
- [x] signature certificate SHA-256 matches `3cc2e7272d8c2d1433a57c4462df39adfe5e8c1931a4a924710c0e4087aa1e32`
- [x] APK Signature Scheme v2/v3 and 16 KiB zipalign verified
- [x] exact signed 0.3.0 APK installs **as an update** over existing 0.2.1 without uninstalling or losing saved STR devices/rooms
- [x] user confirmed the exact signed APK passed the agreed smartphone acceptance test (including STR remote, wizard, persistence and Android permissions)
- [x] signed source commit fixed, annotated v0.3.0 tag created and its CI passed (run 41111993535)
- [x] public GitHub Release v0.3.0 (ID 429390125) has verified signed APK and matching SHA-256 asset, not draft/prerelease
- [x] latest-release links, README and STATUS updated to public 0.3.0, and acceptance evidence recorded

Release evidence: [2026-10-30](evidence/qa/2026-10-30-release-0.3.0.md), rewritten tag source `edc64cd5468e60edff355ec1dd1fd73c338004af` (the historic APK was not rebuilt); APK SHA-256 `c4b1989e00ba21915e34192b2f4e0b73695416c93397e4f0c4d3cc6617a18290`.

---

## 0.2.1 — ACCEPTED

### Implementation/scope

- [x] application version is `0.2.1` / versionCode 10
- [x] native remote-screen banner is compact and retains comfortable Devices/Remote action sizing
- [x] saved reachable speakers use strong green highlighting plus explicit Online text
- [x] saved unreachable speakers use strong red highlighting plus explicit Offline text
- [x] saved-speaker last-known reachability persists locally and remains visually stable during background revalidation
- [x] device-card geometry/status stays stable while probes run and color changes only after a changed result
- [x] device metadata hierarchy is model/status followed by STR version/IP with decreasing text size
- [x] Remote no-Wi-Fi state uses native guidance instead of Chromium's generic error page
- [x] Remote and Devices no-Wi-Fi states expose direct Android Wi-Fi settings access
- [x] recovery/device-selection paths probe the STR endpoint before starting a WebView load
- [x] WebView remains hidden behind native connection state until the main STR page finishes successfully
- [x] no new permission, cloud service, analytics backend or native playback/business logic is introduced
- [x] existing app-private preferences are extended only for last-known reachability; no new persistence dependency/model is introduced
- [x] Gradle 9.7.1 + AGP 9.4.1 remains unchanged

### Local/final device evidence

- [x] local documentation consistency gate passes
- [x] clean `assembleDebug + lintDebug + assembleRelease` succeeds
- [x] lint contains no blocking errors
- [x] Pixel confirms the compact Remote header and normal STR controls remain usable
- [x] Pixel confirms saved reachable speaker = green + Online
- [x] Pixel confirms saved unreachable speaker = red + Offline
- [x] Pixel confirms Devices retains the previous green/red state and stable card geometry while background validation runs
- [x] Pixel confirms Remote with Wi-Fi disabled uses native no-Wi-Fi guidance
- [x] Pixel confirms direct Wi-Fi settings navigation from both Remote and Devices no-Wi-Fi states
- [x] Pixel confirms Wi-Fi restore + Retry follows endpoint probing before STR presentation
- [x] Pixel confirms selecting a saved device no longer normally exposes Chromium's generic connection-error page

### Committed-source/signing/publication

- [x] exact release source is `6a3352928dc177419f2f5d7f5bc49868e9ab0589`
- [x] GitHub Android CI passes for exact release source on `main` (run `36721458188`)
- [x] GitHub Android CI passes for pushed `v0.2.1` (run `36722290992`)
- [x] release build starts from clean committed source
- [x] `apksigner verify --verbose --print-certs` succeeds
- [x] signing identity is `C=DE, CN=STR Remote`
- [x] signer certificate SHA-256 is `3cc2e7272d8c2d1433a57c4462df39adfe5e8c1931a4a924710c0e4087aa1e32`
- [x] APK Signature Scheme v2 and v3 verify successfully
- [x] post-sign `zipalign -c -P 16 -v 4` succeeds
- [x] final `STR-Remote-0.2.1.apk` SHA-256 is `bf019801b4a47226ab1af616f4f74f1dd7abfb71731205253e1d9709af76de67`
- [x] annotated `v0.2.1` tag object `7347fad1f8693746ec5e806df25cd43d326e42b2` resolves to exact release source
- [x] GitHub Release `v0.2.1` is published, non-draft and non-prerelease (release ID `400078904`)
- [x] GitHub Release publishes `STR-Remote-0.2.1.apk` and `STR-Remote-0.2.1.apk.sha256.txt`
- [x] GitHub public APK asset digest is `sha256:bf019801b4a47226ab1af616f4f74f1dd7abfb71731205253e1d9709af76de67`, matching the accepted local artifact

The `0.2.1` release is **ACCEPTED**.

---

## 0.2.0 — ACCEPTED

### Implementation/scope

- [x] saved speakers use app-private platform persistence without a new dependency
- [x] last-successful-endpoint startup path is preserved
- [x] saved/discovered rows use one de-duplicated device list
- [x] current speaker and quick switching use the existing endpoint/WebView path
- [x] unreachable saved speakers remain visible/actionable by design
- [x] optional room assignment includes six built-in presets plus custom text
- [x] built-in room storage is language-neutral and room metadata is excluded from connectivity/security identity
- [x] room assignment is an explicit inline action and implicitly saves the speaker
- [x] saved speakers expose a visible delete action with confirmation
- [x] common device-management actions do not rely on hidden long-press gestures
- [x] active-speaker device card, room/device hierarchy and Remote return path are implemented
- [x] usage guidance can be dismissed and the preference persists locally
- [x] no new permission, background service, cloud feature or playback/business logic is introduced
- [x] Gradle 9.7.1 + AGP 9.4.1 remains unchanged
- [x] application version is `0.2.0` / versionCode 9

### Local/final device evidence

- [x] local documentation consistency gate passes
- [x] clean `assembleDebug + lintDebug` succeeds
- [x] lint contains no blocking errors
- [x] `assembleRelease` succeeds
- [x] Pixel confirms room assignment and room visibility
- [x] Pixel confirms row tap connect/switch and explicit `Fernbedienung` return path
- [x] Pixel confirms delete confirmation/current-list removal behavior
- [x] Pixel confirms dismissible help text remains hidden after relaunch
- [x] Pixel confirms final device-list spacing and selected-device presentation
- [x] normal STR WebView/control path remains operational
- [x] exact signed final APK confirms custom-room persistence after relaunch
- [x] exact signed final APK confirms saved/offline speaker handling and recovery
- [x] exact signed final APK confirms manual-host connection can be assigned/saved
- [x] exact signed final APK confirms inherited Wi-Fi/permission/back-navigation regression checks

### Committed-source/signing/publication

- [x] GitHub Android CI passes for exact release source `b5ff103e318f4342ba3a748b786084108d4093af` (main run `36610855156`)
- [x] GitHub Android CI passes for pushed `v0.2.0` (run `36611841390`)
- [x] release build starts from clean committed source `b5ff103e318f4342ba3a748b786084108d4093af`
- [x] `apksigner verify --verbose --print-certs` succeeds
- [x] signing identity is `C=DE, CN=STR Remote`
- [x] signer certificate SHA-256 is `3cc2e7272d8c2d1433a57c4462df39adfe5e8c1931a4a924710c0e4087aa1e32`
- [x] APK Signature Scheme v2 and v3 verify successfully
- [x] post-sign `zipalign -c -P 16 -v 4` succeeds
- [x] final `STR-Remote-0.2.0.apk` SHA-256 is `5e7ba21f39752ae359a6de0103a440b363a1823223874778e44173f285243aa7`
- [x] exact signed `STR-Remote-0.2.0.apk` installs and passes the final smoke test
- [x] annotated tag `v0.2.0` resolves to exact signed-artifact source commit `b5ff103e318f4342ba3a748b786084108d4093af`
- [x] GitHub Release `v0.2.0` is published, non-draft and non-prerelease
- [x] GitHub Release `v0.2.0` publishes `STR-Remote-0.2.0.apk` and `STR-Remote-0.2.0.apk.sha256.txt`
- [x] GitHub public APK asset digest is `sha256:5e7ba21f39752ae359a6de0103a440b363a1823223874778e44173f285243aa7`, matching the accepted local artifact

The `0.2.0` release is **ACCEPTED**.

---

## 0.1.1 - ACCEPTED

### Build/toolchain

- [x] local documentation consistency gate passes
- [x] clean `assembleDebug + lintDebug` succeeds
- [x] lint contains no blocking errors
- [x] `assembleRelease` succeeds
- [x] deprecated `ConnectivityManager.allNetworks` warning removed
- [x] GitHub Actions builds debug/lint/release from committed feature source `34d64aeb452be4b9d2e30c998e2b57888ff84c95` (run `36324489315`)
- [x] GitHub Actions is green for the final release source commit `d01b47dd348f0a52d7b81917c60c74c5229e17c4` (run `36325864924`)

### Discovery/network resilience

- [x] no-Wi-Fi/error states render as stable user-facing guidance rather than an indefinite spinner
- [x] Retry works without an app restart
- [x] STR prerequisite/help path opens the upstream website
- [x] no-device and STR-unreachable states are implemented as distinct states
- [x] automatic local networking uses a Wi-Fi `NetworkCallback` without requiring Internet validation
- [x] no new Android permission or application dependency was added
- [x] normal app start/WebView path remains operational on the Pixel after the NetworkCallback cleanup
- [x] exact signed final APK passes focused Pixel smoke: no-Wi-Fi -> Retry -> normal STR connection/control
- [x] exact signed final APK confirms saved-endpoint reconnect after relaunch
- [x] exact signed final APK confirms permission recovery and native back navigation remain functional

### Signing/publication

- [x] release build starts from clean committed source `d01b47dd348f0a52d7b81917c60c74c5229e17c4`
- [x] `apksigner verify --verbose --print-certs` succeeds
- [x] signing identity remains `C=DE, CN=STR Remote`
- [x] signer certificate SHA-256 is `3cc2e7272d8c2d1433a57c4462df39adfe5e8c1931a4a924710c0e4087aa1e32`
- [x] APK Signature Scheme v2 and v3 verify successfully
- [x] post-sign `zipalign -c -P 16 -v 4` succeeds
- [x] final `STR-Remote-0.1.1.apk` SHA-256 recorded: `ecc8f8a78c0b65be5a74a44546275aa9d5688b54b2eacf577f3bbfd20b87a1e8`
- [x] exact signed `STR-Remote-0.1.1.apk` installed and smoke-tested
- [x] annotated tag `v0.1.1` points to source commit `d01b47dd348f0a52d7b81917c60c74c5229e17c4`
- [x] GitHub Release `v0.1.1` publishes the signed APK and SHA-256 file
- [x] GitHub public APK asset digest equals `sha256:ecc8f8a78c0b65be5a74a44546275aa9d5688b54b2eacf577f3bbfd20b87a1e8`

The `0.1.1` release is **ACCEPTED**.

---

## 0.1.0 — ACCEPTED

### Build/toolchain

- [x] real debug APK build succeeded with AGP 9.4.1 / Gradle 9.7.1
- [x] working Gradle/JVM baseline captured
- [x] trusted Gradle Wrapper 9.7.1 generated and independently validated
- [x] final `0.1.0` clean debug + lint + release assembly succeeded
- [x] lint contains no blocking errors
- [x] daemon criteria resolve to Java 25
- [x] GitHub Actions independently validates the Wrapper and builds debug/lint/release on Ubuntu with Temurin 25
- [x] release-signing identity/keystore remains outside the repository
- [x] exact final `0.1.0` APK built and cryptographic signature verified

### Real-device core path

- [x] exact final signed `0.1.0` APK installs and starts
- [x] Android App Info reports `0.1.0`
- [x] real STR discovery succeeds
- [x] endpoint resolution opens the STR UI
- [x] system-bar layout and full-width WebView geometry verified
- [x] adaptive/themed launcher icon verified
- [x] native back navigation verified
- [x] reconnect after app restart verified
- [x] permission denial/recovery verified
- [x] normal STR controls smoke-tested through the wrapper

### Product/security/documentation/publication

- [x] no unnecessary application dependency added by release hardening
- [x] README identifies upstream STR as a prerequisite and separates upstream from wrapper responsibilities
- [x] public AI transparency distinguishes development assistance from runtime behavior
- [x] top-level WebView navigation is pinned to the selected speaker host/STR ports
- [x] intentional JavaScript/cleartext LAN requirements are documented explicitly
- [x] independent app branding is used
- [x] security/privacy docs match the accepted runtime behavior
- [x] final signed publication APK installed and smoke-tested
- [x] final APK SHA-256 recorded: `5f48f99f604b7d861f305f4fda0b6383188ded6e45dc660d65d66ea68da8a2fe`
- [x] annotated `v0.1.0` tag points to final source commit `4583e27deb734a5ac268af0c16939009f76c0d63`
- [x] GitHub Release `v0.1.0` publishes the signed APK and SHA-256 file

The `0.1.0` release is **ACCEPTED**.
