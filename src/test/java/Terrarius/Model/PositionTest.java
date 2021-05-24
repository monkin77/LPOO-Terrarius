package Terrarius.Model;

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
    public void adjacent() {
        Assertions.assertEquals(9, position.getLeft().getX());
        Assertions.assertEquals(10, position.getLeft().getY());

        Assertions.assertEquals(11, position.getRight().getX());
        Assertions.assertEquals(10, position.getRight().getY());

        Assertions.assertEquals(10, position.getDown().getX());
        Assertions.assertEquals(11, position.getDown().getY());

        Assertions.assertEquals(10, position.getUp().getX());
        Assertions.assertEquals(9, position.getUp().getY());
    }

    @Test
    public void equals() {
        Assertions.assertTrue(position.equals(new Position(10, 10)));
        Assertions.assertFalse(position.equals(new Position(11, 10)));
        Assertions.assertFalse(position.equals(new Position(10, 11)));
    }
}
