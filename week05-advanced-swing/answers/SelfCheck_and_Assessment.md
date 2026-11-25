# Week 5: Self-Check Answers and Final Self-Assessment

## Activity 1: JList and custom renderers
- **What does a custom renderer do?** It controls how each item appears: icons, colours, fonts or multi-part layouts instead of plain text.
- **How often is the renderer called?** For every visible row, potentially hundreds of times while scrolling, so keep it fast.
- **Why `!e.getValueIsAdjusting()`?** Without it the listener fires several times for one click while the selection is still changing; the check handles only the final selection.

## Activity 2: JTable
- **TableModel vs JTable?** The model holds the data; `JTable` displays it. Changing the model updates the view automatically.
- **Why a JScrollPane?** Without it the column headers and scrollbars do not appear.
- **Make a column non-editable?** Override `isCellEditable(row, column)` in the model and return `false` for that column.

## Activity 3: JTree and JTabbedPane
- **JTree vs JList?** `JList` is flat; `JTree` shows hierarchical data (items inside items).
- **What does `addTab()` do?** Adds a tab with a title and a content component.
- **DefaultMutableTreeNode?** A tree node that can hold children; "mutable" means children can be added or removed later.

## Activity 4: Menus, toolbars, dialogs
- **JMenu vs JMenuItem?** `JMenu` is a top-level menu (File); `JMenuItem` is a clickable entry inside it (Save).
- **showOpenDialog vs showSaveDialog?** Open to pick an existing file to read; save to choose where to write.
- **What does `showConfirmDialog` return?** An `int` (`YES_OPTION`, `NO_OPTION`, `CANCEL_OPTION`, `CLOSED_OPTION`).

## Activity 5: Capstone
- **How do tree and table communicate?** Through a `TreeSelectionListener`; selecting a folder calls `loadFolderContents(folder)`, which updates the table model.
- **Why `tableModel.setRowCount(0)`?** To clear the old rows before adding the new folder's files.
- **What does `JSplitPane` do?** Divides the window into two resizable sections with a draggable divider.

## Final Self-Assessment
**LO1: Match the component to its purpose**
Rate yourself 1–5: 4
| Component | Purpose |
|---|---|
| JList | Flat list of selectable items |
| JTable | Rows and columns of data, editable |
| JTree | Hierarchical data such as folders |
| JTabbedPane | Several views in tabs |

Rate yourself 1–5: 4
**LO2: How do you customise a JList row?** Use `setCellRenderer()` with a `DefaultListCellRenderer` subclass that overrides `getListCellRendererComponent()`. For a `JTable`, call `setCellRenderer()` on a column of the column model with a `DefaultTableCellRenderer` subclass.

Rate yourself 1–5: 4
**LO3: Four common JOptionPane methods:** `showMessageDialog`, `showConfirmDialog`, `

Rate yourself 1–5: 4
**LO4: Did the capstone FileManager run?** Yes: it browses the real file system with a tree on the left and a table on the right.
