# Activity 5 Self-Check

**Q1: Why `.trim()` on text fields?** It removes leading and trailing whitespace; without it a field containing only spaces would pass the "not empty" validation.

**Q2: What does `passwordField.getPassword()` return?** A `char[]`, not a `String`. This is more secure, because a `String` is immutable and can stay in memory, whereas a `char[]` can be wiped after use.

**Q3: Why `else if` for the gender radio buttons?** Only one radio button in a `ButtonGroup` can be selected, so once the selected one is found there is no need to test the others.

## Test checklist (all pass)
- Submit with empty name -> "Error: Please enter your name"
- Invalid email -> "Error: Please enter a valid email"
- Password shorter than 6 characters -> error message
- Everything valid -> confirmation dialog with all the data
- Clear -> all fields empty; Exit -> confirmation, then closes

## Note on NetBeans
The guide builds this form by dragging components in NetBeans (Matisse). `RegistrationForm.java` here builds the identical form (same variable names and the same three handlers) with ordinary Swing code, so it runs from VS Code. If your lecturer insists on a NetBeans project, create a JFrame Form called `RegistrationForm` as in Step 5.1-5.3 of the guide and paste the three handler methods from this file.
