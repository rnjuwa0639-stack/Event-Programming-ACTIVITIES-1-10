# Activity 6: Final Self-Assessment and Summary

## Learning Outcome 1: Define EDP components
1. **Event:** a signal that something happened. Examples: a button click, a key press.
2. **Event source:** the object that generates the event. Examples: a `JButton`, a temperature sensor.
3. **Event listener:** the object registered to be notified when an event occurs. Examples: an `ActionListener`, a `KeyListener`.
4. **Event handler:** the code that runs in response to the event. Examples: the `actionPerformed` method that updates a label, a method that verifies a PIN.
5. **Diagram:**
   ```
   Event Source --(fires)--> Event --(delivered to)--> Listener --(calls)--> Handler
      JButton               ActionEvent             ActionListener       actionPerformed()
   ```

## Learning Outcome 2: Compare paradigms
6. Procedural code runs statements in a fixed order decided by the programmer. Event-driven code waits and runs handlers when events occur, in an order decided by the user or the environment.
7. The user can interact with any control at any time, and the program stays responsive.
8. Procedural is a better choice for simple batch jobs and scripts with a fixed sequence, such as converting a file or calculating a report, where no interaction is needed.

## Learning Outcome 3: Event loop and queue
9. The event loop is a continuous cycle that takes the next event from the queue and dispatches it to the right handler.
10. The event queue is a first-in-first-out list where incoming events wait until the loop processes them.
11. Handlers run on the loop's thread; if one is slow, every event behind it waits and the interface freezes.
12. The EDT (Event Dispatch Thread) is the single thread that runs the Swing event loop, and all Swing GUI code must run on it.

## Learning Outcome 4: Real-world examples
13. ATM, microwave oven, traffic light, video game, elevator (also smartphone, web browser).
14. **ATM:** Source = card slot; Event = card inserted; Listener = card reader; Handler = read the card data and ask for a PIN.
15. Because users expect to interact freely and in any order, and modern software must react to many inputs such as clicks, network messages, sensors and timers.

**Self-score:** 15 / 15 (answers checked against the Week 1 activities).

---

# One-Page Summary of Week 1

**Key definitions.** *Event*: something that happens. *Source*: creates it. *Listener*: waits for it. *Handler*: the code that responds. *Event queue*: waiting line of events. *Event loop*: takes events from the queue and dispatches them one at a time. *EDT*: the Swing thread that runs the loop.

**Paradigms.** Procedural: program controls the order (`ProceduralCalculator`). Event-driven: user controls the order (`EventDrivenCalculator`, 16 button handlers).

**Diagram.**
```
User action -> Source -> Event -> Queue -> Event loop -> Handler -> GUI update
```

**Rules I learned.** (1) Create and update Swing components only on the EDT (`SwingUtilities.invokeLater`). (2) Never run long work on the EDT; use a background thread and report back with `invokeLater`.

**Helpful code.**
```java
new Thread(() -> {
    doLongWork();
    SwingUtilities.invokeLater(() -> label.setText("Done!"));
}).start();
```

**Common bugs fixed.** Blocking the EDT, a missing listener, updating the GUI from the wrong thread, and a memory leak caused by keeping references to removed components.

**Questions I still have.** How do larger systems with many components keep event flow organised (for example with the Observer pattern, which is next week)? How does the same idea work in JavaFX?
