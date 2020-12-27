package fr.nihilus.xenobladechronicles.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import fr.nihilus.xenobladechronicles.monsters.add.AddMonsterViewModel
import fr.nihilus.xenobladechronicles.monsters.list.MonsterListViewModel
import fr.nihilus.xenobladechronicles.viewmodel.ViewModelFactory

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MonsterListViewModel::class)
    abstract fun bindMonsterListViewModel(viewModel: MonsterListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddMonsterViewModel::class)
    abstract fun bindNewMonsterViewModel(viewModel: AddMonsterViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
