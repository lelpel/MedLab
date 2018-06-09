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
 * Created by bruce on 02.12.2017.
 */

public class AddTypeDialog extends DialogFragment {
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
                repository.add(input.getText().toString());
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });

        return builder.create();
    }

}
