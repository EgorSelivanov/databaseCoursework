package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorDelete extends JFrame{
    private JButton OKButton;
    private JPanel panelError;

    public ErrorDelete()
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
