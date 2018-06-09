package ua.lelpel.medlab.ui.analyzes;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import org.joda.time.LocalDate;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.lelpel.medlab.R;
import ua.lelpel.medlab.db.entities.AnalysisTypeDb;
import ua.lelpel.medlab.db.entities.EquipmentDb;
import ua.lelpel.medlab.db.entities.StaffMemberDb;
import ua.lelpel.medlab.ui.EntityFragment;
import ua.lelpel.medlab.ui.SearchDialog;
import ua.lelpel.medlab.ui.entities.AnalysisUi;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class AnalysisFragment extends Fragment implements AddAnalysisDialog.OnDialogInteractionListener, EntityFragment, Toolbar.OnMenuItemClickListener, DatePickerDialog.OnDateSetListener, SearchDialog.OnSearchDialogListener, EditAnalysisDialog.OnEditListener {
    AnalyzesRepository presenter;

    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private AnalyzesRecyclerViewAdapter adapter;

    @BindView(R.id.analyzes_recycler_view)
    RecyclerView analyzesRv;

    @BindView(R.id.add_fab)
    FloatingActionButton addFab;

    @BindView(R.id.analysis_fragment_toolbar)
    Toolbar toolbar;

    private AnalyzesRecyclerViewAdapter.OnEditListener editL;
    private AnalyzesRecyclerViewAdapter.OnDeleteListener deleteL;

    public AnalysisFragment() {

    }

    public static AnalysisFragment newInstance() {
        return new AnalysisFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presenter = new AnalyzesRepository(getActivity().getApplicationContext());

        setUpListeners();
        adapter = new AnalyzesRecyclerViewAdapter(presenter.getData(), null, editL, deleteL);

        View view = inflater.inflate(R.layout.fragment_analysis_list, container, false);
        ButterKnife.bind(this, view);
//
//        Context context = view.getContext();
//        RecyclerView recyclerView = (RecyclerView) view;
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        analyzesRv.setAdapter(adapter);

        toolbar.inflateMenu(R.menu.analyzes_ctx_menu);
        toolbar.setOnMenuItemClickListener(this);


        new ItemTouchHelper(deleteCallback).attachToRecyclerView(analyzesRv);
        return view;
    }

    private void setUpListeners() {
        editL = new AnalyzesRecyclerViewAdapter.OnEditListener() {
            @Override
            public void onEdit(AnalysisUi item) {
                //TODO: show dialog
                DialogFragment dialogFrag = EditAnalysisDialog.newInstance(item.getId());
                dialogFrag.setTargetFragment(AnalysisFragment.this, 101);
                dialogFrag.show(getFragmentManager().beginTransaction(), "dialog");
            }
        };

        deleteL = new AnalyzesRecyclerViewAdapter.OnDeleteListener() {
            @Override
            public void onDelete(AnalysisUi item) {
                Toast.makeText(AnalysisFragment.this.getActivity(), "Анализ удалён", Toast.LENGTH_LONG).show();
                presenter.deleteItem(item);
                adapter.update(presenter.getData());
            }
        };
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(AnalysisUi item);
    }

    @Override
    public void onPositive(AddAnalysisDialog dialog) {
        EquipmentDb eq = (EquipmentDb) dialog.equipmentSpinner.getSelectedItem();
        StaffMemberDb staff = (StaffMemberDb) dialog.staffSpinner.getSelectedItem();
        AnalysisTypeDb type = (AnalysisTypeDb) dialog.typeSpinner.getSelectedItem();
        String result = dialog.resultEt.getText().toString();
        LocalDate date = dialog.d;

        presenter.newAnalysis(result, date, type, staff, eq);

        adapter.update(presenter.getData());

    }

    @Override
    public void onNegative(AddAnalysisDialog dialog) {
        dialog.dismiss();
    }

    @OnClick(R.id.add_fab)
     void onAdd() {
        DialogFragment dialogFrag = new AddAnalysisDialog();
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

            AnalysisUi item = adapter.getById(pos);

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

    boolean isGt = true;

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        List<AnalysisUi> data = null;

        switch (menuItem.getItemId()) {
            case R.id.menuitem_sort_analyses_id_asc:
                data = presenter.sortId(ASC);
                break;
            case R.id.menuitem_sort_analyses_id_desc:
                data = presenter.sortId(DESC);
                break;
            case R.id.menuitem_sort_analyses_date_asc:
                Log.i("SORT", "DATEASC");
                data = presenter.sortDate(ASC);
                break;
            case R.id.menuitem_sort_analyses_date_desc:
                data = presenter.sortDate(DESC);
                break;
            case R.id.menuitem_sort_analyses_type_asc:
                data = presenter.sortType(ASC);
                break;
            case R.id.menuitem_sort_analyses_type_desc:
                data = presenter.sortType(DESC);
                break;
            case R.id.menuitem_filter_analyses_date_lt:
                showDatePicker();
                isGt = false;
                //data = presenter.filterDateLessThan(d);
                break;
            case R.id.menuitem_filter_analyses_date_gt:
                showDatePicker();
                break;
            case R.id.menuitem_analyzes_unfliter:
                data = presenter.getData();
                break;
            case R.id.app_bar_search:
                showSearchDialog("Search");
                break;
        }

        if (data!= null && data.isEmpty()) adapter.update(data);
        return true;
    }

    private void showSearchDialog(String tag) {
        SearchDialog dialogFrag = new SearchDialog();
        dialogFrag.setTargetFragment(this, 101);
        dialogFrag.show(getFragmentManager(), tag);
    }


    private void showDatePicker() {
        DatePickerDialogFragment.newInstance(this).show(getFragmentManager(), DatePickerDialogFragment.TAG);
    }

    LocalDate d;

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthIndexedFromZero, int dayOfMonth) {
        d = new LocalDate(year, monthIndexedFromZero, dayOfMonth);

        if (isGt) adapter.update(presenter.filterDateGreaterThan(d));
        else adapter.update(presenter.filterDateLessThan(d));
    }

    @Override
    public void onSearchQueryOk(String s) {
        //Toast.makeText(getActivity(), "ВОШЛИ В ПОИСК", Toast.LENGTH_SHORT).show();
        List<AnalysisUi> search = presenter.search(s);
        adapter.update(search);
    }

    @Override
    public void onCancel(DialogFragment dialogFragment) {
        dialogFragment.dismiss();
    }

    @Override
    public void onCreate(EditAnalysisDialog dialog) {

    }

    @Override
    public void onPositive(EditAnalysisDialog dialog, long editId) {
        EquipmentDb eq = (EquipmentDb) dialog.equipmentSpinner.getSelectedItem();
        StaffMemberDb staff = (StaffMemberDb) dialog.staffSpinner.getSelectedItem();
        AnalysisTypeDb type = (AnalysisTypeDb) dialog.typeSpinner.getSelectedItem();
        String result = dialog.resultEt.getText().toString();
        LocalDate date = dialog.d;

        presenter.updateAnalysis(editId, result, date, type, staff, eq);

        adapter.update(presenter.getData());

    }

    @Override
    public void onNegative(EditAnalysisDialog dialog) {

    }
}
