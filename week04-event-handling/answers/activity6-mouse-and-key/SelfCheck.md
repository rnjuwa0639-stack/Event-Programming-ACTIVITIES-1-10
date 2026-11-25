# Activity 6 Self-Check

**Q1: MouseListener vs MouseMotionListener?** `MouseListener` handles clicks, presses, releases and entering/exiting a component. `MouseMotionListener` handles movement (`mouseMoved`) and dragging (`mouseDragged`).

**Q2: Why `MouseAdapter` instead of implementing `MouseListener`?** `MouseListener` has five methods; `MouseAdapter` gives empty default versions so you override only the ones you need.

**Q3: Why might a KeyListener not fire?** The component must have keyboard focus. Make it focusable and call `requestFocusInWindow()` (or click it first).

**Q4: What does `e.getKeyCode()` return?** An integer code for the physical key, such as `KeyEvent.VK_UP`, `KeyEvent.VK_A` or `KeyEvent.VK_SPACE`.
