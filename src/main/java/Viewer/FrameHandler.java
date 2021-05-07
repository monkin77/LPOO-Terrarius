package Viewer;

public class FrameHandler {
    private int currentImage;
    private int totalImages;
    private int currentFPI; //FPI -> frames per image
    private int totalFPI;

    public FrameHandler(int currentImage, int totalImages, int currentFPI, int totalFPI){
        this.currentImage = currentImage;
        this.totalImages = totalImages;
        this.currentFPI = currentFPI;
        this.totalFPI = totalFPI;
    }

    //TODO: SHOULD ALL THESE BE 0?
    public FrameHandler(){
        this.currentImage = 0;
        this.totalImages = 0;
        this.currentFPI = 0;
        this.totalFPI = 0;
    }

    public int getCurrentImage() {
        return currentImage;
    }

    public void setCurrentImage(int currentImage) {
        this.currentImage = currentImage;
    }

    public int getTotalImages() {
        return totalImages;
    }

    public void setTotalImages(int totalImages) {
        this.totalImages = totalImages;
    }

    public int getCurrentFPI() {
        return currentFPI;
    }

    public void setCurrentFPI(int currentFPI) {
        this.currentFPI = currentFPI;
    }

    public int getTotalFPI() {
        return totalFPI;
    }

    public void setTotalFPI(int totalFPI) {
        this.totalFPI = totalFPI;
    }

    public void update(){
        currentFPI = (currentFPI +1) % totalFPI;

        if (currentFPI == 0){
            currentImage = (currentImage +1) % totalImages;
        }
    }

    public void reset(){
        currentImage = 0;
        currentFPI = 0;
    }
}
