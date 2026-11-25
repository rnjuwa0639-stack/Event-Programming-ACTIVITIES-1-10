# Week 4: Final Self-Assessment

**Learning Outcome 1: Implement ActionListener using multiple techniques**
Rate yourself 1–5: 4
Three ways to implement an ActionListener: (1) a named class that implements `ActionListener`; (2) an anonymous inner class; (3) a lambda expression (a method reference is a fourth, shorter form).

**Learning Outcome 2: Handle state changes in checkboxes, radio buttons and text fields**
Rate yourself 1–5: 3
- Listener for checkbox state changes: **ItemListener** (`itemStateChanged`)
- Listener for text field changes: **DocumentListener** (on the field's `Document`)

**Learning Outcome 3: Differentiate between listener interfaces**
Rate yourself 1–5: 4

| Component | Listener |
|---|---|
| JButton | ActionListener |
| JCheckBox | ItemListener (also ActionListener) |
| JTextField | DocumentListener (ActionListener for the Enter key) |
| Mouse hover | MouseListener (`mouseEntered` / `mouseExited`) |
| Key press | KeyListener (`keyPressed`) |

**Learning Outcome 4: Build an interactive Swing application**
Rate yourself 1–5: 4
Did the Interactive Registration Form work? **Yes.** `RegistrationForm.java` validates name, email and password, gathers the radio, checkbox and combo-box values, and Clear and Exit both work.
