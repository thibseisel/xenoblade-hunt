package fr.nihilus.xenobladechronicles.monsters.add;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Arrays;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import fr.nihilus.xenobladechronicles.R;
import fr.nihilus.xenobladechronicles.model.Area;
import fr.nihilus.xenobladechronicles.viewmodel.ViewModelFactory;

public class AddMonsterDialogFragment extends AppCompatDialogFragment implements LifecycleRegistryOwner {

    public static final String TAG = "NewMonsterDialog";

    private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);
    @Inject ViewModelFactory mViewModelFactory;
    private EditText mInputLevel;
    private EditText mInputName;
    private AutoCompleteTextView mInputKind;
    private Spinner mSpinnerZone;
    private EditText mInputLocation;
    private AddMonsterViewModel mViewModel;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(AppCompatDialogFragment.STYLE_NO_TITLE, 0);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory)
                .get(AddMonsterViewModel.class);
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_new_monster, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mInputLevel = (EditText) view.findViewById(R.id.input_level);
        mInputName = (EditText) view.findViewById(R.id.input_name);
        mSpinnerZone = (Spinner) view.findViewById(R.id.input_area);
        mInputLocation = (EditText) view.findViewById(R.id.input_location);
        mInputKind = (AutoCompleteTextView) view.findViewById(R.id.input_kind);

        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        view.findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        mInputKind.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                onKindTextChanged(s.toString());
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> zoneAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, getZoneNames());
        mSpinnerZone.setAdapter(zoneAdapter);

        final ArrayAdapter<String> kindAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line);
        mInputKind.setAdapter(kindAdapter);

        mViewModel.getKinds().observe(this, new Observer<String[]>() {
            @Override
            public void onChanged(@Nullable String[] suggestions) {
                kindAdapter.clear();
                if (suggestions != null) {
                    Log.d(TAG, "onChanged: suggestions : " + Arrays.toString(suggestions));
                    kindAdapter.addAll(suggestions);
                }
            }
        });

        Window window = getDialog().getWindow();
        if (window != null) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }

    private String[] getZoneNames() {
        Area[] areas = Area.values();
        String[] zones = new String[areas.length];
        for (int i = 0; i < areas.length; i++) {
            zones[i] = areas[i].getName(getContext());
        }
        return zones;
    }

    private void submit() {
        Editable level = mInputLevel.getText();
        Editable name = mInputName.getText();
        Editable kind = mInputKind.getText();
        Area encounterArea = Area.values()[mSpinnerZone.getSelectedItemPosition()];
        Editable location = mInputLocation.getText();

        if (TextUtils.isEmpty(level)) {
            return;
        }

        if (TextUtils.isEmpty(name)) {
            return;
        }

        int intLevel = Integer.parseInt(level.toString());
        mViewModel.addMonster(intLevel, name.toString(), encounterArea,
                kind == null ? null : kind.toString(),
                location == null ? null : location.toString()
        );

        dismiss();
    }

    private void onKindTextChanged(String input) {
        Log.d(TAG, "onKindTextChanged: recherche de suggestions pour : " + input);
        mViewModel.setKindQuery(input);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return mRegistry;
    }
}
