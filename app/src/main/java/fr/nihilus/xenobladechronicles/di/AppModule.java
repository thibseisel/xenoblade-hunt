package fr.nihilus.xenobladechronicles.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.nihilus.xenobladechronicles.datastore.AppDatabase;
import fr.nihilus.xenobladechronicles.datastore.MonsterDao;
import fr.nihilus.xenobladechronicles.model.Monster;

/**
 * Un module Dagger décrivant la façon de construire
 * les dépendances attachées au contexte de l'application.
 */
@Module(includes = ViewModelModule.class)
class AppModule {

    /**
     * Fournit une instance unique d'une connexion à la base de données de l'application.
     * L'accès aux données se fait via une classe DAO telle que {@link MonsterDao}.
     * @param app contexte de l'application
     */
    @Provides @Singleton
    AppDatabase provideDatabase(Application app) {
        return Room.databaseBuilder(app, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
    }

    /**
     * Fournit une instance unique des préférences partagées de l'application.
     * Bien que marquée comme Singleton, cet objet est déjà partagée entre tous ses clients.
     * @param app contexte de l'application
     */
    @Provides @Singleton
    SharedPreferences provideAppPreferences(Application app) {
        return PreferenceManager.getDefaultSharedPreferences(app);
    }

    /**
     * Fournit un objet permettant de gérer le stockage de {@link Monster}.
     * @param db base de données dont les données proviennent
     */
    @Provides @Singleton
    MonsterDao provideMonsterDao(AppDatabase db) {
        return db.monsterDao();
    }
}
