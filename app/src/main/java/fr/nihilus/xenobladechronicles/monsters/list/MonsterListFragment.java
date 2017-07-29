package fr.nihilus.xenobladechronicles.monsters.list;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import fr.nihilus.recyclerfragment.RecyclerFragment;
import fr.nihilus.xenobladechronicles.R;
import fr.nihilus.xenobladechronicles.datastore.UserPrefs;
import fr.nihilus.xenobladechronicles.model.Monster;
import fr.nihilus.xenobladechronicles.monsters.add.AddMonsterDialogFragment;
import fr.nihilus.xenobladechronicles.util.NumberPickerFragment;
import fr.nihilus.xenobladechronicles.viewmodel.ViewModelFactory;

public class MonsterListFragment extends RecyclerFragment
        implements LifecycleRegistryOwner, MonsterAdapter.MonsterActionListener,
        NumberPickerFragment.ValueCallback {

    private static final String TAG = "MonsterListFragment";

    private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);
    @Inject SharedPreferences mPrefs;
    @Inject ViewModelFactory mViewModelFactory;

    private MonsterAdapter mAdapter;
    private MonsterListViewModel mViewModel;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MonsterListViewModel.class);
        mViewModel.init();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new MonsterAdapter(UserPrefs.getUserLevel(mPrefs), this);
        setAdapter(mAdapter);

        mViewModel.getMonsters().observe(this, new Observer<Monster[]>() {
            @Override
            public void onChanged(@Nullable Monster[] monsters) {
                if (monsters != null) {
                    mAdapter.setMonsters(monsters);
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_monsters, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected() called with: item = [" + item + "]");
        Log.d(TAG, "onOptionsItemSelected() called with: item = [" + item.getItemId() + "]");
        switch (item.getItemId()) {
            case R.id.action_level:
                int partyLevel = UserPrefs.getUserLevel(mPrefs);
                NumberPickerFragment levelPicker = NumberPickerFragment.newInstance(partyLevel, 1, 99);
                levelPicker.setValueCallback(this);
                levelPicker.show(getFragmentManager(), "levelPicker");
                return true;

            case R.id.sorting_alpha:
                // FIXME Inversé avec sorting_level, et je comprends pas du tout pourquoi
                if (!item.isChecked()) {
                    applySorting(Monster.ORDERING_NAME);
                    item.setChecked(true);
                }
                return true;

            case R.id.sorting_level:
                if (!item.isChecked()) {
                    applySorting(Monster.ORDERING_LEVEL);
                    item.setChecked(true);
                }
                return true;
            case R.id.sorting_area:
                if (!item.isChecked()) {
                    applySorting(Monster.ORDERING_AREA);
                    item.setChecked(true);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_monster_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDialogFragment newMonsterFragment = new AddMonsterDialogFragment();
                newMonsterFragment.setTargetFragment(MonsterListFragment.this, 0);
                newMonsterFragment.show(getFragmentManager(), AddMonsterDialogFragment.TAG);
            }
        });
    }

    @Override
    public void onMonsterSelected(@NonNull Monster monster) {
        Toast.makeText(getContext(), monster.getLocation(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMonsterDefeated(@NonNull Monster monster) {
        mViewModel.markAsDefeated(monster);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return mRegistry;
    }

    @Override
    public void onValueChanged(int newLevel) {
        mAdapter.setPlayerLevel(newLevel);
        UserPrefs.setUserLevel(mPrefs, newLevel);

        LinearLayoutManager manager = ((LinearLayoutManager) getRecyclerView().getLayoutManager());
        int firstVisible = manager.findFirstVisibleItemPosition();
        int lastVisible = manager.findLastVisibleItemPosition();

        if (firstVisible != RecyclerView.NO_POSITION && lastVisible != RecyclerView.NO_POSITION) {
            mAdapter.notifyItemRangeChanged(firstVisible, lastVisible - firstVisible);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private void applySorting(@Monster.Ordering int orderStrategy) {
        Log.d(TAG, "applySorting() called with: orderStrategy = [" + orderStrategy + "]");
        mAdapter.setSorting(orderStrategy);
    }
}
