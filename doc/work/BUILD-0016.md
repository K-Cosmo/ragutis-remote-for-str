# BUILD-0016 — Ragutis branding and STR WebView native insets (0.3.3 candidate)

## Owner and STR maintainer input (2026-11-01)

The STR maintainer's second direct response confirms the previous 0.3.1/0.3.2 requirements have been satisfied: distinct older app label, unofficial DE/EN About notice and an English wizard. He intends to list the previous **Remote for STR** GitHub Android 8+ app in upstream Community Apps but also observes that its name is confusingly similar to the adjacent iOS **STR Remote**. He requests a separate product name and invites the Android project to create its own [Show-and-tell discussion](https://github.com/JRpersonal/streborn/discussions/categories/show-and-tell). Questions about embedding and user support should be discussed there.

The maintainer specifically traces the odd top/bottom STR WebView spacing to `applySystemBarInsets()` applying native root padding and then returning unchanged WindowInsets. As a result, STR's browser-oriented `env(safe-area-inset-top)` and bottom value are effectively applied again inside WebView. He proposes consuming the already handled system-bar insets, then **removing** the forced 10px header CSS override and restoring STR's own 16px normal header. Only if necessary after testing did he offer future documented `--str-safe-top` / `--str-safe-bottom` variables with normal `env()` fallbacks.

The owner has now selected the **long full name `Ragutis Remote for SoundTouch Reborn`** and **short Android launcher label `Ragutis STR`**. The same compact name is used inside the Devices toolbar, while the native About dialog shows the full product name.

## Implementation (not yet device accepted)

