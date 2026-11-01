# Planned work

**Latest released version:** see [Status](STATUS.md). The completed BUILD-0013 work, release notes and device tests are maintained in [BUILD-0013](work/BUILD-0013.md), [Changelog](CHANGELOG.md) and the [v0.3.1 release evidence](evidence/qa/2026-10-31-release-0.3.1.md), not duplicated in this plan.

## GitHub repository rename and 0.3.3 release sequencing (2026-11-01)

**Owner completed the rename first:** the canonical repository is now [K-Cosmo/ragutis-remote-for-str](https://github.com/K-Cosmo/ragutis-remote-for-str). The former repository slug remains a historical identifier; GitHub offers redirects, but published **0.3.2 disables redirect following for its fixed GitHub API GET**, so its automatic update check may not discover newer releases. We cannot retrofit that old signed APK.

The new 0.3.3 source already adopts canonical GitHub URLs and standard HTTPS redirects; migration PR #14 and main CI passed. **Owner locally built the signed candidate from exact source commit `44bc6d0c518c9c0dbb01bf109589f3b5cbd29d65` and reports seven signed 0.3.2→0.3.3 phone smoke cases PASS**, including saved data, STR, room picker, Wi-Fi wizard, feedback, DE/EN UI and GitHub links. See [QA evidence](evidence/qa/2026-11-01-ragutis-0.3.3-signed-qa.md). **Separate owner approval was subsequently granted**, and stable GitHub [v0.3.3](https://github.com/K-Cosmo/ragutis-remote-for-str/releases/tag/v0.3.3) is publicly available. The annotated tag targets the exact signed source, APK asset's GitHub SHA-256 equals the locally tested digest, and `releases/latest` resolves to v0.3.3. No Google Play release or community publication has been performed. Do not claim v0.3.2's old updater behavior was fixed.

## Updater diagnostics / manual check (0.3.4 development)

The published Ragutis v0.3.3 has a silent automatic GitHub release check on application startup, with a 24h success throttle. The latest GitHub v0.3.3 **equals the current installed production v0.3.3**, so no update prompt should appear. This is not conclusive evidence that a genuinely newer release can be discovered. Since v0.3.2 used an old non-redirecting API URL, old installations after the repository rename may need manual update.

A separate 0.3.4 draft introduces user-controlled About update checks with progress and explicit `current / request failed / newer` results, using the same fixed official HTTPS GitHub API and parser, without automatic APK install. Real-device QA must verify when no newer release is available, the no-network case (e.g. local-only STR Wi-Fi), and a future real public newer release when one exists. This does not block local STR operation or change package/signing identity.

## Current priorities

| Item | Priority | Next action |
|---|---|---|
| **STR-035 / STR-036** | P0 | **GitHub v0.3.3 released and independently verified**; signed 7/7 smoke PASS; exhaustive edge-case and legacy-device QA separately deferred |
| **STR-037 / STR-038** | P1 | Signed smoke includes feedback and room picker PASS; granular negative paths, TalkBack and full model/room permutations not separately certified |
| **STR-022** | P1 | Test native inset zeroing first; request documented upstream `--str-safe-top` / `--str-safe-bottom` only if still needed |
| **STR-025** | P1 | Send the prepared German email with the now-verified [v0.3.3 GitHub Release](https://github.com/K-Cosmo/ragutis-remote-for-str/releases/tag/v0.3.3); request upstream Community Apps label update. Owner confirms the release email was sent on 2026-11-01; await upstream response and verify listing before claiming it changed |
| **STR-023** | P1 | Set up federated community support: upstream discovery thread, app Issues and optional email |
| **STR-027** | P1 | Run explicitly deferred negative-path QA and validate future positive release-update notification |
| **STR-024** | P1 | Follow up on residual GitHub-managed historical PR references and caches |
| **STR-026 / STR-012** | P2 | Independently collect original Bose-firmware provisioning / STR remote compatibility evidence |
| **STR-014 / BUILD-0008** | P2 | Evaluate Gradle 9.8.x as isolated maintenance work |

For details and current states, see [Backlog](BACKLOG.md) and [Findings](FINDINGS.md).

## STR-022 — Maintainer-confirmed inset root cause; upstream CSS fallback only if needed

**Released 0.3.2 (historical):** Android forwarded root system insets unchanged to WebView and compensated for STR's `calc(16px + env(safe-area-inset-top))` with a guarded private DOM `10px` CSS override. This created fragile top/bottom spacing. The STR maintainer diagnosed duplicate padding in his second response (2026-11-01).

**Released 0.3.3 (owner-reported signed QA PASS):** Native Android root owns the safe-area padding and zeroes already applied system-bar/display-cutout insets for WebView on API 30+; legacy APIs consume handled insets. The DOM and CSS override code has been removed. STR keeps its **normal 16px CSS header padding** and its own PWA/browser behavior. No change to STR source is proposed in the primary implementation. Real-device verification of top/bottom insets, WebView controls, rotation and keyboard/IME is **mandatory**.

**If the native-only fix fails on tested devices:** The maintainer offered a future upstream-documented pair `--str-safe-top` and `--str-safe-bottom` on `:root`, with normal `env()` fallbacks. Treat these as **proposed**, not current available CSS API, until his implementation/versioned documentation exists. Ask via [STR Show and tell](https://github.com/JRpersonal/streborn/discussions/categories/show-and-tell) after test evidence; do not again target private `#dev`, `#powerBtn`, or `body > header` selectors.

References: [Android root inset implementation](../app/src/main/kotlin/app/strremote/android/MainActivity.kt), [current integration contract](API_CONTRACTS.md), [new decision D-047](DECISIONS.md), [Android WebView guidance](https://developer.android.com/develop/ui/views/layout/webapps/understand-window-insets).

## STR Community Apps and support

The maintainer's second response (2026-11-01) acknowledges the 0.3.1/0.3.2 prior compliance and says he intends to list the older Android app as an early GitHub version for Android 8+. However, the adjacent iOS **STR Remote** listing is too easy to confuse with **Remote for STR**. The owner selected the distinct active name **Ragutis Remote for SoundTouch Reborn** and short launcher label **Ragutis STR**. The maintainer said he can update the Community Apps entry after the naming decision; **no completed website update or published discussion has been confirmed**.

Once 0.3.3 device QA and owner approval are complete, prepare a separate upstream [Show-and-tell thread](https://github.com/JRpersonal/streborn/discussions/categories/show-and-tell) with the new name, verified Android versions, clear unofficial attribution, actual release link and inset test result. The maintainer asks for app/integration discussion there so community support is discoverable. Specific bug reports may still be tracked in this repository's [Issues](https://github.com/K-Cosmo/ragutis-remote-for-str/issues); opt-in email remains available without GitHub login. Do not claim upstream endorsement.

## Later candidates

Play Store / F-Droid distribution (after the Ragutis development/release round; Play PR #10 and the old version-only PR #12 remain on hold), further hardware evidence and possible STR authentication changes remain separate future priorities. Preserve the original Android package ID, signing identity, stored user data and already published APKs.
