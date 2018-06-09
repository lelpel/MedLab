package ua.lelpel.medlab.ui.equipment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.lelpel.medlab.R;
import ua.lelpel.medlab.db.entities.AnalysisTypeDb;
import ua.lelpel.medlab.db.entities.ReagentDb;

/**
 * Created by bruce on 02.12.2017.
 */

public class AddEquipmentDialog extends DialogFragment {
    interface OnDialogInteractionListener {
        void onPositive(AddEquipmentDialog dialog);
        void onNegative(AddEquipmentDialog dialog);
    }

    AddEquipmentRepository presenter;
    private OnDialogInteractionListener listener;

    @BindView(R.id.add_eq_reagent_spinner)
    Spinner reagentSpinner;

    @BindView(R.id.add_eq_type_spinner)
    Spinner typeSpinner;

    @BindView(R.id.et_add_eq)
    EditText nameEt;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        presenter = new AddEquipmentRepository(getActivity().getApplicationContext());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_eq_dialog, null);

        builder.setMessage(R.string.add_eq).setView(view).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onPositive(AddEquipmentDialog.this);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onNegative(AddEquipmentDialog.this);
            }
        });

        ButterKnife.bind(this, view);

        setUpSpinnerData();

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listener = (OnDialogInteractionListener) getTargetFragment();
    }

    //done
    private void setUpSpinnerData() {
        ArrayAdapter<ReagentDb> reagAdapter = new ArrayAdapter<ReagentDb>(getActivity(), R.layout.support_simple_spinner_dropdown_item, presenter.getReagents());
        reagentSpinner.setAdapter(reagAdapter);

        ArrayAdapter<AnalysisTypeDb> typeAdapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, presenter.getTypes());
        typeSpinner.setAdapter(typeAdapter);
    }
}
