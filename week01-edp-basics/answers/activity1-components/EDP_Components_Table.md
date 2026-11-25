# Activity 1: Spot the Components

Each row identifies the **Event Source**, the **Event**, the **Listener** (the part that is waiting for it) and the **Handler/Action** (what actually runs).

| System | Event Source | Event | Listener | Handler / Action |
|---|---|---|---|---|
| ATM | Keypad button | Button pressed | ATM software | Verify PIN |
| ATM | Card slot | Card inserted | Card reader | Read card data |
| ATM | Cash dispenser | Cash taken | Sensor | End transaction |
| Microwave oven | Start button | Button pressed | Control board | Start magnetron and countdown timer |
| Microwave oven | Door switch | Door opened | Door interlock sensor | Stop heating and pause timer |
| Microwave oven | Timer | Countdown reaches 0 | Controller | Stop heating and beep |
| Traffic light | Timer | Interval elapsed | Signal controller | Switch phase (green, amber, red) |
| Traffic light | Road sensor (inductive loop) | Vehicle detected | Signal controller | Extend or request the green phase |
| Traffic light | Pedestrian button | Button pressed | Signal controller | Schedule the walk phase |
| Video game | Keyboard / gamepad | Key or button pressed | Game input handler | Move the character |
| Video game | Collision detector | Collision | Physics engine | Reduce health, play sound |
| Video game | Game clock | Frame tick | Game loop | Update state and redraw |
| Elevator | Hall call button | Button pressed | Elevator controller | Add floor to request queue and dispatch a car |
| Elevator | Floor sensor | Car arrives at floor | Elevator controller | Stop motor and open door |
| Elevator | Door sensor | Beam broken | Door controller | Reopen the door |
| Smartphone touchscreen | Touch panel | Tap / swipe | Operating system | Pass the gesture to the foreground app |
| Netflix / YouTube | Play button | Click | Video player | Start streaming and playback |
| Online shopping site | "Add to cart" button | Click | Web page script | Update cart and total |
| Self-checkout | Barcode scanner | Item scanned | Checkout software | Look up price and add to the bill |
| Security alarm | Door/window sensor | Contact opened (while armed) | Alarm panel | Sound siren and notify the owner |

**Total: 10 systems, 20 rows.**

## Self-check

1. **Difference between an event source and an event listener?**
   The source is the object that *generates* the event (a button, a sensor, a timer). The listener is the object that is *registered to be told* when the event happens and reacts to it. The source does not need to know what the listener does.

2. **Can one event source have multiple listeners? Example.**
   Yes. A "Place order" button can notify an email service, an inventory service and a receipt printer. In Java Swing, one `JButton` can have several `ActionListener`s added.

3. **Can one listener handle events from multiple sources? Example.**
   Yes. A single ActionListener can be added to all the buttons of a calculator and use `e.getSource()` or the action command to see which button fired. Likewise an ATM controller listens to the keypad, the card reader and the cash dispenser.
