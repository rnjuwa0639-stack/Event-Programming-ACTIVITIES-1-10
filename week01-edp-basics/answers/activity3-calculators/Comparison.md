# Activity 3: Procedural vs. Event-Driven

## Procedural calculator: questions

1. **Exact order of steps?** Read first number, read operator, read second number, calculate with a `switch`, display the result, end.
2. **Wrong input at step 2?** An unknown operator (e.g. `x`) goes to the `default` case, prints "Invalid operator" and the program ends. The user cannot correct it. (Typing text where a number is expected in steps 1 or 3 crashes with `InputMismatchException`.)
3. **Calculate again without restarting?** No. The program ends after one calculation.
4. **Who controls the flow?** The program. It decides what is asked next and the user can only answer.

## Event-driven calculator: questions

1. **Click buttons in any order?** Yes, the user decides the order. (Only sensible sequences give meaningful results.)
2. **A mistake?** Press **C** to clear and start again; dividing by zero shows "Error" instead of crashing.
3. **Calculate again without restarting?** Yes. The window stays open and waits for the next event.
4. **Who controls the flow?** The user. The program simply reacts to each event.
5. **How many event handlers?** 16 `ActionListener`s are registered: 10 digit buttons, 4 operator buttons, 1 equals button and 1 clear button (written in 4 factory methods).

## Comparison table

| ASPECT | PROCEDURAL | EVENT-DRIVEN |
|---|---|---|
| Who controls the flow? | The program (fixed sequence of steps) | The user (events arrive in any order) |
| Can the user make mistakes and recover easily? | No. A mistake ends or crashes the program | Yes. Clear/retry without restarting |
| Can the user interact in any order? | No, only in the order the program asks | Yes |
| How many possible execution paths? | Few, one main path with small branches | Very many, depends on the order of events |
| Code organization | One top-to-bottom `main` method | Many small handlers reacting to events, state kept in fields |
| User experience | Text prompts, one calculation per run | Interactive window that stays open and responds immediately |

## Reflection

The main difference is who is in charge. In the procedural calculator the program asks questions in a fixed order and the user can only answer, so one wrong entry ends the run. In the event-driven calculator the program just waits, and each button click is an event handled by a small piece of code, so the user can click in any order, correct mistakes and calculate repeatedly. For interactive applications I prefer the event-driven approach, because people expect to click what they want when they want. Event-driven programming is better for GUIs because a GUI has many independent controls and the program cannot predict which one will be used next. Handlers keep each reaction small and separate, which also makes the code easier to extend, for example by adding a new button without touching the others.
