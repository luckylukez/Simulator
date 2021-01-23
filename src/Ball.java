import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.lang.Math;

public class Ball extends JPanel implements ActionListener {

    private Boll b1;
    private Boll b2;
    private ArrayList<Boll> balls;
    private Timer paintTimer;
    private Timer calcTimer;
    private int paintCount;
    private int calcCount;

    public final int PAINT_DELAY_MILLIS = 10; //milliseconds
    public final int CALC_DELAY_MILLIS = 1;
    public final double PAINT_DELAY = (double)PAINT_DELAY_MILLIS/1000;
    public final double CALC_DELAY = (double)CALC_DELAY_MILLIS/1000;
    public static final double GRAVITY_CONSTANT = 1000;

    public Ball(){
        b1 = new Boll(200, 100, 0, 100, 70, 1);
        b2 = new Boll(100,300, 0, -500, 70, 1);
        balls = new ArrayList<>();
        balls.add(b1);
        balls.add(b2);

        this.paintTimer = new Timer(PAINT_DELAY_MILLIS, this);
        paintTimer.start();
        //this.calcTimer = new Timer(CALC_DELAY_MILLIS, this);
        //calcTimer.start();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g); //Needed for some preparation for drawing the object
        Graphics2D g2d = (Graphics2D) g;

        paintBalls(g2d);
    }

    void paintBalls(Graphics2D g2d){
        for(Boll b: balls){
            g2d.setColor(b.getColor());
            b.fillBoll(g2d);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        long calcStartTime = System.nanoTime();

        calcNewV();
        calcNewPos();

        if (calculateNewDistance() <= b1.getR() + b2.getR()){
            collisionVCalc();
            for(Boll b: balls){
                b.setPossibleX(b.getX() + PAINT_DELAY*b.getPossibleVx());
                b.setPossibleY(b.getY() + PAINT_DELAY*b.getPossibleVy());
            }
        }

        for (Boll b: balls) {
            if(b.getPossibleX()-b.getR() <= 0){
                b.setPossibleX(b.getR() + 1);
                if(b.getPossibleVx() < 0){
                    b.setPossibleVx(-b.getVx());
                }
            }

            if(b.getPossibleX()+b.getR() >= World.X_PIXELS){
                b.setPossibleX(World.X_PIXELS-b.getR() - 1);
                if(b.getPossibleVx() > 0){
                    b.setPossibleVx(-b.getVx());
                }
            }

            if(b.getPossibleY()-b.getR() <= 0){
                b.setPossibleY(b.getR() + 1);
                if(b.getPossibleVy() < 0){
                    b.setPossibleVy(-b.getVy());
                }
            }

            if(b.getPossibleY()+b.getR() >= World.Y_PIXELS){
                b.setPossibleY(World.Y_PIXELS-b.getR() - 1);
                if(b.getPossibleVy() > 0){
                    b.setPossibleVy(-b.getVy());
                }
            }
        }
        b1.printState();
        b1.printPossibleState();
        updatePositions();

        long calcEndTime = System.nanoTime();
        //System.out.println("Calc time: " + (calcEndTime - calcStartTime));

/*
        System.out.println("y: " + this.y);
        System.out.println("vy: " + this.vy);
        System.out.println("ay: " + this.ay);
        */
        long paintStartTime = System.nanoTime();
        repaint();
        long paintEndTime = System.nanoTime();
        //System.out.println("Paint time: " + (paintEndTime - paintStartTime));
        paintCount++;
    }

    void collisionVCalc(){
        double deltaX = b2.getX() - b1.getX();
        System.out.println("deltaX: " + deltaX);
        double deltaY = b2.getY() - b1.getY();
        System.out.println("deltaY: " + deltaY);

        double sin = deltaY / (b1.getR() + b2.getR());
        System.out.println("sin: " + sin);
        double cos = deltaX / (b1.getR() + b2.getR());
        System.out.println("cos: " + cos);

        //Introduce new coordinates s and t
        double vs1 = cos * b1.getVx() + sin * b1.getVy();
        System.out.println("vs1: " + vs1);
        double vt1 = -sin * b1.getVx() + cos * b1.getVy();
        System.out.println("vt1: " + vt1);


        double vs2 = cos * b2.getVx() + sin * b2.getVy();
        System.out.println("vs2: " + vs2);
        double vt2 = -sin * b2.getVx() + cos * b2.getVy();
        System.out.println("vt2: " + vt2);

        /*
        double p = -2 * m1 / m2 * (m1 * vs1 + m2 * vs2) / (m1 + Math.pow(m1, 2) / m2);
        double q = (Math.pow(m1 * vs1 + m2 * vs2, 2) / m2 - m1 * Math.pow(vs1, 2) - m2 * Math.pow(vs2, 2)) / (m1 + Math.pow(m1, 2) / m2);

        double vs1New = -(-p / 2 + Math.sqrt(Math.pow(p / 2, 2) - q));
        double vt1New = vt1;

        double vs2New = - Math.pow(m1 * vs1 + m2 * vs2, 2) - vs1New * m1 / m2;
        double vt2New = vt2;
        */

        double vs1New = vs2;
        double vs2New = vs1;
        double vt1New = vt1;
        double vt2New = vt2;

        b1.setPossibleVx(cos * vs1New - sin * vt1New);
        b1.setPossibleVy(sin * vs1New + cos * vt1New);
        System.out.println("vy1" + b1.getPossibleVy());
        b2.setPossibleVx(cos * vs2New + -sin * vt2New);
        b2.setPossibleVy(sin * vs2New + cos * vt2New);
        /*
        b1.setPossibleVy(cos * vs1New + sin * vt1New);
        b1.setPossibleVx(sin * vs1New + cos * vt1New);
        b2.setPossibleVx(cos * vs2New + sin * vt2New);
        b2.setPossibleVy(sin * vs2New + cos * vt2New);
        */
    }

    void calcNewV(){
        for(Boll b: balls){
            b.setPossibleVx(b.getVx() + b.getAx()*PAINT_DELAY);
            b.setPossibleVy(b.getVy() + b.getAy()*PAINT_DELAY);
        }
    }
    void calcNewPos(){
        for(Boll b: balls){
            b.setPossibleX(b.getX() + PAINT_DELAY * (b.getPossibleVx() + b.getVx()) / 2);
            b.setPossibleY(b.getY() + PAINT_DELAY * (b.getPossibleVy() + b.getVy()) / 2);
        }
    }
    void updatePositions(){
        for(Boll b: balls){
            b.updatePosition();
        }
    }

    double calculateNewDistance(){
        return Math.sqrt((Math.pow(calculateNewDeltaX(), 2)) + (Math.pow(calculateNewDeltaY(), 2)));
    }

    double calculateNewDeltaX(){
        return b2.getPossibleX() - b1.getPossibleX();
    }

    double calculateNewDeltaY(){
        return b2.getPossibleY() - b1.getPossibleY();
    }
}
