package ua.lelpel.medlab.ui.types;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.lelpel.medlab.R;
import ua.lelpel.medlab.db.entities.AnalysisTypeDb;

public class TypeFragment extends Fragment {
    TypesRepository repository;

    private TypeRecyclerViewAdapter adapter;

    @BindView(R.id.types_recycler_view)
    RecyclerView typesRv;

    @BindView(R.id.add_type_fab)
    FloatingActionButton addFab;


    private TypeRecyclerViewAdapter.OnEditListener editL;
    private TypeRecyclerViewAdapter.OnDeleteListener deleteL;

    public TypeFragment() {
    }

    public static TypeFragment newInstance() {
        return new TypeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        repository = new TypesRepository(getActivity().getApplicationContext());

        setUpListeners();
        adapter = new TypeRecyclerViewAdapter(repository.getData(), editL, deleteL);

        View view = inflater.inflate(R.layout.fragment_types, container, false);
        ButterKnife.bind(this, view);

        typesRv.setAdapter(adapter);

        new ItemTouchHelper(deleteCallback).attachToRecyclerView(typesRv);
        return view;
    }

    private void setUpListeners() {
        editL = new TypeRecyclerViewAdapter.OnEditListener() {
            @Override
            public void onEdit(AnalysisTypeDb item) {
                DialogFragment dialogFrag = EditTypeDialog.newInstance(item.getId());
                dialogFrag.setTargetFragment(TypeFragment.this, 101);
                dialogFrag.show(getFragmentManager().beginTransaction(), "dialog");
            }
        };

        deleteL = new TypeRecyclerViewAdapter.OnDeleteListener() {
            @Override
            public void onDelete(AnalysisTypeDb item) {
                repository.deleteItem(item);
                adapter.update(repository.getData());
            }
        };
    }


    @OnClick(R.id.add_type_fab)
     void onAdd() {
        DialogFragment dialogFrag = new AddTypeDialog();
        dialogFrag.show(getFragmentManager().beginTransaction(), "dialog");
    }

    private ItemTouchHelper.SimpleCallback deleteCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int pos = viewHolder.getAdapterPosition();

            AnalysisTypeDb item = adapter.getById(pos);

            deleteL.onDelete(item);
        }
    };
}
