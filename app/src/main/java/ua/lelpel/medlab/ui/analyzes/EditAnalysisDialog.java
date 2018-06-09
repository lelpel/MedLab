package ua.lelpel.medlab.ui.analyzes;

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

/**
 * Created by bruce on 04.12.2017.
 */

public class EditAnalysisDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    LocalDate d = LocalDate.now();
    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthIndexedFromZero, int dayOfMonth) {
        d = new LocalDate(year, monthIndexedFromZero, dayOfMonth);
        Log.i("DATE", d.toString());
    }

    interface OnEditListener {
        void onCreate(EditAnalysisDialog dialog);
        void onPositive(EditAnalysisDialog dialog, long editId);
        void onNegative(EditAnalysisDialog dialog);
    }

    AddAnalysisRepository presenter;
    private OnEditListener listener;

    @BindView(R.id.add_anal_eq_spinner)
    Spinner equipmentSpinner;

    @BindView(R.id.add_anal_staff_spinner)
    Spinner staffSpinner;

    @BindView(R.id.add_anal_type_spinner)
    Spinner typeSpinner;

    @BindView(R.id.et_add_result)
    EditText resultEt;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        presenter = new AddAnalysisRepository(getActivity().getApplicationContext());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_analysis_dialog, null);

        builder.setMessage(R.string.edit_analysis).setView(view).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onPositive(EditAnalysisDialog.this,  getArguments().getLong("EDITID"));
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onNegative(EditAnalysisDialog.this);
            }
        });

        ButterKnife.bind(this, view);

        listener.onCreate(EditAnalysisDialog.this);
        setUpSpinnerData();

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listener = (OnEditListener) getTargetFragment();
    }

    @OnClick(R.id.add_anal_datepicker)
    public void onDatePickerCall() {
        DatePickerDialogFragment.newInstance(this).show(getFragmentManager(), DatePickerDialogFragment.TAG);
    }

    private void setUpSpinnerData() {
        ArrayAdapter<EquipmentDb> eqAdapter = new ArrayAdapter<EquipmentDb>(getActivity(), R.layout.support_simple_spinner_dropdown_item, presenter.getEquipment());
        equipmentSpinner.setAdapter(eqAdapter);

        ArrayAdapter<AnalysisTypeDb> typeAdapter = new ArrayAdapter<AnalysisTypeDb>(getActivity(), R.layout.support_simple_spinner_dropdown_item, presenter.getTypes());
        typeSpinner.setAdapter(typeAdapter);

        ArrayAdapter<StaffMemberDb> staffAdapter = new ArrayAdapter<StaffMemberDb>(getActivity(), R.layout.support_simple_spinner_dropdown_item, presenter.getStaff());
        staffSpinner.setAdapter(staffAdapter);
    }

    public static EditAnalysisDialog newInstance(long id) {
        Bundle b = new Bundle();
        b.putLong("EDITID", id);
        EditAnalysisDialog instance = new EditAnalysisDialog();
        instance.setArguments(b);
        return instance;
    }
}
