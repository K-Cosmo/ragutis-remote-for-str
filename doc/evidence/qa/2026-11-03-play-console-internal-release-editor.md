# Play Console internal-test release editor — signed AAB attached (2026-11-03)

**Evidence:** owner-provided screenshot, Play Console "Internen Testrelease erstellen". The editor lists `Ragutis-0.3.5-play-signed.aab` as a structured `App bundle`, version `16 (0.3.5)`, API `26 oder höher`, target SDK `37`, 4 screen layouts, all ABIs, and 3 required features. It shows "2 von 3 Optimierungen aktiv", proposed private release name "16 (0.3.5)" and **de-DE release notes still placeholder text**.

**Precisely established:** The signed AAB has been **uploaded/attached to the internal-release editor**, where Google Play Console recognizes its high-level metadata. This supersedes older "not uploaded" statements. **Not established:** that release draft was saved, Google completed review, an internal rollout started, a tester was enrolled, the binary was delivered or verified after Play processing. The screenshot does not independently re-hash Play's stored AAB bytes against the known local SHA-256 `7B94567B796D13A8747A2184DEE073C4FC7EFD0F89838414306D9DFC03EB33B8`.

**Next actions (without rollout):** Set a private release name, enter actual release notes in supported languages, save the release as a draft, configure internal tester email list and feedback. Review Play error summary and remaining in-app privacy/backup/WebView and production smoke gates, then obtain owner approval before any actual track rollout. Internal track does not make app publicly searchable.

[Release gates](../../RELEASE_GATES.md). [Google internal testing](https://support.google.com/googleplay/android-developer/answer/9845334?hl=de).
