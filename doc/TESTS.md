# Test and evidence strategy

## Minimum maintenance gate

For every change:

1. run the documentation consistency gate;
2. run `git diff --check`;
3. select additional Android/runtime gates according to the changed scope.

Windows PowerShell:

```powershell
.\tools\verify-doc-consistency.ps1 -ProjectRoot .
git diff --check
```

When application source/resources/build configuration changed:

```powershell
.\gradlew.bat --version
.\gradlew.bat clean :app:assembleGithubDebug :app:lintGithubDebug :app:testGithubDebugUnitTest :app:assemblePlayDebug :app:lintPlayDebug :app:testPlayDebugUnitTest :app:assembleGithubRelease :app:lintGithubRelease :app:bundlePlayRelease :app:lintPlayRelease --stacktrace
```

Use `./gradlew` on POSIX systems.

Real-device verification is required when runtime/user-visible behavior changed. Relevant cases include:

- local-network permission grant/deny/recovery;
- discovery of a real STR speaker;
- 8888/17008 endpoint resolution;
- WebView loading and normal STR control;
- app relaunch and last-speaker reconnect;
- native back navigation;
- changed speaker-management behavior;
- focused Logcat/screenshots when diagnosing runtime behavior.

Store applicable evidence under `doc/evidence/qa/`.

Documentation-only changes do not require a new Android APK when they do not change application source/resources/build configuration. GitHub Actions remains an Android build/lint/release-assembly check only.

## Product-flavor distribution QA — debug owner PASS, release gates OPEN

The staged 0.3.5 code adds `github` and `play` Gradle flavors **after** the prior owner-reported 6/6 debug pass of `8ccb4564ee0d08a83527ecb391a1a514dc451d46`. For the later exact functional source `c17f6a00a5028a49e72321d8e4e21dd5d6f64698`, the owner reported **both debug flavor builds show the requested behavior, content and links**; [dated dual-flavor QA](evidence/qa/2026-11-03-ragutis-0.3.5-flavor-device-qa.md). The following is the targeted test scope, not proof of every unreported negative-path or signed production case:

1. **githubDebug** (`app.strremote.android.poc`): About manual GitHub check retains visible current/failure status; automatic GitHub check is not enabled on the debug package; links, DE/EN privacy, STR control, room management and browser Back remain functional.
2. **playDebug** (same `.poc` debug package; install it **after** the Github debug trial): About shows **Updates in Google Play öffnen / Open updates in Google Play**, and does not expose the GitHub check button, GitHub APK update dialog or request a Github update on startup. The Play URL points to `app.strremote.android`; without tester entitlement it may show the unpublished listing as unavailable. Confirm the app returns normally and STR/rooms and privacy/source/feedback still work.
3. CI must compile/lint/test **both** flavors and assemble `githubRelease` plus **unsigned** `playRelease` AAB. Verify generated **production** `applicationId=app.strremote.android`, debug `.poc`, `versionCode=16`, Android permissions/merged manifests and intended update-channel flags. No signing keys or Play uploads in CI.
4. **Original-signer GitHub release smoke** and a later **Play-delivered** cross-channel in-place upgrade are **separate, still-open tests**. Preserve an untouched signed GitHub v0.3.4/code15/data baseline for Play. The Play bundle is to be signed with the **distinct** registered Play upload key, not the APK signer.

## Google Play internal candidate — separate distribution QA (OPEN)

**PR #21 / 0.3.5-code16 focused QA:** On an actual Android device, verify native About shows **Datenschutzhinweise** in DE and **Privacy policy** in EN; the action must open the correct readable public [privacy policy](PRIVACY.md) in an external browser without authentication, and Android Back must return to Ragutis without data loss. Reopen About and verify source/issues/email actions still behave normally. Preserve and exercise STR connection, stored speaker/room data and normal playback. A debug `.poc` build verifies UI/navigation only; a distinct original-signer signed production candidate is needed for real installed upgrade/data preservation QA. When no external browser can handle a link, show the localized failure message and keep external WebView navigation blocked. Offline failure must not be recorded as a successful page load. The six categories **reported PASS on the debug client** are documented in [dated 0.3.5 debug QA](evidence/qa/2026-11-03-ragutis-0.3.5-debug-device-qa.md); they do **not** satisfy the distinct original-signer/Play delivery and untested negative cases below.

