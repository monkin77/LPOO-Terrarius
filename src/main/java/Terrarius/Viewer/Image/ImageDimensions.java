package Terrarius.Viewer.Image;

//TODO: SAME AS DIMENSIONS. USE THE SAME
public class ImageDimensions {
    private int height;
    private int width;

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
