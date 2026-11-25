# Activity 5: Self-Check Answers

**Q1: How many different layout managers did you use?**
Three explicit layout managers plus one implicit: `BorderLayout` (main frame, header, status bar, stat cards), `GridLayout` (sidebar and the three stat cards row), `BoxLayout` (vertical stacking in the main content), and `FlowLayout` implicitly (the default for a plain `JPanel`).

**Q2: Why do we use Box.createVerticalStrut(20)?**
It inserts an invisible 20-pixel-tall spacer between components, which is cleaner than adding empty panels or margins.

**Q3: What is the purpose of setPreferredSize() on the sidebar?**
It gives the sidebar a fixed width of 180 pixels. Without it, `BorderLayout.WEST` would shrink the sidebar to the minimum needed by its contents.

**Customisation:** the welcome text and the status bar show my name and admission number.
