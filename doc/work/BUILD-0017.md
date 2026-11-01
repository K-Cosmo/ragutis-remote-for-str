# BUILD-0017 — Ragutis 0.3.3: GitHub rename, original-signer signed release and verification

**Scope:** Record the reproducible original-signer local build, completed owner-reported 7/7 signed device QA and **confirmed stable GitHub v0.3.3 publication**. Repository: [K-Cosmo/ragutis-remote-for-str](https://github.com/K-Cosmo/ragutis-remote-for-str). Public [v0.3.3 Release](https://github.com/K-Cosmo/ragutis-remote-for-str/releases/tag/v0.3.3). Historical v0.3.2 artifacts remain unchanged.

## Fixed identities / hard release requirements

| Identity | Value |
|---|---|
| Full product | Ragutis Remote for SoundTouch Reborn |
| Launcher | Ragutis STR |
| Android application ID | `app.strremote.android` (unchanged) |
| Version candidate | `versionName=0.3.3`, `versionCode=14` |
| GitHub project | `K-Cosmo/ragutis-remote-for-str` |
| Public API | `https://api.github.com/repos/K-Cosmo/ragutis-remote-for-str/releases/latest` |
| APK name | `Ragutis-Remote-for-SoundTouch-Reborn-0.3.3.apk` |
| SHA-256 companion | `Ragutis-Remote-for-SoundTouch-Reborn-0.3.3.apk.sha256.txt` |
| **Original app signer SHA-256** | `3CC2E7272D8C2D1433A57C4462DF39ADFE5E8C1931A4A924710C0E4087AA1E32` |

**Keys:** Use the **original GitHub app-signing keystore** for the standalone signed APK. The `remote-str-upload.jks` created for Google Play (upload certificate `41BFFC71...`) is the **wrong key** and must never be used for a GitHub APK expected to update public v0.3.2. Store all keys and passwords locally outside the repository, not in docs, GitHub Actions, chat, build.gradle or shell command arguments. The existing local script prompts for passwords and refuses the wrong signer.

**Known old updater limitation:** v0.3.2's fixed GitHub Releases API URL still points to the former repository and its HTTPS client **does not follow redirects**. This cannot be corrected in an already distributed APK. Affected users must update manually from the new repository. v0.3.3 uses new canonical URLs and normal platform HTTPS redirect handling.

## Prerequisites

1. Merged canonical URL + redirect PR with green exact-head CI and post-merge `main` CI. **Do not sign a branch commit** that does not match the final `v0.3.3` source.
2. Local Windows machine with existing Java/Android Gradle build prerequisites, Android SDK/build-tools and `git`. Phone with **public original-key-signed v0.3.2** installed (package `app.strremote.android`) with existing speaker/room data to verify update preservation. The parallel debug `.poc` package is **not** sufficient.
3. Protected local original release keystore and its **original alias** available; verify certificate before signing. No private key uploads to GitHub, Google Forms or chat.
4. Verify `git status --porcelain` clean in a dedicated detached worktree at the actual final merged `main` SHA.

## Owner-local PowerShell preparation (after PR merge)

Example base paths match the owner's workspace; adapt as needed. These steps **do not publish**.

```powershell
$Repo = "D:\Android\STR\remote-for-str-android" # existing local clone directory may keep its old name
$ReleaseTree = "D:\Android\STR\ragutis-033-release"

git -C $Repo remote set-url origin https://github.com/K-Cosmo/ragutis-remote-for-str.git
git -C $Repo fetch origin --tags --prune
if ($LASTEXITCODE -ne 0) { throw "Git fetch failed" }

$ReleaseHead = (git -C $Repo rev-parse origin/main).Trim()
if ($LASTEXITCODE -ne 0) { throw "Cannot read origin/main" }
Write-Host "Candidate source commit: $ReleaseHead"

if (Test-Path $ReleaseTree) { throw "Release worktree already exists; inspect/remove it explicitly before continuing" }
git -C $Repo worktree add --detach $ReleaseTree $ReleaseHead
if ($LASTEXITCODE -ne 0) { throw "Cannot create release worktree" }

if (git -C $ReleaseTree status --porcelain) { throw "Release source is not clean" }
Select-String -Path "$ReleaseTree\app\build.gradle.kts" -Pattern 'applicationId = "app.strremote.android"','versionCode = 14','versionName = "0.3.3"'
```

**Before signing:** Compare `$ReleaseHead` to the exact tested/approved merged `main` SHA recorded in this document or PR/CI. Do not sign if unexpected commits or failing checks exist.

```powershell
$OriginalAppKeystore = "D:\Android\STR\keys\<original-app-signing-keystore>.jks"
$OriginalAlias = "<original-release-alias>" # not the Google Play upload alias

keytool -list -v -keystore "$OriginalAppKeystore" -alias "$OriginalAlias"
if ($LASTEXITCODE -ne 0) { throw "Original keystore cannot be read" }
# Compare its public SHA256 certificate fingerprint to 3C:C2:E7:27:...:AA:1E:32

Set-Location $ReleaseTree
.\tools\build-signed-release.ps1 -ProjectRoot $ReleaseTree -KeystorePath "$OriginalAppKeystore" -KeyAlias "$OriginalAlias"
if ($LASTEXITCODE -ne 0) { throw "Signing script failed" }
```

PowerShell scripts may throw terminating errors; the script's own built-in fail-closed checks are decisive. Do not paste password or private-keystore content into tickets or chat.

## Release output and signed in-place device QA

Expected paths relative to release worktree:

- `dist/Ragutis-Remote-for-SoundTouch-Reborn-0.3.3.apk`
- `dist/Ragutis-Remote-for-SoundTouch-Reborn-0.3.3.apk.sha256.txt`

The script performs Gradle debug/release build, lint and unit tests, 16-KiB zip alignment, original-certificate SHA-256 verification with `apksigner`, and generates the APK checksum. **Do not call the result accepted until you inspect the final script output and actual file digest.**

```powershell
$Apk = Join-Path $ReleaseTree "dist\Ragutis-Remote-for-SoundTouch-Reborn-0.3.3.apk"
$Checksum = "$Apk.sha256.txt"
if (!(Test-Path $Apk) -or !(Test-Path $Checksum)) { throw "Signed APK/checksum missing" }
Get-FileHash -Algorithm SHA256 -LiteralPath $Apk
Get-Content -LiteralPath $Checksum

$Adb = "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe"
& $Adb devices -l
& $Adb shell dumpsys package app.strremote.android | Select-String "versionCode|versionName"
# STOP unless the existing production (NOT .poc) app is v0.3.2, code 13,
# and its saved speakers and rooms are visible before installation.

& $Adb install -r "$Apk"   # in-place; NEVER adb uninstall or pm clear
if ($LASTEXITCODE -ne 0) { throw "Signed in-place upgrade failed" }
& $Adb shell dumpsys package app.strremote.android | Select-String "versionCode|versionName"
```

**Manual acceptance:** Confirm package `app.strremote.android` is v0.3.3/code 14, original saved speakers and room names survive, STR playback/presets/navigation work, both DE/EN feedback paths point to the new mailbox without sending automatically, the WLAN model selector requires an explicit choice and shows correctly aligned hints, the updated room cards preserve all choices, and About opens canonical GitHub and Issue URLs. Record `PASS / FAIL / NOT TESTED` precisely per scenario. Recheck after restart. A successful Gradle build alone is insufficient.

## GitHub Release publication — sequence performed after separate owner approval

1. Verify *exact* `$ReleaseHead` matches the finalized clean `main`, and no additional changes landed since signing.
2. Create **`v0.3.3` tag pointing to that exact source**; do not rebase or replace prior tags.
3. Use the renamed repository's GitHub **Releases → Draft a new release**, choose tag `v0.3.3`, title `Ragutis Remote for SoundTouch Reborn 0.3.3`.
4. Upload the signed APK and SHA-256 companion listed above. Use release notes summarizing the UI/inset fixes and manual-update limitation, and the unofficial/non-Bose affiliation. Mark stable, not prerelease, only after successful QA and owner approval.
5. Download both public assets again; verify published APK digest equals the local signed APK and released source/tag equals `$ReleaseHead`; check public `/releases/latest` is `v0.3.3`. Then update README/status/changelog evidence in a subsequent documentation commit, without changing the signed source tag.

## Signed candidate evidence and completed public release (2026-11-01)

The owner supplied successful local output from the original-signer build script:

- Signed source commit: `44bc6d0c518c9c0dbb01bf109589f3b5cbd29d65` (**same as GitHub `main`** at this review).
- Local APK file: `Ragutis-Remote-for-SoundTouch-Reborn-0.3.3.apk`.
- Local APK SHA-256: `045c5e8e6b521f92c398a3051deffd4b997bad78214b9337e149492e36d2de3f`.
- Corresponding local `.apk.sha256.txt` file reported by the script.
- Original APK release certificate is enforced by the script (do not confuse with Play upload signer). Private key and APK bytes were not provided to the assistant.

**Owner confirms all seven signed device smoke categories PASS**: update/data preservation, STR control, room selection, Wi-Fi wizard, project feedback, DE/EN UI and correct GitHub links. This is **the owner's real-device QA report**, not raw ADB/keystore/bin-level evidence independently inspected by the assistant. Read [QA record](../evidence/qa/2026-11-01-ragutis-0.3.3-signed-qa.md) and [Release gates](../RELEASE_GATES.md) for precise limitations.

**Signed candidate state: ACCEPTED for 7/7 reported smoke categories. Public GitHub v0.3.3 release: COMPLETED after separate express owner approval.** The owner then ran the `gh` publication and independent download/verification commands. This is not approval or evidence of Google Play publication, an upstream maintainer email, or a community announcement.

**Publication independently verified via GitHub API:** release [v0.3.3](https://github.com/K-Cosmo/ragutis-remote-for-str/releases/tag/v0.3.3) ID `430710031`, published 2026-11-01T16:36:19Z, stable and latest. Annotated Git tag object `d0d0cc32c9fa899ac6fbdfe97c0e509b05faca47` targets **exact signed source commit** `44bc6d0c518c9c0dbb01bf109589f3b5cbd29d65`. APK asset `698874479`, size 1,201,797 bytes, GitHub-reported SHA-256 `045c5e8e6b521f92c398a3051deffd4b997bad78214b9337e149492e36d2de3f` matches locally tested original. Checksum companion asset `698874480`, size 114 bytes, own GitHub digest `c456d2de189eb560aa650e31b164978f2af66926824d35a575cf46ab76c30e65`. Owner reports successful fresh public download/checksum comparison; private APK bytes were not independently available to assistant. The historical v0.3.2 and key remain untouched.

GitHub `releases/latest` now resolves to public stable `v0.3.3`. Broader hardware compatibility, older Android API and negative-path coverage remain deferred/not certified; downstream Google Play/STR maintainer email/community recruitment are separate workflows.

References: [Release gates](../RELEASE_GATES.md), [Decision log](../DECISIONS.md), [Building script](../../tools/build-signed-release.ps1), [Public v0.3.2](https://github.com/K-Cosmo/ragutis-remote-for-str/releases/tag/v0.3.2).
