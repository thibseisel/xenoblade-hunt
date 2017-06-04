package fr.nihilus.xenobladechronicles.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import fr.nihilus.xenobladechronicles.monsters.add.AddMonsterViewModel;
import fr.nihilus.xenobladechronicles.monsters.list.MonsterListViewModel;
import fr.nihilus.xenobladechronicles.viewmodel.ViewModelFactory;

@Module
public abstract class ViewModelModule {
    @Binds @IntoMap
    @ViewModelKey(MonsterListViewModel.class)
    abstract ViewModel bindMonsterListViewModel(MonsterListViewModel viewModel);

    @Binds @IntoMap
    @ViewModelKey(AddMonsterViewModel.class)
    abstract ViewModel bindNewMonsterViewModel(AddMonsterViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
