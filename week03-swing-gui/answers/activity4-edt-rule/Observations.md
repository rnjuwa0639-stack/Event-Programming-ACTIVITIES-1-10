# Activity 4: What I Observed

## FreezeDemo (bad practice)
After clicking "Freeze the UI" the window could not be moved or resized and the button could not be clicked again for 5 seconds. The label never showed "Working..." because the screen could not repaint while the EDT was sleeping. After 5 seconds it jumped straight to "Done!". The `Thread.sleep(5000)` ran inside the listener, which runs on the Event Dispatch Thread, so the event loop could not process any other events.

## FixedDemo (good practice)
After clicking "Do Long Operation" the label immediately showed "Working..." and the button became disabled. The window could be moved and resized freely during the 5 seconds. After that the label changed to "Done!" and the button was enabled again. The sleep ran on a separate thread (`Thread-0`), and only the small GUI update was sent back to the EDT (`AWT-EventQueue-0`) with `SwingUtilities.invokeLater`.

## Self-check
**Q1: Why does Thread.sleep() on the EDT freeze the UI?** The EDT runs the event loop. If it is busy sleeping it cannot process other events, so the UI cannot repaint, respond to clicks or resize.

**Q2: Safe way to update a label from a background thread?** `SwingUtilities.invokeLater(() -> label.setText("..."))`, which schedules the update on the EDT.

**Q3: What happens if you violate the EDT rule?** Best case the UI freezes; worst case it glitches, shows stale data or crashes, because Swing is not thread-safe.

## The two golden rules
1. All GUI creation and updates must happen on the EDT.
2. Never perform long operations on the EDT.
