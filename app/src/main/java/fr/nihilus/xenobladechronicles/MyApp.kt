package fr.nihilus.xenobladechronicles

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import fr.nihilus.xenobladechronicles.di.DaggerAppComponent

class MyApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}
