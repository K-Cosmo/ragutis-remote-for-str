# Agent entry point

This file is only a task-specific router. The normative project rules remain in `/doc` and are not replaced by agent instructions.

Read and apply the documents relevant to the actual change rather than loading the entire project before every edit:

- Product behavior, architecture and external contracts: `doc/SOURCE_OF_TRUTH.md`, `doc/ARCHITECTURE.md` and `doc/API_CONTRACTS.md` as relevant.
- Code changes: `doc/GOVERNANCE.md`, `doc/AI_CODING_GUARDRAILS.md` and `doc/CLEAN_CODE.md`; include the applicable security, privacy and compatibility authorities for the affected boundaries.
- Testing, review, release or Play distribution: `doc/TESTS.md`, `doc/RELEASE_GATES.md` and relevant release/Play runbooks.
- An active feature: its applicable `doc/work/` and `specs/` records; these do not override `/doc`.
- A narrow documentation correction: the affected authority and applicable governance/consistency gates, without unrelated bulk reading.

Context selection never waives an applicable normative requirement, mandatory test or owner approval. For code/runtime/documentation disagreements, follow the evidence and convergence process in `doc/GOVERNANCE.md`.
