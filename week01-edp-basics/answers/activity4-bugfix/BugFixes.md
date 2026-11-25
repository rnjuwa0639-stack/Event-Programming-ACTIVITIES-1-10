# Bug Fix Documentation

## Bug 1: Blocking the EDT
- **Symptom:** UI freezes for 5 seconds when the button is clicked (cannot move or resize the window).
- **Cause:** `Thread.sleep(5000)` ran inside the `ActionListener`, which executes on the Event Dispatch Thread, so the event loop could not process anything else.
- **Fix:** Run the long operation in a background thread, update the GUI on the EDT.
- **Code Change:** The listener now sets the label to "Processing...", disables the button, and starts `new Thread(...)`. The thread sleeps, then calls `SwingUtilities.invokeLater(...)` to set "Done!" and re-enable the button.

## Bug 2: Missing Listener
- **Symptom:** Clicking "Increment" does nothing.
- **Cause:** No `ActionListener` was registered, so nothing was listening for the button's event.
- **Fix:** Added an ActionListener to the increment button.
- **Code Change:** `incrementButton.addActionListener(e -> { count++; countLabel.setText("Count: " + count); });`

## Bug 3: Wrong Thread
- **Symptom:** Erratic behaviour and possible crashes or stale display, because the label is changed from a non-GUI thread.
- **Cause:** The background loop called `timeLabel.setText(...)` directly. Swing components are not thread-safe and must only be touched on the EDT.
- **Fix:** Used `SwingUtilities.invokeLater()` for the GUI update.
- **Code Change:** The loop builds the text string in the background thread and passes only `timeLabel.setText(now)` to `invokeLater`. I also made `running` `volatile`, disabled the Start button so a second clock thread cannot be started, and made the thread a daemon.

## Bug 4: Memory Leak
- **Symptom:** Memory use grows over time even though the buttons disappear from the screen.
- **Cause:** "Remove All" removed the buttons from the panel but `createdButtons` still held references to them, so they could never be garbage collected.
- **Fix:** Clear the list when removing the buttons.
- **Code Change:** Added `createdButtons.clear();` after `buttonPanel.removeAll();`.
