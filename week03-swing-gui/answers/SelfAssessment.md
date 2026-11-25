# Week 3: Final Self-Assessment

## Learning Outcome 1: Create a functional Swing application with a main window
Rate yourself 1–5: 4
Minimal code to create a JFrame from memory:
```java
import javax.swing.*;

public class FirstWindow {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("My First Swing App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
```

## Learning Outcome 2: Add, customize, and arrange components using layouts
Rate yourself 1–5: 3
| Layout | Use Case |
|---|---|
| FlowLayout | A row of toolbar buttons that wraps when the window is narrow |
| BorderLayout | Main window shell with header, sidebar, content and status bar |
| GridLayout | Calculator keypad or photo gallery with equal cells |
| BoxLayout | A vertical stack of items, or a horizontal row with glue and spacers |

## Learning Outcome 3: Ensure GUI code runs on the EDT
Rate yourself 1–5: 4
1. All GUI creation and updates must happen on the EDT (`SwingUtilities.invokeLater`).
2. Never perform long operations on the EDT; run them in a background thread and report back with `invokeLater`.

## Learning Outcome 4: Build a static, non-interactive UI layout
Rate yourself 1–5: 3
`StudentPortal.java` compiles and shows a header, sidebar, stat cards, notifications and status bar.

Did your StudentPortal.java run successfully? Yes