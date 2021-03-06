package ua.lelpel.medlab.ui.types;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.joda.time.LocalDate;

public class DatePickerDialogFragment extends android.app.DialogFragment {

    public static final String TAG = DatePickerDialogFragment.class.getSimpleName();

    private static final int FRAGMENT_REQUEST_CODE = 101;

    private DatePickerDialog.OnDateSetListener dateSetListener;

    public static DatePickerDialogFragment newInstance(android.app.Fragment targetFragment) {
        DatePickerDialogFragment fragment = new DatePickerDialogFragment();
        fragment.setTargetFragment(targetFragment, FRAGMENT_REQUEST_CODE);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dateSetListener = (DatePickerDialog.OnDateSetListener) getTargetFragment();

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        LocalDate date = LocalDate.now();

        final int year =  date.getYear();
        final int monthIndexedFromZero =  date.getMonthOfYear() - 1;
        final int dayOfMonth =date.getDayOfMonth();

        //        Instant now = Instant.now();
//
//        //Date zonedDateTime = Calendar.getInstance().getTime();
//
//        final int year = now.get(ChronoField.YEAR);
//        final int monthIndexedFromZero = now.get(ChronoField.MONTH_OF_YEAR) - 1;
//        final int dayOfMonth = now.get(ChronoField.DAY_OF_MONTH);

        return new DatePickerDialog(getTargetFragment().getActivity(), STYLE_NORMAL, dateSetListener, year, monthIndexedFromZero, dayOfMonth);
    }
}