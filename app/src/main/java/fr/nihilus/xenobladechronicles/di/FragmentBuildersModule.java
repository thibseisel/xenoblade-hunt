package fr.nihilus.xenobladechronicles.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import fr.nihilus.xenobladechronicles.monsters.add.AddMonsterDialogFragment;
import fr.nihilus.xenobladechronicles.monsters.list.MonsterListFragment;

@Module
public abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract MonsterListFragment contributeMonsterListFragment();

    @ContributesAndroidInjector
    abstract AddMonsterDialogFragment contributeNewMonsterFragment();
}