The live Play **app-signing** and **upload** certificate fingerprints were owner-reported PASS on 2026-11-03; [dated PLAY-001 evidence](evidence/qa/2026-11-03-play-signing-acceptance.md). Verify the separately signed AAB using `jarsigner` and `keytool -printcert -jarfile`, not `apksigner`. Inspect merged Play manifest permissions, Android backups, embedded STR WebView, public/privacy link in About and data-safety declarations.

After owner-authorized internal Play rollout, install the **Play-delivered** app over a previously installed GitHub signed version with a **lower versionCode** and preserve speaker/room data, then verify installed app certificate SHA-256 is the original GitHub/Play **app-signing** cert (not the upload cert), normal STR control, manual update-check channel UX, DE/EN, restart and live device/room state. A fresh Play install alone is **not sufficient** evidence of cross-channel in-place upgrade. If current GitHub v0.3.4 has code 15, plan a distinct Play candidate code **16+**. See [Play preparation](PLAY_STORE_PREPARATION.md).

## Accepted regression matrix — Discovery & Network Resilience (BUILD-0007 / 0.1.1)

These cases remain regression coverage:

1. Wi-Fi off while mobile data remains available -> no local probe/discovery; stable no-Wi-Fi state.
2. No usable Wi-Fi -> same stable state, no indefinite spinner.
3. Wi-Fi present but no STR speaker -> finite discovery ends and exposes STR help/Retry/manual-host paths.
4. Known STR speaker present -> normal discovery/probe/WebView/control path.
5. Saved endpoint plus Wi-Fi unavailable -> no pointless saved-endpoint probe.
6. Speaker discovered but ports 8888/17008 unreachable -> explicit STR-unreachable state distinct from no-device.
7. Enable Wi-Fi and Retry -> recovery without app restart.
8. Wi-Fi without Internet validation -> local STR operation remains eligible.
9. Manual host while Wi-Fi absent -> no-Wi-Fi state rather than pointless probe.
10. Permission recovery, native back navigation and constrained WebView navigation remain regression checks.

## Accepted regression matrix — Speaker Management / Rooms (BUILD-0009 / 0.2.0)

These cases remain regression coverage:

1. Room action on a reachable discovered speaker saves it and survives app restart.
2. Built-in rooms persist and display localized labels.
3. Custom room text persists unchanged.
4. Language change localizes built-in room display without changing stored identity; custom text is unchanged.
5. Saved + discovered representation of the same keyed speaker produces one row.
6. Unkeyed/manual saved endpoint may merge with later discovery by host fallback.
7. Two different keyed speakers do not merge solely because a host is reused.
8. Delete confirmation: cancel preserves; confirm removes saved metadata/current row; later discovery may rediscover the physical speaker.
9. Offline/unreachable saved speaker remains representable/actionable.
10. Switching rows uses the existing endpoint/WebView path and updates reconnect state.
11. Current speaker is clearly indicated; room label does not affect connectivity/security.
12. Successful manual connection can be saved/room-assigned.
13. Dismissed device-management hint stays hidden after relaunch.
14. Device-list spacing remains balanced with the hint visible or hidden.
15. Wi-Fi, timeout, no-device/unreachable, permission, back and WebView restrictions remain regression coverage.

## Release artifact gate

For a public release:

- build only from a clean committed source tree;
- record exact source commit;
- sign with the accepted Remote for STR signing identity;
- verify APK signature schemes and certificate;
- run post-sign 16 KiB-aware zipalign verification;
- record final SHA-256;
- install and smoke-test the exact signed APK;
- tag the exact source commit;
- publish exactly the accepted APK + checksum file;
- verify the public asset digest;
- close out `/doc` without moving the release tag or rebuilding the released artifact.

## Documentation consistency regression checks

