package week04.activity5;

import javax.swing.*;
import java.awt.*;

/**
 * Interactive Registration Form (Activity 5).
 * The guide builds this in NetBeans with drag-and-drop. This version builds the
 * same form (same components, same variable names, same handlers) in plain Swing
 * code so it compiles and runs from VS Code without NetBeans.
 */
public class RegistrationForm extends JFrame {
    private static final long serialVersionUID = 1L;


    private final JLabel titleLabel = new JLabel("Registration Form");
    private final JTextField nameField = new JTextField(30);
    private final JTextField emailField = new JTextField(30);
    private final JPasswordField passwordField = new JPasswordField(30);
    private final JRadioButton maleRadio = new JRadioButton("Male");
    private final JRadioButton femaleRadio = new JRadioButton("Female");
    private final JRadioButton otherRadio = new JRadioButton("Other");
    private final ButtonGroup genderGroup = new ButtonGroup();
    private final JCheckBox sportsCheck = new JCheckBox("Sports");
    private final JCheckBox musicCheck = new JCheckBox("Music");
    private final JCheckBox readingCheck = new JCheckBox("Reading");
    private final JComboBox<String> countryBox =
            new JComboBox<>(new String[]{"Kenya", "Tanzania", "Uganda", "Rwanda"});
    private final JTextArea commentsArea = new JTextArea(3, 30);
    private final JButton submitButton = new JButton("Submit");
    private final JButton clearButton = new JButton("Clear");
    private final JButton exitButton = new JButton("Exit");
    private final JLabel statusLabel = new JLabel("Status: Ready");

    public RegistrationForm() {
        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 560);
        setLocationRelativeTo(null);
        buildLayout();
        wireEvents();
    }

    private void buildLayout() {
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.anchor = GridBagConstraints.WEST;

        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        form.add(titleLabel, c);
        c.gridwidth = 1;

        addRow(form, c, 1, "Full Name:", nameField);
        addRow(form, c, 2, "Email:", emailField);
        addRow(form, c, 3, "Password:", passwordField);

        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(otherRadio);
        addRow(form, c, 4, "Gender:", genderPanel);

        JPanel interestsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        interestsPanel.add(sportsCheck);
        interestsPanel.add(musicCheck);
        interestsPanel.add(readingCheck);
        addRow(form, c, 5, "Interests:", interestsPanel);

        addRow(form, c, 6, "Country:", countryBox);

        commentsArea.setLineWrap(true);
        commentsArea.setWrapStyleWord(true);
        addRow(form, c, 7, "Comments:", new JScrollPane(commentsArea));

        submitButton.setBackground(new Color(120, 200, 120));
        exitButton.setBackground(new Color(230, 110, 110));
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttons.add(submitButton);
        buttons.add(clearButton);
        buttons.add(exitButton);
        c.gridx = 0; c.gridy = 8; c.gridwidth = 2; c.anchor = GridBagConstraints.CENTER;
        form.add(buttons, c);

        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 8, 20));

        setLayout(new BorderLayout());
        add(form, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    private void addRow(JPanel form, GridBagConstraints c, int row, String label, JComponent field) {
        c.gridx = 0; c.gridy = row; c.anchor = GridBagConstraints.NORTHWEST;
        form.add(new JLabel(label), c);
        c.gridx = 1;
        form.add(field, c);
    }

    private void wireEvents() {
        submitButton.addActionListener(e -> submitButtonActionPerformed());
        clearButton.addActionListener(e -> clearButtonActionPerformed());
        exitButton.addActionListener(e -> exitButtonActionPerformed());
    }

    // ---- Submit ----
    private void submitButtonActionPerformed() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        // Validation
        if (name.isEmpty()) {
            showError("Error: Please enter your name");
            return;
        }
        if (email.isEmpty() || !email.contains("@")) {
            showError("Error: Please enter a valid email");
            return;
        }
        if (password.length() < 6) {
            showError("Error: Password must be at least 6 characters");
            return;
        }

        // Gather gender
        String gender = "Not specified";
        if (maleRadio.isSelected()) gender = "Male";
        else if (femaleRadio.isSelected()) gender = "Female";
        else if (otherRadio.isSelected()) gender = "Other";

        // Gather interests
        StringBuilder interests = new StringBuilder();
        if (sportsCheck.isSelected()) interests.append("Sports ");
        if (musicCheck.isSelected()) interests.append("Music ");
        if (readingCheck.isSelected()) interests.append("Reading ");

        String country = (String) countryBox.getSelectedItem();
        String comments = commentsArea.getText().trim();

        String message = "Registration Successful!\n\n"
                + "Name: " + name + "\n"
                + "Email: " + email + "\n"
                + "Gender: " + gender + "\n"
                + "Interests: " + (interests.length() > 0 ? interests.toString().trim() : "None") + "\n"
                + "Country: " + country + "\n"
                + "Comments: " + (comments.isEmpty() ? "None" : comments);

        JOptionPane.showMessageDialog(this, message, "Registration Complete",
                JOptionPane.INFORMATION_MESSAGE);

        statusLabel.setText("Registration submitted successfully!");
        statusLabel.setForeground(new Color(0, 128, 0));
    }

    private void showError(String text) {
        statusLabel.setText(text);
        statusLabel.setForeground(Color.RED);
    }

    // ---- Clear ----
    private void clearButtonActionPerformed() {
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        genderGroup.clearSelection();
        sportsCheck.setSelected(false);
        musicCheck.setSelected(false);
        readingCheck.setSelected(false);
        countryBox.setSelectedIndex(0);
        commentsArea.setText("");
        statusLabel.setText("Status: Form cleared");
        statusLabel.setForeground(Color.BLACK);
    }

    // ---- Exit ----
    private void exitButtonActionPerformed() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationForm().setVisible(true));
    }
}
