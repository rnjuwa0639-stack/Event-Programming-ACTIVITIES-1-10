# Activity 2: Build the Event Loop

## Answers to the questions

1. **What happened to the other events while I was stuck on the slow handler?**
   They sat in the queue, unprocessed. The event loop handles one event at a time, so nothing behind the slow handler can run until it finishes.

2. **What would a user see in a real application?**
   The window would stop responding: buttons do not react, the window cannot be moved or repainted, and the operating system may show "Not Responding". All the clicks made meanwhile would suddenly be processed afterwards.

3. **How could this problem be fixed?**
   Move the long-running work to a background thread (or a worker/Task), and only send the small result back to the event loop thread to update the GUI. The handler returns immediately so the loop keeps going.

## Reflection

The event queue is a waiting line where every event (a click, a key press, a timer tick, a resize) is placed in the order it happened. The event loop is the process that repeatedly takes the next event from the front of that queue and passes it to the handler that is registered for it. Because the loop deals with only one event at a time, any handler that takes a long time holds up everything behind it in the queue. In a GUI this means painting, clicking and resizing all stop, so the program appears frozen. For this reason the event loop must never be blocked: handlers should be short, and long tasks such as downloads or database queries must run on separate threads. This is why Swing has the Event Dispatch Thread (EDT) and JavaFX has the Application Thread.
