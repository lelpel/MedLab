package ua.lelpel.medlab.ui.reagents;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import org.joda.time.LocalDate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.lelpel.medlab.R;
import ua.lelpel.medlab.db.entities.AnalysisTypeDb;
import ua.lelpel.medlab.db.entities.EquipmentDb;
import ua.lelpel.medlab.db.entities.StaffMemberDb;
import ua.lelpel.medlab.ui.analyzes.DatePickerDialogFragment;

/**
 * Created by bruce on 02.12.2017.
 */

public class AddReagentDialog extends DialogFragment {
    interface OnDialogInteractionListener {
        void onPositive(AddReagentDialog dialog);
        void onNegative(AddReagentDialog dialog);
    }

    private OnDialogInteractionListener listener;

    @BindView(R.id.et_add_reagent)
    EditText nameEt;

    @BindView(R.id.et_add_reagent_amount)
    EditText amountEt;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_reagent_dialog, null);

        builder.setMessage(R.string.add).setView(view).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onPositive(AddReagentDialog.this);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onNegative(AddReagentDialog.this);
            }
        });

        ButterKnife.bind(this, view);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listener = (OnDialogInteractionListener) getTargetFragment();
    }
}
