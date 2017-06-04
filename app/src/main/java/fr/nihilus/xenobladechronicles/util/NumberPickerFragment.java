package fr.nihilus.xenobladechronicles.util;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.NumberPicker;

import fr.nihilus.xenobladechronicles.R;

public class NumberPickerFragment extends AppCompatDialogFragment
        implements DialogInterface.OnClickListener {

    private static final String ARG_INITIAL_VALUE = "initial";
    private static final String ARG_MIN_VALUE = "min";
    private static final String ARG_MAX_VALUE = "max";

    private int mValue;
    private int mMin;
    private int mMax;

    private ValueCallback mCallback;

    public static NumberPickerFragment newInstance(int initialValue, int minValue, int maxValue) {
        if (minValue > maxValue)
            throw new IllegalArgumentException("minValue should be less than maxValue");

        if (initialValue < minValue || initialValue > maxValue)
            throw new IllegalArgumentException("initialValue should be in minValue..maxValue");

        Bundle args = new Bundle();
        args.putInt(ARG_INITIAL_VALUE, initialValue);
        args.putInt(ARG_MIN_VALUE, minValue);
        args.putInt(ARG_MAX_VALUE, maxValue);
        NumberPickerFragment fragment = new NumberPickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void setValueCallback(@Nullable ValueCallback callback) {
        mCallback = callback;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mValue = args.getInt(ARG_INITIAL_VALUE, 0);
            mMin = args.getInt(ARG_MIN_VALUE, 0);
            mMax = args.getInt(ARG_MAX_VALUE, 100);
        } else {
            mValue = 0;
            mMin = 0;
            mMax = 100;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        NumberPicker picker = new NumberPicker(getContext());
        picker.setValue(mValue);
        picker.setMinValue(mMin);
        picker.setMaxValue(mMax);
        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mValue = newVal;
            }
        });

        FrameLayout container = new FrameLayout(getContext());
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        container.addView(picker, lp);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder.setTitle(R.string.input_party_level)
                .setView(container)
                .setPositiveButton(R.string.validate, this)
                .setNegativeButton(R.string.cancel, null)
                .create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (mCallback != null) {
            mCallback.onValueChanged(mValue);
        }
    }

    public interface ValueCallback {
        void onValueChanged(int newValue);
    }
}
