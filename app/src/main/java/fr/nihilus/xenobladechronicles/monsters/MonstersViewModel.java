package fr.nihilus.xenobladechronicles.monsters;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

class MonstersViewModel extends ViewModel {
    private final MonsterRepository mRepository;
    private LiveData<Monster[]> mAllMonsters;

    @Inject
    MonstersViewModel(MonsterRepository monsterRepo) {
        mRepository = monsterRepo;
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
}
