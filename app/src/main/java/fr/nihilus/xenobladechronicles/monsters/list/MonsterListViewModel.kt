package fr.nihilus.xenobladechronicles.monsters.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import fr.nihilus.xenobladechronicles.datastore.MonsterDao
import fr.nihilus.xenobladechronicles.datastore.UserPrefs
import fr.nihilus.xenobladechronicles.model.DangerLevel
import fr.nihilus.xenobladechronicles.model.Monster
import fr.nihilus.xenobladechronicles.model.MonsterOrdering
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

class MonsterListViewModel @Inject constructor(
    private val monsterDao: MonsterDao,
    private val prefs: UserPrefs
) : ViewModel() {

    private val ordering = MutableStateFlow(MonsterOrdering.BY_LEVEL)

    val state: LiveData<UiState> =
        combine(monsterDao.getAll(), ordering, prefs.partyLevel) { monsters, ordering, partyLevel ->
            UiState(
                ordering = ordering,
                partyLevel = partyLevel,
                monsters = monsters
                    .sortedWith(ordering.comparator)
                    .map {
                        UiState.MonsterInfo(
                            monster = it,
                            dangerLevel = DangerLevel.from(it.level, partyLevel),
                        )
                    }
            )
        }.asLiveData()

    fun orderBy(ordering: MonsterOrdering) {
        this.ordering.value = ordering
    }

    fun markAsDefeated(monster: UiState.MonsterInfo) {
        viewModelScope.launch {
            val defeatedMonster = monster.monster.copy(isDefeated = true)
            monsterDao.update(defeatedMonster)
        }
    }
}

data class UiState(
    val ordering: MonsterOrdering,
    val monsters: List<MonsterInfo>,
    val partyLevel: Int
) {
    data class MonsterInfo(
        val monster: Monster,
        val dangerLevel: DangerLevel
    )
}
