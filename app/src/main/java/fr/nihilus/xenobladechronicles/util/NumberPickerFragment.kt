package fr.nihilus.xenobladechronicles.util

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup.LayoutParams
import android.widget.FrameLayout
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import fr.nihilus.xenobladechronicles.R

class NumberPickerFragment : AppCompatDialogFragment() {
    private var value = 0
    private var minValue = 0
    private var maxValue = 0
    private var callback: ValueCallback? = null

    fun setValueCallback(callback: ValueCallback?) {
        this.callback = callback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = requireArguments()
        value = args.getInt(ARG_INITIAL_VALUE, 0)
        minValue = args.getInt(ARG_MIN_VALUE, 0)
        maxValue = args.getInt(ARG_MAX_VALUE, 100)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = requireContext()
        val picker = NumberPicker(context).also {
            it.minValue = minValue
            it.maxValue = maxValue
            it.value = value
            it.setOnValueChangedListener { _, _, newVal -> value = newVal }
        }

        val container = FrameLayout(context)
        val lp = FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        lp.gravity = Gravity.CENTER
        container.addView(picker, lp)

        val builder = AlertDialog.Builder(context)
        return builder.setTitle(R.string.input_party_level)
            .setView(container)
            .setPositiveButton(R.string.validate) { _, _ -> callback?.onValueChanged(value) }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }

    interface ValueCallback {
        fun onValueChanged(newValue: Int)
    }

    companion object {
        private const val ARG_INITIAL_VALUE = "initial"
        private const val ARG_MIN_VALUE = "min"
        private const val ARG_MAX_VALUE = "max"

        fun newInstance(initialValue: Int, minValue: Int, maxValue: Int): NumberPickerFragment {
            require(minValue <= maxValue) { "minValue should be less than maxValue" }
            require(initialValue in minValue..maxValue) { "initialValue should be in minValue..maxValue" }
            return NumberPickerFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_INITIAL_VALUE, initialValue)
                    putInt(ARG_MIN_VALUE, minValue)
                    putInt(ARG_MAX_VALUE, maxValue)
                }
            }
        }
    }
}
