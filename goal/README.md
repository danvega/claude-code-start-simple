# /goal

A small Java project for trying Claude Code's `/goal` command. Give Claude a
measurable outcome and it can keep working across turns until that outcome is met.

## The project

[SlugGenerator](src/main/java/dev/danvega/slug/SlugGenerator.java) turns a blog title
into a URL slug:

```text
"Spring Boot 4: Modular Auto-Configuration"
  -> "spring-boot-4-modular-auto-configuration"
```

The implementation is a stub. The [14 tests](src/test/java/dev/danvega/slug/SlugGeneratorTest.java)
cover casing, digits, accents, punctuation, separators, invalid inputs, and a
50-character limit. Long titles are cut at a word boundary. A single word longer
than the limit is cut at 50 characters.

Requires Java 25 or later, Maven, and Claude Code with `/goal` available.

## Try it

From this directory, save the original stub and run the tests:

```bash
slug_backup=$(mktemp)
cp src/main/java/dev/danvega/slug/SlugGenerator.java "$slug_backup"
mvn test
```

All 14 tests should be unsuccessful because the method is not implemented.
Keep this terminal open so the backup path is available when you reset.

Start Claude Code:

```bash
claude
```

Then enter this single prompt:

```text
/goal Implement SlugGenerator so mvn test reports 14 passing tests and 0 skipped. Change only src/main/java/dev/danvega/slug/SlugGenerator.java and keep MAX_LENGTH at 50. Show the test summary.
```

Setting the goal starts work immediately. No second prompt is needed.
Limiting edits to the implementation keeps the tests and build configuration intact.

For fewer tool approval prompts, start with `claude --permission-mode auto` instead,
if auto mode is available in your setup. Existing permission rules can also allow
routine actions. A goal does not change the session's permission mode.

## What /goal adds

A normal coding turn can already include several edit, test, and fix cycles.
This example is small enough that it may finish in one turn.

With `/goal`, a separate evaluator checks the completion condition after each turn.
If it is not met, Claude can start another turn without another user prompt.
The evaluator uses the evidence in the conversation; it does not independently
run Maven or inspect files. That is why the prompt asks for the test summary.

This is a compact introduction to the command. Its benefit is more visible on
larger tasks, such as migrating several classes until all callers compile and
the test suite passes.

See the [Claude Code goal documentation](https://code.claude.com/docs/en/goal).

## Check or stop the goal

- Enter `/goal` to see its status.
- Enter `/goal clear` to remove the active goal.

The goal also clears when the evaluator considers it complete or impossible,
or certain errors prevent the session from continuing.

## Reset

Exit Claude Code and use the same terminal where you saved the stub.
These commands replace the implementation with that saved copy, even if the
project is not tracked by Git:

```bash
cp "$slug_backup" src/main/java/dev/danvega/slug/SlugGenerator.java
mvn test
```

All 14 tests should be unsuccessful again.

## Try another title

After the implementation passes, open JShell:

```bash
jshell --class-path target/classes
```

```java
import dev.danvega.slug.SlugGenerator;
SlugGenerator.slugify("What's New in Java 26?");
```
