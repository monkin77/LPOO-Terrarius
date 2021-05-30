package Terrarius.Model.Game;

import Terrarius.Model.Game.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;

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

    @Property
    public void incrementX(@ForAll @IntRange(min=1, max=10000) int a,
                               @ForAll @IntRange(min=-10000, max=10000) int w,
                               @ForAll @IntRange(max=100) int b) {

        Position position1 = new Position(a, 1);
        position1.incrementX(w);

        Assertions.assertTrue(position1.getX() > w);

        if (w + a <= 0) return;

        Assertions.assertEquals(a + w, position1.getX());

        position1.incrementX(-w);
        Assertions.assertEquals(a, position1.getX());

        if (w < 0) return;

        for (int i = 0; i < b; ++i)
            position1.incrementX(w);

        Assertions.assertEquals(a + b * w, position1.getX());

    }

    @Property
    public void incrementY(@ForAll @IntRange(min=1, max=10000) int a,
                           @ForAll @IntRange(min=-10000, max=10000) int w,
                           @ForAll @IntRange(max=100) int b) {

        Position position1 = new Position(1, a);
        position1.incrementY(w);

        Assertions.assertTrue(position1.getY() > w);

        if (w + a <= 0) return;

        Assertions.assertEquals(a + w, position1.getY());

        position1.incrementY(-w);
        Assertions.assertEquals(a, position1.getY());

        if (w < 0) return;

        for (int i = 0; i < b; ++i)
            position1.incrementY(w);

        Assertions.assertEquals(a + b * w, position1.getY());

    }

}
