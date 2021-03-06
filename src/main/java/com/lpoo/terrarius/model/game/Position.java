package com.lpoo.terrarius.model.game;

import com.lpoo.terrarius.utils.Dimensions;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position pos) {
        this.x = pos.getX();
        this.y = pos.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Position getLeft() {
        return new Position(x - 1, y);
    }

    public Position getRight() {
        return new Position(x + 1, y);
    }

    public Position getUp() {
        return new Position(x, y - 1);
    }

    public Position getDown() {
        return new Position(x, y + 1);
    }

    public void incrementX(int incValue) {

        this.x += incValue;

    }

    public void incrementY(int incValue) {
        this.y += incValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    public static boolean checkElementsCollision(Position p1, Dimensions d1, Position p2, Dimensions d2) {
        boolean leftSideElementCollides = p1.getX() >= p2.getX() &&
                p1.getX() <= p2.getX() + d2.getWidth() - 1;

        boolean rightSideElementCollides = p1.getX() <= p2.getX() &&
                p1.getX() + d1.getWidth() - 1 >= p2.getX();

        if(leftSideElementCollides || rightSideElementCollides) {
            boolean topElementCollides = p1.getY() >= p2.getY() &&
                    p1.getY() <= p2.getY() + d2.getHeight() - 1;

            boolean bottomElementCollides = p1.getY() <= p2.getY() &&
                    p1.getY() + d1.getHeight() - 1 >= p2.getY();

            boolean topElementCollides2 = p2.getY() >= p1.getY() &&
                    p2.getY() <= p1.getY() + d1.getHeight() - 1;

            boolean bottomElementCollides2 = p2.getY() <= p1.getY() &&
                    p2.getY() + d2.getHeight() - 1 >= p1.getY();

            return topElementCollides || bottomElementCollides || topElementCollides2 || bottomElementCollides2;
        }
        return false;
    }
}
