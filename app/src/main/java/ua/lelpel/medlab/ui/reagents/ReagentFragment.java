package ua.lelpel.medlab.ui.reagents;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.lelpel.medlab.R;
import ua.lelpel.medlab.db.entities.ReagentDb;
import ua.lelpel.medlab.ui.EntityFragment;
import ua.lelpel.medlab.ui.SearchDialog;

/**
 *
 */
public class ReagentFragment extends Fragment implements AddReagentDialog.OnDialogInteractionListener, EntityFragment, Toolbar.OnMenuItemClickListener, SearchDialog.OnSearchDialogListener, EditReagentDialog.OnEditListener {
    private ReagentRepository presenter;
    private ReagentRecyclerViewAdapter adapter;

    @BindView(R.id.reagents_recycler_view)
    RecyclerView reagentsRv;

    @BindView(R.id.add_reagent_fab)
    FloatingActionButton addFab;

    @BindView(R.id.reagent_fragment_toolbar)
    Toolbar toolbar;

    private ReagentRecyclerViewAdapter.OnEditListener editL;
    private ReagentRecyclerViewAdapter.OnDeleteListener deleteL;

    public ReagentFragment() {

    }

    public static ReagentFragment newInstance() {
        return new ReagentFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presenter = new ReagentRepository(getActivity().getApplicationContext());

        setUpListeners();
        adapter = new ReagentRecyclerViewAdapter(presenter.getData(), editL, deleteL);

        View view = inflater.inflate(R.layout.fragment_reagents, container, false);
        ButterKnife.bind(this, view);

        reagentsRv.setAdapter(adapter);

        toolbar.inflateMenu(R.menu.reagents_ctx_menu);
        toolbar.setOnMenuItemClickListener(this);

        new ItemTouchHelper(deleteCallback).attachToRecyclerView(reagentsRv);
        return view;
    }

    private void setUpListeners() {
        editL = new ReagentRecyclerViewAdapter.OnEditListener() {
            @Override
            public void onEdit(ReagentDb item) {
                //TODO: show dialog
                DialogFragment dialogFrag = EditReagentDialog.newInstance(item.getId());
                dialogFrag.setTargetFragment(ReagentFragment.this, 101);
                dialogFrag.show(getFragmentManager().beginTransaction(), "dialog");
            }
        };

        deleteL = new ReagentRecyclerViewAdapter.OnDeleteListener() {
            @Override
            public void onDelete(ReagentDb item) {
                Toast.makeText(ReagentFragment.this.getActivity(), "LISTENER", Toast.LENGTH_LONG).show();
                presenter.deleteItem(item);
                adapter.update(presenter.getData());
            }
        };
    }

    @Override
    public void onPositive(AddReagentDialog dialog) {
        presenter.newReagent(dialog.nameEt.getText().toString(), dialog.amountEt.getText().toString());

        adapter.update(presenter.getData());

    }

    @Override
    public void onNegative(AddReagentDialog dialog) {
        dialog.dismiss();
    }

    @OnClick(R.id.add_reagent_fab)
     void onAdd() {
        DialogFragment dialogFrag = new AddReagentDialog();
        dialogFrag.setTargetFragment(this, 101);
        dialogFrag.show(getFragmentManager().beginTransaction(), "dialog");
    }

    private ItemTouchHelper.SimpleCallback deleteCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            Log.i("SWIPE", "WE SWIPED");
            int pos = viewHolder.getAdapterPosition();

            ReagentDb item = adapter.getById(pos);

            deleteL.onDelete(item);
        }
    };

    @Override
    public void sort(int type) {
    }

    @Override
    public void search() {

    }

    @Override
    public void filter() {

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
//        List<ReagentDb> data = null;
//
//        switch (menuItem.getItemId()) {
//            case R.id.menuitem_sort_analyses_id_asc:
//                data = presenter.sortId(ASC);
//                break;
//            case R.id.menuitem_sort_analyses_id_desc:
//                data = presenter.sortId(DESC);
//                break;
//            case R.id.menuitem_sort_analyses_date_asc:
//                Log.i("SORT", "DATEASC");
//                data = presenter.sortDate(ASC);
//                break;
//            case R.id.menuitem_sort_analyses_date_desc:
//                data = presenter.sortDate(DESC);
//                break;
//            case R.id.menuitem_sort_analyses_type_asc:
//                data = presenter.sortType(ASC);
//                break;
//            case R.id.menuitem_sort_analyses_type_desc:
//                data = presenter.sortType(DESC);
//                break;
//
//            case R.id.menuitem_filter_analyses_date_lt:
//                showDatePicker();
//                isGt = false;
//                data = presenter.filterDateLessThan(d);
//                break;
//            case R.id.menuitem_filter_analyses_date_gt:
//                showDatePicker();
//                break;
//            case R.id.menuitem_analyzes_unfliter:
//                data = presenter.getData();
//                break;
//            case R.id.app_bar_search:
//                showSearchDialog("Search");
//                break;
//        }
//
//        if (data != null) adapter.update(data);
        return true;
    }

    private void showSearchDialog(String tag) {
        SearchDialog dialogFrag = new SearchDialog();
        dialogFrag.setTargetFragment(this, 101);
        dialogFrag.show(getFragmentManager(), tag);
    }

    @Override
    public void onSearchQueryOk(String s) {
        //Toast.makeText(getActivity(), "ВОШЛИ В ПОИСК", Toast.LENGTH_SHORT).show();
        List<ReagentDb> search = presenter.search(s);
        adapter.update(search);
    }

    @Override
    public void onCancel(DialogFragment dialogFragment) {
        dialogFragment.dismiss();
    }

    @Override
    public void onCreate(EditReagentDialog dialog) {

    }

    @Override
    public void onPositive(EditReagentDialog dialog, int editId) {
        presenter.updateAnalysis(editId, dialog.nameEt.getText().toString(), dialog.amountEt.getText().toString());

        adapter.update(presenter.getData());

    }

    @Override
    public void onNegative(EditReagentDialog dialog) {

    }
}
