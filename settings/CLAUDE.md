# CLAUDE.md

## What this is

A single-file Java demo used in the "Claude Code: Start Simple, Scale Smart" talk.
`Hello.java` prints a conference schedule. That is the whole program.

The talk shows how to use Claude Code day to day. It starts with the fundamentals:
the vocabulary that matters, picking a surface, and setting up permissions so you
can trust the tool. Then it builds up to CLAUDE.md, skills, MCP, subagents, hooks,
and goals. You do not need to learn everything at once. You need to know what
matters first and what to add as you grow.

The point of the file is the *language features*, not the output. It shows off
compact source files (JEP 512, final in Java 25) and the new `java.lang.IO` class.

## Running it

```bash
java Hello.java
```

No build step. No `javac`. The launcher compiles it in memory and runs it.
Requires Java 25 or later. This machine is on Java 26.

## Rules for this file

These matter more than usual, because the demo breaks if you "fix" the code.

- **Keep it a compact source file.** No class declaration. The file is the class.
- **No `public static void main(String[] args)`.** It is `void main()`, an
  instance method.
- **No imports from `java.base`.** It is imported as a module automatically.
  `List` and `record` work with no import line.
- **Use `IO.println`, never `System.out.println`.** `IO` is the new
  `java.lang.IO` class and it is half the reason this demo exists.
- **Do not add a `pom.xml`, `build.gradle`, or a `src/main/java` folder.** A
  build file would defeat the entire point.

If you think one of these is a mistake, it is not. It is the demo.

## Style

This is teaching code. It gets read on a slide, at the back of a room.

- Favor clarity over cleverness. A reader has about five seconds per line.
- Keep methods short and named for what they do.
- Comments explain *why* a Java feature is interesting, not what a line does.
- Small helpers over one dense method. `printSchedule()` and `totalMinutes()`
  exist to show that a compact source file can hold real structure.

## Adding to the demo

New talks go in the `talks` list. Keep the list short enough to fit on screen.
If you add a language feature, add a comment at the top of the file saying what
it demonstrates, matching the style of the existing header.
