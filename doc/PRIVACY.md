# Privacy

Ragutis Remote for SoundTouch Reborn does not operate a server, create user accounts, collect analytics or transmit telemetry.

The app accesses the local network to discover and connect to SoundTouch Reborn speakers. The embedded STR web application may perform its own network requests required by STR features; Ragutis Remote for SoundTouch Reborn does not collect those requests.

Ragutis Remote for SoundTouch Reborn stores local data in Android app-private preferences. Persisted metadata can include:

- the last successful speaker endpoint;
- explicitly saved speaker host/port/display/model/discovery metadata;
- optional built-in or custom room assignment;
- local UI preferences such as whether the device-management help hint was dismissed.

A custom room name is stored exactly as local user-entered text. Room/UI metadata is presentation-only and is not used for speaker authentication, WebView security or playback behavior.

Saved-speaker, room and UI-preference data is not uploaded or cloud-synced by Ragutis Remote for SoundTouch Reborn.

Removing application data or uninstalling Ragutis Remote for SoundTouch Reborn removes this app-private metadata.


## Bose Wi-Fi setup (since 0.3.0)

The optional setup assistant uses the phone's Android-approved connection to the temporary Bose setup Wi-Fi. The selected SSID and password are passed directly from this app to the speaker's **original Bose firmware** over local, unencrypted HTTP. They are not sent to Ragutis Remote for SoundTouch Reborn servers or analytics services; Ragutis Remote for SoundTouch Reborn runs no such servers. The Wi-Fi password is kept only for the active attempt and is not written to preferences or logs.

After the attempt, this app may locally retain a Bose device identifier, verified local IP address, setup status and user-assigned room to display the speaker separately from STR-enabled devices until STR becomes reachable. The selected Wi-Fi password is never part of that record. Other platform-level backup policies remain governed by Android settings.


## GitHub release update check (released in 0.3.1)

The released Android app can query GitHub's **public** `api.github.com/repos/K-Cosmo/remote-for-str-android/releases/latest` endpoint over HTTPS on app start, at most once per 24 h after a successful response. This requires a direct request to GitHub: GitHub can observe the requesting IP address, standard network information and the minimal `STR-Remote-Android` User-Agent. The app sends no speaker identifier, Wi-Fi SSID, password, usage analytics, account ID or device telemetry. The result is only used to show a local release notification; the last successful check timestamp is kept in app-private preferences. The app does not fetch or install APK files automatically.


## Optional model feedback by email (released in 0.3.1)

On choosing “Anderes SoundTouch-Modell”, a voluntary form asks for the model name and a manually selected success/failure response (no default answer). As the user types, suggestions come from a local static historic Bose catalog; no typed text is sent to a remote service for matching. The model remains freely editable.

Only after the user taps **“Feedback als E-Mail vorbereiten”** does Android open an installed email app with a draft addressed to the app's fixed project-feedback mailbox. The draft includes the entered model, selected outcome, installed Ragutis Remote for SoundTouch Reborn version (with a Debug qualifier when applicable), and Android version/API level. This version information is shared only as part of the draft, not uploaded silently. Users review and send the message themselves; no GitHub account is required.

No Wi-Fi SSID or password, Bose device ID, IP/MAC address, diagnostic trace, attachment, Google account or any other identifier is automatically included. The user’s email client/provider and recipient process normal email metadata (including the sender address) if the user actually sends. No email address, message or selected model is saved by Ragutis Remote for SoundTouch Reborn as an analytics record.

## Scope and deferred cases

The public 0.3.1 release contains the update checker and voluntary feedback functionality described above. No-email-client handling, WLAN-permission refusal and network-failure paths were not included in the six positive signed-APK acceptance checks; they remain [explicitly deferred](https://github.com/K-Cosmo/remote-for-str-android/issues/7), **not verified**. See [Release status](STATUS.md) for current evidence.

## Released in 0.3.2 — historic recipient handling

Published 0.3.2 centralized the project-feedback recipient for both About and SoundTouch setup mail drafts, reconstructed at runtime from reversible character codes rather than committed as a complete plaintext address. This does not prevent extraction from a client APK and does not change user-controlled, explicit sending, network disclosure or the email provider's handling of ordinary sender metadata. No backend is introduced.

## Ragutis branding and WebView insets (0.3.3 candidate, not released)

The planned Ragutis name and native WebView inset correction do **not** introduce additional analytics, server data collection, tracking or a new account. The same existing update-check GitHub endpoint and optional user-triggered email flow remain. Version 0.3.3 changes the future public APK asset basename and keeps historical naming compatibility. Native layout propagation is corrected without injecting private upstream CSS selectors. Platform-level Android backup remains governed by the device's backup policies; changing the display name does not reset app-private data.

## 0.3.3 new optional feedback recipient and room presentation (not released)

The owner has replaced the dedicated feedback mailbox used by the app. The in-development Ragutis 0.3.3 candidate changes the *same centralized, reversible runtime-decoded recipient* for both user-triggered About and SoundTouch model-feedback drafts. The chosen mail application shows the resolved recipient and sends **only after a user confirms**; no new network endpoint, telemetry or backend has been added. The complete mailbox is intentionally not printed in public documentation or committed code. Historical APKs cannot be retroactively changed.

The new room picker is presentation-only: preset and custom room assignments remain in the same Android app-private storage and are not transmitted by the picker. The design now highlights the current selection and adds accessible DE/EN text; it does not change speaker identity, STR remote behavior or network permissions. The existing Android platform-backup caveat still applies.
