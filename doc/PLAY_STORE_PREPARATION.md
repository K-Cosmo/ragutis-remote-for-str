# Google Play — Ragutis Remote for SoundTouch Reborn

**Stand: 2026-11-03. Preparation / no Play AAB uploaded / no internal or closed track rollout / no Play Store listing published.**

This is the current **proposed** runbook, derived from current repository code and the previously owner-reported Google Play signing checks recorded in historical drafts [PR #10](https://github.com/K-Cosmo/ragutis-remote-for-str/pull/10) and [PR #12](https://github.com/K-Cosmo/ragutis-remote-for-str/pull/12). Those PRs are historical/superseded and **must not be merged or uploaded unchanged**. Official Play Console state is not independently accessible through this repository. Avoid claiming that a private-account configuration step, Play asset, tester count or production permission has been completed without new Console evidence.

## 1. Source, package, key and version identity

| Property | Checked or owner-reported value |
|---|---|
| Current public GitHub APK | [Ragutis v0.3.4](https://github.com/K-Cosmo/ragutis-remote-for-str/releases/tag/v0.3.4), `versionCode=15`, owner-signed six-case device QA PASS |
| Exact signed v0.3.4 app source | `c68f65f12902e4c484745e004c6af8af87cd85bd` (tag points here; subsequent docs merges are not signed source) |
| Android app package | `app.strremote.android`, **do not change** |
| Version configuration | `minSdk=26`, `compileSdk=37`, `targetSdk=37` |
| GitHub app signing / Google Play **app-signing** certificate | SHA-256 `3CC2E7272D8C2D1433A57C4462DF39ADFE5E8C1931A4A924710C0E4087AA1E32`; owner previously confirmed same certificate in Play Console after PEPK import |
| Separate Google Play **upload** certificate | SHA-256 `41BFFC7105A5345DF3F2C1C65CBB52595DB5048C4C80A24564C2863331FA7791`; **local** `remote-str-upload` JKS owner-verified 2026-11-03, but the new Play app's **live upload certificate is still unverified** |
| Previous key alias | `remote-str-upload` for the separate upload JKS, owner-local (never commit/store keystore/passwords/PEPK export) |
| Public Google Play artifact | **None verified**; first AAB/track still to prepare |

**P0 — Play signing identity NOT MATCHING original GitHub APK in currently reported certificates (2026-11-03). NO AAB UPLOAD.** Owner confirmed uploading `remote-str-encrypted-key.zip` (PEPK-protected key export prepared earlier) and `upload_certificate.pem` (public separate upload certificate) when creating the Play entry **Ragutis Remote for STR**; these are the intended types of files. On the current Console's **App-Signaturschlüssel — In Verwendung** panel, SHA-256 is `F2DCDD5F6B016D2F2E8B02B108249EF05D6A427636176C801E8E43EA37F03E69`, unlike original public GitHub app signer `3CC2E7272D8C2D1433A57C4462DF39ADFE5E8C1931A4A924710C0E4087AA1E32`. Under **Bisherige App-Signaturschlüssel**, a 2026-11-03 entry is labelled **Quantenbereit (Beta)**; the owner also reported classical SHA-256 `D0FDE60FBC832F10269DF28BE27D0E6528123E793BFADEC3D03E893A0148493C` and PQC SHA-256 `803406BF1E06D71F53231C9A0391E9CEE102E4052FA8EE1FCD8AA5250C8DD2B2`. **None of these three reported Play SHA-256 fingerprints matches the original signer.** Google's hybrid signing legitimately has distinct classic/PQC keys on Android 17+, but *does not establish* a matching Android 16-and-earlier key in the supplied screenshot. The report thus gives strong reason to hold cross-store migration; it does not independently prove what is inside the encrypted PEPK ZIP.

**Original release keystore NOW OWNER-VERIFIED 2026-11-03:** `PKCS12` (SUN), **alias `key0`**, created **2026-09-26**, self-signed `CN=STR Remote, C=DE`, RSA **2048**, SHA-256 **`3CC2E7272D8C2D1433A57C4462DF39ADFE5E8C1931A4A924710C0E4087AA1E32`**, matching the public GitHub APK signer exactly. This establishes that the correct original app-signing private key is still present locally. It does **not** establish which private key the earlier PEPK ZIP contained, or which Google accepted. The previous upload key is independently a separate RSA 4096 key with alias `remote-str-upload`; it must not be used for PEPK app-key import.

**Next safe action:** In the current **Schlüssel ändern → Einen Schlüssel aus dem Java KeyStore exportieren und hochladen** form, download the current encryption public key and PEPK tool as directed. Generate a **new** encrypted ZIP locally from the **original PKCS12 keystore with `--alias=key0`**, using the Console-provided command with `--include-cert`, `--rsa-aes-encryption`, and `--encryption-key-path` (do not use `remote-str-upload` or expose passwords); upload only under this explicit new-app signing-key replacement workflow while the app remains eligible, then verify the shown **in-use** signing certificate SHA-256 equals `3CC2…`. Keep upload PEM registration separate; do not submit an AAB or publish a Play track while this is unresolved. [Official new-app signing guide](https://support.google.com/googleplay/android-developer/answer/9842756?hl=de).

**Immediate diagnostic:** The owner should first read the **Schlüssel ändern** screen without committing any change and check if **Kopie des eigenen App-Signaturschlüssels bereitstellen** is offered. Verify the original *local production GitHub APK* signing keystore/alias (read-only `keytool -list -v`) against expected `3CC2E727…`; do not confuse it with separate upload JKS `remote-str-upload` (`41BFFC71…`). Only after verifying the source of the PEPK-encrypted export, follow Google's **new app** procedure to select the correct private app signer and encrypt/upload as directed by the live Console. Recheck both the **in-use** app signer and separately registered upload certificate SHA-256 and package `app.strremote.android`. If no option is available, stop and consult official Play Console support. **Do not use a regular key upgrade as a workaround without understanding Android-version continuity; don't upload an AAB or start a track until resolved.** The owner reports a new app with **0.0% installation base**, so no Play installs are reported in that screenshot; this does not establish definitive track history. [Google Play official app-signing setup](https://support.google.com/googleplay/android-developer/answer/9842756?hl=de) and [Android signing guide](https://developer.android.com/studio/publish/app-signing).

**Version strategy:** Public GitHub v0.3.4 already has `versionCode=15`. A Play internal candidate with the same code can be installed on a fresh device or tested over an older code (for example 0.3.3/14) if the app-signing key matches, but **Google Play does not treat installed code 15 as outdated when the Play bundle is also code 15**. For a meaningful Google-Play-managed in-place update on the owner's currently installed v0.3.4, **draft PR #21 already stages** a distinct internal candidate with `versionCode=16` / `versionName=0.3.5`; it is **not merged, signed or uploaded**. Do not rebuild the already published v0.3.4 tag as code 16 or claim Play 0.3.5 is a public GitHub release. Exact version choice, signing and Play internal rollout require separate approval and device QA. [Official update semantics](https://developer.android.com/google/play/app-updates).

**Build variant and publication plan:** `:app:assembleGithubDebug` / `:app:assemblePlayDebug` make test APKs; CI also builds an **unsigned** `:app:assembleGithubRelease` APK and an **unsigned** `:app:bundlePlayRelease` AAB. The existing `tools/build-signed-release.ps1` must select **only** the GitHub release APK and verify the original APK certificate. Later locally sign the Play **AAB with the registered separate upload key** and verify `jarsigner` and SHA-256 before an owner-approved upload. Both **production** variants retain `app.strremote.android` and code16 in this candidate; do not publish the GitHub code16 variant before a separately reviewed version-code/release-order decision. The public GitHub release stays v0.3.4/code15.

**Device baseline preservation for the cross-channel test:** Keep at least one real device on the existing **GitHub v0.3.4/code15**, with its saved speakers/rooms intact, until it receives **Play-delivered v0.3.5/code16**. The separate debug `app.strremote.android.poc` package can be installed alongside production v0.3.4 for UI/link QA without consuming this upgrade path. For local **production-signed 0.3.5** device checks, use a **second test device**: installing code16 on the sole code15 baseline would prevent a genuine same-code Play-managed upgrade, while downgrading/uninstalling risks stored data. Do not clear app data to fake continuity. Exact acceptance cases remain in [Tests](TESTS.md).

## 2. Account / Console state (reported, still to reconfirm)

- Owner paid developer registration in September 2026 and created new Play Console entry **`Ragutis Remote for STR` on 2026-11-03**, reportedly uploading earlier prepared encrypted-key ZIP and upload certificate PEM. Console screenshot shows **in-use** app signer `F2DCDD5F…` and previously used quantum-ready (Beta) signing history; provided quantum-ready classical `D0FDE60F…` and PQC `803406BF…` are also **not** original GitHub `3CC2E727…`. Screenshot's Digital Asset Links JSON confirms package **`app.strremote.android`**. Source-keystore/PEPK-to-current-registration mapping and the current Play **upload** certificate must be verified before signing/upload.
- Default language: choose DE or EN deliberately; provide both human-reviewed German and English listing content.
- App, free; target adult users of an unofficial community companion; do not claim affiliation with Bose or the STR project.
- Confirm developer/account verification, developer contact and required publicly visible Play support email. Do not put a literal feedback email address into this public repository (project anti-harvest policy); a real store contact **will** be public in Google Play.
- **No Play action in this runbook creates approval for production.** Internal testing, closed testing and production use separate release gates.

## 3. P0 policy and runtime review **before closed testing**

### Privacy policy in app and on Play (device/URL gate open)

Google requires a public privacy policy linked **in the app and on the Play listing**. The current bilingual [public policy](PRIVACY.md) has already been corrected to describe local device/room metadata, `android:allowBackup=true`, GitHub requests, embedded STR traffic and optional user-triggered mail. The **published GitHub v0.3.4 app still lacks the native About link**; the **unreleased 0.3.5/code16 draft [PR #21](https://github.com/K-Cosmo/ragutis-remote-for-str/pull/21)** stages a labeled DE/EN link. The owner reported **6/6 debug-device QA PASS** for source `8ccb4564ee0d08a83527ecb391a1a514dc451d46`, including DE/EN privacy-link opening; see [dated evidence](evidence/qa/2026-11-03-ragutis-0.3.5-debug-device-qa.md). This satisfies the scoped **debug** test, not the PR merge, original-signer production test, independently validated worldwide/no-login URL or Play Console policy acceptance. Those gates remain open. The final URL must be readable without login or download. No complete feedback address belongs in this repository. [Official privacy requirements](https://support.google.com/googleplay/android-developer/answer/9859455?hl=en).

### Data safety, network and user data (BLOCKER before closed/production)

- Android manifest currently declares `INTERNET`, Wi-Fi/network state, API-limited coarse/fine location (max 32), `NEARBY_WIFI_DEVICES`, `ACCESS_LOCAL_NETWORK` and an `android:allowBackup=true` setting; review the **actual merged Play AAB manifest** and runtime permissions for target API 37 before declarations.
- The local STR WebView and original Bose provisioning use **unencrypted LAN HTTP**. The Bose temporary setup path transfers the Wi-Fi SSID/password directly to original firmware. No server/analytics claims for Ragutis are supported in the repo, but the *embedded STR page* and the Android/Google backup behavior require separate assessment; do not mechanically answer "no data collected/shared" or "all data encrypted" based on a thin wrapper.
- The manual/automatic version check contacts GitHub HTTPS, exposing normal network metadata to GitHub, no known speaker secrets. Optional support feedback is a user-initiated email *draft*, which the external email provider handles only if the user sends.
- Google [Data safety policy](https://support.google.com/googleplay/android-developer/answer/10787469?hl=en) applies to closed/open/production tracks; apps exclusively in internal testing are exempt from the Data safety **form**, not from truthful disclosure or other policies. Confirm actual Data safety responses by endpoint/data-category audit.

### Embedded web remote / affiliation (maintainer accepted; Play review open)

On 2026-11-03 the STR maintainer **accepted the current Ragutis 0.3.3+ native WebView/inset integration** and confirmed its independent listing. The official [STR Community Apps FAQ](https://st-reborn.de/de/#faq-q48) independently lists the app with the renamed repository link. [Status](STATUS.md) distinguishes the owner-provided maintainer reply from the publicly verifiable listing. **Do not continue to describe upstream consent/listing as missing.**

Google's [WebView spam policy](https://support.google.com/googleplay/android-developer/answer/9899034?hl=en) remains an **independent Play-platform review**: document the Android-owned discovery, device/room management and Bose original-firmware setup, and make the third-party STR WebView attribution clear. The STR maintainer's integration approval is **not** Bose trademark authorization, Play approval or an endorsement of the independent Ragutis project. Preserve the unofficial/no-affiliation notices and avoid unlicensed Bose branding.

### Google Play channel UX (source implemented; P0 QA before distribution)

Google's [Developer Program Policy — Device and Network Abuse](https://support.google.com/googleplay/android-developer/answer/18265346) prohibits **non-Play self-updates** for a Play-distributed app. The previous shared updater had a policy ambiguity: although it never installed an APK itself, it could prompt a link to a GitHub Release containing a downloadable APK.

**Implemented in draft PR #21 (source only):** One codebase now has `github` and `play` product flavors. The `github` build retains the existing bounded GitHub update check and manual About result. The `play` build **does not call** the GitHub updater at startup, does not expose the manual GitHub-update button, and instead offers **Updates in Google Play öffnen / Open updates in Google Play** linking to `https://play.google.com/store/apps/details?id=app.strremote.android` in the external browser/Play handler. No install-source heuristic, new SDK, permissions or alternate app ID is introduced. Regular voluntary GitHub **source/Issues/privacy** links are still available; they are not APK upgrade prompts. The listing may not be accessible before tester authorization.

**Current evidence:** Both flavor builds passed CI on exact tested source `c17f6a00a5028a49e72321d8e4e21dd5d6f64698`. On 2026-11-03 the owner confirmed **both debug variants show the expected behavior, content and links**; see [dated device QA](evidence/qa/2026-11-03-ragutis-0.3.5-flavor-device-qa.md). **Release condition:** Inspect both merged release manifests and perform original-signer/Play-delivered update testing in [Tests](TESTS.md). A working debug flavor is not Google approval or a signed Play track test. Do not upload an AAB before owner authorization, live certificate checks and the remaining privacy/policy gates.

## 4. Store presence — proposed content, not yet submitted

Google Play app name is at most **30** characters, so the full product name **Ragutis Remote for SoundTouch Reborn** does **not** fit. The proposed short Play name is **Ragutis Remote for STR**. Full product name appears in descriptions and About. Short description max **80** characters, long description max **4,000**. [Official store fields](https://support.google.com/googleplay/android-developer/answer/9859152?hl=en).

### German (de-DE)

**Name:** Ragutis Remote for STR

**Short description:** Fernbedienung für SoundTouch Reborn – Geräte im WLAN finden und steuern.

**Full description:**

Ragutis Remote for SoundTouch Reborn ist eine unabhängige Android-App für Lautsprecher mit SoundTouch Reborn (STR). Sie verbindet die bestehende STR-Fernbedienung mit praktischen Android-Funktionen.

Funktionen:
- STR-Lautsprecher im lokalen WLAN finden, auswählen und steuern
- Gespeicherte Lautsprecher und individuelle Raumzuordnungen
- Wiedergabe, Lautstärke und Favoriten über die Original-STR-Oberfläche
- WLAN-Einrichtung auf unterstützten Lautsprechern mit Bose-Original-Firmware (Android 10 oder neuer; bisher nur bestimmte Modelle geprüft)
- Deutsch und Englisch, manueller GitHub-Versionscheck mit klaren Rückmeldungen

Für die Fernbedienung muss SoundTouch Reborn auf dem Lautsprecher installiert sein. Die WLAN-Einrichtung installiert STR **nicht**. Die App benötigt kein eigenes Benutzerkonto und hat keine eigenen Werbe- oder Analysedienste. Der Versionscheck fragt bei GitHub nach neuen Versionen. Feedback wird nur auf Wunsch als E-Mail-Entwurf geöffnet und niemals automatisch versendet.

Die WLAN-Einrichtung überträgt Zugangsdaten auf dem temporären Bose-Einrichtungsnetz in der Original-Firmware über **unverschlüsseltes HTTP**. Nutze diese Funktion nur in einer vertrauenswürdigen Umgebung.

Ragutis ist ein **inoffizielles, unabhängiges Community-Projekt**, nicht verbunden mit oder unterstützt von Bose Corporation oder dem SoundTouch-Reborn-Projekt. Bose und SoundTouch sind Marken der Bose Corporation.

### English (en-US)

**Name:** Ragutis Remote for STR

**Short description:** Find and control SoundTouch Reborn speakers on your local Wi-Fi network.

**Full description:**

Ragutis Remote for SoundTouch Reborn is an independent Android companion for speakers running SoundTouch Reborn (STR). It brings the existing STR remote together with practical Android controls.

Features:
- Discover and select STR speakers on your local Wi-Fi network
- Save speakers, assign rooms and switch between them
- Use the existing STR remote for playback, volume and presets
- Optional Wi-Fi setup on supported speakers running original Bose firmware (Android 10 or later; only specific models have been tested)
- German and English interface and manually triggered GitHub update checks with clear status feedback

STR must already be installed on the speaker for remote control. The Wi-Fi assistant **does not install STR**. The app requires no separate account and has no built-in advertising or analytics service. The optional version check contacts GitHub. Feedback is opened as an email draft only when requested, never sent automatically.

During setup, original Bose firmware receives Wi-Fi credentials over **unencrypted HTTP** on the temporary setup network. Use this function only in a trusted environment.

Ragutis is an **independent, unofficial community project**. It is not affiliated with or endorsed by Bose Corporation or the SoundTouch Reborn project. Bose and SoundTouch are trademarks of Bose Corporation.

### Store imagery

Before publishing the store listing: required **512×512 32-bit PNG Play icon**, **1024×500 JPEG or opaque PNG feature graphic**, and **at least two genuine app screenshots**. Four portrait screenshots around 1080×1920 are recommended. Remove IP addresses, SSIDs, device IDs, email recipients, private home labels and any password content. Use the actual Ragutis UI and app branding; avoid counterfeit Bose/STR branding. Store graphics are separate from launcher assets. [Play media specification](https://support.google.com/googleplay/android-developer/answer/9866151?hl=en).

## 5. Local Play AAB build and signature verification (NOT authorized for upload automatically)

- Choose the reviewed **Play internal candidate** and its exact clean Git commit; check `applicationId`, `versionName`, `versionCode` and GitHub CI. Do not build from an old draft PR (#10/#12).
- On Windows in **Android Studio**: **Build → Generate Signed Bundle / APK → Android App Bundle → app → release**, selecting the **separate, registered Google Play upload keystore** and alias `remote-str-upload`. Enter passwords only in Android Studio's local dialog, never in public commands, tracked Gradle files or chat.
- Android Studio outputs a **signed AAB**, typically under the chosen destination or `app/release/`. Copy it into a protected owner-local distribution directory outside Git. The AAB is **not** an APK: verify it with `jarsigner -verify` / `keytool -printcert -jarfile`, *not* `apksigner` or APK `zipalign`. Compare the embedded AAB signer SHA-256 with Play's **upload certificate** `41BFFC7105A5345DF3F2C1C65CBB52595DB5048C4C80A24564C2863331FA7791`. Record the AAB file SHA-256 and exact source.
- First internal Play track upload requires separate owner authorization and **live Play Console** key and app record confirmation. After Play delivers the test app, inspect the **installed APK signer**: it should match the separate **app-signing** certificate `3CC2E7272D8C2D1433A57C4462DF39ADFE5E8C1931A4A924710C0E4087AA1E32`. Then test a real in-place GitHub→Play versionCode increment with retained speakers/rooms.
- Check merged manifest, pre-launch report, local-STR Wi-Fi and Android 13+/17+ network/permission paths before applying for closed test.

### Reproducible owner-local read-only checks

```powershell
$UploadKeystore = Read-Host "Path to registered Play upload keystore"
$UploadAlias = "remote-str-upload"
$Aab = Read-Host "Path to locally signed AAB"

keytool -list -v -keystore $UploadKeystore -alias $UploadAlias
if ($LASTEXITCODE -ne 0) { throw "Cannot read Play upload key certificate" }

jarsigner -verify -strict -verbose -keystore $UploadKeystore $Aab
if ($LASTEXITCODE -ne 0) { throw "AAB signature verification failed" }

keytool -printcert -jarfile $Aab
if ($LASTEXITCODE -ne 0) { throw "Cannot inspect AAB signer" }

Get-FileHash -Algorithm SHA256 -LiteralPath $Aab
```

Compare the public certificate digests locally. Never send private JKS, PEPK exports, passwords, or the signed AAB itself to public GitHub issues. Official guide: [App signing](https://developer.android.com/studio/publish/app-signing).

## 6. Internal → closed → production gates

1. **Internal testing:** Optional, small trusted devices. The user will explicitly approve AAB upload and tester invitations; verify app install, GitHub→Play signer continuity, saved rooms/speakers and core STR playback on a real phone. This is not a production deployment.
2. **Closed testing:** For a **personal developer account created after 2023-11-13**, Google requires **at least 12 testers opted in continuously for at least 14 days** before production-access application. Internal-only testers do **not** satisfy that condition. Prepare private sign-up/Google Forms only after the closed-test link and consent wording are ready; request real testers among STR community rather than inflate counts. The owner reports the Console shows that gate. [Official requirements](https://support.google.com/googleplay/android-developer/answer/14151465?hl=en).
3. **Production:** Requires completed store/declaration tasks, enough real closed testers and a subsequent approved production-access application. Only a **separate owner-authorized rollout** can publish the app publicly in Play.

## 7. Scope / operational hand-off

**DONE:** public GitHub Ragutis 0.3.4, exact app source, owner-reported six signed device smoke PASS, current public APK digest independently verified; prior owner-reported Play key enrollment fingerprints are documented historically.

**OPEN / HOLD:** live Console recheck; draft 0.3.5/code16 link QA and merge; policy URL, Data safety/backup/merged-manifest audits and **independent Google Play review of WebView use**; signed AAB; owner-permitted internal upload and actual GitHub→Play test; store graphics; closed tester invitation and qualifying 14-day window. **STR maintainer approval and official community listing are confirmed.**

**Privacy and source rule:** keep complete addresses, SSIDs, passwords, identifying device details, secret key material and the existing protected project feedback mailbox out of this repository and its PRs. No settings, account access or upload action is implied by this document.
