package Viewer.Image;

public class ImageDimensions {
    protected int height;
    protected int width;

    public ImageDimensions(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
