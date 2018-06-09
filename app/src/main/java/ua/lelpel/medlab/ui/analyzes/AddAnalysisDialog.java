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
 * Created by bruce on 02.12.2017.
 */

public class AddAnalysisDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    LocalDate d = LocalDate.now();

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthIndexedFromZero, int dayOfMonth) {
        d = new LocalDate(year, monthIndexedFromZero + 1, dayOfMonth);
        Log.i("DATE", d.toString());
    }

    interface OnDialogInteractionListener {
        void onPositive(AddAnalysisDialog dialog);
        void onNegative(AddAnalysisDialog dialog);
    }

    AddAnalysisRepository presenter;
    private OnDialogInteractionListener listener;

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

        builder.setMessage(R.string.add_analysis).setView(view).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onPositive(AddAnalysisDialog.this);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onNegative(AddAnalysisDialog.this);
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

    @OnClick(R.id.add_anal_datepicker)
    public void onDatePickerCall() {
        DatePickerDialogFragment.newInstance(this).show(getFragmentManager(), DatePickerDialogFragment.TAG);
    }

    private void setUpSpinnerData() {
        ArrayAdapter<EquipmentDb> eqAdapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, presenter.getEquipment());
        equipmentSpinner.setAdapter(eqAdapter);

        ArrayAdapter<AnalysisTypeDb> typeAdapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, presenter.getTypes());
        typeSpinner.setAdapter(typeAdapter);

        ArrayAdapter<StaffMemberDb> staffAdapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, presenter.getStaff());
        staffSpinner.setAdapter(staffAdapter);
    }
}
