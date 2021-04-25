package Model.items.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ToolStatsTest {
    ToolStats stats;

    @BeforeEach
    public void createStats() {
        stats = new ToolStats(10, 5, 3);
    }

    @Test
    public void getters() {
        Assertions.assertEquals(10, stats.getFightingPower());
        Assertions.assertEquals(5, stats.getMiningPower());
        Assertions.assertEquals(3, stats.getMiningHardness());
    }
}
