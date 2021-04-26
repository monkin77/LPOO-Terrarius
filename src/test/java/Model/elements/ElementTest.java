package Model.elements;

import Model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ElementTest {
    Element element;
    Position position;

    @BeforeEach
    public void createElement() {
        position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(10);
        Mockito.when(position.getY()).thenReturn(15);
        element = new Element(position);
    }

    @Test
    public void getters() {
        Position elementPos = element.getPosition();
        Assertions.assertEquals(position.getX(), elementPos.getX());
        Assertions.assertEquals(position.getY(), elementPos.getY());
    }

    @Test
    public void setters() {
        Position newPosition = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(20);
        Mockito.when(position.getY()).thenReturn(25);

        element.setPosition(newPosition);
        Position elementPos = element.getPosition();
        Assertions.assertEquals(newPosition.getX(), elementPos.getX());
        Assertions.assertEquals(newPosition.getY(), elementPos.getY());
    }
}
