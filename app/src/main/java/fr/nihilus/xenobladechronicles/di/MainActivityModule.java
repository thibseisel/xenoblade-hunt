package fr.nihilus.xenobladechronicles.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import fr.nihilus.xenobladechronicles.MainActivity;

@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract MainActivity contributeMainActivity();
}