- `app/build.gradle.kts` versionName equals `doc/STATUS.md` Development version;
- README Current release equals `doc/STATUS.md` Latest public release;
- accepted releases have a matching `doc/CHANGELOG.md` heading and no unchecked release gates;
- current planning no longer points at an already accepted final-publication step;
- relative links in public/root entry-point Markdown and current normative `doc/*.md` files resolve (dated work/evidence remain historical records);
- root helper Markdown does not duplicate normative `/doc` policy;
- canonical decision (`D-nnn`) and finding (`F-nnn`) IDs are unique;
- no complete literal email addresses appear in any public Markdown or Android source/resource/test files; CI must not log sensitive matched values.

A successful compile alone is not functional evidence.

## Private-data check before public evidence or release

The CI documentation-consistency tool checks tracked `/doc/**/*.md` text for unexpected RFC1918 host addresses, allowing only the explicitly documented Bose setup alternate endpoint. New screenshots, user-supplied logs and test reports must be sanitized before committing: use RFC 5737 example addresses and remove SSIDs, MAC/serial numbers, tokens, usernames, account identifiers and unnecessary personal paths. This preventive check only covers current Markdown; historical commits require a separately coordinated `git-filter-repo` cleanup. See `GIT_HISTORY_CLEANUP.md`.

## Historical 0.3.2 compact launcher and feedback recipient QA (accepted scope)

- JVM-test deterministic recipient reconstruction and user-consented draft creation for both German and English, without a complete mailbox literal anywhere in source or tests.
- Ensure native About and the SoundTouch feedback action both use the same `BoseCommunityFeedback.RECIPIENT` value.
- Upgrade the **exact newly signed 0.3.2 APK** in place over public 0.3.1 on a device. Check launcher icon label **Remote STR**, in-app Devices title **Remote for STR**, About trademark/community disclosure, existing speakers/rooms, and actual email-client destination for both paths. Compilation or unit tests alone do not clear these device gates.

## Manual GitHub update checker — ongoing regression criteria

