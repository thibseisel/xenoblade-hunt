package fr.nihilus.xenobladechronicles.monsters.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import fr.nihilus.xenobladechronicles.model.Monster;
import fr.nihilus.xenobladechronicles.monsters.MonsterRepository;

public class MonsterListViewModel extends ViewModel {

    private final MonsterRepository mRepository;
    private LiveData<Monster[]> mAllMonsters;

    @Inject
    MonsterListViewModel(MonsterRepository repo) {
        mRepository = repo;
    }

    void init() {
        mAllMonsters = mRepository.getAll();
    }

    LiveData<Monster[]> getMonsters() {
        return mAllMonsters;
    }

    void markAsDefeated(Monster defeatedMonster) {
        defeatedMonster.setDefeated(true);
        mRepository.update(defeatedMonster);
    }

    void delete(Monster monsterToDelete) {
        mRepository.delete(monsterToDelete);
    }
}
