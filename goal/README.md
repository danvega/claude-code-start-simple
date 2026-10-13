# /goal

Work through a plan with a verifiable definition of complete. Claude implements
one task, adds unit and integration tests, runs `./mvnw verify`, and updates
`PLAN.md` before moving on. The demo uses the same prompt as the goal slide.

## The project

A small Java CLI turns blog titles into URL slugs. The starter already handles
lowercase, digits, and whitespace:

```text
"Spring Boot 4" -> "spring-boot-4"
```

[PLAN.md](PLAN.md) has four unfinished tasks: punctuation and separators, accents,
the 50-character limit, and invalid titles. Each includes acceptance examples.
The behavior from the old 14-test demo now lives in the plan, with a few extra
edge cases. Claude writes the tests as it implements each task.

The starter includes:

- [SlugGenerator](src/main/java/dev/danvega/slug/SlugGenerator.java), the code to extend.
- [SlugCli](src/main/java/dev/danvega/slug/SlugCli.java), the command-line entry point.
- [SlugGeneratorTest](src/test/java/dev/danvega/slug/SlugGeneratorTest.java), two passing unit tests.
- [SlugCliIT](src/test/java/dev/danvega/slug/SlugCliIT.java), one passing integration
  test that launches the packaged JAR in a separate JVM and checks its output and
  exit code. Its `runCli` helper is ready for more cases.

Requires Java 25 or later and Claude Code with `/goal` available. The Maven wrapper
is included; an installed Maven is not required. Run verification before the talk
to download Maven and dependencies while you have a network connection.

## Prepare the demo

From the repository root:

```bash
cd goal
```

Make a disposable copy so the starter stays ready for the next run. Keep this
terminal open; `goal_starter` points back to the original directory.

```bash
goal_starter="$PWD"
goal_demo=$(mktemp -d)
cp -R src .mvn mvnw mvnw.cmd pom.xml PLAN.md README.md "$goal_demo/"
cd "$goal_demo"
./mvnw verify
```

Expect **2 unit tests and 1 integration test**, all passing with none skipped,
followed by `BUILD SUCCESS`. The build is green, but all four plan tasks remain
unchecked: passing the starter tests alone does not satisfy the goal.

Open this demo directory in your editor to show `PLAN.md` beside the terminal.
You can also show the working baseline:

```bash
java -jar target/slug-1.0.0-SNAPSHOT.jar "Spring Boot 4"
```

## Run the goal

Start Claude Code in the demo directory:

```bash
claude
```

For fewer tool approval prompts, use `claude --permission-mode auto` if available
in your setup. A goal does not change the session's permission mode.

Paste the prompt from the slide:

```text
/goal Every task in PLAN.md is marked complete, and ./mvnw verify passes with output shown for each one. A task is not complete until it has a unit test and an integration test covering its behavior. Update PLAN.md after each task. Stop after 25 turns.
```

Setting the goal starts work immediately. The plan makes “output shown for each
one” concrete: after each task, show both test summaries and `BUILD SUCCESS`,
then record the test names and results under completion evidence in `PLAN.md`.

## What to look for

1. Open `PLAN.md` and look at the four unchecked tasks and definition of complete.
2. Run the goal. Follow one task through implementation, new tests in both layers,
   a successful `./mvnw verify`, and its checkbox and evidence update.
3. Show that Claude continues through the remaining tasks without another prompt.
4. Review the completed plan and the final unit and integration test summaries.
   Try a title that exercises the new behavior:

   ```bash
   java -jar target/slug-1.0.0-SNAPSHOT.jar "What's New in Café Java 26?"
   ```

   Expected output: `whats-new-in-cafe-java-26`.

`./mvnw test` runs only the unit tests. `./mvnw verify` also packages the CLI and
runs `*IT` integration tests with Maven Failsafe. Both test phases fail if no tests
are discovered. Maven checks the tests; reviewing the plan and test cases confirms
that each task's behavior is covered.

## What /goal adds

A normal coding turn can include multiple tasks and edit/test cycles. This demo
may still finish in one turn; a task is not the same as a turn.

With `/goal`, a separate evaluator checks the completion condition after each
turn and can start another turn when work remains. It judges the evidence in the
conversation rather than independently running commands or reading files, which
is why this prompt asks for verification output after each task.

“Stop after 25 turns” is a limit stated in the goal and evaluated from the
conversation, not a Maven setting or a fixed tool-call budget. If that limit is
reached first, Claude should report what remains and leave unfinished boxes
unchecked. See the [Claude Code goal documentation](https://code.claude.com/docs/en/goal).

## Check or stop the goal

- Enter `/goal` to see its status and evaluated turn count.
- Enter `/goal clear` to remove the active goal.

## Repeat the demo

Exit Claude Code, then make another copy from the original starter using the same
terminal. The previous demo copy remains available for reviewing its results.

```bash
cd "$goal_starter"
goal_demo=$(mktemp -d)
cp -R src .mvn mvnw mvnw.cmd pom.xml PLAN.md README.md "$goal_demo/"
cd "$goal_demo"
./mvnw verify
```

The fresh copy should again have four unchecked tasks, two passing unit tests,
and one passing integration test.
