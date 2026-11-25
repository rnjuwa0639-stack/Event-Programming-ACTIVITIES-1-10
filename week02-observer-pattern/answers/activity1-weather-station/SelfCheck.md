# Activity 1 Self-Check

1. **Role of the Subject interface?** It defines the contract every publisher must follow (`registerObserver`, `removeObserver`, `notifyObservers`), so observers depend on an interface and not on a concrete class.
2. **`notifyObservers()` with no observers?** Nothing happens. The loop has nothing to iterate over, so there is no error.
3. **`registerObserver()` vs `removeObserver()`?** `registerObserver` adds an observer to the list so it receives notifications; `removeObserver` takes it out so it no longer does.
4. **Why is the observers list an instance variable?** The list must live as long as the subject and be shared by all its methods (register, remove, notify). A local variable would disappear after each method call.
