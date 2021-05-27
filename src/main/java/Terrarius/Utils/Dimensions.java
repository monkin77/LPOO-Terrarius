package Terrarius.Utils;

public class Dimensions {

    private int height;
    private int width;

    public Dimensions(int height, int width){
        this.width = width;
        this.height = height;
    }

    public Dimensions(Dimensions dimensions) {
        this.width = dimensions.getWidth();
        this.height = dimensions.getHeight();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void incrementWidth(int incValue) {
        if(this.width + incValue > 0) this.width += incValue;
    }

    public void incrementHeight(int incValue) {
        if(this.height + incValue > 0) this.height += incValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dimensions dim = (Dimensions) o;
        return dim.getHeight() == this.height && dim.getWidth() == this.width;
    }
}
