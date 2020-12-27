package fr.nihilus.xenobladechronicles.monsters.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment
import fr.nihilus.xenobladechronicles.R
import fr.nihilus.xenobladechronicles.datastore.UserPrefs
import fr.nihilus.xenobladechronicles.model.MonsterOrdering
import fr.nihilus.xenobladechronicles.monsters.add.AddMonsterDialogFragment
import fr.nihilus.xenobladechronicles.monsters.list.MonsterAdapter.MonsterActionListener
import fr.nihilus.xenobladechronicles.util.NumberPickerFragment
import fr.nihilus.xenobladechronicles.viewmodel.ViewModelFactory
import javax.inject.Inject

class MonsterListFragment : DaggerFragment(R.layout.fragment_monster_list),
    NumberPickerFragment.ValueCallback {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var prefs: UserPrefs

    private lateinit var adapter: MonsterAdapter
    private lateinit var monsterList: RecyclerView

    private val viewModel by viewModels<MonsterListViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        monsterList = view.findViewById(R.id.recycler)

        adapter = MonsterAdapter(object : MonsterActionListener {
            override fun onMonsterSelected(monster: UiState.MonsterInfo) {
                Toast.makeText(context, monster.monster.location, Toast.LENGTH_SHORT).show()
            }

            override fun onMonsterDefeated(monster: UiState.MonsterInfo) {
                viewModel.markAsDefeated(monster)
            }
        })

        monsterList.adapter = adapter

        viewModel.state.observe(viewLifecycleOwner) { state ->
            adapter.submitList(state.monsters)
        }

        view.findViewById<View>(R.id.btn_add).setOnClickListener {
            val newMonsterFragment: AppCompatDialogFragment = AddMonsterDialogFragment()
            newMonsterFragment.setTargetFragment(this@MonsterListFragment, 0)
            newMonsterFragment.show(parentFragmentManager, AddMonsterDialogFragment.TAG)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_monsters, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_level -> {
                val partyLevel = viewModel.state.value?.partyLevel ?: 1
                val levelPicker = NumberPickerFragment.newInstance(partyLevel, 1, 99)
                levelPicker.setValueCallback(this)
                levelPicker.show(parentFragmentManager, "levelPicker")
                return true
            }

            R.id.sorting_alpha -> {
                // FIXME Inversé avec sorting_level, et je comprends pas du tout pourquoi
                applySorting(MonsterOrdering.BY_NAME)
                item.isChecked = true
                return true
            }
            R.id.sorting_level -> {
                applySorting(MonsterOrdering.BY_LEVEL)
                item.isChecked = true
                return true
            }
            R.id.sorting_area -> {
                applySorting(MonsterOrdering.BY_AREA)
                item.isChecked = true
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onValueChanged(newValue: Int) {
        prefs.setUserLevel(newValue)
    }

    private fun applySorting(ordering: MonsterOrdering) {
        viewModel.orderBy(ordering)
    }
}
