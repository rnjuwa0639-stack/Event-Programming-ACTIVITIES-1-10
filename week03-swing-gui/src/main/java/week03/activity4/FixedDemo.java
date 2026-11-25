package week03.activity4;

import javax.swing.*;
import java.awt.*;

public class FixedDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("EDT Demo - GOOD Practice");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new FlowLayout());

            JLabel status = new JLabel("Ready");
            JButton button = new JButton("Do Long Operation");

            // GOOD: Run the long operation in a background thread
            button.addActionListener(e -> {
                status.setText("Working...");
                button.setEnabled(false);

                new Thread(() -> {
                    System.out.println("Working on: " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(5000); // Blocks THIS thread, not the EDT
                    } catch (InterruptedException ex) { }

                    // CRITICAL: Update the GUI on the EDT
                    SwingUtilities.invokeLater(() -> {
                        System.out.println("Updating GUI on: " + Thread.currentThread().getName());
                        status.setText("Done!");
                        button.setEnabled(true);
                    });
                }).start();
            });

            frame.add(button);
            frame.add(status);
            frame.setVisible(true);
        });
    }
}
