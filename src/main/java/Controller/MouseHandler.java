package Controller;

public class MouseHandler {

    private boolean pressed;
    private int x;
    private int y;

    public MouseHandler(){
        this.pressed = false;
        this.x = 0;
        this.y = 0;
    }

    public void mousePressed(){
        pressed = true;
    }

    public void mouseRelease(){
        pressed = false;
    }

    public boolean isPressed(){
        return pressed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
