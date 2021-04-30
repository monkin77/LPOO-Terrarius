package Model.elements.enemies;

import Model.Level;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnemyStatsTest {
    EnemyStats stats;

    @BeforeEach
    public void createStats() {
        stats = new EnemyStats(100, 20, new Level(4, 0));
    }

    @Test
    public void getters() {
        Assertions.assertEquals(100, stats.getHp());
        Assertions.assertEquals(20, stats.getPower());
        Assertions.assertEquals(new Level(4, 0), stats.getLevel());
    }

    @Test
    public void setters() {
        stats.setHp(150);
        Assertions.assertEquals(150, stats.getHp());
    }
}
