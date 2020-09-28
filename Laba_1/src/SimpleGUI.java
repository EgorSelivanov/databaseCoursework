import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleGUI extends JFrame{
    private JButton button_1 = new JButton("Шифровать");
    private JTextField input_str = new JTextField("", 10);
    private JTextField n = new JTextField("", 10);
    private JLabel label_1 = new JLabel("Ввод строки:");
    private JLabel label_2 = new JLabel("Введите количество символов для сдвига:");
    private JRadioButton rbut_1 = new JRadioButton("Сдвиг влево");
    private JRadioButton rbut_2 = new JRadioButton("Сдвиг вправо");

    public SimpleGUI() {
        super("Шифратор Цезаря");
        this.setBounds(100, 100, 400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(4, 1));

        JPanel panel_up = new JPanel();
        panel_up.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel_up.add(label_2);
        panel_up.add(n);
        container.add(panel_up);

        JPanel panel_cntr_1 = new JPanel();
        panel_cntr_1.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel_cntr_1.add(label_1);
        panel_cntr_1.add(input_str);
        container.add(panel_cntr_1);

        ButtonGroup radio_group = new ButtonGroup();
        radio_group.add(rbut_1);
        radio_group.add(rbut_2);

        JPanel panel_cntr_2 = new JPanel();
        panel_cntr_2.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel_cntr_2.add(rbut_1);
        rbut_1.setSelected(true);
        panel_cntr_2.add(rbut_2);
        container.add(panel_cntr_2);

        button_1.addActionListener(new Button_click());
        JPanel panel_down = new JPanel();
        panel_down.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel_down.add(button_1);
        container.add(panel_down);
    }
    class Button_click implements ActionListener{
        public void actionPerformed (ActionEvent e){
            try {
                Cipher res = new Cipher(input_str.getText(), Integer.parseInt(n.getText()), rbut_2.isSelected());
                JFrame message = new JFrame("Результат:");
                message.setBounds(300,100,400,100);
                message.setVisible(true);
                message.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                Container container = message.getContentPane();
                container.setLayout(new GridLayout(1, 1));
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout(FlowLayout.CENTER));

                JLabel label_result = new JLabel(res.ciph());
                JLabel label_message = new JLabel("Строка после шифрации:");
                panel.add(label_message);
                panel.add(label_result);
                container.add(panel);

            }catch (Exception er){
                JOptionPane.showMessageDialog(null, "Вводите корректные данные!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
