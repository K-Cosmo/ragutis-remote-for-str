# Source of Truth

## Product

Ragutis Remote for SoundTouch Reborn is a thin Android integration layer around the web remote already served by SoundTouch Reborn (STR). The Android app discovers STR speakers on the local network, resolves a reachable STR endpoint and displays the existing STR web UI in a constrained WebView.

## Architectural boundaries

1. STR owns playback/business logic and the phone-remote web application.
2. Ragutis Remote for SoundTouch Reborn owns Android discovery, endpoint selection, local persistence, permission handling and WebView integration.
3. Do not duplicate STR playback/business logic natively without an explicit recorded decision.
4. No analytics, tracking, account system or hosted Ragutis Remote for SoundTouch Reborn backend.
5. Prefer Android platform APIs. A new dependency requires a documented reason and decision.
6. STR discovery uses DNS-SD/mDNS service types `_streborn._tcp.` and legacy `_soundtouchstick._tcp.`.
7. Endpoint probing accepts STR web ports 8888 and 17008; 8888 is preferred when reachable.
8. The local network is the current trust boundary.
9. The native shell owns Android system insets; the embedded STR WebView fills the remaining content rectangle and is not wrapped in product padding.
10. Automatic STR discovery/probing requires a Wi-Fi transport but never requires validated Internet access. A local-only Wi-Fi connection is a valid STR network.
11. Explicitly saved speakers and optional room assignments are local app-private UI metadata. Room metadata never changes speaker identity, endpoint probing, playback/business behavior or WebView security.

## Documentation authority

`/doc` is the only normative project documentation. Spec Kit files orchestrate work but cannot silently change product policy or architecture.

Runtime/build evidence can prove that this documentation has drifted. Such evidence does not create an undocumented new rule: the drift is recorded as a finding and `/doc` must be converged to verified reality before acceptance.

## Brand and embedding (unreleased 0.3.3)

The full independent product identity is **Ragutis Remote for SoundTouch Reborn**, while the Android launcher and compact Devices toolbar show **Ragutis STR**. The public GitHub 0.3.2 and earlier historical releases retain their original names. The Android package and signing identity never change solely for branding. The native root applies safe-area padding once and forwards zeroed handled inset types to WebView; leave upstream STR's normal header CSS untouched. Hardware QA is required before release. See [D-046/D-047](DECISIONS.md).
