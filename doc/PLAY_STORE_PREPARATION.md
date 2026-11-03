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
| Separate Google Play **upload** certificate | SHA-256 `41BFFC7105A5345DF3F2C1C65CBB52595DB5048C4C80A24564C2863331FA7791`; owner previously confirmed it is registered in Play Console |
| Previous key alias | `remote-str-upload` for the separate upload JKS, owner-local (never commit/store keystore/passwords/PEPK export) |
| Public Google Play artifact | **None verified**; first AAB/track still to prepare |

**Critical signing gate:** In Play Console verify **both** live certificate SHA-256 values before uploading anything. Google's **app-signing** certificate is what signs delivered APKs; the **upload** certificate only authenticates the AAB sent to Google. A matching upload fingerprint alone cannot prove upgrade compatibility across GitHub and Play. Do not replace or re-enroll the app-signing key without a separate risk decision. [Android official signing guide](https://developer.android.com/studio/publish/app-signing).

**Version strategy:** Public GitHub v0.3.4 already has `versionCode=15`. A Play internal candidate with the same code can be installed on a fresh device or tested over an older code (for example 0.3.3/14) if the app-signing key matches, but **Google Play does not treat installed code 15 as outdated when the Play bundle is also code 15**. For a meaningful Google-Play-managed in-place update on the owner's currently installed v0.3.4, **draft PR #21 already stages** a distinct internal candidate with `versionCode=16` / `versionName=0.3.5`; it is **not merged, signed or uploaded**. Do not rebuild the already published v0.3.4 tag as code 16 or claim Play 0.3.5 is a public GitHub release. Exact version choice, signing and Play internal rollout require separate approval and device QA. [Official update semantics](https://developer.android.com/google/play/app-updates).

## 2. Account / Console state (reported, still to reconfirm)

- Owner paid the Google developer registration in September 2026; on 2026-10-31 the owner reported Play Console availability for package `app.strremote.android`, internal testing and a dashboard requirement to complete setup, closed testing and production-access application. The **actual app-record creation and all current Console task statuses need live confirmation**.
- Default language: choose DE or EN deliberately; provide both human-reviewed German and English listing content.
- App, free; target adult users of an unofficial community companion; do not claim affiliation with Bose or the STR project.
- Confirm developer/account verification, developer contact and required publicly visible Play support email. Do not put a literal feedback email address into this public repository (project anti-harvest policy); a real store contact **will** be public in Google Play.
- **No Play action in this runbook creates approval for production.** Internal testing, closed testing and production use separate release gates.

## 3. P0 policy and runtime review **before closed testing**

### Privacy policy in app and on Play (device/URL gate open)

Google requires a public privacy policy linked **in the app and on the Play listing**. The current bilingual [public policy](PRIVACY.md) has already been corrected to describe local device/room metadata, `android:allowBackup=true`, GitHub requests, embedded STR traffic and optional user-triggered mail. The **published GitHub v0.3.4 app still lacks the native About link**; the **unreleased 0.3.5/code16 draft [PR #21](https://github.com/K-Cosmo/ragutis-remote-for-str/pull/21)** stages a labeled DE/EN link. Do not call this accepted until PR review/merge, exact-source signed-device navigation and live worldwide public URL/Play Console review pass. The final URL must be readable without login or download. No complete feedback address belongs in this repository. [Official privacy requirements](https://support.google.com/googleplay/android-developer/answer/9859455?hl=en).

### Data safety, network and user data (BLOCKER before closed/production)

- Android manifest currently declares `INTERNET`, Wi-Fi/network state, API-limited coarse/fine location (max 32), `NEARBY_WIFI_DEVICES`, `ACCESS_LOCAL_NETWORK` and an `android:allowBackup=true` setting; review the **actual merged Play AAB manifest** and runtime permissions for target API 37 before declarations.
- The local STR WebView and original Bose provisioning use **unencrypted LAN HTTP**. The Bose temporary setup path transfers the Wi-Fi SSID/password directly to original firmware. No server/analytics claims for Ragutis are supported in the repo, but the *embedded STR page* and the Android/Google backup behavior require separate assessment; do not mechanically answer "no data collected/shared" or "all data encrypted" based on a thin wrapper.
- The manual/automatic version check contacts GitHub HTTPS, exposing normal network metadata to GitHub, no known speaker secrets. Optional support feedback is a user-initiated email *draft*, which the external email provider handles only if the user sends.
- Google [Data safety policy](https://support.google.com/googleplay/android-developer/answer/10787469?hl=en) applies to closed/open/production tracks; apps exclusively in internal testing are exempt from the Data safety **form**, not from truthful disclosure or other policies. Confirm actual Data safety responses by endpoint/data-category audit.

### Embedded web remote / affiliation (maintainer accepted; Play review open)

On 2026-11-03 the STR maintainer **accepted the current Ragutis 0.3.3+ native WebView/inset integration** and confirmed its independent listing. The official [STR Community Apps FAQ](https://st-reborn.de/de/#faq-q48) independently lists the app with the renamed repository link. [Status](STATUS.md) distinguishes the owner-provided maintainer reply from the publicly verifiable listing. **Do not continue to describe upstream consent/listing as missing.**

Google's [WebView spam policy](https://support.google.com/googleplay/android-developer/answer/9899034?hl=en) remains an **independent Play-platform review**: document the Android-owned discovery, device/room management and Bose original-firmware setup, and make the third-party STR WebView attribution clear. The STR maintainer's integration approval is **not** Bose trademark authorization, Play approval or an endorsement of the independent Ragutis project. Preserve the unofficial/no-affiliation notices and avoid unlicensed Bose branding.

### Google Play channel UX (REVIEW)

The existing in-app updater points to **GitHub Releases**, regardless of distribution channel. Determine whether Play-installed builds should instead direct users to Google Play or suppress external APK update prompts. Do not silently redirect Play users to an off-store installation without clear product decision. Keep the GitHub release app behavior unchanged unless a separate design/source change is reviewed.

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
