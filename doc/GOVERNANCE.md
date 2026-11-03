# Development governance

## Workflow

Normal feature work follows:

`Specification → Plan → Tasks → Analyze → Implement → Converge → Tests → Real run → Evidence → Acceptance`

Small maintenance/correctness/documentation changes may use:

`Smallest scoped change → Consistency/Tests → Evidence → Acceptance`

## Status model

`PLANNED → IMPLEMENTED → VERIFIED → ACCEPTED`

- **PLANNED:** agreed work, no code claim.
- **IMPLEMENTED:** code/document change exists.
- **VERIFIED:** required builds/tests/evidence passed.
- **ACCEPTED:** release/change gates satisfied and `/doc` converged.

A compiling change is not automatically verified. A plausible AI explanation is not evidence.

## Documentation authority

- `/doc` contains normative product, architecture, security, compatibility and quality truth.
- the public root `README.md` is a product overview, not a second normative specification;
- root helper files may point into `/doc` but must not duplicate normative content;
- `.specify/` and `specs/<feature>/` are development-process artifacts only;
- `doc/work/` and `doc/evidence/qa/` are dated/historical records.

Historical records preserve the state that existed when evidence/work was captured. Superseded wording inside such a historical record is not current-state drift. Current truth must be read from the normative `/doc` authorities.

## Convergence rule

If code/runtime evidence contradicts current normative documentation:

1. record the discrepancy in `FINDINGS.md`;
2. verify actual behavior;
3. update the relevant `/doc` authority and decision record;
4. rerun the applicable documentation/build/runtime gates;
5. only then mark the change VERIFIED/ACCEPTED.

## Duplication rule

There must be one canonical copy of each normative subject.

- changelog: `doc/CHANGELOG.md` only;
- privacy: `doc/PRIVACY.md` (root `PRIVACY.md` is only a pointer);
- contribution rules: `doc/CONTRIBUTING.md` (root `CONTRIBUTING.md` is only a pointer).

When a convenient public entry point is useful, prefer a short link/pointer over copied text.

## Semantic documentation ownership review

For each documentation-only or release-related PR, explicitly check **meaning as well as syntax**:

- public root `README.md`: current product overview, install/support entry points and a link to the canonical changelog; **no per-release change narrative, provenance log or duplicate policy**;
- `STATUS.md`: current verified state and narrowly scoped evidence references, not a competing changelog or work plan;
- `PLANNED.md`: future execution order and dependencies; completed release history belongs in `CHANGELOG.md` / `RELEASE_GATES.md` / dated QA;
- `BACKLOG.md`: open/deferred work items; do not retain completed implementation as a second change history;
- `BLOCKERS.md`: current blockers only, with explicit scope (GitHub release versus Play rollout);
- `ARCHITECTURE.md` / `API_CONTRACTS.md`: **current** design and contracts, not archived candidate status; `SECURITY.md` / `PRIVACY.md` own their distinct trust/data disclosures;
- `TESTS.md` describes regression criteria, `RELEASE_GATES.md` tracks acceptance, and `evidence/qa/` retains dated execution proof.

Remove duplicate normative paragraphs and ambiguous status words (`planned`, `staged`, `merged`, `verified`, `released`) when they refer to different states. Use links to the authoritative topic instead of copying its text; preserve history in dated evidence/decision records. A green automated link/version check does **not** replace this semantic review.

## Automated documentation consistency gate

`tools/verify-doc-consistency.ps1` is the deterministic drift gate for facts that can be checked mechanically.

It verifies at minimum:

- application `versionName` matches `/doc/STATUS.md` `Development version`;
- public README `Current release` matches `/doc/STATUS.md` `Latest public release`;
- the latest public release has a corresponding `/doc/CHANGELOG.md` heading;
- an ACCEPTED release has no unchecked entries in `RELEASE_GATES.md`;
- an ACCEPTED release is no longer described as the current final-publication step in `PLANNED.md`;
- relative Markdown links in root public entry points and current `doc/*.md` authorities resolve; dated work/QA remain historical snapshots.
- decision and finding IDs are unique in their respective canonical registers;
- public Markdown (including historical records) and Android source/resources contain **no complete email address literal**. Reference contact functionality without publishing mailbox strings. Runtime string encoding is only a superficial anti-harvest barrier, not cryptographic protection.

The script runs locally **and in GitHub Android CI** before compilation. CI verifies version/release metadata and the Android build; human review is still required for semantic drift and private-data risks that cannot be detected deterministically.

## Public AI transparency

Ragutis Remote for SoundTouch Reborn is intentionally AI-assisted during development. Public project material must distinguish development assistance from runtime behavior: AI may help implement, analyze, debug, review and document the project, but acceptance remains evidence-based and human-directed. The shipped Android app has no AI/LLM runtime or AI-service data path unless a future explicit product decision changes that boundary.
