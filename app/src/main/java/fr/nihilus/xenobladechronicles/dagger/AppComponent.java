package fr.nihilus.xenobladechronicles.dagger;

import javax.inject.Singleton;

import dagger.Component;
import fr.nihilus.xenobladechronicles.monsters.MonsterListFragment;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    /*
     * Ajouter ici la liste des méthodes "inject" à générer par Dagger.
     * Chaque méthode doit avoir un argument qui est le nom d'une classe du système (Activity, Fragment, Service...)
     */
    void inject(MonsterListFragment fragment);
}
