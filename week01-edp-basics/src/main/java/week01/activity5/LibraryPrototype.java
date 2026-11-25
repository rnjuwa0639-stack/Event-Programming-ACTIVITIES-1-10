package week01.activity5;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/** Prototype of the Library Management System: three event sources, three handlers. */
public class LibraryPrototype extends JFrame {

    private final JLabel statusLabel;
    private final JLabel summaryLabel;
    private final JTextArea eventLog;
    private final JComboBox<String> bookBox;

    // System state
    private final String[] titles = {"Java: The Complete Reference", "Clean Code", "Design Patterns"};
    private final boolean[] checkedOut = new boolean[titles.length];
    private int fines = 0; // in KES

    public LibraryPrototype() {
        setTitle("Library Management Prototype");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Status Panel (NORTH)
        JPanel north = new JPanel(new GridLayout(2, 1));
        statusLabel = new JLabel("System Ready");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        summaryLabel = new JLabel();
        north.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        north.add(statusLabel);
        north.add(summaryLabel);
        add(north, BorderLayout.NORTH);

        // Event Log (CENTER)
        eventLog = new JTextArea(10, 40);
        eventLog.setEditable(false);
        add(new JScrollPane(eventLog), BorderLayout.CENTER);

        // Control Panel (SOUTH)
        JPanel controls = new JPanel(new GridLayout(2, 1, 5, 5));
        controls.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        bookBox = new JComboBox<>(titles);
        JPanel pick = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pick.add(new JLabel("Book:"));
        pick.add(bookBox);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton checkoutBtn = new JButton("Check Out");
        JButton returnBtn = new JButton("Return");
        JButton overdueBtn = new JButton("Simulate Overdue Return");
        buttons.add(checkoutBtn);
        buttons.add(returnBtn);
        buttons.add(overdueBtn);

        controls.add(pick);
        controls.add(buttons);
        add(controls, BorderLayout.SOUTH);

        // ---- HANDLER 1: BookCheckedOut ----
        checkoutBtn.addActionListener(e -> {
            int i = bookBox.getSelectedIndex();
            if (checkedOut[i]) {
                logEvent("REJECTED: \"" + titles[i] + "\" is already checked out");
                statusLabel.setText("Book not available");
            } else {
                checkedOut[i] = true;
                logEvent("BookCheckedOut: \"" + titles[i] + "\"");
                statusLabel.setText("Checked out: " + titles[i]);
            }
            refreshSummary();
        });

        // ---- HANDLER 2: BookReturned ----
        returnBtn.addActionListener(e -> returnBook(false));

        // ---- HANDLER 3: BookOverdue (charges a fine) ----
        overdueBtn.addActionListener(e -> returnBook(true));

        refreshSummary();
        pack();
        setLocationRelativeTo(null);
    }

    private void returnBook(boolean overdue) {
        int i = bookBox.getSelectedIndex();
        if (!checkedOut[i]) {
            logEvent("REJECTED: \"" + titles[i] + "\" was not checked out");
            statusLabel.setText("Nothing to return");
        } else {
            checkedOut[i] = false;
            if (overdue) {
                fines += 50;
                logEvent("BookOverdue: \"" + titles[i] + "\" returned late, fine KES 50 added");
                statusLabel.setText("Returned late - fine charged");
            } else {
                logEvent("BookReturned: \"" + titles[i] + "\"");
                statusLabel.setText("Returned: " + titles[i]);
            }
        }
        refreshSummary();
    }

    private void refreshSummary() {
        int out = 0;
        for (boolean b : checkedOut) if (b) out++;
        summaryLabel.setText("Books out: " + out + " / " + titles.length + "   |   Fines owed: KES " + fines);
    }

    private void logEvent(String event) {
        eventLog.append("[" + new Date() + "] " + event + "\n");
        eventLog.setCaretPosition(eventLog.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryPrototype().setVisible(true));
    }
}
