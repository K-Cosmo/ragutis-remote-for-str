# Ragutis v0.3.5 — unsigned Play AAB ZIP-structure QA (2026-11-03)

**Source of evidence:** Owner-reported PowerShell ZIP-inspection output for the bundle built using `:app:bundlePlayRelease`, which previously returned `BUILD SUCCESSFUL in 19s` (38 tasks executed, 8 cached, 3 up-to-date). The same draft PR #21 contains the Android source; no app source code changed since prior owner debug-device acceptance.

| ZIP entry | Reported check |
|---|---|
| `BundleConfig.pb` | PASS |
| `base/manifest/AndroidManifest.xml` | PASS |
| `base/dex/classes.dex` | PASS |
| `base/resources.pb` | PASS |

**Gate result:** The four essential expected ZIP entries were **all reported present**. This is scoped ZIP-structure QA, **not** full AAB integrity or built-app manifest content acceptance. The user shared only the four PASS lines; **AAB basename, size, last-modified timestamp and SHA-256 were not reported**. Do not record or invent those values. Local build output was not uploaded for independent byte inspection.

**Next checks:** Record the actual AAB filename/length/SHA-256 locally. Read the **protobuf-packaged** base manifest with Google bundletool (e.g. `bundletool dump manifest --bundle=<path> --module=base`), compare `app.strremote.android`, `versionCode=16`, `versionName=0.3.5`, SDK range and permission/backup/network/exported-activity values with the previously accepted generated merged release manifests. Do not assume protobuf manifest is plain XML. Only after review may the candidate be signed separately using the registered **Play upload** key. Signed AAB and real Play-install update tests remain OPEN.

**No merge, application-code change, signing, Google Play upload or release** occurred in this step. [Release gates](../../RELEASE_GATES.md).
