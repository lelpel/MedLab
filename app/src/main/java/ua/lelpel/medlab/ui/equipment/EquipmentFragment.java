package ua.lelpel.medlab.ui.equipment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.lelpel.medlab.R;
import ua.lelpel.medlab.db.entities.AnalysisTypeDb;
import ua.lelpel.medlab.db.entities.ReagentDb;
import ua.lelpel.medlab.ui.SearchDialog;
import ua.lelpel.medlab.ui.entities.AnalysisUi;
import ua.lelpel.medlab.ui.entities.EquipmentUi;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class EquipmentFragment extends Fragment implements AddEquipmentDialog.OnDialogInteractionListener, EditEquipmentDialog.OnDialogInteractionListener {
    EquipmentRepository presenter;
    private EquipmentRecyclerViewAdapter adapter;

    @BindView(R.id.eq_recycler_view)
    RecyclerView eqRv;

    @BindView(R.id.add_eq_fab)
    FloatingActionButton addFab;

    @BindView(R.id.eq_fragment_toolbar)
    Toolbar toolbar;

    private EquipmentRecyclerViewAdapter.OnEditListener editL;
    private EquipmentRecyclerViewAdapter.OnDeleteListener deleteL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presenter = new EquipmentRepository(getActivity().getApplicationContext());

        setUpListeners();
        adapter = new EquipmentRecyclerViewAdapter(presenter.getData(), editL, deleteL);

        View view = inflater.inflate(R.layout.fragment_eq, container, false);
        ButterKnife.bind(this, view);

        eqRv.setAdapter(adapter);

        //toolbar.inflateMenu(R.menu.analyzes_ctx_menu);
        //toolbar.setOnMenuItemClickListener(this);


        new ItemTouchHelper(deleteCallback).attachToRecyclerView(eqRv);
        return view;
    }

    private void setUpListeners() {
        editL = new EquipmentRecyclerViewAdapter.OnEditListener() {
            @Override
            public void onEdit(EquipmentUi item) {
                //TODO: show dialog
                DialogFragment dialogFrag = EditEquipmentDialog.newInstance(item.getId());
                dialogFrag.setTargetFragment(EquipmentFragment.this, 101);
                dialogFrag.show(getFragmentManager().beginTransaction(), "dialog");
            }
        };

        deleteL = new EquipmentRecyclerViewAdapter.OnDeleteListener() {
            @Override
            public void onDelete(EquipmentUi item) {
                Toast.makeText(EquipmentFragment.this.getActivity(), "LISTENER", Toast.LENGTH_LONG).show();
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
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(AnalysisUi item);
    }

    @Override
    public void onPositive(AddEquipmentDialog dialog) {
        ReagentDb reagent = (ReagentDb) dialog.reagentSpinner.getSelectedItem();
        AnalysisTypeDb type = (AnalysisTypeDb) dialog.typeSpinner.getSelectedItem();
        String name = dialog.nameEt.getText().toString();

        presenter.newEquipment(name, type, reagent);

        adapter.update(presenter.getData());

    }

    @Override
    public void onNegative(AddEquipmentDialog dialog) {
        dialog.dismiss();
    }

    @OnClick(R.id.add_eq_fab)
     void onAdd() {
        DialogFragment dialogFrag = new AddEquipmentDialog();
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

            EquipmentUi item = adapter.getById(pos);

            deleteL.onDelete(item);
        }
    };


//    @Override
//    public boolean onMenuItemClick(MenuItem menuItem) {
//        List<AnalysisUi> data = null;
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
//        return true;
//    }

    private void showSearchDialog(String tag) {
        SearchDialog dialogFrag = new SearchDialog();
        dialogFrag.setTargetFragment(this, 101);
        dialogFrag.show(getFragmentManager(), tag);
    }

//    @Override
//    public void onSearchQueryOk(String s) {
//        //Toast.makeText(getActivity(), "ВОШЛИ В ПОИСК", Toast.LENGTH_SHORT).show();
//        List<AnalysisUi> search = presenter.search(s);
//        adapter.update(search);
//    }
//
//    @Override
//    public void onCancel(DialogFragment dialogFragment) {
//        dialogFragment.dismiss();
//    }


    @Override
    public void onPositive(EditEquipmentDialog dialog, long editId) {
        ReagentDb reagent = (ReagentDb) dialog.reagentSpinner.getSelectedItem();
        AnalysisTypeDb type = (AnalysisTypeDb) dialog.typeSpinner.getSelectedItem();
        String name = dialog.nameEt.getText().toString();

        presenter.updateEquipment(name, type, reagent);

        adapter.update(presenter.getData());
    }

    @Override
    public void onNegative(EditEquipmentDialog dialog) {
        dialog.dismiss();
    }
}
