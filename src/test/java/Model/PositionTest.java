package Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositionTest {
    Position position;

    @BeforeEach
    public void createPosition() {
        position = new Position(10, 10);
    }

    @Test
    public void getters() {
        Assertions.assertEquals(10, position.getX());
        Assertions.assertEquals(10, position.getY());
    }

    @Test
    public void setters() {
        position.setX(15);
        position.setY(20);
        Assertions.assertEquals(15, position.getX());
        Assertions.assertEquals(20, position.getY());
    }
}
