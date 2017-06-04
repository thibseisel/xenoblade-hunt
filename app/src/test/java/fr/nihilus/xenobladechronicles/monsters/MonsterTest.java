package fr.nihilus.xenobladechronicles.monsters;

import org.junit.Before;
import org.junit.Test;

import fr.nihilus.xenobladechronicles.model.Area;
import fr.nihilus.xenobladechronicles.model.Monster;

import static org.junit.Assert.assertEquals;

public class MonsterTest {

    private Monster subject;

    @Before
    public void setUp() throws Exception {
        subject = new Monster();
        subject.setId(42L);
        subject.setName("Rodriguez le Souple");
        subject.setLevel(40);
        subject.setArea(Area.COLONY_9);
        subject.setKind("Batraz");
        subject.setLocation("Nord de la rive d'Agora");
        subject.setDefeated(false);
    }

    @Test
    public void getDangerLevel() throws Exception {
        assertDangerIs(Monster.LEVEL_DANGER, 20);
        assertDangerIs(Monster.LEVEL_DANGER, 34);
        assertDangerIs(Monster.LEVEL_STRONG, 35);
        assertDangerIs(Monster.LEVEL_STRONG, 37);
        assertDangerIs(Monster.LEVEL_EQUAL, 38);
        assertDangerIs(Monster.LEVEL_EQUAL, 42);
        assertDangerIs(Monster.LEVEL_WEAK, 43);
        assertDangerIs(Monster.LEVEL_WEAK, 45);
        assertDangerIs(Monster.LEVEL_EASY, 46);
        assertDangerIs(Monster.LEVEL_EASY, 50);
    }

    @Test
    public void getDefeatedLevel() throws Exception {
        subject.setDefeated(true);
        assertDangerIs(Monster.LEVEL_DEFEATED, 20);
        assertDangerIs(Monster.LEVEL_DEFEATED, 34);
        assertDangerIs(Monster.LEVEL_DEFEATED, 38);
        assertDangerIs(Monster.LEVEL_DEFEATED, 42);
        assertDangerIs(Monster.LEVEL_DEFEATED, 45);
        assertDangerIs(Monster.LEVEL_DEFEATED, 50);
    }

    private void assertDangerIs(@Monster.DangerLevel int level, int playerLevel) {
        assertEquals(level, subject.getDangerLevel(playerLevel));
    }
}