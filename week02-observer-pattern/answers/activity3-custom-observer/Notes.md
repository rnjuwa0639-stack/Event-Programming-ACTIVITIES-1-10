# Activity 3 Notes: Tightly Coupled vs Observer Pattern

## Identify the coupling problem
1. **Adding a third display class?** Yes, `DataModel.setValue()` must be edited to create and call the new display. The model has to know every display.
2. **LabelDisplay deleted or renamed?** `DataModel` no longer compiles, because it refers to the class by name. A change in the display breaks the model.
3. **Test DataModel in isolation?** Not easily. Every test of `setValue` also runs the display classes (and would need a real UI in a real application).

## Comparison

| Aspect | Tightly Coupled | Observer Pattern |
|---|---|---|
| Adding a new display | Modify DataModel | Just register a new observer (`TextLogger` was added without changing `DataModel`) |
| Testing DataModel in isolation | Hard: it needs the display classes | Easy: register a test observer or none |
| Removing a display | Modify DataModel | Just don't register it, or call `removeObserver` |
| Who knows whom | Model knows every concrete display | Model knows only the `DataObserver` interface |
| Dependency direction | Model depends on the views | Views depend on the model's interface |
