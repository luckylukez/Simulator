import java.lang.System.*;
import javax.swing.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
//import java.awt.panel;
import javax.swing.JPanel;


public class Main {
    public static void main(String[] args) {
        long now, next;
        long loopTime = (long) 0.1e9; // nanoseconds
        int millis = (int) (loopTime / 1e6);

        World world = new World();
        world.setSize(500, 500);
        JPanel panel = new MyRectangleJPanel(); // changed this line
        //panel.setSize(200, 200);
        //JPanel ball = new Ball(100,100, 5);
        //world.add(new Ball(100,100, 5));
        //world.add(panel);
        //world.pack();
        world.setVisible(true);

        int[] bajs  = {12,  44,  13,  88,  23,  94,  11,  39,  20,  16};
        for(int b: bajs){
            System.out.println(Integer.toString(b) + ": " + (2*b+5)%11);
        }

        //vad är ett graphics objekt?

        //Ellipse2D e = new Ellipse2D.Double(0, 0, 80, 130);
        //g2d.setStroke(new BasicStroke(1));
        //g2d.setColor(Color.gray);
        /*
        while(true){
            now = System.nanoTime();
            next = now + loopTime;

            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // update shit
            //update picture
        }
        */

    }
}

class MyRectangleJPanel extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(10, 10, 100, 100);
    }
}
