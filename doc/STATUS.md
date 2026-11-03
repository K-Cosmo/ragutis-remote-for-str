# Status

## Ragutis 0.3.5 — PLAY INTERNAL CANDIDATE (UNRELEASED)

**Development version:** 0.3.5
**Latest public release:** 0.3.4
**Status:** PLAY-001 CERTIFICATES PASS; MERGED MANIFEST COMPARISON PASS; BOTH DEBUG FLAVORS PASS; PRODUCTION/PLAY GATES OPEN
**Date:** 2026-11-03

**Implemented in draft [PR #21](https://github.com/K-Cosmo/ragutis-remote-for-str/pull/21):** `versionName=0.3.5` / `versionCode=16` and the unchanged production `app.strremote.android` identity, a native DE/EN [privacy-policy](PRIVACY.md) link and **two distribution flavors**. The `github` flavor retains the original GitHub APK updater; the `play` flavor disables GitHub update checks and links to the Play listing instead. Both keep shared STR behavior and persisted-data schema. The source adds no permission, SDK, backend or WebView bridge. Signing continuity requires distinct on-device/Play evidence.

**Debug QA (2026-11-03):** The owner reports **6/6 PASS** on the separately installable `app.strremote.android.poc` debug build from exact application-source commit `8ccb4564ee0d08a83527ecb391a1a514dc451d46`: DE/EN privacy links, browser-back navigation, source/Issues/email actions, STR controls and room assignment, and preservation of the separately installed regular v0.3.4/data. [Dated scoped device evidence](evidence/qa/2026-11-03-ragutis-0.3.5-debug-device-qa.md). Neither an original-signer production APK nor the cross-channel Play upgrade has passed this gate.

**Current distribution QA (2026-11-03):** After the prior 6/6 unflavored debug acceptance, two product flavors were introduced. For exact functional source `c17f6a00a5028a49e72321d8e4e21dd5d6f64698`, the owner then reported that **both `githubDebug` and `playDebug` show their requested behavior, content and links**. This supports the scoped dual-flavor UI QA; see [dated owner-reported evidence](evidence/qa/2026-11-03-ragutis-0.3.5-flavor-device-qa.md). Earlier 6/6 QA is separate, not retroactively attributed to these builds. Productive signature, full merged-manifest/Data safety review and actual Play delivery remain OPEN; see [Tests](TESTS.md) and [Release gates](RELEASE_GATES.md).

**PLAY-001 signing acceptance (owner-reported, 2026-11-03):** The Play Console's **in-use app-signing SHA-256** is now `3CC2E7272D8C2D1433A57C4462DF39ADFE5E8C1931A4A924710C0E4087AA1E32`, equal to the GitHub APK and original local PKCS12 `key0`; the **Play upload SHA-256** is `41BFFC7105A5345DF3F2C1C65CBB52595DB5048C4C80A24564C2863331FA7791`, equal to separate local `remote-str-upload`. Package `app.strremote.android`; Play title **Ragutis Remote for STR**. [Certificate evidence](evidence/qa/2026-11-03-play-signing-acceptance.md) preserves earlier F2DC/PEPK troubleshooting. **Signing enrollment gate CLOSED**, but genuine Play-delivered GitHub v0.3.4 → 0.3.5/data-retaining upgrade not yet tested.

**Merged release manifest QA (2026-11-03):** Owner executed both Gradle release-manifest processing tasks successfully and provided the parsed `githubRelease` / `playRelease` manifests. Both had identical `app.strremote.android`, 9 permissions, backup/cleartext/security configuration and exactly the two expected activity export settings. **Scoped manifest consistency PASS**, with [dated evidence](evidence/qa/2026-11-03-merged-release-manifest-qa.md). Source review separately confirms local speaker/room preferences may participate in Android backup and runtime LAN permission is requested on Android 17; these are **not** final backup/Data safety or real device test acceptance.

**Unsigned Play bundle build (2026-11-03):** Owner ran `:app:bundlePlayRelease` locally and reported `BUILD SUCCESSFUL in 19s` (49 actionable tasks: 38 executed, 8 cached, 3 up-to-date). This is **an unsigned Gradle build PASS**, not independent inspection or verification of its output path/content/hash, not production signing, and not a Play Console upload. Subsequent owner-reported ZIP entry inspection found `BundleConfig.pb`, `base/manifest/AndroidManifest.xml`, `base/dex/classes.dex` and `base/resources.pb` (**4/4 PASS**, [evidence](evidence/qa/2026-11-03-play-aab-zip-structure-qa.md)). Owner then reported **bundletool 1.18.3 successfully dumped the packaged base manifest** from `app-play-release.aab` (1,160,743 bytes): package/code/name/minSdk/targetSdk, all nine permission names, backup/cleartext and exported activities matched expectations. [Dated compiled-manifest QA](evidence/qa/2026-11-03-play-aab-bundletool-manifest-qa.md). The Protobuf `sun.misc.Unsafe` warning was nonfatal. **Final follow-up unsigned AAB checks PASS:** SHA-256 `15BA556123614F926EF78AE163F43F89843157E18661187083FE57D1F997DA5E`, compiled `networkSecurityConfig="@xml/network_security_config"` and `maxSdkVersion=32` on both coarse/fine location permissions owner-confirmed. **Unsigned AAB verified; locally signed Play upload-key AAB also owner-reported PASS:** `Ragutis-0.3.5-play-signed.aab`, 1,165,897 bytes, SHA-256 `7B94567B796D13A8747A2184DEE073C4FC7EFD0F89838414306D9DFC03EB33B8`, `jarsigner` PASS, and upload certificate `41BFFC7105A5345DF3F2C1C65CBB52595DB5048C4C80A24564C2863331FA7791` matched Play Console. [Signed QA](evidence/qa/2026-11-03-play-aab-upload-signing-qa.md). **Privacy, production device and Play-delivered upgrade QA remain OPEN.**

**Distribution boundary:** Current public GitHub version is still [v0.3.4](https://github.com/K-Cosmo/ragutis-remote-for-str/releases/tag/v0.3.4) (code 15). This 0.3.5 source has **debug-device acceptance only**; it is **not accepted on a signed-production test device, not merged, not publicly released and not uploaded to Google Play**; locally signed upload-key AAB is ready. The next checks concern production-signed operation and genuine cross-channel data retention; live Play certificate checks and merged-manifest consistency are now PASS; backup/Data safety audit and Play-delivered in-place update remain separate OPEN gates; local signed AAB verification has passed. See [Release gates](RELEASE_GATES.md) and [Play runbook](PLAY_STORE_PREPARATION.md).

## Ragutis 0.3.4 — RELEASED ON GITHUB / SIGNED 6/6 SMOKE PASS

**Released version:** 0.3.4
**Latest public release:** 0.3.4
**Status:** RELEASED / SIGNED DEVICE QA 6/6 PASS (OWNER-REPORTED)
**Date:** 2026-11-03

**Public release:** [v0.3.4](https://github.com/K-Cosmo/ragutis-remote-for-str/releases/tag/v0.3.4) is the latest stable GitHub APK, released after separate owner authorization. Exact signed source: `c68f65f12902e4c484745e004c6af8af87cd85bd`; public APK SHA-256: `7563764c09fe15f5bfe122a05d37964929cb905f7c1a92bf05e896775a5f17f7`. Source tag and public asset digest were verified against the owner's reported local build; the APK bytes and raw device logs were not independently provided.

**Verified scope:** Owner-reported **6/6 signed-device smoke PASS** for in-place upgrade/data retention, manual GitHub check online, immediate repeat, offline failure/recovery, DE/EN interface and STR control. The native manual check shows progress and separates current-version/no-newer from request failure; it does not install APKs. The automatic GitHub check remains subject to its 24-hour successful-request throttle. [Signed build/device evidence](evidence/qa/2026-11-03-ragutis-0.3.4-local-signed-build.md); [release acceptance gates](RELEASE_GATES.md).

**Limitations:** An actual notification for a **future newer stable GitHub release**, extended Android/device/accessibility combinations and [negative-path cases](https://github.com/K-Cosmo/ragutis-remote-for-str/issues/7) have not been certified. The old signed 0.3.2 client may not follow the renamed GitHub API redirect; manual in-place installation is its recovery path. No Google Play AAB or rollout has occurred.

## Google Play Console — PREPARATION ONLY (2026-11-03)

**State:** GitHub v0.3.4/code15 remains the latest accepted public APK. **Unsigned and locally Play-upload-key-signed AAB builds have now been owner-reported verified; neither has been uploaded**, and no internal/closed/production track rollout has been verified. Play Console account/package and both **live** certificate fingerprints were reconfirmed by the owner on 2026-11-03, not accessed directly through a Console integration.

**Current work:** [Play Store preparation](PLAY_STORE_PREPARATION.md) is the sole operational runbook. The distinct 0.3.5/code16 privacy-link candidate is staged **only** in [draft PR #21](https://github.com/K-Cosmo/ragutis-remote-for-str/pull/21); no signed-production device acceptance, merge or Play upload is implied; a locally signed Play upload-key AAB is documented above. Current release/deployment gates are in [Release gates](RELEASE_GATES.md).

## STR upstream integration — MAINTAINER ACCEPTANCE AND LISTING CONFIRMED (2026-11-03)

The owner supplied the STR maintainer's third response, dated 2026-11-03. The maintainer accepted the differentiated **Ragutis** name and the 0.3.3+ native inset integration (system bars and display cutout handled in Android while IME remains available), expressly stating that the removed private CSS override makes the **embedding acceptable to him**. This is **owner-provided correspondence**, not a published blanket license or Bose trademark permission.

The maintainer said the app was now listed on the STR website. Independently verified on 2026-11-03: the official [Community Apps FAQ](https://st-reborn.de/de/#faq-q48) lists **Ragutis Remote for SoundTouch Reborn**, Android 8+, by K-Cosmo, linking to the renamed GitHub repository. The upstream Show-and-tell thread is still requested and **not confirmed published**. These facts close the prior STR-022 native embedding objection and STR-025 listing follow-up; **Google Play policy/Data safety review and the unofficial/no-Bose-affiliation disclosure remain separate open work**.

## Ragutis Remote for SoundTouch Reborn 0.3.3 — RELEASED / ACCEPTED

**Development version:** 0.3.3
**Latest public release:** 0.3.3 (prior version 0.3.2 was branded **Remote for STR**)
**Status:** RELEASED / SIGNED 7/7 OWNER-REPORTED DEVICE SMOKE PASS (DEFERRED QA TRACKED)
**Date:** 2026-11-01

**Owner-requested naming change:** full Android product identity **Ragutis Remote for SoundTouch Reborn**; launcher and native Devices toolbar **Ragutis STR**. About shows the full name; DE/EN helper/update/feedback texts are updated. The independent/unofficial disclosure and trademark statement remain in place.

**STR maintainer's second response (2026-11-01):** acknowledged the previously fulfilled requirements and intended a Community Apps listing; asked for a differentiated name because the former **Remote for STR** was visually confused with the iOS **STR Remote**. The maintainer identified double forwarding of native system-bar insets to the embedded WebView, and proposed consuming handled insets to avoid duplicate CSS `env(safe-area-inset-*)` padding. The maintainer offered future documented `--str-safe-top` / `--str-safe-bottom` variables if still needed.

**Implementation:** the root owns native system-bar/display-cutout padding. On API 30+, the inset values already handled are **zeroed** with `WindowInsets.Builder` before forwarding, retaining IME updates; on API 26–29, legacy insets are consumed. Android's previous guarded private-DOM `10px` header CSS injection and its source/test have been removed. STR retains its original normal `16px + env(safe-area-inset-top)` behavior. No upstream CSS feature is claimed implemented or agreed.

**Compatibility:** keep `applicationId=app.strremote.android`, original signing certificate, local device/room data and immutable v0.3.2 APK. Only the active GitHub project/release/issue URLs change to the canonical renamed repository. Candidate `versionCode=14` / `versionName=0.3.3` was **signed and installed for owner-reported successful in-place device QA, and publicly released on 2026-11-01**. Device acceptance covers the seven reported smoke categories only. GitHub APK update matching accepts new `Ragutis-Remote-for-SoundTouch-Reborn-<version>.apk` plus both previous exact release naming conventions.

**User-reported first debug QA (2026-11-01):** all seven requested categories PASS on the Ragutis 0.3.3 *debug* build: product/launcher/About names, top/bottom STR WebView spacing, controls, system-bar navigation, keyboard/orientation/themes, and bilingual setup. This is owner-reported real-device QA of the **earlier** PR head, not an independently viewed screenshot, not API 26–29 certification and **not a signed 0.3.2→0.3.3 upgrade test**.

**Second-round 0.3.3 development:** the project feedback recipient is changed centrally to a new owner-provided mailbox using the existing reversible character-code construction (no complete address in public source/docs). Both native About and SoundTouch model-feedback drafts use this value. The room picker now adopts the wizard's scrollable, two-line rounded card presentation, blue selected-row background and accessible row labels; existing room assignments are highlighted and custom room editing is retained. A testable `RoomPickerPolicy` preserves preset order and `null`/custom mapping. These changes passed exact-source CI and are included in the owner's signed 7/7 category-level smoke test, covering feedback and rooms. Not every individual model/room and negative edge case is independently certified. No backend, permission, saved-data schema or original app-signing change.

**Wizard UX follow-up from owner screenshots (2026-11-01):** the three screenshots show duplicated „geprüft“ suffixes for checked models and no intermediate status text for Other; this is targeted UI evidence, not full process QA. Development now orders the model dropdown as **SoundTouch 10 → SoundTouch 20 III → Anderes SoundTouch-Modell**, removes tested qualifiers from dropdown labels, retains the approved test notice below the selector and adds a concise unverified-model notice at the same location for Other. The detailed unverified instructions stay in the existing card and optional failure-feedback button remains. The owner reports the final signed 0.3.3 WLAN/UI smoke category PASS, including DE/EN; exhaustive per-model screenshots and every edge permutation were not provided. The previously reported 7/7 first-round QA and later Room/Email code CI are historical to earlier commits, not proof of this new source.

**Latest wizard refinement (owner request, 2026-11-01):** initial selection is now **none** rather than silently preselecting SoundTouch 20 III. The former standalone model heading is replaced by the disabled placeholder **SoundTouch-Modell auswählen** (EN: **Select SoundTouch model**) within the spinner. The preparation action and model instructions remain disabled/hidden until an actual model choice. A deliberately chosen model persists through legitimate retry and configuration restoration, including the unchanged enum ID. The status hint beneath the dropdown gains 14dp side padding to align with both selector/card text. Unit checks for empty/invalid/restored choices and the spinner index shift passed CI; the owner reports the signed WLAN wizard smoke category PASS, while every retry/rotation/large-font variant is not individually verified.

**CI evidence:** candidate source `08ca3d5c3025fbce7df5130fa8961a00713fa954` passed [push CI 41306285299](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41306285299) and [PR CI 41306330468](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41306330468), including documentation consistency, Android build and unit tests. This is static/build evidence only, not a signed device release.

**Current second-round CI evidence:** functional code head `37b93dc38e971d51d9c8d9af1b2e3aff0077b12d`, [push CI 41310056025](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41310056025) and [PR CI 41310061181](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41310061181) both **PASS** with Android build/lint/unit and documentation/privacy checks. This evidence does not certify the new mail drafts/room UI on a real phone or signed in-place update.

**Owner decision and release (2026-11-01):** PRs #13/#14 merged into app source `44bc6d0c518c9c0dbb01bf109589f3b5cbd29d65`, repo renamed, owner-reported signed in-place QA 7/7 PASS and then **separately approved the public GitHub release**. Stable `v0.3.3`, exact tag/source/asset/checksum and latest-release API were independently verified. The STR maintainer email, Google Play track and community announcement have **not** been sent/opened as a side effect.

**Next work:** notify STR maintainer and request Community Apps listing update; prepare a Google Play testing AAB from the released Ragutis app source, using the **distinct** Play upload certificate, and coordinate separate Google Forms/closed-test tester onboarding and Show-and-tell. Deeper compatibility/negative-path testing remains separately deferred, not universal PASS. Old Play-only PR #12 is superseded and must not be uploaded.

The sections below record previously published releases and intentionally retain their original historical product names and evidence.

---

## Remote for STR 0.3.2 — RELEASED / ACCEPTED

**Development version:** 0.3.2
**Latest public release:** 0.3.2
**Status:** RELEASED / ACCEPTED
**Date:** 2026-10-31

**Historical v0.3.2 signed public release:** [Remote for STR 0.3.2](https://github.com/K-Cosmo/ragutis-remote-for-str/releases/tag/v0.3.2), published **2026-10-31 at 15:29:07 UTC (16:29:07 CET)**. GitHub Release ID `430018074`, `draft=false`, `prerelease=false`, was returned by `releases/latest` until v0.3.3 became the latest stable release on 2026-11-01. The prior public v0.3.1 release remains unchanged.

### Release provenance

- **Exact signed and on-device-tested source:** `547fe4e52935bac03ddb0c3eb1f1ca4c7e517323`; annotated `v0.3.2` tag object `a076305a65f9207c2df36acfedf5ba94c6e576a9` resolves to that commit. The Git tag is **not cryptographically signed**; APK signing is separate.
- **Merge and CI:** [PR #9](https://github.com/K-Cosmo/ragutis-remote-for-str/pull/9) normal merge `d937365e6187fefe1d097aacb2d68b4ae8b393f6` preserved the tested source in history. The post-merge [main CI](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41214563587) passed.
- **Version:** `versionName=0.3.2`, `versionCode=13`, production `applicationId=app.strremote.android` unchanged. Android launcher **Remote STR**; project name/native title **Remote for STR**.
- **Public APK:** [`Remote-for-STR-0.3.2.apk`](https://github.com/K-Cosmo/ragutis-remote-for-str/releases/download/v0.3.2/Remote-for-STR-0.3.2.apk), size **1,197,701 bytes**, GitHub Asset ID `695738568`, GitHub-reported SHA-256 `19b0cf987351fde11ad76c9c61d5334784dbeb5466f98e5b5602ea91549e10ec` exactly matches the local expected/tester-verified signed artifact.
- **Companion checksum:** [`Remote-for-STR-0.3.2.apk.sha256.txt`](https://github.com/K-Cosmo/ragutis-remote-for-str/releases/download/v0.3.2/Remote-for-STR-0.3.2.apk.sha256.txt), GitHub Asset ID `695738567`, 92 bytes, GitHub-reported asset digest `sha256:aac04dbe6760d4f7cb406b1feb429143f1fdf7b19afe45b9c854330dae97f645`. The user reports successfully re-downloading and checking both public assets.
- **Local verification (tester-executed):** existing signed APK and checksum file PASS; `apksigner verify`, historic signer certificate SHA-256 `3cc2e7272d8c2d1433a57c4462df39adfe5e8c1931a4a924710c0e4087aa1e32`, and 16 KiB `zipalign` PASS. User also confirmed all **six** device QA scenarios, including 0.3.1→0.3.2 upgrade with retained speakers/rooms and STR controls. This is user-supplied verification output, not an independent APK binary inspection by the assistant.
- **Changes:** compact launcher label, centralized runtime-decoded optional-feedback email recipient, deterministic source/docs guard against complete mailbox literals. Address obfuscation is not encryption.

**Still not certified:** no-email-app fallback, denied WLAN permission, failures/timeouts, untested Bose firmware/hardware configurations, and a live positive update-alert test against the newly released version. These remain separately tracked under [STR-027](https://github.com/K-Cosmo/ragutis-remote-for-str/issues/7), not recorded as PASS.

Detailed [0.3.2 publication evidence](evidence/qa/2026-10-31-release-0.3.2.md), [release gates](RELEASE_GATES.md) and [BUILD-0014](work/BUILD-0014.md).


## Remote for STR 0.3.1 — RELEASED / ACCEPTED

**Release date:** 2026-10-31

BUILD-0013 was merged via [PR #6](https://github.com/K-Cosmo/ragutis-remote-for-str/pull/6) and publicly released as **[Remote for STR 0.3.1](https://github.com/K-Cosmo/ragutis-remote-for-str/releases/tag/v0.3.1)** on 2026-10-31 at **09:56:33 UTC** (10:56:33 CET). GitHub Release ID `429849089`, `draft=false`, `prerelease=false`, currently returned by `releases/latest`.

### Release provenance

- **Signed-source commit:** `0e6bab9d9e1bcac19b542755b7a1774c69d2776a` (distinct from the later merge/docs commits); `v0.3.1` is an **annotated tag** with tag-object `dd0480df720d805b0c6b76fd20757b59d0ee5cfe`, verified to target the exact signed source.
- **Version:** `0.3.1` / versionCode `12`; production `applicationId=app.strremote.android` unchanged.
- **Published APK:** [`Remote-for-STR-0.3.1.apk`](https://github.com/K-Cosmo/ragutis-remote-for-str/releases/download/v0.3.1/Remote-for-STR-0.3.1.apk), 1,197,701 bytes, GitHub Asset ID `694988694`.
- **Published APK SHA-256:** `8100334f067346c9987a68969bdebd9065455bfe73c21a0634f0dba42ddbd759`, matching the signed local build and GitHub's independent reported asset digest.
- **Checksum asset:** `Remote-for-STR-0.3.1.apk.sha256.txt`, GitHub Asset ID `694988695`, published and uploaded.
- **Signer:** the release script enforces the historic production certificate SHA-256 `3cc2e7272d8c2d1433a57c4462df39adfe5e8c1931a4a924710c0e4087aa1e32` and signature/zipalign verification.
- **CI:** exact signed-source [push 41146044403](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41146044403) and [PR 41146049240](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41146049240) passed; final PR runs `41189139487` and `41189143522` passed; post-merge `main` runs `41189328176` and `41189408057` passed. Normal merge commit `ed7652783a9752e107449a572c5b6f2dff624922` preserved signed-source ancestry.
- **Device acceptance (tester-reported):** six defined checks passed, including the signed v0.3.0 → v0.3.1 in-place update with stored speakers/rooms, control, DE/EN wizard, email composer and restart.
- **Explicitly untested/deferred:** no-email-handler, denied WLAN permission, network failures, unverified Bose configurations and positive future-version update notification; tracked under [STR-027](https://github.com/K-Cosmo/ragutis-remote-for-str/issues/7). **Deferral is not test success.**
- **Privacy provenance:** ordinary branch/tag historical rewrite completed earlier; GitHub-managed legacy PR refs/caches remain a separate cleanup item. Historic signed release assets were not rewritten.

See [0.3.1 publication evidence](evidence/qa/2026-10-31-release-0.3.1.md), [signed-device candidate evidence](evidence/qa/2026-10-31-release-candidate-0.3.1.md), and [release gates](RELEASE_GATES.md).

## 0.3.0 release — ACCEPTED

BUILD-0012 Bose SoundTouch WLAN-Einrichtung is released and accepted.

Release identity:

- version: `0.3.0` / versionCode `11`; package `app.strremote.android`;
- historical APK was signed from pre-rewrite source (original identity retained in encrypted offline mirror); rewritten tag source: `edc64cd5468e60edff355ec1dd1fd73c338004af` (the signed APK was not rebuilt);
- current rewritten annotated tag `v0.3.0` object: `2e0c29414f71ae711b410b8bfe0b1e370b0aa14b`; this is **not** the original signing-time tag identity;
- public [GitHub Release 0.3.0](https://github.com/K-Cosmo/ragutis-remote-for-str/releases/tag/v0.3.0), release ID `429390125`, published 2026-10-30T19:03:45Z;
- signed APK `STR-Remote-0.3.0.apk`, size 1,156,741 bytes, SHA-256 `c4b1989e00ba21915e34192b2f4e0b73695416c93397e4f0c4d3cc6617a18290`;
- companion checksum asset `STR-Remote-0.3.0.apk.sha256.txt`;
- original release-signing certificate SHA-256 `3cc2e7272d8c2d1433a57c4462df39adfe5e8c1931a4a924710c0e4087aa1e32`.

Acceptance:

- exact release-source `main` CI: success, run [41108758228](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41108758228);
- exact release-source `v0.3.0` tag CI: success, run [41111993535](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41111993535);
- local signing script reported matching original signing certificate, signature verification and alignment checks;
- user confirmed that the signed production APK passed the planned smartphone acceptance test;
- public APK asset SHA-256 is an exact match to the locally signed SHA-256;
- production STR control remains compatible with Android 8+, the new WLAN assistant requires Android 10+;
- SoundTouch 20 III setup verified on the reference original firmware; SoundTouch 10 original-firmware WLAN setup remains experimental.

See [release evidence](evidence/qa/2026-10-30-release-0.3.0.md) and [release gates](RELEASE_GATES.md).

## 0.2.1 release

BUILD-0011 is released and accepted.

Release identity:

- current rewritten tag source commit: `8afa46d25e5ec2be10bf84c07df071eb555b0ed2` (signed APK predates history rewrite);
- annotated tag: `v0.2.1`;
- rewritten annotated tag object: `f3b020247bb195ee1c9c9d326c8590aa75743ff6`;
- final APK: `STR-Remote-0.2.1.apk`;
- APK SHA-256: `bf019801b4a47226ab1af616f4f74f1dd7abfb71731205253e1d9709af76de67`;
- signer: `C=DE, CN=STR Remote`;
- signer certificate SHA-256: `3cc2e7272d8c2d1433a57c4462df39adfe5e8c1931a4a924710c0e4087aa1e32`;
- public GitHub release ID: `400078904`;
- publication time: `2026-09-30T13:33:15Z`.

Verified release gates:

- local documentation consistency: PASS;
- clean debug build + lint + release assembly: PASS;
- GitHub Android CI green for the exact release source commit on `main` (run `36721458188`);
- GitHub Android CI green for the pushed `v0.2.1` tag (run `36722290992`);
- real-device UX/network smoke for the final 0.2.1 implementation: PASS;
- compact Remote header and stable saved-speaker card geometry: PASS;
- persisted Online/Offline state and changed-result-only color transitions: PASS;
- no-Wi-Fi guidance plus direct Wi-Fi settings action on Remote and Devices: PASS;
- Wi-Fi return / endpoint probe / Retry recovery path: PASS;
- selected-device endpoint probing prevents the Chromium error page from becoming the normal user-facing transition: PASS;
- signed APK built from clean committed source;
- APK Signature Scheme v2/v3 verification: PASS;
- post-sign 16 KiB-aware zipalign verification: PASS;
- annotated `v0.2.1` tag resolves to exact release source `6a3352928dc177419f2f5d7f5bc49868e9ab0589`;
- GitHub release is published, non-draft and non-prerelease;
- public APK asset digest matches the accepted local artifact.

0.2.1 extends the accepted 0.2.0 speaker-management baseline with:

- a more compact native Remote header;
- explicit and accessible saved-speaker Online/Offline presentation;
- persistent last-known reachability used while background validation runs;
- stable device-card layout during revalidation;
- native no-Wi-Fi guidance on both major app views;
- direct Android Wi-Fi settings access;
- endpoint-first reconnect/device-selection behavior before WebView presentation.

The existing app-private preferences store is extended with last-known reachability state; no new persistence dependency/model, permission, cloud service, analytics backend, playback/business logic, Gradle or AGP upgrade is introduced.

## 0.2.0 release

Speaker Management / Rooms is released and accepted.

Release identity:

- current rewritten tag source commit: `945bb9d7cfaa9f737fd6fb8d24780ada571249f0` (signed APK predates history rewrite);
- annotated tag: `v0.2.0`;
- final APK: `STR-Remote-0.2.0.apk`;
- APK SHA-256: `5e7ba21f39752ae359a6de0103a440b363a1823223874778e44173f285243aa7`;
- signer: `C=DE, CN=STR Remote`;
- signer certificate SHA-256: `3cc2e7272d8c2d1433a57c4462df39adfe5e8c1931a4a924710c0e4087aa1e32`;
- public GitHub release ID: `399416342`;
- publication time: `2026-09-29T18:27:43Z`.

## Verified release gates

- local documentation consistency: PASS;
- clean debug build + lint + release assembly: PASS;
- GitHub Android CI green for the exact release source commit and release tag;
- signed APK built from clean committed source;
- APK Signature Scheme v2/v3 verification: PASS;
- post-sign 16 KiB-aware zipalign verification: PASS;
- annotated `v0.2.0` tag resolves to the exact release source;
- public GitHub APK digest matches the accepted local artifact;
- exact signed-APK device smoke: PASS.

## Accepted product baseline

0.2.0 includes:

- finite Wi-Fi-aware STR discovery/recovery;
- persistent saved speakers and quick switching;
- built-in/custom room assignment;
- explicit room/delete device actions;
- manual-host fallback;
- current-device/room presentation and direct Remote return path;
- dismissible local device-management guidance;
- constrained STR WebView wrapper with no cloud/account/analytics backend.

Accepted build baseline:

- minSdk 26;
- compile/target API 37;
- AGP 9.4.1;
- Gradle 9.7.1;
- Java 25 Gradle runtime criteria;
- Java source/target 17.

## Documentation baseline

Post-0.2.0 documentation drift has been converged:

- `/doc` remains the only normative documentation;
- root duplicate changelog is removed;
- root privacy/contribution files are pointers rather than duplicate policy;
- current architecture, compatibility, blocker, privacy, security and test documents describe the accepted 0.2.0 baseline;
- historical build/spec/evidence records remain unchanged as dated snapshots.

`0.3.0` remains an accepted **historical** public release; the current release is **0.3.1** (see the release identity and acceptance evidence above).


These dated paragraphs describe pre-release testing and are superseded by the accepted 0.3.1 release evidence above.

## Historical development notes

BUILD-0013's dated preparation, UI, locale and failed-test history is preserved in [BUILD-0013](work/BUILD-0013.md) and [the signed-candidate QA record](evidence/qa/2026-10-31-release-candidate-0.3.1.md). These are not current release blockers or pending acceptance requirements; current public status is defined at the top of this document.
