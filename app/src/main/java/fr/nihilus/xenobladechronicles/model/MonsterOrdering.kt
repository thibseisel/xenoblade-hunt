package fr.nihilus.xenobladechronicles.model

enum class MonsterOrdering(
    val comparator: Comparator<Monster>
) {
    BY_LEVEL(compareBy(Monster::level)),
    BY_NAME(compareBy(Monster::name)),
    BY_AREA(compareBy(Monster::area).then(BY_LEVEL.comparator));
}