- Unit tests: failed/empty API response -> failure status, same/older stable release -> no newer update, valid newer public stable release with matching APK asset -> update available; exclude malformed tags, drafts, prereleases and mismatched assets.
- Signed production app: About -> **Auf Updates prüfen / Check for updates** shows progress and an explicit result; repeat immediately and verify the manual check is not blocked by the successful 24-hour automatic throttle.
- With Internet unavailable but local STR Wi-Fi connected, manual check fails visibly without disrupting local STR control; after recovery a new manual request succeeds. No hanging progress or silent failure.
- Automatic production checks remain best-effort, limited by the last successful 24-hour window and without intrusive errors; debug `.poc` builds allow metadata diagnostics only and never offer a production APK upgrade.
- When a **genuinely newer** stable signed GitHub APK exists, confirm the production upgrade dialog links to the correct canonical public release. Do **not** invent a dummy release for testing. The positive signed-device case remains [deferred](https://github.com/K-Cosmo/ragutis-remote-for-str/issues/7).

The specific earlier v0.3.3→v0.3.4 online/offline/signed QA outcomes are [dated release evidence](evidence/qa/2026-11-03-ragutis-0.3.4-local-signed-build.md), not current pending gates.

## Ragutis native WebView insets — retained regression criteria (introduced in 0.3.3)

- CI for exact candidate source must confirm build/lint/JVM tests and doc-consistency/anti-harvesting, but does not constitute real-device acceptance. Source `08ca3d5c3025fbce7df5130fa8961a00713fa954`: push and PR runs passed on 2026-11-01.
- Verify DE and EN launcher label **Ragutis STR**, compact Devices toolbar **Ragutis STR** next to tappable About, and **full Ragutis Remote for SoundTouch Reborn** in About. Check bilingual trademark/unofficial statement, update dialog and feedback draft version label.
- Confirm original `app.strremote.android` package, original APK signer, preferences and stored speaker/room entries after signed 0.3.2 → 0.3.3 in-place update; never uninstall/data-clear during acceptance.
- On the STR WebView, compare top/bottom spacing before/after, verify **normal upstream 16px header** and no double safe-area padding, working play/pause, device and preset buttons. Include WebView navigation/back and cold restart.
- Test Android API 30+ on current physical device with both gesture and 3-button navigation, portrait/landscape, screen cutout, light/dark; toggle keyboard/IME repeatedly and ensure no ghost bottom inset. Test API 26–29 legacy inset dispatch separately or document device unavailability as **NOT TESTED**, not PASS.
- Verify no dependency on `#dev`, `#powerBtn` or a forced `10px` header injection remains. CI Android build/lint/unit checks do not certify physical-device layout.
- Check updated GitHub release checker accepts new Ragutis APK name and both historical names, but rejects mismatched asset-version/draft/prerelease; check signed release script creates Ragutis artifact from clean code with existing app signing identity when authorized.
- Historical old Play-only drafts PR #10/#12 are superseded and must not be uploaded. Current Play work is governed by [Play preparation](PLAY_STORE_PREPARATION.md).

## 0.3.3 explicit model selection and hint alignment (owner-signed-smoke PASS; granular cases pending)

- On fresh wizard open in DE and EN, **no SoundTouch model is implicitly selected**. The spinner itself displays the localized `bose_model_select_desc` prompt and the separate model heading is absent. The placeholder must be non-selectable from the opened menu, the model-specific instruction card and status note remain hidden, and the Next action is visibly disabled.
- Open and close the dropdown without selection: nothing progresses. Select SoundTouch 10, SoundTouch 20 III or Other: the correct notice/instruction appears and Next becomes enabled. Check model-specific failure-feedback remains only for Other.
- After an explicit selection, navigation back, device rotation and fresh-network retry must retain the chosen enum ID, never replace it with a hidden default. Check unrecognized/absent model IDs resolve to no selection; pure policy unit tests enforce placeholder index 0 and valid models at indices 1–3.
- Confirm model status hint has balanced 14dp horizontal insets and visually aligns with selector and instruction card **text**, without clipping/overlap on narrow displays/large font sizes.

## 0.3.3 wizard model dropdown clarification (owner-signed-smoke PASS; granular cases pending)

- Pure model-policy test: presentation order is **SoundTouch 10, SoundTouch 20 III, Other last**, while stable `BoseSetupModel.name` IDs are untouched. **There is deliberately no default model**; the picker starts on a disabled placeholder and requires explicit selection. No order inferred by replacing persistent IDs.
- DE and EN spinner values contain no „geprüft“/“verified” suffix; the existing checked-status note remains below the spinner for SoundTouch 10/20 III. For Other, a newly localized unverified-test note occupies the same area; detailed specific caution remains inside the preparation card.
- Test switching each of the three models, instructions and status notice, optional Other feedback action, phones with small display/large fonts. No WLAN-protocol/compatibility certifications added by the UI change.

## 0.3.3 second-round regression — feedback and room UI

- **Recipient:** Unit test new feedback recipient constructed from separate fragments (never a complete public mailbox literal). Verify the *same* central recipient powers native About and SoundTouch feedback and is shown by the external mail application; neither path sends automatically. Check DE/EN subject/body, app/Android version, no SSID/password or diagnostics, and no mail client negative path if available.
- **Card design:** Check that room picker matches established WLAN popup: white 10dp corners / thin blue-gray outline, pale blue selected state with check mark, two-line text, 68dp minimum touch height and a scrollable maximum-height dialog.
- **Room semantics:** Current saved preset/custom room is preselected; all six presets, `No room`, and `Custom room` map to unchanged `RoomAssignment` values. Selecting a preset saves; removing assignment clears only room; custom entry and re-edit (up to 60 chars) work; invalid blank custom input leaves editor open, cancellation makes no mutation.
- **Coverage:** Run on one reachable STR device and one pending Bose device if available; DE/EN, dark/light, narrow display, larger fonts and TalkBack. Unit-test `RoomPickerPolicy` with missing, preset, custom and invalid index behavior.
- **Evidence split:** Earlier debug tests predate the final code. The owner later reports **signed v0.3.2→v0.3.3 device smoke 7/7 PASS on exact final source** for update/data, STR control, room selection, WLAN wizard, feedback, DE/EN UI, and GitHub links. See [signed QA record](evidence/qa/2026-11-01-ragutis-0.3.3-signed-qa.md). This covers the reported smoke scope but does not certify every enumerated negative, legacy API 26–29 or accessibility/permutation scenario. Do not promote unreported subcases to PASS.
