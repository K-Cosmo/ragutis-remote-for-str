# Status

## Ragutis 0.3.3 — merged development source, release not yet published (2026-11-01)

**Actual GitHub repository:** [K-Cosmo/ragutis-remote-for-str](https://github.com/K-Cosmo/ragutis-remote-for-str), renamed by the owner on 2026-11-01. **Source merge completed:** PR #13 into `main` as `f821602e1b45172610a7735f6e0830de4bfdee79`, [post-merge CI PASS](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41315289069). This does not authorize or prove an APK publication.

**Signed release status:** Latest public GitHub Release remains `v0.3.2` with its original signed APK; v0.3.3 (`versionCode 14`) is source-only. A new migration PR changes the 0.3.3 release-API, About/source and Issues URLs to the **new canonical slug** and allows documented HTTPS redirects. This is a source/runtime link migration, not an Android package rename; `app.strremote.android` and original release-signing certificate remain unchanged.

**Unavoidable old-client limitation:** published 0.3.2 hardcodes the former GitHub Releases API URL and sets `instanceFollowRedirects=false`. Following the repository rename, the old API may return HTTP 301/307 which 0.3.2 does not follow; therefore its update notification may fail. Manual download and **in-place** install from the new repository can still work if the new APK uses the same app-signing key and higher versionCode. This requires a real device test; never claim repaired 0.3.2 behavior.

**Immediate release gates:** migration PR CI and merge, original-signer signed APK built from **final clean main**, SHA-256/zipalign/apksigner verification, signed v0.3.2→v0.3.3 device upgrade retaining rooms/speakers, and final DE/EN wizard/mail/room UX smoke checks. Then publish v0.3.3 under the already renamed GitHub repository and record release evidence. No APK signing/Google Play upload/STR-owner email has happened in this workflow.

## Ragutis Remote for SoundTouch Reborn 0.3.3 — DEVELOPMENT / NOT RELEASED

**Development version:** 0.3.3
**Latest public release:** 0.3.2 (historical brand **Remote for STR**)
**Status:** IMPLEMENTED / SECOND-ROUND PHONE QA PENDING
**Date:** 2026-11-01

**Owner-requested naming change:** full Android product identity **Ragutis Remote for SoundTouch Reborn**; launcher and native Devices toolbar **Ragutis STR**. About shows the full name; DE/EN helper/update/feedback texts are updated. The independent/unofficial disclosure and trademark statement remain in place.

**STR maintainer's second response (2026-11-01):** acknowledged the previously fulfilled requirements and intended a Community Apps listing; asked for a differentiated name because the former **Remote for STR** was visually confused with the iOS **STR Remote**. The maintainer identified double forwarding of native system-bar insets to the embedded WebView, and proposed consuming handled insets to avoid duplicate CSS `env(safe-area-inset-*)` padding. The maintainer offered future documented `--str-safe-top` / `--str-safe-bottom` variables if still needed.

**Implementation:** the root owns native system-bar/display-cutout padding. On API 30+, the inset values already handled are **zeroed** with `WindowInsets.Builder` before forwarding, retaining IME updates; on API 26–29, legacy insets are consumed. Android's previous guarded private-DOM `10px` header CSS injection and its source/test have been removed. STR retains its original normal `16px + env(safe-area-inset-top)` behavior. No upstream CSS feature is claimed implemented or agreed.

**Compatibility:** keep `applicationId=app.strremote.android`, original signing certificate, local device/room data and immutable v0.3.2 APK. Only the active GitHub project/release/issue URLs change to the canonical renamed repository. New candidate `versionCode=14` / `versionName=0.3.3` is **not signed, published, or installed as a verified release**. GitHub APK update matching accepts new `Ragutis-Remote-for-SoundTouch-Reborn-<version>.apk` plus both previous exact release naming conventions.

**User-reported first debug QA (2026-11-01):** all seven requested categories PASS on the Ragutis 0.3.3 *debug* build: product/launcher/About names, top/bottom STR WebView spacing, controls, system-bar navigation, keyboard/orientation/themes, and bilingual setup. This is owner-reported real-device QA of the **earlier** PR head, not an independently viewed screenshot, not API 26–29 certification and **not a signed 0.3.2→0.3.3 upgrade test**.

**Second-round 0.3.3 development:** the project feedback recipient is changed centrally to a new owner-provided mailbox using the existing reversible character-code construction (no complete address in public source/docs). Both native About and SoundTouch model-feedback drafts use this value. The room picker now adopts the wizard's scrollable, two-line rounded card presentation, blue selected-row background and accessible row labels; existing room assignments are highlighted and custom room editing is retained. A testable `RoomPickerPolicy` preserves preset order and `null`/custom mapping. These **new changes require fresh CI and device QA**, particularly both email actions, preset/custom assignment and persistence. No backend, permission, saved-data schema or original app-signing change.

**Wizard UX follow-up from owner screenshots (2026-11-01):** the three screenshots show duplicated „geprüft“ suffixes for checked models and no intermediate status text for Other; this is targeted UI evidence, not full process QA. Development now orders the model dropdown as **SoundTouch 10 → SoundTouch 20 III → Anderes SoundTouch-Modell**, removes tested qualifiers from dropdown labels, retains the approved test notice below the selector and adds a concise unverified-model notice at the same location for Other. The detailed unverified instructions stay in the existing card and optional failure-feedback button remains. DE/EN screenshot validation of this **new** change is pending. The previously reported 7/7 first-round QA and later Room/Email code CI are historical to earlier commits, not proof of this new source.

**Latest wizard refinement (owner request, 2026-11-01):** initial selection is now **none** rather than silently preselecting SoundTouch 20 III. The former standalone model heading is replaced by the disabled placeholder **SoundTouch-Modell auswählen** (EN: **Select SoundTouch model**) within the spinner. The preparation action and model instructions remain disabled/hidden until an actual model choice. A deliberately chosen model persists through legitimate retry and configuration restoration, including the unchanged enum ID. The status hint beneath the dropdown gains 14dp side padding to align with both selector/card text. Unit checks for empty/invalid/restored choices and the spinner index shift are staged; **new on-phone QA is pending**.

**CI evidence:** candidate source `08ca3d5c3025fbce7df5130fa8961a00713fa954` passed [push CI 41306285299](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41306285299) and [PR CI 41306330468](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41306330468), including documentation consistency, Android build and unit tests. This is static/build evidence only, not a signed device release.

**Current second-round CI evidence:** functional code head `37b93dc38e971d51d9c8d9af1b2e3aff0077b12d`, [push CI 41310056025](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41310056025) and [PR CI 41310061181](https://github.com/K-Cosmo/ragutis-remote-for-str/actions/runs/41310061181) both **PASS** with Android build/lint/unit and documentation/privacy checks. This evidence does not certify the new mail drafts/room UI on a real phone or signed in-place update.

**Owner decision and actual state (2026-11-01):** Ragutis PR #13 has been merged into `main`, and GitHub repository rename to `K-Cosmo/ragutis-remote-for-str` has been completed. The **source merge and repo rename are not a signed APK release**. Signing, real 0.3.2→0.3.3 in-place upgrade, checksums, target URL verification and final device QA remain pending. No signed 0.3.3, GitHub tag/Release, email to maintainer or Google Play track upload is claimed.

**Next gates:** native header and WebView device testing (API 26–29 / 30+, Android 16/17, status/navigation bars, cutout, keyboard/IME, rotation and STR control clicks), signed in-place upgrade from GitHub 0.3.2 keeping speakers/rooms, DE/EN brand/feedback About checks, upstream Show-and-tell communication, and explicit owner approval for merge/publication. Play planning PR #10 and the old Play-only version candidate PR #12 are **on hold**; an AAB must be based on this renamed/tested code, not the outdated Play-only candidate.

The sections below record previously published releases and intentionally retain their original historical product names and evidence.

---

## Remote for STR 0.3.2 — RELEASED / ACCEPTED

**Development version:** 0.3.2
**Latest public release:** 0.3.2
**Status:** RELEASED / ACCEPTED
**Date:** 2026-10-31

**Latest signed public version:** [Remote for STR 0.3.2](https://github.com/K-Cosmo/ragutis-remote-for-str/releases/tag/v0.3.2), published **2026-10-31 at 15:29:07 UTC (16:29:07 CET)**. GitHub Release ID `430018074`, `draft=false`, `prerelease=false`, returned by `releases/latest`. The prior public v0.3.1 release remains unchanged.

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
