package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

public class passwordGUI extends JFrame{
    private JPanel mainPanel;
    private JPanel centralPanel;
    private JLabel name_label;
    private JTextField textFieldName;
    private JPasswordField passwordField;
    private JLabel password_label;
    private JButton buttonSignedIn;
    private JLabel FB_label;
    private JButton buttonUser;


    public passwordGUI()
    {
        super("Турагентство");
        this.setContentPane(mainPanel);
        this.setSize(380, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        buttonUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ClientAppGUI(false);
            }
        });
        buttonSignedIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());
                if (textFieldName.getText().equals("admin") && password.equals("admin")) {
                    dispose();
                    new AppGUI();
                }
                else
                {
                    new ErrorSignedIn();
                }
            }
        });
    }
}
