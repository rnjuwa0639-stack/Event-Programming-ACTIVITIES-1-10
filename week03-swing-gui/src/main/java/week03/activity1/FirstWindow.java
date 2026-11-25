package week03.activity1;

import javax.swing.*; // Swing components (JFrame, JButton, etc.)

// The simplest possible Swing application: it creates a JFrame and displays it.
public class FirstWindow {

    public static void main(String[] args) {
        // CRITICAL: All Swing code must run on the EDT.
        SwingUtilities.invokeLater(() -> createAndShowWindow());
    }

    private static void createAndShowWindow() {
        // 1. Create the JFrame (the main window)
        JFrame frame = new JFrame("Renee's First Swing App");

        // 2. What happens when the user clicks the X button (EXIT_ON_CLOSE ends the JVM)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 3. Window size in pixels (width x height)
        frame.setSize(500, 400);

        // 4. Center the window on the screen
        frame.setLocationRelativeTo(null);

        // 5. Make the window visible (hidden by default)
        frame.setVisible(true);

        // Log to console to prove we're on the EDT
        System.out.println("Window created on thread: " + Thread.currentThread().getName());
    }
}
