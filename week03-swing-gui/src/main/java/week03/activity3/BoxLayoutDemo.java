package week03.activity3;

import javax.swing.*;
import java.awt.*;

// The guide names four layout managers (Flow, Border, Grid and Box).
// This demo covers BoxLayout: a vertical stack and a horizontal row.
public class BoxLayoutDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("BoxLayout Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 350);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());

            JPanel vertical = new JPanel();
            vertical.setLayout(new BoxLayout(vertical, BoxLayout.Y_AXIS));
            vertical.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            for (int i = 1; i <= 4; i++) {
                JButton b = new JButton("Vertical item " + i);
                b.setAlignmentX(Component.LEFT_ALIGNMENT);
                vertical.add(b);
                vertical.add(Box.createVerticalStrut(10)); // spacer
            }

            JPanel horizontal = new JPanel();
            horizontal.setLayout(new BoxLayout(horizontal, BoxLayout.X_AXIS));
            horizontal.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
            horizontal.add(new JButton("Left"));
            horizontal.add(Box.createHorizontalGlue()); // pushes the next button to the right edge
            horizontal.add(new JButton("Right"));

            frame.add(vertical, BorderLayout.CENTER);
            frame.add(horizontal, BorderLayout.SOUTH);
            frame.setVisible(true);
        });
    }
}
