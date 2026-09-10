# Slug generator plan

Build on the starter's lowercase, digit, and whitespace handling. Keep those
behaviors working and keep `MAX_LENGTH` at 50. Work through these tasks in order.

## Definition of complete

For each task:

1. Implement the behavior and add unit tests in `SlugGeneratorTest`.
2. Add integration tests in `SlugCliIT` that run the packaged CLI and cover the
   same behavior. Calling `slugify` directly is a unit test, not an integration test.
3. Run `./mvnw verify` with no tests skipped. Show the unit test summary,
   integration test summary, and `BUILD SUCCESS` in the conversation for that task.
4. Only then check its box and add the test method names and verification result
   to the evidence section below. Update this file before starting the next task.

Preserve existing tests and keep both Maven test phases enabled. Add enough tests
to cover every acceptance example below; one test per layer is the minimum,
not a reason to leave behavior uncovered. If verification fails, fix it before
marking the task complete. At the 25-turn limit, report progress and leave any
unfinished tasks unchecked.

## Tasks

- [ ] **1. Normalize punctuation and separators.** Remove straight and curly
  apostrophes without splitting a word. Treat other punctuation, underscores,
  and repeated hyphens as separators; collapse runs and trim separators at both
  ends. `What's New in Java 26?` and `What’s New in Java 26?` both become
  `whats-new-in-java-26`; `Spring Boot 4: Modular Auto-Configuration` becomes
  `spring-boot-4-modular-auto-configuration`; `Building_a_CLI_with_Java` becomes
  `building-a-cli-with-java`; `  --100% Test Coverage!!  ` becomes
  `100-test-coverage`.
- [ ] **2. Fold accents to ASCII.** Normalize accented Latin letters, including
  combining accents, before filtering to ASCII letters, digits, and separators.
  `Café Con Leche` becomes `cafe-con-leche`; `Déjà Vu` becomes `deja-vu`;
  `Cafe\u0301 Con Leche` (a combining acute accent after the `e`, not the literal
  backslash sequence) also becomes `cafe-con-leche`. Both test layers must cover
  precomposed and combining accents.
- [ ] **3. Limit slugs to 50 characters.** Keep the longest sequence of complete
  words that fits, with no trailing hyphen. Cut the first word at 50 characters
  if that word alone exceeds the limit. `Everything You Need to Know About Spring
  Boot 4 and the New Modular Auto Configuration` becomes
  `everything-you-need-to-know-about-spring-boot-4`; 60 `a` characters become
  50 `a` characters. An exactly 50-character slug stays unchanged, including
  when the next character in a longer title would be a separator.
- [ ] **4. Reject unusable titles.** `slugify` throws `IllegalArgumentException`
  with a useful message for null, empty, whitespace-only, or punctuation-only
  titles. For unusable titles passed as an argument, the CLI exits with code 2,
  writes a useful `Error:` message to stderr, writes nothing to stdout, and shows
  no stack trace. Cover null in a unit test; use empty, whitespace-only, and
  punctuation-only arguments in both test layers.

## Completion evidence

After each task, append its number, unit and integration test method names,
and the actual `./mvnw verify` summaries (counts, no skips, and build result).
Do not fill this in before running the tests.
