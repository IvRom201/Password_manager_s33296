package GUI;

import Core.Entry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.security.SecureRandom;

public class Form_Dialog extends JDialog {
    private JTextField name = new JTextField(20);
    private JTextField password = new JTextField(20);
    private JTextField category = new JTextField(20);
    private JTextField login = new JTextField(20);
    private JTextField website = new JTextField(20);
    private JTextField location = new JTextField(20);
    private Entry res = null;

    public Form_Dialog(JFrame frame, Entry toEdit) {
        super(frame, "Add password", true);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Name:"));
        panel.add(name);
        panel.add(new JLabel("Password:"));
        panel.add(password);
        panel.add(new JLabel("Core.Category:"));
        panel.add(category);
        panel.add(new JLabel("Login:"));
        panel.add(login);
        panel.add(new JLabel("Website:"));
        panel.add(website);
        panel.add(new JLabel("Location:"));
        panel.add(location);
        add(panel, BorderLayout.CENTER);

        panel.add(new JLabel("Generate password: "));
        JButton generate = new JButton("Generate");
        generate.addActionListener(this::generatePas);
        panel.add(generate, BorderLayout.SOUTH);

        JButton ok = new JButton("Ok");
        JButton cancel = new JButton("Cancel");


        ok.addActionListener(e -> {
            if (name.getText().trim().isEmpty() || password.getText().trim().isEmpty() || category.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter the required fields correctly");
            } else {
                res = new Entry(name.getText(), password.getText(), category.getText());
                res.setLogin(login.getText());
                res.setWebsite(website.getText());
                res.setLocation(location.getText());
                dispose();
            }
        });
        cancel.addActionListener(e -> {
            res = null;
            dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ok);
        buttonPanel.add(cancel);
        add(buttonPanel, BorderLayout.SOUTH);

        if(toEdit != null) {
            name.setText(toEdit.getName());
            password.setText(toEdit.getPassword());
            category.setText(toEdit.getCategory());
            login.setText(toEdit.getLogin());
            website.setText(toEdit.getWebsite());
            location.setText(toEdit.getLocation());
        }

        pack();
        setLocationRelativeTo(frame);
        setVisible(true);
    }

    public Entry getRes() {
        return res;
    }

    private void generatePas(ActionEvent e) {
        String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            int index = random.nextInt(symbols.length());
            sb.append(symbols.charAt(index));
        }
        password.setText(sb.toString());
    }
}
