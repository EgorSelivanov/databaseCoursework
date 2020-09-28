import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    public MainFrame(){
        super("Главное окно");
        setLookAndFeel();
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlowLayout flo = new FlowLayout();
        setLayout(flo);

        JButton play = new JButton("Воспроизведение");
        JButton stop = new JButton("Остановка");
        JButton pause = new JButton("Пауза");
        add(play);
        add(stop);
        add(pause);

        JLabel lab = new JLabel("Адрес веб-страницы: ", JLabel.RIGHT);
        JTextField text1 = new JTextField(20);
        add(lab);
        add(text1);
        setVisible(true);
    }
    private void setLookAndFeel(){
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception exc){
        }
    }
}
