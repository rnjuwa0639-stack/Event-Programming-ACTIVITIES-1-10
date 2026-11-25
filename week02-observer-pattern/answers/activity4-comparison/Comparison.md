# Comparison of Event Notification Mechanisms in Java

| Feature | Custom Observer | java.util.Observer | PropertyChangeListener | JavaFX Bindings |
|---------|-----------------|--------------------|-----------------------|-----------------|
| **Push or Pull?** | Both possible | Pull | Push (carries old + new) | Push |
| **Is it deprecated?** | No | Yes (Java 9+) | No | No |
| **Requires inheritance?** | No (interface) | Yes (class) | No | No |
| **Thread-safe?** | Depends on you | No | Yes (with the right setup) | Yes |
| **Auto-generates boilerplate?** | No | No | Yes (via PropertyChangeSupport) | Yes |
| **Best for...** | Custom systems | Legacy code | JavaBeans / Swing | JavaFX apps |
| **Can have multiple listeners?** | Yes | Yes | Yes | Yes |
| **Can be typed to send any data?** | Yes | No (Object) | Yes (via generics not typical) | Yes |
| **Recommended today?** | Yes | No | Only for legacy | Yes |

## Which One Should You Use?
- For a **plain Java project with custom needs** -> Custom Observer
- For a **JavaFX application** -> JavaFX Properties and Bindings
- For **legacy code using JavaBeans** -> PropertyChangeListener
- **Never** -> java.util.Observer

## Self-Check
1. **Which mechanism carries both the old and new value of a property?** PropertyChangeListener (via `PropertyChangeEvent`; JavaFX's `ChangeListener` also does).
2. **Which mechanism requires a helper class to manage the listener list?** PropertyChangeListener, using `PropertyChangeSupport`.
3. **Which two mechanisms are recommended for modern Java?** Custom Observer interfaces and JavaFX Properties/Bindings.
4. **Which mechanism is deprecated and should be avoided?** `java.util.Observer` / `Observable`.
