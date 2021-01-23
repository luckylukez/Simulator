import jdk.swing.interop.SwingInterOpUtils;

import java.awt.*;
import java.util.Random;

public class Boll {
    private Color color;
    private double r, x, y, vx, vy, ax, ay;
    private double possibleX, possibleY, possibleVx, possibleVy;
    private double m;

    public Boll(int x, int y, double r) {
        this(x, y, (double)0, (double)0, r, 1);
    }

    public Boll(int x, int y, double vx, double vy, double r, double m) {
        this.r = r;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.ax = 0;
        this.ay = Ball.GRAVITY_CONSTANT;
        this.m = m;

        Random rand = new Random();
        float red = rand.nextFloat();
        float green = rand.nextFloat();
        float blue = rand.nextFloat();
        this.color = new Color(red, green, blue);
    }


    public void fillBoll(Graphics2D g2d){
        g2d.fillOval((int)(this.x-r), (int)(this.y-r), 2*(int)r, 2*(int)r);
    }

    public void printState(){
        System.out.println("######################################################################################");
        System.out.println("State");
        System.out.println("x: " + x);
        System.out.println("y: " + y);
        System.out.println("vx " + vx);
        System.out.println("vy " + vy);
    }

    public void printPossibleState(){
        System.out.println("######################################################################################");
        System.out.println("Possible state");
        System.out.println("x: " + possibleX);
        System.out.println("y: " + possibleY);
        System.out.println("vx " + possibleVx);
        System.out.println("vy " + possibleVy);
    }

    public void checkEdges(){
        if(getPossibleX()-getR() <= 0){
            possibleX = r;
            if(vx < 0){
                vx = -vx;
            }
        }
        if(x+r >= World.X_PIXELS){
            x = World.X_PIXELS-r;
            if(vx > 0){
                vx = -vx;
            }
        }
        if(y-r <= 0){
            y = r;
            if(vy < 0){
                vy = -vy;
            }
        }
        if(y+r >= World.Y_PIXELS) {
            y = World.Y_PIXELS - r;
            if (vy > 0) {
                vy = -vy;
            }
        }
    }

    public void updatePosition(){
        x = possibleX;
        y = possibleY;
        vx = possibleVx;
        vy = possibleVy;

    }

    //Getters
    public double getR() {
        return r;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public double getAx() {
        return ax;
    }

    public double getAy() {
        return ay;
    }

    public Color getColor() {
        return color;
    }

    public double getM() {
        return m;
    }


    //Setters
    public void setR(double r) {
        this.r = r;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public void setAx(double ax) {
        this.ax = ax;
    }

    public void setAy(double ay) {
        this.ay = ay;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setM(double m) {
        this.m = m;
    }

    public void setPossibleX(double possibleX) {
        this.possibleX = possibleX;
    }

    public void setPossibleY(double possibleY) {
        this.possibleY = possibleY;
    }

    public void setPossibleVx(double possibleVx) {
        this.possibleVx = possibleVx;
    }

    public void setPossibleVy(double possibleVy) {
        this.possibleVy = possibleVy;
    }

    public double getPossibleX() {
        return possibleX;
    }

    public double getPossibleY() {
        return possibleY;
    }

    public double getPossibleVx() {
        return possibleVx;
    }

    public double getPossibleVy() {
        return possibleVy;
    }
}
