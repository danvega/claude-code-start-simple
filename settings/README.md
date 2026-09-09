# How `.claude` folders work

Claude Code reads config from two places. Your home folder, and the project you
are in.

```
~/.claude/                     <- you, everywhere
  CLAUDE.md                      your instructions for every project
  settings.json                  your settings for every project
  commands/                      your slash commands
  skills/                        your skills

my-project/
  CLAUDE.md                    <- the project, for everyone (NOT in .claude)
  .claude/
    settings.json                team settings, checked in
    settings.local.json          your settings here only, gitignored
    commands/                    project slash commands
    skills/                      project skills
```

## The one confusing part

`CLAUDE.md` is split across the two levels, and it is not symmetric.

| Level | Path |
|---|---|
| Yours, everywhere | `~/.claude/CLAUDE.md` (inside the folder) |
| This project | `my-project/CLAUDE.md` (at the root, not inside `.claude`) |

Both get loaded. They stack, they do not replace each other. Your personal rules
apply on top of the project's rules.

## Settings override in order

Settings are different. Later wins.

```
~/.claude/settings.json          your defaults
        v
.claude/settings.json            the team's settings, checked in
        v
.claude/settings.local.json      your overrides, gitignored
```

Read it as "most general to most specific." The file closest to the work wins.

Two useful details:

- Permission lists merge instead of replacing. The team's `allow` rules and your
  personal `allow` rules add together.
- Anything else, like `model`, is a straight override. The last file to set it
  wins.

That is why `settings.local.json` is the right home for secrets, machine paths,
and personal preferences. It is also why it is gitignored.

## What goes where

| I want to... | Put it in |
|---|---|
| Set a rule for every project | `~/.claude/CLAUDE.md` |
| Set a rule for this project, for the team | `CLAUDE.md` at the repo root |
| Share build permissions and hooks with the team | `.claude/settings.json` |
| Set an API key or a local path | `.claude/settings.local.json` |
| Add a slash command for the team | `.claude/commands/` |

## This project

A working example of all of it.

- [CLAUDE.md](CLAUDE.md) tells Claude not to "fix" a compact source file into a
  normal Java class.
- [.claude/settings.json](.claude/settings.json) has permissions and a format
  hook the team shares.
- [.claude/settings.local.json](.claude/settings.local.json) has a fake API key
  and a machine specific `JAVA_HOME`. It is in `.gitignore`.

Run the demo:

```bash
java Hello.java
```
