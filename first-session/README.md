# Demo 01: A First Session

Section 02 of the talk. This demo opens Claude Code for the first time and walks through the basic loop: you type, Claude reads, Claude asks, you answer. The code it writes does not matter here. The loop does.

**Watch the loop, not the code.**

## Before you start

- Have the terminal, the desktop app, and the web app all open and signed in.
- Have Spring PetClinic cloned at `spring-petclinic/app`. The README in that folder has the clone command.
- Run `/clear` in any open session so you start from an empty context.

## 1. Three ways in

Claude Code runs in the terminal, the desktop app, and the web app. The rest of this demo uses the terminal.

## 2. Starting up

The commands you will use most:

| Command | What it does |
| --- | --- |
| `claude` | Start a new interactive session |
| `claude "prompt"` | Start a session with the first prompt already sent |
| `claude -c` | Continue the most recent conversation |
| `claude -r` | Pick a past conversation from a list |
| `claude -p "prompt"` | Print the answer and exit. No session. Good for scripts. |

## 3. Tour the screen

Learn the parts: the input box, the status line, the permission mode indicator, and the model name. Once you know the names, the rest gets easier.

## 4. `/status` and `/help`

```
/status
```

Shows the version, the model, the working directory, and the auth method. This is the first place to look when something feels off.

```
/help
```

Lists every slash command. You never need to memorize the list. It is one command away.

## 5. `/model` and `/effort`

```
/model
```

Switch between models.

```
/effort
```

Set how hard the model thinks before it answers.

A subscription does not make it all free. Bigger models and higher effort cost more tokens and more time. Pick what the task needs.

## 6. PetClinic: "Read this project"

Move into the PetClinic project and ask Claude to read it.

```
cd ../spring-petclinic/app
claude
```

```
Give a new developer a quick overview of this project. Cover what it does, key dependencies, architecture, how the code is organized, and how to build and run it. Use a short heading for each, with 2-3 bullets under it. No code.
```

A clear prompt gets a clear answer. Say who the answer is for, what you want covered, and how long it should be.

The first permission prompt shows up here. Read it before you answer.

Claude asks before it acts. This is the trust loop. You decide what it can touch.

## 7. Dictation

Claude Code takes voice input. Hold the dictation key, describe a task out loud, release. Quick prompts are often faster spoken than typed.

## 8. `/context`

```
/context
```

Shows what is filling the context window right now.

Notice what the system prompt costs before you type a single word. Look at the split between what the agent knows and what the harness adds around it.

## 9. `/clear`

```
/clear
```

Wipe the context and start fresh. Do this between unrelated tasks.
