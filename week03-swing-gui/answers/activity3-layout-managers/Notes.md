# Activity 3: Self-Check Answers

**Q1: When would you choose GridLayout over FlowLayout?**
Use `GridLayout` when you need equal-sized cells in a fixed grid, such as a calculator keypad or a photo gallery. Use `FlowLayout` when you want components to flow naturally and wrap at the edge of the container.

**Q2: Why do we often nest layout managers?**
No single layout manager handles every situation. By nesting panels, each with its own layout, you can build complex UIs (for example a `BorderLayout` shell containing a `GridLayout` sidebar and a `FlowLayout` toolbar).

**Q3: What does BorderLayout.CENTER do differently from the other regions?**
CENTER expands to fill all the space left over. The other four regions only take as much space as they need.
