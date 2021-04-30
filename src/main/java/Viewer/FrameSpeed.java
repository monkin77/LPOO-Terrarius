package Viewer;

public class FrameSpeed {
    private int currentFrame;
    private int totalFrames;
    private int currentSpeed;
    private int totalSpeed;

    public FrameSpeed(int currentFrame, int totalFrames, int currentSpeed, int totalSpeed){
        this.currentFrame = currentFrame;
        this.totalFrames = totalFrames;
        this.currentSpeed = currentSpeed;
        this.totalSpeed = totalSpeed;
    }

    public FrameSpeed(){
        this.currentFrame = 0;
        this.totalFrames = 0;
        this.currentSpeed = 0;
        this.totalSpeed = 0;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public int getTotalFrames() {
        return totalFrames;
    }

    public void setTotalFrames(int totalFrames) {
        this.totalFrames = totalFrames;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public int getTotalSpeed() {
        return totalSpeed;
    }

    public void setTotalSpeed(int totalSpeed) {
        this.totalSpeed = totalSpeed;
    }

    public void update(){
        currentSpeed = (currentSpeed+1) % totalSpeed;

        if (currentSpeed == 0){
            currentFrame = (currentFrame+1) % totalFrames;
        }
    }

    public void reset(){
        currentFrame = 0;
        currentSpeed = 0;
    }
}
