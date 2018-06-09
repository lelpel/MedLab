package ua.lelpel.medlab.ui.staff;

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
import android.widget.DatePicker;
import android.widget.EditText;

import org.joda.time.LocalDate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.lelpel.medlab.R;
import ua.lelpel.medlab.ui.analyzes.DatePickerDialogFragment;

/**
 * Created by bruce on 04.12.2017.
 */

public class EditStaffDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    interface OnEditListener {
        void onCreate(EditStaffDialog dialog);
        void onPositive(EditStaffDialog dialog, int editId);
        void onNegative(EditStaffDialog dialog);
    }

    private EditStaffDialog.OnEditListener listener;

    @BindView(R.id.et_add_staff_firstname)
    EditText firstNameEt;

    @BindView(R.id.et_add_staff_latname)
    EditText lastNameEt;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_staff_dialog, null);

        builder.setMessage(R.string.add).setView(view).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onPositive(EditStaffDialog.this, getArguments().getInt("EDITID"));
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onNegative(EditStaffDialog.this);
            }
        });

        ButterKnife.bind(this, view);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listener = (EditStaffDialog.OnEditListener) getTargetFragment();
    }


    LocalDate d = LocalDate.now();

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthIndexedFromZero, int dayOfMonth) {
        d = new LocalDate(year, monthIndexedFromZero, dayOfMonth);
        Log.i("DATE", d.toString());
    }


    @OnClick(R.id.add_staff_datepciker)
    public void onDatePickerCall() {
        DatePickerDialogFragment.newInstance(this).show(getFragmentManager(), DatePickerDialogFragment.TAG);
    }


    public static EditStaffDialog newInstance(int id) {
        Bundle b = new Bundle();
        b.putInt("EDITID", id);
        EditStaffDialog instance = new EditStaffDialog();
        instance.setArguments(b);
        return instance;
    }
}
