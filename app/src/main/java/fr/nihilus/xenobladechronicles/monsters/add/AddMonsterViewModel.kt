package fr.nihilus.xenobladechronicles.monsters.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import fr.nihilus.xenobladechronicles.datastore.MonsterDao
import fr.nihilus.xenobladechronicles.model.Area
import fr.nihilus.xenobladechronicles.model.Monster
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.ExperimentalTime
import kotlin.time.milliseconds

/**
 * Un [ViewModel] gérant la tâche d'ajout d'un [Monster].
 */
class AddMonsterViewModel @Inject constructor(
    private val monsterDao: MonsterDao
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")

    /**
     * @return suggestions pour la saisie de l'espèce à laquelle un monstre appartient
     */
    @OptIn(ExperimentalTime::class, FlowPreview::class)
    val kinds: LiveData<List<String>> = searchQuery
        .debounce(300.milliseconds)
        .flatMapLatest { rawSearch ->
            val search = rawSearch.trim()
            when {
                search.isEmpty() -> flowOf(emptyList())
                else -> monsterDao.searchKinds(rawSearch)
            }
        }
        .asLiveData()

    /**
     * Spécifie les caractères saisis par l'utilisateur lorsqu'il précise l'espèce
     * à laquelle le monstre ajouté appartient.
     * Les suggestions retournées par [.getKinds] seront automatiquement mises à jour.
     */
    fun setKindQuery(input: String) {
        searchQuery.value = input
    }

    fun registerMonster(level: Int, name: String, kind: String, area: Area, location: String) {
        viewModelScope.launch {
            val monster = Monster(id = null, level, name, kind, area, location, isDefeated = false)
            monsterDao.insert(monster)
        }
    }
}
