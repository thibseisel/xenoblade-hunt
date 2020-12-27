package fr.nihilus.xenobladechronicles.di

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import dagger.Module
import dagger.Provides
import fr.nihilus.xenobladechronicles.datastore.AppDatabase
import fr.nihilus.xenobladechronicles.datastore.MonsterDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

/**
 * Un module Dagger décrivant la façon de construire
 * les dépendances attachées au contexte de l'application.
 */
@Module(includes = [ViewModelModule::class])
internal class AppModule {
    /**
     * Fournit une instance unique d'une connexion à la base de données de l'application.
     * L'accès aux données se fait via une classe DAO telle que [MonsterDao].
     * @param app contexte de l'application
     */
    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .build()
    }

    /**
     * Fournit une instance unique des préférences partagées de l'application.
     * Bien que marquée comme Singleton, cet objet est déjà partagée entre tous ses clients.
     * @param app contexte de l'application
     */
    @Provides
    @Singleton
    fun provideAppPreferences(app: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(app)
    }

    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides @Named("main")
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides @Named("io")
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    /**
     * Fournit un objet permettant de gérer le stockage de [Monster].
     * @param db base de données dont les données proviennent
     */
    @Provides
    @Singleton
    fun provideMonsterDao(db: AppDatabase): MonsterDao = db.monsterDao
}
