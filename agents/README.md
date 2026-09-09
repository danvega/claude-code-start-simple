# orders

A small Java order-processing library with intentional bugs. It shows how a
Claude Code review agent can find problems that passing tests miss.

## Get started

You need JDK 26, Maven, and Claude Code. The source targets Java 21, but the
included JVM options require JDK 26.

From this directory, run the tests and start Claude Code:

```bash
mvn test
claude
```

All six tests pass. The agent reviews the code for cases those tests do not cover.

## Use the review agent

In Claude Code, type `@` and select **code-reviewer (agent)**, then submit:

```text
@"code-reviewer (agent)" Review src/main/java for correctness bugs. Report findings without changing files.
```

You can narrow the review to one class:

```text
@"code-reviewer (agent)" Review PricingService.java and its callers.
```

Or start a session that uses the reviewer directly:

```bash
claude --agent code-reviewer
```

## How it works

The definition lives in [.claude/agents/code-reviewer.md](.claude/agents/code-reviewer.md).
It uses Haiku with three tools: `Read`, `Glob`, and `Grep`.

The agent reads the requested code, project rules, and relevant tests. It returns
findings ordered by severity, with file locations, concrete examples, and suggested
fixes. As a subagent, it works in a separate context and returns its report to the
main conversation.

It cannot edit files or run tests. Use a normal Claude Code session to apply fixes
and verify them. Learn more in the
[Claude Code subagent documentation](https://code.claude.com/docs/en/sub-agents).

## Agents vs. skills

The review instructions could also be a skill. The choice depends on how the work
should run:

- A **skill** provides reusable instructions and resources. It normally guides the
  agent in the current conversation, such as applying a review checklist while coding.
- A **subagent** handles a delegated task with its own context, model, and tools,
  then returns a result to the main agent.

This example uses a subagent to give the reviewer Haiku and read-only tools while
the main coding agent keeps its tools for making changes. The reviewer returns
findings; the main agent can apply the fixes.

They can also work together. A skill can run in a subagent using `context: fork`
and `agent: code-reviewer`, and an agent can load skills as reference material.
See [running skills in a subagent](https://code.claude.com/docs/en/skills#run-skills-in-a-subagent).

## Money-type hook

The project also includes a [hook](.claude/hooks/check-money-type.sh), configured in
[.claude/settings.json](.claude/settings.json), that checks Java `Write` and `Edit`
calls for floating point declarations with money-related names. Matching calls are
denied with a message to use `BigDecimal`.

The hook requires Bash and `jq`. It matches text rather than parsing Java, so it
does not replace tests or code review.
