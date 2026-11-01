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
.\gradlew.bat clean :app:assembleDebug :app:lintDebug :app:assembleRelease --stacktrace
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

## 0.3.3 Ragutis / STR WebView insets — release candidate device gates

- Verify DE and EN launcher label **Ragutis STR**, compact Devices toolbar **Ragutis STR** next to tappable About, and **full Ragutis Remote for SoundTouch Reborn** in About. Check bilingual trademark/unofficial statement, update dialog and feedback draft version label.
- Confirm original `app.strremote.android` package, original APK signer, preferences and stored speaker/room entries after signed 0.3.2 → 0.3.3 in-place update; never uninstall/data-clear during acceptance.
- On the STR WebView, compare top/bottom spacing before/after, verify **normal upstream 16px header** and no double safe-area padding, working play/pause, device and preset buttons. Include WebView navigation/back and cold restart.
- Test Android API 30+ on current physical device with both gesture and 3-button navigation, portrait/landscape, screen cutout, light/dark; toggle keyboard/IME repeatedly and ensure no ghost bottom inset. Test API 26–29 legacy inset dispatch separately or document device unavailability as **NOT TESTED**, not PASS.
- Verify no dependency on `#dev`, `#powerBtn` or a forced `10px` header injection remains. CI Android build/lint/unit checks do not certify physical-device layout.
- Check updated GitHub release checker accepts new Ragutis APK name and both historical names, but rejects mismatched asset-version/draft/prerelease; check signed release script creates Ragutis artifact from clean code with existing app signing identity when authorized.
- Keep the Play Store work paused: an old 0.3.3 candidate from draft PR #12 must not be uploaded/merged instead of the validated Ragutis build.
