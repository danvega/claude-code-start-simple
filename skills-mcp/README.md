# Demo 03: Skills and MCP

Section 04 of the talk. Two ways to make Claude Code yours.

A skill teaches Claude **how you do something**. An MCP server gives Claude
**access to something real**. Skills are your process. MCP is your data.

## Skills

A skill is a folder with a `SKILL.md` in it. The description tells Claude when to
load it. Nothing loads until it is needed.

```
~/.claude/skills/            <- yours, in every project
  my-skill/
    SKILL.md
my-project/.claude/skills/   <- the project's, checked in for the team
```

Run `/skills` to see what is loaded.

Skills are not just for code. Any task you repeat can be one: writing a post,
editing a video, prepping a podcast episode.

For a team skill, put it in the project's `.claude/skills/` and check it in.

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

Things to ask it:

```
What are my most popular YouTube videos?
Have I written about Spring Boot 4 yet?
Where am I speaking next?
```

I did not train a model on my content. I wrote a small Spring Boot
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
