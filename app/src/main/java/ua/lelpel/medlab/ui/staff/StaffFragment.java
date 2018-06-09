package ua.lelpel.medlab.ui.staff;

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
import ua.lelpel.medlab.db.entities.StaffMemberDb;
import ua.lelpel.medlab.ui.EntityFragment;
import ua.lelpel.medlab.ui.SearchDialog;

/**
 *
 */
public class StaffFragment extends Fragment implements AddStaffDialog.OnDialogInteractionListener, EntityFragment, Toolbar.OnMenuItemClickListener, SearchDialog.OnSearchDialogListener, EditStaffDialog.OnEditListener {
    private StaffRepository presenter;
    private StaffRecyclerViewAdapter adapter;

    @BindView(R.id.staff_recycler_view)
    RecyclerView reagentsRv;

    @BindView(R.id.add_staff_fab)
    FloatingActionButton addFab;

    @BindView(R.id.staff_fragment_toolbar)
    Toolbar toolbar;

    private StaffRecyclerViewAdapter.OnEditListener editL;
    private StaffRecyclerViewAdapter.OnDeleteListener deleteL;

    public StaffFragment() {

    }

    public static StaffFragment newInstance() {
        return new StaffFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presenter = new StaffRepository(getActivity().getApplicationContext());

        setUpListeners();
        adapter = new StaffRecyclerViewAdapter(presenter.getData(), editL, deleteL);

        View view = inflater.inflate(R.layout.fragment_staff, container, false);
        ButterKnife.bind(this, view);

        reagentsRv.setAdapter(adapter);

        toolbar.inflateMenu(R.menu.reagents_ctx_menu);
        toolbar.setOnMenuItemClickListener(this);

        new ItemTouchHelper(deleteCallback).attachToRecyclerView(reagentsRv);
        return view;
    }

    private void setUpListeners() {
        editL = new StaffRecyclerViewAdapter.OnEditListener() {
            @Override
            public void onEdit(StaffMemberDb item) {
                //TODO: show dialog
                DialogFragment dialogFrag = EditStaffDialog.newInstance(item.getId());
                dialogFrag.setTargetFragment(StaffFragment.this, 101);
                dialogFrag.show(getFragmentManager().beginTransaction(), "dialog");
            }
        };

        deleteL = new StaffRecyclerViewAdapter.OnDeleteListener() {
            @Override
            public void onDelete(StaffMemberDb item) {
                Toast.makeText(StaffFragment.this.getActivity(), "LISTENER", Toast.LENGTH_LONG).show();
                presenter.deleteItem(item);
                adapter.update(presenter.getData());
            }
        };
    }

    @Override
    public void onPositive(AddStaffDialog dialog) {
        presenter.newStaffMember(dialog.firstNameEt.getText().toString(), dialog.lastNameEt.getText().toString(), dialog.d);

        adapter.update(presenter.getData());

    }

    @Override
    public void onNegative(AddStaffDialog dialog) {
        dialog.dismiss();
    }

    @OnClick(R.id.add_staff_fab)
     void onAdd() {
        DialogFragment dialogFrag = new AddStaffDialog();
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

            StaffMemberDb item = adapter.getById(pos);

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
        List<StaffMemberDb> data = null;

        switch (menuItem.getItemId()) {
            case R.id.menuitem_sort_analyses_date_asc:
                Log.i("SORT", "DATEASC");
                data = presenter.sortDate(ASC);
                break;
            case R.id.menuitem_sort_analyses_date_desc:
                data = presenter.sortDate(DESC);
                break;
            case R.id.menuitem_filter_analyses_date_lt:
                //showDatePicker();
                //isGt = false;
                break;
            case R.id.menuitem_filter_analyses_date_gt:
                //showDatePicker();
                break;
            case R.id.menuitem_analyzes_unfliter:
                data = presenter.getData();
                break;
            case R.id.app_bar_search:
                showSearchDialog("Search");
                break;
        }

        if (data != null) adapter.update(data);
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
        List<StaffMemberDb> search = presenter.search(s);
        adapter.update(search);
    }

    @Override
    public void onCancel(DialogFragment dialogFragment) {
        dialogFragment.dismiss();
    }

    @Override
    public void onCreate(EditStaffDialog dialog) {

    }

    @Override
    public void onPositive(EditStaffDialog dialog, int editId) {
        presenter.update(editId, dialog.firstNameEt.getText().toString(), dialog.lastNameEt.getText().toString(), dialog.d);

        adapter.update(presenter.getData());
    }

    @Override
    public void onNegative(EditStaffDialog dialog) {
        dialog.dismiss();
    }
}
