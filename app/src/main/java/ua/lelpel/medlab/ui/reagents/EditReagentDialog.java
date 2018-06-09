package ua.lelpel.medlab.ui.reagents;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.lelpel.medlab.R;

/**
 * Created by bruce on 04.12.2017.
 */

public class EditReagentDialog extends DialogFragment {
    interface OnEditListener {
        void onCreate(EditReagentDialog dialog);
        void onPositive(EditReagentDialog dialog, int editId);
        void onNegative(EditReagentDialog dialog);
    }

    private EditReagentDialog.OnEditListener listener;

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
                listener.onPositive(EditReagentDialog.this, getArguments().getInt("EDITID"));
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onNegative(EditReagentDialog.this);
            }
        });

        ButterKnife.bind(this, view);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listener = (EditReagentDialog.OnEditListener) getTargetFragment();
    }


    public static EditReagentDialog newInstance(int id) {
        Bundle b = new Bundle();
        b.putInt("EDITID", id);
        EditReagentDialog instance = new EditReagentDialog();
        instance.setArguments(b);
        return instance;
    }
}
