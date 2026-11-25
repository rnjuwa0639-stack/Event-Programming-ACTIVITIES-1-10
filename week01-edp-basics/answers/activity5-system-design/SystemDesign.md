# Event-Driven System Design

## System Name: Library Management System

---

## Part 1: Event Sources

1. Checkout desk screen (librarian's "Check Out" button) - generates checkout requests
2. Return desk screen ("Return" button) - generates book-returned events
3. Barcode scanner - generates book-scanned and member-card-scanned events
4. System clock / scheduler - generates a "day ended" event used to find overdue books
5. Member portal (website) - generates reserve-book and renew-book requests

---

## Part 2: Events

| Event Name | Source | Description |
|---|---|---|
| BookCheckedOut | Checkout desk | A member borrows an available book |
| BookReturned | Return desk | A borrowed book is brought back |
| BookScanned | Barcode scanner | A book's barcode is read |
| BookOverdue | System clock | Due date passed and the book is not returned |
| BookReserved | Member portal | A member reserves a book that is currently out |

---

## Part 3: Event Handlers

| Event | Handler Action |
|---|---|
| BookCheckedOut | Mark book as unavailable, record due date, add to member's loans, write to the log |
| BookReturned | Mark book as available, remove loan, calculate any fine, notify the next member in the reservation queue |
| BookScanned | Look up the book record and show its title and status on screen |
| BookOverdue | Calculate fine, send a reminder email to the member, flag the account |
| BookReserved | Add the member to the reservation queue and confirm on screen |

---

## Part 4: Event Flow Diagram

```
User Action -> Event Source -> Event Object -> Event Queue -> Event Loop -> Handler -> GUI Update

[Librarian clicks Check Out]
      |
      v
 JButton (source) --creates--> ActionEvent --> Event Queue --> EDT event loop
                                                                    |
                                                                    v
                                                    checkOut handler (listener)
                                                       |-> update book status
                                                       |-> write event log
                                                       |-> update status label
```

---

## Part 5: Potential Issues

1. **Two librarians check out the same copy at once** - Make the checkout operation atomic (a synchronized method or a database transaction) and re-check availability before confirming.
2. **A slow database lookup freezes the screen** - Run lookups on a background thread and update the GUI with `SwingUtilities.invokeLater`.
3. **Duplicate or lost events (e.g. returning a book that was never borrowed)** - Validate the state in each handler and log and reject invalid events instead of crashing.
