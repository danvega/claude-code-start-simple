# CLAUDE.md

## What this is

A small order processing library. Plain Java, Maven, no framework.
It exists to be reviewed, not shipped.

## Build

```bash
mvn -o test
```

The `-o` flag runs offline. Everything is already in the local repo.

## Rules

- **Money is `BigDecimal`, never `double` or `float`.** Floating point
  cannot represent most decimal fractions, so totals drift by cents.
- Getters must not hand out internal collections. Return a copy or an
  unmodifiable view.
- If a class overrides `equals`, it overrides `hashCode` too.
- Anything reachable from more than one thread needs a thread safe type.
- Never catch an exception and discard it.

## Style

Small classes. Constructor injection. No static mutable state.
Tests use JUnit 5 and plain assertions.
