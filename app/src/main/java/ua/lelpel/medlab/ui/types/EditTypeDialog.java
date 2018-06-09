package ua.lelpel.medlab.ui.types;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import ua.lelpel.medlab.R;

/**
 * Created by bruce on 04.12.2017.
 */

public class EditTypeDialog extends DialogFragment {
    EditText input;
    private AddTypeRepository repository;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        repository = new AddTypeRepository(getActivity().getApplicationContext());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        input = new EditText(getActivity());

        input.setInputType(InputType.TYPE_CLASS_TEXT);

        builder.setMessage(R.string.add_type).setView(input).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                repository.update(input.getText().toString(), getArguments().getInt("EDITID"));
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });

        return builder.create();
    }


    public static EditTypeDialog newInstance(int id) {
        Bundle b = new Bundle();
        b.putInt("EDITID", id);
        EditTypeDialog instance = new EditTypeDialog();
        instance.setArguments(b);
        return instance;
    }
}
