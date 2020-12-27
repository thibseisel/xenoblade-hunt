package fr.nihilus.xenobladechronicles.monsters.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import fr.nihilus.xenobladechronicles.R
import fr.nihilus.xenobladechronicles.model.Area
import fr.nihilus.xenobladechronicles.viewmodel.ViewModelFactory
import javax.inject.Inject

class AddMonsterDialogFragment : AppCompatDialogFragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<AddMonsterViewModel> { viewModelFactory }

    private lateinit var inputLevel: EditText
    private lateinit var inputName: EditText
    private lateinit var inputKind: AutoCompleteTextView
    private lateinit var spinnerZone: Spinner
    private lateinit var inputLocation: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.dialog_new_monster, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        inputLevel = view.findViewById(R.id.input_level)
        inputName = view.findViewById(R.id.input_name)
        spinnerZone = view.findViewById(R.id.input_area)
        inputLocation = view.findViewById(R.id.input_location)
        inputKind = view.findViewById(R.id.input_kind)

        view.findViewById<View>(R.id.btn_cancel).setOnClickListener { dismiss() }
        view.findViewById<View>(R.id.btn_save).setOnClickListener { submit() }

        inputKind.doAfterTextChanged {
            onKindTextChanged(it!!.toString())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val zoneAdapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_dropdown_item, zoneNames)

        spinnerZone.adapter = zoneAdapter
        val kindAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
        inputKind.setAdapter(kindAdapter)

        viewModel.kinds.observe(this) { suggestions ->
            kindAdapter.clear()
            kindAdapter.addAll(suggestions)
        }

        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    private val zoneNames: Array<String?>
        get() {
            val context = requireContext()
            val areas = Area.values()
            val zones = arrayOfNulls<String>(areas.size)
            for (i in areas.indices) {
                zones[i] = context.getString(areas[i].nameId)
            }
            return zones
        }

    private fun submit() {
        val level = inputLevel.text
        val name = inputName.text
        val kind = inputKind.text
        val encounterArea = Area.values()[spinnerZone.selectedItemPosition]
        val location = inputLocation.text

        if (level.isEmpty()) {
            return
        }
        if (name.isEmpty()) {
            return
        }

        val intLevel = level.toString().toInt()
        viewModel.registerMonster(
            level = intLevel,
            name = name.toString(),
            area = encounterArea,
            location = location.toString(),
            kind = kind.toString()
        )
        dismiss()
    }

    private fun onKindTextChanged(input: String) {
        viewModel.setKindQuery(input)
    }

    companion object {
        const val TAG = "NewMonsterDialog"
    }
}
