# Activity 4 Self-Check

**Q1: Why is `count` an instance variable and not a local variable?** Event handlers run at different times, after `createAndShow()` has finished. A local variable would be out of scope (and lambdas cannot change local variables), while an instance variable persists between events.

**Q2: What does `doClick()` do?** It programmatically simulates a click on the button, which fires its `ActionListener`: the same handler that runs on a real click.

**Q3: Why check `if (count < MAX_VALUE)` before incrementing?** To enforce a business rule: the counter must not go above its maximum value.

## Behaviour
Starts at 0 (orange); `+` increments with a status message; `-` decrements; Reset returns to 0; at 20 the number is red and `+` shows "Maximum reached!"; at 0 `-` shows "Minimum reached!". Keyboard: Up arrow, Down arrow and R.
