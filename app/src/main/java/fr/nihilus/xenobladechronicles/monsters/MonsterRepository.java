package fr.nihilus.xenobladechronicles.monsters;

import android.arch.lifecycle.LiveData;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.nihilus.xenobladechronicles.datastore.MonsterDao;

@Singleton
public class MonsterRepository {

    private MonsterDao mDao;

    @Inject
    public MonsterRepository(MonsterDao dao) {
        mDao = dao;
    }

    LiveData<Monster[]> getAll() {
        return mDao.getAll();
    }

    LiveData<Monster[]> searchByName(String name) {
        return mDao.searchByName(name);
    }

    void add(Monster newMonster) {
        mDao.insert(newMonster);
    }

    void update(Monster updatedMonster) {
        mDao.update(updatedMonster);
    }
}
