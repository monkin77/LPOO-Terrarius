package Model.items.food;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FoodStatsTest {
    FoodStats stats;

    @BeforeEach
    public void createStats() {
        stats = new FoodStats(20, 5);
    }

    @Test
    public void getters() {
        Assertions.assertEquals(20, stats.getAmountHP());
        Assertions.assertEquals(5, stats.getDuration());
    }
}
