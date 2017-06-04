package fr.nihilus.xenobladechronicles.monsters;

import android.arch.lifecycle.LiveData;
import android.text.TextUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.nihilus.xenobladechronicles.AppExecutors;
import fr.nihilus.xenobladechronicles.datastore.MonsterDao;
import fr.nihilus.xenobladechronicles.model.Monster;
import fr.nihilus.xenobladechronicles.util.AbsentLiveData;

@Singleton
public class MonsterRepository {

    private final AppExecutors mExecutors;
    private final MonsterDao mDao;

    @Inject
    MonsterRepository(AppExecutors executors, MonsterDao dao) {
        mExecutors = executors;
        mDao = dao;
    }

    public LiveData<Monster[]> getAll() {
        return mDao.getAll();
    }

    public LiveData<Monster[]> searchByName(String name) {
        return mDao.searchByName(name);
    }

    public LiveData<String[]> getKinds(String search) {
        if (TextUtils.isEmpty(search)) {
            return AbsentLiveData.create();
        }

        return mDao.searchKinds(search);
    }

    public void add(final Monster newMonster) {
        mExecutors.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDao.insert(newMonster);
            }
        });
    }

    public void update(final Monster updatedMonster) {
        mExecutors.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDao.update(updatedMonster);
            }
        });
    }

    public void delete(final Monster monsterToDelete) {
        mExecutors.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDao.delete(monsterToDelete);
            }
        });
    }
}
