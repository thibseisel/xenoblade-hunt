package fr.nihilus.xenobladechronicles.monsters

import fr.nihilus.xenobladechronicles.model.Area
import fr.nihilus.xenobladechronicles.model.DangerLevel
import fr.nihilus.xenobladechronicles.model.Monster
import org.junit.Assert.assertEquals
import org.junit.Test

private val RODRIGUEZ = Monster(
    id = 42L,
    level = 40,
    name = "Rodriguez le Souple",
    kind = "Batraz",
    area = Area.COLONY_9,
    location = "Nord de la rive d'Agora",
    isDefeated = false
)

class MonsterTest {

    @Test
    fun getDangerLevel() {
        RODRIGUEZ.assertDangerIs(DangerLevel.DANGER, 20)
        RODRIGUEZ.assertDangerIs(DangerLevel.DANGER, 34)
        RODRIGUEZ.assertDangerIs(DangerLevel.STRONG, 35)
        RODRIGUEZ.assertDangerIs(DangerLevel.STRONG, 37)
        RODRIGUEZ.assertDangerIs(DangerLevel.EQUAL, 38)
        RODRIGUEZ.assertDangerIs(DangerLevel.EQUAL, 42)
        RODRIGUEZ.assertDangerIs(DangerLevel.WEAK, 43)
        RODRIGUEZ.assertDangerIs(DangerLevel.WEAK, 45)
        RODRIGUEZ.assertDangerIs(DangerLevel.EASY, 46)
        RODRIGUEZ.assertDangerIs(DangerLevel.EASY, 50)
    }

    private fun Monster.assertDangerIs(danger: DangerLevel, playerLevel: Int) {
        assertEquals(danger, DangerLevel.from(level, playerLevel))
    }
}
