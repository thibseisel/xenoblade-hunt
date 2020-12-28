package fr.nihilus.xenobladechronicles.di

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import fr.nihilus.xenobladechronicles.datastore.DatabaseModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

/**
 * Un module Dagger décrivant la façon de construire
 * les dépendances attachées au contexte de l'application.
 */
@Module(includes = [
    DatabaseModule::class,
    ViewModelModule::class
])
internal class AppModule {
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
}