- Change future Android version metadata to `0.3.3 / 14`. The previous public signed GitHub 0.3.2 remains at code 13 under its historical product identity. No released tag/APK is modified.
- Preserve Android `applicationId=app.strremote.android`, package namespace, original release-signing identity, saved speaker/room data and GitHub canonical API/repository URLs.
- Localize DE/EN product, launcher, title, discovery-permission, update notification, setup assistant and voluntarily composed feedback version labels; keep bilingual independent-community/Bose trademark disclosures. Do not place the long 36-character label in the narrow toolbar.
- Replace the old private-STR-DOM JavaScript style override entirely. For API 30+, the native root handles `systemBars|displayCutout` padding, then dispatches a `WindowInsets.Builder(...).setInsets(handled, Insets.NONE).build()` result to children, preserving IME insets. On API 26–29, consume system window insets; on 28–29 also consume the cutout. This differs intentionally from the maintainer's literal `WindowInsets.CONSUMED` suggestion because [Android's official WebView inset guidance](https://developer.android.com/develop/ui/views/layout/webapps/understand-window-insets) recommends zeroing when forwarding changes, preventing stale IME/viewport padding. No dependency on upstream DOM element IDs remains.
- Future GitHub APK artifact name `Ragutis-Remote-for-SoundTouch-Reborn-0.3.3.apk` (with corresponding SHA-256 companion). Keep the previous `Remote-for-STR-<version>.apk` and `STR-Remote-<version>.apk` patterns in the update checker for history/compatibility. No changed GitHub repo slug, release API or service.
- Play Store preparation PR #10 and old Play-only code-14 candidate PR #12 stay **DRAFT/ON HOLD**; do not publish the outdated version-only AAB, reuse its source or silently merge it. Google Play's 30-character listing-name limit will require a separately approved concise storefront title: do not truncate the full project name arbitrarily.

## Tests and gates — must not be marked PASS without real evidence

1. CI (Gradle compile/lint/unit, documentation drift, privacy guard) on exact candidate commit; new and legacy release asset parsing test, composed feedback brand/version assertions.
2. Physical phone (currently tested Android modern version) with both gesture and 3-button navigation: root top/bottom padding once, STR original 16px header, play/pause, speaker switch, presets, both panels and no horizontal/vertical clipping. Capture sanitized before/after screenshots.
3. IME keyboard show/hide repeatedly, rotate portrait/landscape, notch/cutout, fullscreen/navigation gestures and light/dark themes. Distinguish `NOT TESTED` from actual passes.
4. Android 8/9 legacy system insets acceptance if hardware/emulator is available. Lack of device is explicit deferred evidence, not blanket certification.
5. DE and EN launcher label **Ragutis STR**, Devices title and About info entry, full product name, trademark/unofficial notice, email body/version field and Wi-Fi wizard page. Check no accidental full mailbox plaintext in docs/source.
6. Build exact clean-source **original-signer** APK when owner permits. Install it over existing signed GitHub 0.3.2 **without uninstall**: saved room/speaker metadata and STR controls persist.
7. Only after owner acceptance: update any upstream Community Apps entry and make the separate Show-and-tell post; request any custom CSS API solely if native-only fix fails proven tests.

## Upstream message outline — not yet posted

- Name: **Ragutis Remote for SoundTouch Reborn** (Android launcher **Ragutis STR**), independent/unofficial community client for the existing STR WebUI.
- Thanks for the inset diagnosis. Native root now zeroes already handled system-bar/cutout insets for WebView; no private HTML/CSS override, STR's 16px stays unchanged.
- Describe tested Android versions, exact signed release link, device models and user-visible caveats **only after QA**. Ask whether `--str-safe-top`/`--str-safe-bottom` are still necessary based on actual evidence.
- Route general usage/support discussion here; link specific bug reports to the app repository Issues. No statement that upstream endorsed or trademark-cleared the Android app.

## Third small UI adjustment — SoundTouch model selection (2026-11-01)

Owner-supplied three wizard screenshots show SoundTouch 20 III and SoundTouch 10 carrying „geprüft“ in the *dropdown* although an explicit verified status is already shown below; selecting **Anderes SoundTouch-Modell** hides the status view, leaving the card too close to the dropdown. Change **presentation only**: display order SoundTouch 10, SoundTouch 20 III, then Other; no extra verified/checked text in option labels; keep the existing verified notice for both tested models and render a short localized unverified notice for Other in the same position. Leave detailed caution in the instruction card. Preserve enum IDs, default 20 III, current selected model, model-dependent instructions, verification semantics and protocol. Unit test covers order/mapping; require DE/EN device screenshots and simple model-switch regression before acceptance. The user has **not** yet reported testing this change.

## Status

**IMPLEMENTED in a Draft PR, no device QA, no signed release, no Google Play upload and no upstream discussion posted.** Await exact CI, device screenshots and owner approval for merge or publication. Public v0.3.2 and Play signing configurations are unaffected.

## Second-round 0.3.3 extension — owner request (2026-11-01)

**Owner-reported first debug QA:** all seven requested categories PASS on the earlier Ragutis 0.3.3 debug head: name/About, top/bottom STR spacing, controls, system navigation, keyboard/orientation/themes and bilingual setup. This does **not** establish signed upgrade acceptance, legacy API coverage or acceptance of the subsequently changed code.

**Follow-up implementation:** rotate the centrally and reversibly decoded project-feedback recipient for both opt-in mail drafts; retain the existing email privacy guard and require no full recipient literal in public source or documents. Reuse the existing WLAN picker two-line rounded selection-card design in the native room picker with current-selection highlights, scroll and accessible DE/EN labels. A pure `RoomPickerPolicy` preserves stored IDs, none/custom semantics and is unit-tested. Existing custom names are prefilled; invalid empty input leaves the editor open.

**New QA required:** Compare both mail action recipients with the newly created owner mailbox, check that messages are only drafts, inspect the text and version. For rooms, validate all six presets, no room, custom names (including re-edit and long names), scroll/selection states and preservation across restart for STR and pending Bose devices. Existing Android 0.3.2→0.3.3 signed-upgrade gate remains open. Older phone/API validation also remains open where not verified.

**Release control:** remain in the same unpublished 0.3.3 candidate; no merge, new GitHub tag/APK or Google Play AAB may occur without explicit owner authorization.
