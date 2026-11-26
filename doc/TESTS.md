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

## Risk-based regression selection and release smoke

For each candidate, record the last accepted baseline, exact tested source and candidate source. Compare Git blobs and diffs for source, resources, manifests, dependencies, toolchain, flavors and build configuration. Trace changed behavior through shared callers, storage, permissions, networking and external contracts; byte-identical files alone do not establish unchanged integration or generated binaries.

- Always run the minimum maintenance gate and, for application changes, the full automated Android gate above. Test changed behavior and affected integration/negative paths on physical hardware wherever the behavior depends on real network, speaker, permissions or device characteristics.
- Reuse dated manual evidence only when its exact scope, relevant dependencies and environment remain applicable. Record selected tests, exclusions, justification, residual risk and owner acceptance. A deferred/untested case is never PASS. Expand coverage for uncertain impact, security/privacy changes, migrations, toolchain changes or regressions.
- Use the physical debug app for STR/network behavior, a dedicated signed GitHub AVD for install/update/data retention and release smoke, and a physical Play installation for genuine Play-delivered updates. An emulator is not proof of physical Wi-Fi association, mDNS behavior, or all supported Android versions. Keep the prior signed baseline and saved data (snapshot when available); verify exact artifact hash, signer, package/version, restart, retained data, relevant changed behavior and channel routing. Never uninstall or clear data during an upgrade test.
- Separate pre-publication no-newer-release checking from the positive public update/download/install test after publication. Verify the published asset digest against the accepted artifact. No artificial public release for testing.

See [Release gates](RELEASE_GATES.md) for acceptance and [Governance](GOVERNANCE.md) for approval boundaries.

## Historical 0.3.5 closed-test Play upgrade — scoped device QA (2026-11-22)

The owner reports automatic Google Play installation over an existing GitHub version **without uninstall**, all saved speakers and room assignments intact, STR control working, and both Play/privacy links operational. [Dated evidence](evidence/qa/2026-11-22-play-closed-test-owner-qa.md). This is a **real-device cross-channel functional PASS**. Owner follow-up `dumpsys package` shows installed 0.3.5/code16/targetSdk37, and their `apksigner verify --print-certs` check of the ADB-extracted Play `base.apk` returned the matching app-signing SHA-256 `3CC2E7272D8C2D1433A57C4462DF39ADFE5E8C1931A4A924710C0E4087AA1E32` (**installed signer PASS, owner-reported**). Still not proof of exact uploaded AAB/source SHA, cloud-backup restoration, API-26 Play device compatibility, Play Data safety or 0.3.6 signed-device QA.

## Product-flavor distribution QA

- GitHub and Play debug variants: verify localized About links, channel-safe update routing, STR control and room management; debug package remains isolated.
- Both release variants: verify production package, intended version, permissions and compiled manifest; CI builds unsigned artifacts without credentials.
- Play AAB: verify registered upload signature and bundle validity. Play-delivered APK: verify original app-signing certificate, installed version, retained data, restart, STR control, Play/privacy links. A fresh install is not upgrade evidence.
- Preserve distinct distribution authorization and actual delivery evidence. See [Release gates](RELEASE_GATES.md).

The 0.3.6 Play delivery is owner-confirmed; post-update checks remain open. [Dated evidence](evidence/qa/2026-11-26-ragutis-036-play-delivery.md).

## 0.3.6 owner debug-device acceptance — scoped PASS (2026-11-22)

Exact tested PR #25 source `f8f95d1e1929cc882daad77cdc7c209455a7c197` passed both-flavor CI, and the owner reported **12 PASS / 0 FAIL / 2 N/A**: T01–T11 and T14 PASS; T12 original-firmware Bose and T13 manual-IP are **NOT TESTED**. [Full dated matrix](evidence/qa/2026-11-22-ragutis-036-owner-debug-device-acceptance.md). The original three reported bugs and standard discovery/room/multi-device regressions were verified. Exceptional same-IP/different-key F-034 interactions were covered by JVM policy tests but not reproduced on hardware; signed GitHub and Play update smoke are tracked separately in Release gates.

## 0.3.6 signed GitHub emulator acceptance — scoped PASS (2026-11-25)

Owner reports R01–R07 all PASS on Pixel 9a AVD Android 17/API 37.1 (16 KB page-size system image), using the original-key signed 0.3.6 candidate over 0.3.4. See [dated QA](evidence/qa/2026-11-25-ragutis-036-signed-emulator-qa.md) for exact source/artifact identity, test cases and limitations. This complements but does not replace physical debug QA or prove a physical signed-upgrade, full Bose WLAN setup or newer-release download. Apply the risk-based regression selection above; existing requirements are not silently waived.

## 0.3.6 positive GitHub online update — scoped PASS (2026-11-25)

See [dated evidence](evidence/qa/2026-11-25-ragutis-036-public-release-acceptance.md) for screenshot-supported positive version detection, release navigation, download, Android package-manager update and owner-reported retained data/STR operation. Prior signed AVD smoke remains separate; physical and negative-path limitations remain unchanged.

## 0.3.6 bugfix candidate — REAL-DEVICE QA MATRIX (T12/T13 STILL OPEN)

JVM policy regression tests: unsaved deletion action, neutral while probing, reachable green/unreachable red after probe, saved cached reachability, no Wi-Fi, and pending-Bose isolation. These tests **cannot** replace Android UI QA.

Additional **identity isolation** regression: two different real device IDs sharing an IP/host must remain separate rows, separate hide actions and independently probeable candidates. A manually entered/unkeyed saved endpoint may match by host only until claimed by a keyed device; it must not be assigned concurrently to a second differently keyed speaker. Synthesized mDNS fallback keys still use host matching. Keep Bose-pending rows distinct. Confirm on device where practical; unit policy tests do not simulate Android UI/mDNS callbacks end-to-end.

1. A previously unknown STR speaker has its delete icon immediately, with no room assignment. Cancel keeps it. Confirm removes only its list card for this discovery session; an outstanding mDNS announcement cannot re-add it, but the next user-started scan can.
2. Newly found STR cards start neutral during the HTTP probe, then automatically become green (STR reachable) or red (STR unreachable) without tapping or assigning a room. Confirm the appropriate DE/EN text and accessible delete label.
3. Offline/red saved STR device: choose No room, a preset room and a custom room; it must remain red/offline throughout, even across screen refresh. Restart and verify room data and reachability. Only a successful connection probe can turn it green.
4. Online saved device retains correct green state while changing room. No phantom connection or duplicate entries; deleting saved speaker still clears its metadata after confirmation.
5. Original-firmware Bose setup entries stay distinct and retain their current room and delete behavior. Verify discovery/reconnection/STR remote control and the manual-address path.
6. Both `githubDebug` and `playDebug` pass DE/EN smoke and app update routing. Verify identical `app.strremote.android`, version 0.3.6/code17 and unchanged merged permissions/backup configuration in release artifacts.
7. Original GitHub signer + real in-place update from v0.3.4/code15 to v0.3.6/code17 preserves speakers, rooms, state and playback. Independently, Play-delivered in-place v0.3.5/code16 → v0.3.6/code17 preserves data; the Play-delivered APK uses the original app-signing cert while the AAB upload uses the separate registered upload key. Never uninstall the baseline first.
8. Save exact CI commit, production-signed artifact hashes, certificate identity, phone test evidence and separate release authorizations **before** publishing GitHub v0.3.6 and starting the Play closed-track v0.3.6 rollout. A green CI alone is not a release gate.

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
