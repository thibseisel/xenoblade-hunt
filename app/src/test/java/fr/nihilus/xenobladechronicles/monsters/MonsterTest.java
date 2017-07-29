package fr.nihilus.xenobladechronicles.monsters;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import fr.nihilus.xenobladechronicles.model.Area;
import fr.nihilus.xenobladechronicles.model.Monster;

import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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

    @Test
    public void ordering_Area() throws Exception {
        ArrayList<Monster> monsters = new ArrayList<>(3);
        Monster m1 = new Monster();
        m1.setArea(Area.FALLEN_ARM);
        m1.setLevel(58);
        monsters.add(m1);

        Monster m2 = new Monster();
        m2.setArea(Area.BIONIS_LEG);
        m2.setLevel(90);
        monsters.add(m2);

        Monster m3 = new Monster();
        m3.setArea(Area.BIONIS_LEG);
        m3.setLevel(16);
        monsters.add(m3);

        Comparator<Monster> areaComparator = Monster.comparator(Monster.ORDERING_AREA);
        Collections.sort(monsters, areaComparator);

        assertThat(monsters, contains(m3, m2, m1));
    }

    private void assertDangerIs(@Monster.DangerLevel int level, int playerLevel) {
        assertEquals(level, subject.getDangerLevel(playerLevel));
    }
}