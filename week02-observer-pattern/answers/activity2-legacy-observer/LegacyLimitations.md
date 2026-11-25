# Limitations of java.util.Observer and java.util.Observable

## Limitation 1: Single Inheritance
Explain: Why is it a problem that Observable is a CLASS, not an INTERFACE?

Your answer: Java classes can extend only one parent class. If a class must extend `Observable` to be a subject, it cannot extend anything else (for example `JFrame` or a domain base class). A subject that already has a parent class cannot use the pattern, and the class cannot be replaced by a test double easily. An interface would not have this problem.

## Limitation 2: Cannot Change the Notification Order
Explain: In what order are observers notified? Can you control this?

Your answer: The order is unspecified. `Observable` stores observers in an internal collection and its documentation does not guarantee any order (in practice it was the reverse order of registration). You cannot set priorities or choose the order, so code that depends on one observer running before another is fragile.

## Limitation 3: setChanged() is Confusing
Explain: Why must you call setChanged() before notifyObservers()?

Your answer: `notifyObservers()` only notifies if the internal "changed" flag is true, and `setChanged()` is the only way to set it (it is `protected`, so it can only be called from inside a subclass). If you forget it, observers are silently not notified and there is no error message. It is extra ceremony that a simple publish/subscribe design does not need.

## Limitation 4: Pull Model by Default
Explain: How does the observer get the data? Is this efficient?

Your answer: `notifyObservers()` without arguments sends nothing, so each observer receives only a reference to the Observable and must call getters to pull what it needs (and cast to the right type, as in `LegacyDisplay`). That is extra code, needs `instanceof` and casts, and is easy to get wrong. With the push model used in Activity 1 the data arrives as typed parameters.

## Limitation 5: Deprecated Since Java 9
Explain: Why did Java deprecate these classes?

Your answer: The API was judged poorly designed: `Observable` is a class, notification order is unspecified, events carry only an untyped `Object`, `setChanged()` is easy to forget, and it is not serializable or thread-safe in a way that supports modern use. Java recommends `java.beans.PropertyChangeListener`, `java.util.concurrent.Flow`, or your own listener interfaces.

---

## Self-Check

1. **Why can't you extend another class if you already extend Observable?** Because Java has single class inheritance; `extends Observable` uses up the only `extends` slot.
2. **Push vs. pull model?** In push, the subject sends the changed data to the observer as arguments of `update()`. In pull, the subject only says "something changed" and the observer asks the subject for the data it needs.
3. **Why was it deprecated?** See Limitation 5: a class instead of an interface, unspecified order, untyped events, forgettable `setChanged()`, and better alternatives exist.
