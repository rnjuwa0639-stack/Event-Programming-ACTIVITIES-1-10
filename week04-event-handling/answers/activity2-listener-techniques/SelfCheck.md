# Activity 2 Self-Check

**Q1: Best listener style for a simple button action?** A lambda expression: concise, modern and readable.

**Q2: When would you use a named class instead of a lambda?** When the same listener logic is shared by several components, or when the listener must keep its own state.

**Q3: What does `e.getSource()` return?** A reference to the component that fired the event (for example the `JButton` that was clicked).

**Q4: What does `e.getActionCommand()` return?** A `String` command for the event; for buttons it is normally the button's text.

## Which style should you use?
| Style | When to use | Verbosity |
|---|---|---|
| Named class | Listener reused across components, or it needs state | Medium |
| Anonymous inner class | Legacy code, or when `this` of the listener is needed | High |
| Lambda | Modern code, short handlers | Low |
| Method reference | The handler already exists as a method | Low |
