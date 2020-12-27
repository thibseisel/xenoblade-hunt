package fr.nihilus.xenobladechronicles.model

enum class DangerLevel {
    DANGER,
    STRONG,
    EQUAL,
    WEAK,
    EASY;

    companion object {
        /**
         * Détermine le niveau de danger d'un monstre par rapport au niveau du leader de l'équipe.
         * @return niveau de danger représenté par le monstre.
         */
        fun from(monsterLevel: Int, partyLevel: Int): DangerLevel {
            require(partyLevel in 1..99)
            require(monsterLevel in 1..120)
            val diff = partyLevel - monsterLevel

            return when {
                diff < -5 -> DANGER
                diff < -2 -> STRONG
                diff < 2 -> EQUAL
                diff < 5 -> WEAK
                else -> EASY
            }
        }
    }
}
