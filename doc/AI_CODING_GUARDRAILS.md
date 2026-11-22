# AI coding guardrails

These rules are mandatory for AI-assisted development.

## Core rule

Small changes, real tests, real debug data, clear failure descriptions, no blind magic.

## Required behavior

- Analyze the existing implementation before changing it.
- Prefer small, isolated patches over broad rewrites.
- Extend existing structures instead of creating parallel logic or a shadow architecture.
- Reality beats assumption: builds, logs, runtime behavior and reproducible tests outrank AI explanations.
- One goal per iteration where practical.
- No new dependency without documented benefit exceeding maintenance/supply-chain cost.
- No silent architecture, state-management, concurrency or API-contract change.
- Keep user behavior simple; avoid options and hidden special paths without clear value.
- Do not call a change correct merely because it compiles.

## Code-quality guardrails

Avoid unnecessary abstraction layers, one-off generic helpers, duplicate models, copy/paste variants, clever one-liners and speculative optimization. Prefer readable, localizable and debuggable code.

## Risk areas

Treat caching, concurrency, error handling, state management, retry logic, performance optimization and large refactors as high-risk. Measure/observe first.

## Task scope, context and completion

- Select the smallest useful set of source files, relevant normative documents and existing tests for the task. Expand the review when dependencies, trust boundaries or observed failures require it; do not mechanically read every document on every iteration.
- Complete authorized, reversible work through applicable local checks, review and evidence collection instead of stopping after a plan or the first plausible implementation. State consequential assumptions. Stop for material scope changes, owner decisions and irreversible or destructive actions.
- Preserve the existing product terminology, Android/STR architecture, established behavior and DE/EN localization. Reuse current contracts before inventing new ones.
- For UI changes, keep controls, labels and status messages only where they inform an action or state; do not remove accessibility cues, permissions notices, reachability feedback or necessary error recovery for visual minimalism.

## Verifiable handoff

- Summarize the changed files and exact revision, relevant checks and their real outcomes, observed device behavior, remaining gaps and required approvals in a self-contained handoff.
- Distinguish code review, CI/build, simulated/unit tests, owner-reported hardware QA, and Play-delivered or production-signed evidence. A passed stage never implies a later gate passed.

## AI self-review is not acceptance

AI-generated code and AI review can help find issues but cannot establish correctness. Verification requires the evidence defined in `TESTS.md` and `RELEASE_GATES.md`.
