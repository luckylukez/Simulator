import javax.swing.*;
import java.awt.*;

public class WaitingEx extends JFrame {

    public WaitingEx() {

        initUI();
    }

    private void initUI() {

        add(new Surface());

        setTitle("Waiting");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                WaitingEx ex = new WaitingEx();
                ex.setVisible(true);
            }
        });
    }
}
