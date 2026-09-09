# Demo 03: Skills and MCP

Section 04 of the talk. Two ways to make Claude Code yours.

A skill teaches Claude **how you do something**. An MCP server gives Claude
**access to something real**. Skills are your process. MCP is your data.

## Skills that I use every day

A skill is a folder with a `SKILL.md` in it. The description tells Claude when to
load it. Nothing loads until it is needed.

```
~/.claude/skills/            <- yours, in every project
  video-packaging/
    SKILL.md
my-project/.claude/skills/   <- the project's, checked in for the team
```

Run `/skills` to see what is loaded.

### What is in mine

I have 36 of these. They live in one repo, grouped by the kind of work.

**[github.com/danvega/skills](https://github.com/danvega/skills)**

#### dev

Everyday development. These are the ones worth stealing first.

| Skill | What it does |
| --- | --- |
| `readme` | Writes a README that fits the project and its readers |
| `pr-description` | Turns a diff into a PR title and description |
| `code-review` | Finds real bugs in a diff, with file references |
| `bug-repro` | Reproduces a bug, captures it in a test, then fixes it |
| `code-tour` | Traces a real request through the code |
| `release-notes` | Turns changes between two versions into notes |

#### ship

A process for new demos and small products. `PRODUCT.md` holds the scope.

`ship-shape` to narrow the idea, `ship-mvp` to write it down, `ship-stack` for the
technical calls, `ship-prototype`, `ship-verify`, `ship-feature`, `ship-retro`.

Not seven required stages. Start wherever the project actually is.

#### spring

| Skill | What it does |
| --- | --- |
| `jackson-3` | Jackson 3 in Boot 4, the new packages and changed defaults |
| `modular-auto-config` | Why your auto-config vanished in the Boot 4 upgrade |

#### blog, newsletter, podcast, video

The workflows behind danvega.dev, Spring Office Hours, and the YouTube channel.
This is where most of my skills are, and it is the part people do not expect.

| Skill | What it does |
| --- | --- |
| `blog-new-post` | Drafts a post in my voice, with the site's frontmatter |
| `blog-seo-opportunities` | Ranks what to write next using Search Console data |
| `spring-office-hours-edit` | Cuts a raw stream down to the podcast mp3 |
| `video-ideation` | Finds and ranks video ideas from real demand signals |
| `video-packaging` | Turns an idea into a title, thumbnail brief, and hook |
| `video-shorts` | Chops a long video into captioned vertical clips |

Talking point: none of these are prompts I retype. I wrote the process down once,
and now it runs. The dev and ship ones will work in your projects. The content
ones are wired to my paths and my accounts, so treat those as examples.

### Install one

Clone the repo, then link the folder you want:

```bash
git clone https://github.com/danvega/skills.git
cd skills
ln -s "$PWD/skills/dev/readme" "$HOME/.claude/skills/readme"
```

The link points back at the checkout, so a `git pull` updates the skill. Use
`cp -R` instead if you would rather copy it. For a team skill, put it in the
project's `.claude/skills/` and check it in.

## MCP

Model Context Protocol. It is the plug between Claude and a system that holds
real state. Without it, Claude guesses from training data. With it, Claude reads
the source.

**Wherever Claude is guessing, plug in the source.**

### The ones people actually use

| Server | What it plugs into |
| --- | --- |
| `github` | Read the real issue, comment on the real PR |
| `postgres` | Query the actual schema, not a guess at it |
| `chrome-devtools` | Drive a real browser: live DOM, console, network |
| `figma` | Build from the design file, not a screenshot |
| `cloudflare` | Manage workers, DNS, and logs on your real account |
| `context7` | Current docs for your framework version, not training data |

Every one of these replaces a guess with a fact. That is the whole pitch.

Grab the exact install command from each server's own docs. They differ. Some run
locally over stdio, some are a remote URL.

### DVaaS: my own server

Dan Vega as a Service. It is my content, exposed as tools.

- 21 tools across five areas: YouTube, blog, speaking, newsletter, podcasts.
- Built with Spring Boot and Spring AI (`spring-ai-starter-mcp-server-webmvc`).
- Remote, streamable HTTP. No API key.
- Source: [github.com/danvega/dvaas](https://github.com/danvega/dvaas)
- Docs: [danvega.dev/mcp](https://www.danvega.dev/mcp)

Add it:

```bash
claude mcp add --transport http dvaas https://mcp.danvega.dev/mcp
```

Then check it connected:

```bash
claude mcp list
```

Or run `/mcp` inside a session to see the server, its tools, and its auth state.

Things to ask it on stage:

```
What are my most popular YouTube videos?
Have I written about Spring Boot 4 yet?
Where am I speaking next?
```

Talking point: I did not train a model on my content. I wrote a small Spring Boot
app and pointed Claude at it. Any API you own can become an MCP server.

### One caution

Every server you add costs context before you type a word. Tool definitions load
up front. Add what a project needs, not everything you can find. Run `/context`
to see the bill.

## The scope flag

Same idea as settings. Pick where the server lives.

```bash
claude mcp add --scope local ...      # just me, just here (default)
claude mcp add --scope project ...    # .mcp.json, checked in for the team
claude mcp add --scope user ...       # me, in every project
```
