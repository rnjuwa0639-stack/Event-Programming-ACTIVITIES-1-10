# Activity 5: Final Self-Assessment

## Learning Outcome 1: Design and implement the Observer pattern
1. **UML diagram**
   ```
   <<interface>> Subject                 <<interface>> Observer
   + registerObserver(o)  ------------>  + update(...)
   + removeObserver(o)       1    *            ^
   + notifyObservers()                         |
          ^                                    |
          |                        +-----------+-----------+
   WeatherData (ConcreteSubject)   CurrentConditionsDisplay  StatisticsDisplay  ForecastDisplay
   - observers: List<Observer>     (ConcreteObservers)
   - temperature, humidity, pressure
   ```
2. **Subject interface from memory**
   ```java
   public interface Subject {
       void registerObserver(Observer o);
       void removeObserver(Observer o);
       void notifyObservers();
   }
   ```
3. **Why a list of observers?** The subject can have any number of observers and must know whom to call when its state changes; the list stores them.

## Learning Outcome 2: Evaluate legacy java.util.Observer
4. It is a class, not an interface (single inheritance problem); the notification order is unspecified; `setChanged()` is easy to forget; data is untyped (`Object`); pull model by default.
5. It was deprecated in Java 9 because of those design problems and because better alternatives exist.
6. In the pull model the subject only signals "changed" and each observer has to call getters (and cast) to fetch the data. This is harder because it needs extra code and type checks, and is easy to get wrong.

## Learning Outcome 3: Create a custom event notification system
7. `addObserver`/`registerObserver`, `removeObserver` and `notifyObservers`.
8. It loops through its list of observers and calls each observer's `update`/`onDataChanged` method.
9. Remove observers (`removeObserver`) when they are no longer needed, so the subject does not keep references to them (observers that stay registered cannot be garbage collected).

## Learning Outcome 4: Compare different mechanisms
10. Custom observer interfaces, `java.util.Observer`/`Observable`, `PropertyChangeListener`, JavaFX properties and bindings.
11. JavaFX Properties and Bindings.
12. `java.util.Observer` / `Observable`.

**Score:** 12 / 12.

---

# One-Page Summary of the Observer Pattern

The Observer pattern lets one object (the **Subject**) keep a list of other objects (**Observers**) and automatically notify them whenever its state changes. The subject only knows that each observer implements an interface with an `update` method, so the two sides are loosely coupled: new observers can be added or removed without touching the subject. In `WeatherData`, three displays (current conditions, statistics, forecast) react to each new measurement, and removing `ForecastDisplay` simply stopped it from receiving updates.

The legacy `java.util.Observable` offers the same idea but is deprecated because it is a class, has unspecified notification order, needs `setChanged()` and passes untyped data. Java also provides `PropertyChangeListener`, which carries the old and new value of a named property, and JavaFX properties and bindings, which are the best choice for modern GUIs.

The pattern is the foundation of event-driven programming: a button is a subject and an `ActionListener` is an observer. When I refactored `TightlyCoupledSystem` into `DataModel` with `DataObserver`s, adding a new `TextLogger` needed no change to the model.

---

## Final Reflection

The Observer pattern is a design in which a subject notifies all registered observers when its state changes, and it matters because it keeps parts of a program independent of each other. It is the basis of event-driven programming: a GUI component is the event source (subject) and the listeners registered on it are observers that run their handlers when events occur. For a plain Java project I would use my own observer interfaces, because they are simple, typed and give full control. For a JavaFX project I would use properties and bindings, since they update the UI automatically and need no boilerplate. I would not use `java.util.Observable` because it is deprecated. Learning the pattern by hand first makes it much easier to understand what these built-in mechanisms do internally.
