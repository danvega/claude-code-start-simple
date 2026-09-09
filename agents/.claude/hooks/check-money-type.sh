#!/usr/bin/env bash
# PreToolUse on Write|Edit.
# Refuses to let money be written into a floating point type.
# CLAUDE.md asks for BigDecimal. This is the version that cannot be argued with.

input=$(cat)

file=$(printf '%s' "$input" | jq -r '.tool_input.file_path // empty')
case "$file" in
  *.java) ;;
  *) exit 0 ;;
esac

# Write sends the whole file in .content, Edit sends the replacement in .new_string
payload=$(printf '%s' "$input" | jq -r '.tool_input.content // .tool_input.new_string // empty')
[ -n "$payload" ] || exit 0

hits=$(printf '%s' "$payload" \
  | grep -nE '(double|float)[[:space:]]+[a-zA-Z]*([Pp]rice|[Tt]otal|[Aa]mount|[Ss]ubtotal|[Dd]iscount)' \
  || true)

if [ -n "$hits" ]; then
  jq -n --arg f "$(basename "$file")" --arg h "$hits" '{
    hookSpecificOutput: {
      hookEventName: "PreToolUse",
      permissionDecision: "deny",
      permissionDecisionReason: ("Money must not be stored in a floating point type. Rejected in \($f):\n\($h)\nUse java.math.BigDecimal.")
    },
    systemMessage: ("Hook denied write to \($f): money in a floating point type.")
  }'
fi

exit 0
