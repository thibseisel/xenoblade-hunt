package fr.nihilus.xenobladechronicles.dagger;

import android.app.Application;

public class MyApp extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Utilise le composant généré par Dagger à la compilation
        // pour initialiser l'injection de dépendances pour ce module
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getComponent() {
        return mAppComponent;
    }
}
