import java.awt.EventQueue;
import javax.swing.JFrame;

public class World extends JFrame {
    public static final int X_PIXELS = 600;
    public static final int Y_PIXELS = 600;

    public World() {

        initUI();
    }

    private void initUI() {

        setTitle("World");
        setSize(X_PIXELS, Y_PIXELS);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new Ball());
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                World world = new World();
                world.setVisible(true);
            }
        });
    }
}
