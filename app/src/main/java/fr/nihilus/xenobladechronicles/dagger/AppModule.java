package fr.nihilus.xenobladechronicles.dagger;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.nihilus.xenobladechronicles.datastore.AppDatabase;
import fr.nihilus.xenobladechronicles.monsters.MonsterRepository;

@Module
public class AppModule {

    private final Application mApp;

    public AppModule(Application application) {
        mApp = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return mApp;
    }

    @Provides
    @Singleton
    public SharedPreferences providePreferences(Application app) {
        return PreferenceManager.getDefaultSharedPreferences(app);
    }

    @Provides
    @Singleton
    public AppDatabase provideDatabase(Application app) {
        return Room.databaseBuilder(app, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
    }

    @Provides
    @Singleton
    public MonsterRepository provideMonsterRepository(AppDatabase db) {
        return new MonsterRepository(db.monsterDao());
    }
}
