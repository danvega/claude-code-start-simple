# Demo 01: A First Session

Section 02 of the talk. This demo opens Claude Code for the first time and walks through the basic loop: you type, Claude reads, Claude asks, you answer. The code it writes does not matter here. The loop does.

**Watch the loop, not the code.**

## Before you start

- Have the terminal, the desktop app, and the web app all open and signed in.
- Have Spring PetClinic cloned at `spring-petclinic/app`. The README in that folder has the clone command.
- Run `/clear` in any open session so you start from an empty context.

## 1. Three ways in

Show the terminal, the desktop app, and the web app. Point at each, say what it is, and move on. Do not linger. The rest of the demo runs in the terminal.

## 2. Starting up

Show each of these. Say what it does in one line.

| Command | What it does |
| --- | --- |
| `claude` | Start a new interactive session |
| `claude "prompt"` | Start a session with the first prompt already sent |
| `claude -c` | Continue the most recent conversation |
| `claude -r` | Pick a past conversation from a list |
| `claude -p "prompt"` | Print the answer and exit. No session. Good for scripts. |

## 3. Tour the screen

Name the parts. The input box, the status line, the permission mode indicator, the model name. Once the audience knows the names, the rest of the talk gets easier.

## 4. `/status` and `/help`

```
/status
```

Shows the version, the model, the working directory, and the auth method. This is the first place to look when something feels off.

```
/help
```

Lists every slash command. Tell the audience they never need to memorize the list. It is one command away.

## 5. `/model` and `/effort`

```
/model
```

Switch between models. Show the picker.

```
/effort
```

Set how hard the model thinks before it answers.

Talking point: a subscription does not make it all free. Bigger models and higher effort cost more tokens and more time. Pick what the task needs.

## 6. PetClinic: "Read this project"

Move into the PetClinic project and ask Claude to read it.

```
cd ../spring-petclinic/app
claude "Read this project and tell me what it does"
```

The first permission prompt lands here. Let it sit a beat. Let the audience see it before you answer.

Talking point: Claude asks before it acts. This is the trust loop. You decide what it can touch.

## 7. Dictation

Show voice input. Hold the dictation key, describe a task out loud, release. Quick prompts are often faster spoken than typed.

## 8. `/context`

```
/context
```

Shows what is filling the context window right now. Callback to the vocabulary section: tokens and the context window.

Ask the audience: what is the system prompt costing before you type a single word? Point at the split between what the agent knows and what the harness adds around it.

## 9. `/clear`

```
/clear
```

Wipe the context and start fresh. End the demo here. The next section builds on a clean session.
