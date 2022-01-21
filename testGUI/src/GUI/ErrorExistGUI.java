package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorExistGUI extends JFrame{
    private JPanel panelError;
    private JButton OKButton;

    public ErrorExistGUI()
    {
        super("Ошибка");

        this.setContentPane(panelError);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 130);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
    }
}
