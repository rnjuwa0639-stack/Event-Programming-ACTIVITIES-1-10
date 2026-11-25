# Activity 3 Self-Check

**Q1: What event does a JCheckBox fire when toggled?** An `ItemEvent`, handled with an `ItemListener` and `itemStateChanged()`.

**Q2: ItemListener vs ActionListener?** `ItemListener` fires when a component's selected state changes (checkbox, radio button, combo box). `ActionListener` fires when the user performs an action (click, Enter). Checkboxes and radio buttons fire both.

**Q3: Why does DocumentListener need all three methods?** It is not a functional interface: it has three abstract methods, and lambdas only work with interfaces that have exactly one.

**Q4: When does `insertUpdate` fire?** Whenever text is inserted into the document, including typing a character or pasting text.

## Troubleshooting notes
- Add the `DocumentListener` to the **Document** (`textArea.getDocument()`), not to the component.
- Put all radio buttons in the same `ButtonGroup` or several can be selected.
- An `ItemListener` fires once per state change; a radio button switch fires twice (deselect old, select new), which is normal.
