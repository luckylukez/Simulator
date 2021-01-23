import javax.swing.*;
import java.time.*;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args){
        Boll b1 = new Boll(100, 100, 50, 0, 50, 1);
        Boll b2 = new Boll(300,100, -50, 0, 50, 1);

        ArrayList<Boll> balls = new ArrayList<>();
        balls.add(b1);
        balls.add(b2);

        b1.setX(200);
        System.out.println(balls.get(0).getX());


        balls.get(0).setX(300);
        System.out.println(b1.getX());

        long now, next;
        long loopTime = (long) 0.1e9; // nanoseconds
        int millis = (int) (loopTime / 1e6);

        World world = new World();
        world.setSize(500, 500);
        JPanel panel = new MyRectangleJPanel(); // changed this line
        //panel.setSize(200, 200);
        JPanel ball = new Ball();

        world.add(ball);

        //world.add(panel);
        //world.pack();
        world.setVisible(true);
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }
        //((Ball) ball).setX(200);
        //((Ball) ball).setY(200);

        ball.repaint();



        int[] bajs  = {12,  44,  13,  88,  23,  94,  11,  39,  20,  16};
        for(int b: bajs){
            System.out.println(Integer.toString(b) + ": " + (2*b+5)%11);
        }
    }
}
