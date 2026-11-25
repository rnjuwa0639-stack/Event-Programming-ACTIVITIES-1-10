# Activity 1 Self-Check

**Q1: What is the event source?** The `JButton` named `clickButton`; it generates the `ActionEvent` when clicked.

**Q2: What is the event listener?** The lambda expression `e -> {...}`, which implements the `ActionListener` interface.

**Q3: What is the event handler?** The body of the lambda, the code inside it (`JOptionPane.showMessageDialog(...)`).

**Q4: Where does the `e` in the lambda come from?** It is the `ActionEvent` object that Swing creates and passes to `actionPerformed`; it carries information about the click.
