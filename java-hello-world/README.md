# Demo 02: Permissions + Plan Mode

Section 02 of the talk. This demo shows how Claude Code asks before it acts and how you control what it can do. The Java program is a prop. It exists, so the permission prompts have something to fire on.

**Decide what it can do before you let it run.**

Settings files get a short look here. The full walkthrough lives in the `settings/` demo.

## Before you start

- This folder starts empty. Claude writes the program live.
- Make sure `java` is on your path so Claude can compile and run what it writes.
- Start with no `.claude/` folder in here. You want every prompt to fire.

## 1. Plain Java program

Start a Claude session in this folder and paste this prompt.

```text
Create a Hello World program in Java that uses unnamed classes and run it.
```

Claude will ask permissions for everything it needs to do to achieve the task. This is the default permissions mode, and it's something you want to avoid going forward because it will slow you down.


## 2. Auto Mode

After you have watched the process, switch to auto mode and see the difference. 

## 3. Skip permissions

Exit and start again with the flag.

```
claude --dangerously-skip-permissions
```

Note the two hyphens. Autocorrect likes to turn them into a dash.

Ask for the same program. No prompts this time.

## 4. Plan mode

Start a normal session again.

```
claude
```

Press **Shift + Tab, twice**. The mode indicator changes to plan mode.

Then ask for something with a few moving parts.

```
Add a Greeter class that takes a name and returns a greeting. Update the main method to use it.
```

Plan mode is read-only. Claude explores the files, asks clarifying questions, and writes a plan. Nothing changes on disk until you approve.

Explore, clarify, plan, approve. Use plan mode when you want to see the thinking before the doing.

## 5. Settings side by side

Compare the two settings files. The `settings/` demo covers them in depth.

| File | Tracked in git | Holds |
| --- | --- | --- |
| `.claude/settings.json` | Yes, committed | Team rules that travel with the project |
| `.claude/settings.local.json` | No, gitignored | Your personal overrides and anything secret |

The project file is the shared contract. The local file is yours.

## 6. `/permissions`

```
/permissions
```

Shows the allowed, ask, and deny lists that are active right now. Add a rule from here, and the next matching command runs without a prompt.

Permissions are not all or nothing. Allow the safe stuff, ask on the rest, deny what should never run.
