package fr.nihilus.xenobladechronicles.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import fr.nihilus.xenobladechronicles.MainActivity

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}
