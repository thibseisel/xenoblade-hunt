package fr.nihilus.xenobladechronicles.monsters;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import javax.inject.Inject;

import fr.nihilus.recyclerfragment.RecyclerFragment;
import fr.nihilus.xenobladechronicles.dagger.MyApp;
import fr.nihilus.xenobladechronicles.datastore.UserPrefs;

public class MonsterListFragment extends RecyclerFragment
        implements LifecycleRegistryOwner, MonsterAdapter.MonsterActionListener {

    @Inject SharedPreferences mPrefs;

    private MonstersViewModel mViewModel;
    private LifecycleRegistry mRegistry;

    private MonsterAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApp) getActivity().getApplication()).getComponent().inject(this);
        mRegistry = new LifecycleRegistry(this);
        mViewModel = ViewModelProviders.of(this).get(MonstersViewModel.class);
        setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel.init();

        mAdapter = new MonsterAdapter(getContext(), UserPrefs.getUserLevel(mPrefs));
        setAdapter(mAdapter);

        if (savedInstanceState == null) {
            setRecyclerShown(false);
        }
    }

    @Override
    public void onMonsterSelected(Monster monster) {
        Toast.makeText(getContext(), "Selected: " + monster.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMonsterDefeated(Monster monster) {
        mViewModel.markAsDefeated(monster);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return mRegistry;
    }
}
