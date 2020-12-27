package fr.nihilus.xenobladechronicles.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import fr.nihilus.xenobladechronicles.monsters.add.AddMonsterDialogFragment
import fr.nihilus.xenobladechronicles.monsters.list.MonsterListFragment

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMonsterListFragment(): MonsterListFragment

    @ContributesAndroidInjector
    abstract fun contributeNewMonsterFragment(): AddMonsterDialogFragment
}
