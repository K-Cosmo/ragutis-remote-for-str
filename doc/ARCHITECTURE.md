# Architecture

## Scope

Ragutis Remote for SoundTouch Reborn remains a thin Android integration layer around the web remote served by SoundTouch Reborn. STR owns playback/business behavior; Ragutis STR owns Android discovery, endpoint selection, local UI metadata, persistence, permission/network handling and WebView integration.

## Components

- `MainActivity` — Android lifecycle, local-network permission and Wi-Fi state, discovery/device-management UI, speaker switching, manual-host flow, system insets and WebView orchestration.
- `StrDiscovery` — mDNS/DNS-SD discovery and STR TXT-record parsing.
- `EndpointProbe` — asynchronous reachability checks and STR port fallback.
- `PreferencesStore` — app-private persistence for the last successful endpoint, explicitly saved speakers, room assignments and local UI preferences such as the dismissed device hint.
- `SpeakerModels` — internal candidate/endpoint/saved-speaker/room models and identity helpers.

No background service, database, cloud backend or native playback stack exists.

## Startup and connection flow

1. Initialize local-network permission and current Wi-Fi transport state.
2. Rebuild persistent saved-speaker presentation from app-private preferences.
3. When Wi-Fi is available, probe the last successful endpoint for fast reconnect.
4. Run finite STR discovery and merge discovered candidates with saved entries.
5. Resolve reachable STR endpoints using advertised-port handling plus 8888/17008 fallback.
6. A successful endpoint becomes the current/last-successful endpoint and opens in the constrained WebView.
7. STR's own web application handles playback/business behavior.

No validated Internet connection is required for these local operations.

## Speaker-management flow

The device list is one merged view of discovered and explicitly saved speakers:

- row tap connects/switches through the existing endpoint path;
- assigning a room implicitly saves the reachable speaker;
- built-in rooms are stored as language-neutral IDs and localized only for display;
- custom room text is stored locally as entered;
- deleting removes saved metadata and hides the row for the current view; a later discovery may find the physical speaker again;
- unreachable saved speakers remain representable so the user can retry/manage them;
- room/UI metadata never changes endpoint probing, speaker identity or WebView security.

## Persistence boundary

Persistence is deliberately small and app-private:

- last successful endpoint supports reconnect;
- explicitly saved speakers support durable device management;
- room metadata is presentation-only;
- the dismissed-help preference is UI-only;
- none of this data is uploaded or synchronized by Ragutis STR.

## Dependency direction

The Android wrapper depends on STR's local contracts. STR does not depend on the Android wrapper. There is no hosted Ragutis service.

## Native shell geometry

The native root owns Android system-bar insets. Toolbar/device-list spacing is component-local. The WebView fills the remaining content rectangle and the upstream STR web application owns layout inside that WebView.


## Original Bose-firmware setup

- `BoseSetupProbeActivity`: Android 10+ four-step, model-aware setup wizard plus separate verification and result views.
- `BoseWirelessSiteSurvey`, `BoseWirelessProfileXml`, `BoseWirelessProfilePost`, `BoseSupportedUrlsProbe`: bounded, network-scoped interactions with Bose original firmware. State-changing `addWirelessProfile` runs only once after the explicit user action, with no automatic retry.
- `BoseLanVerification`: bounded same-device recognition after the system returns to the phone's normal Wi-Fi, using local discovery and Bose `/info`.
- `PendingBoseStore`: local, independent original-firmware speaker records (device identity, optional verified IP, room). These are never saved STR endpoints.
- `DiscoveryCycleGuard`, `SpeakerTapPolicy`: JVM-testable policies for finite probing and predictable behavior of STR-enabled versus Bose-only rows.

The production APK retains the original `app.strremote.android` package identity for signed in-place updates. Android 8/9 continue to use the existing STR features, while the Bose setup activity is only reachable from Android 10+. Browser installation guidance opens the upstream STR website.


## Update checking and release channel

One `main` source tree builds `github` and `play` product flavors, both using production `app.strremote.android` and the same app-local storage schema. The build-time `BuildConfig.GITHUB_UPDATES` flag is true only for the `github` flavor; it is not inferred from an Android installer package. Debug variants keep the isolated `.poc` application ID.

- **GitHub flavor:** `AppUpdatePolicy` compares strict GitHub stable versions and approved APK asset names. `AppUpdateChecker` queries the canonical Releases HTTPS API automatically at app start (24-hour success throttle), and the About dialog exposes a manual, immediate check with visible success/current/failure results. A later public release opens an external browser link only on a production package; no silent APK installation. Debug performs metadata checks only.
- **Play flavor:** startup **does not call the GitHub updater**; About instead offers a direct external `https://play.google.com/store/apps/details?id=app.strremote.android` link. Google Play owns installation/update delivery. GitHub source, issue and privacy/support links remain as voluntary informational actions and are not APK update prompts.
- Neither flavor adds a permission, SDK, runtime updater service or backend. Both production flavors must be verified with correct signing keys: the GitHub APK uses the original app key; a Play AAB uses a **separate registered upload key** and must be delivered with the original matching app-signing certificate.

The flavor split is **implemented but not production/Play-device accepted**; see [Tests](TESTS.md), [Release gates](RELEASE_GATES.md) and [Play preparation](PLAY_STORE_PREPARATION.md).

## Optional feedback and model lookup

- `BoseSoundTouchCatalog` offers a local suggestion list of historic Bose SoundTouch models. Selection is not hardware certification; free-form model entry remains possible.
- `BoseCommunityFeedback` builds an optional user-initiated email draft including the selected model/outcome and app/Android versions. A single runtime-decoded project recipient is reused without a complete address literal in public source or docs. Reversible obfuscation limits casual harvesting, not extraction by a motivated party.
- An Android `ACTION_SENDTO` intent opens the mail client; the app neither sends messages in the background nor uses a server. The no-email-client negative case remains deferred under [STR-027](https://github.com/K-Cosmo/ragutis-remote-for-str/issues/7).

## Ragutis branding and embedded WebView layout

The released product name is **Ragutis Remote for SoundTouch Reborn**, with compact launcher/device title **Ragutis STR**. Android package identity `app.strremote.android`, signing continuity and local preferences are retained.

The upstream STR maintainer diagnosed duplicate system insets in the earlier wrapper and accepted the **released v0.3.3+ native-only fix**. Android applies system-bar/cutout padding at the native root; on API 30+ it zeroes only already-handled inset types for WebView while retaining IME updates, and on API 26–29 it consumes legacy handled insets. No private STR DOM/CSS injection or JavaScript bridge remains.

The STR site owns its own CSS; no upstream `--str-safe-top` / `--str-safe-bottom` extension is required or advertised as existing. See [API contracts](API_CONTRACTS.md), [D-047](DECISIONS.md) and [external acceptance in Status](STATUS.md).
