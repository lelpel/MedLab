package ua.lelpel.medlab.ui.analyzes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.lelpel.medlab.R;
import ua.lelpel.medlab.ui.dummy.DummyContent.DummyItem;
import ua.lelpel.medlab.ui.entities.AnalysisUi;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link AnalysisFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class AnalyzesRecyclerViewAdapter extends RecyclerView.Adapter<AnalyzesRecyclerViewAdapter.ViewHolder> {

    private final List<AnalysisUi> mValues;
    private final AnalysisFragment.OnListFragmentInteractionListener mListener;
    private OnEditListener editL;
    private OnDeleteListener deleteL;

    public AnalyzesRecyclerViewAdapter(List<AnalysisUi> items, AnalysisFragment.OnListFragmentInteractionListener listener, OnEditListener editL, OnDeleteListener deleteL) {
        mValues = items;
        mListener = listener;
        this.editL = editL;
        this.deleteL = deleteL;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_analysis, parent, false);
        return new ViewHolder(view, editL, deleteL);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        //TODO: связать данные из обьекта с текстовыми надписями
        holder.type.setText(mValues.get(position).getTypeName());
        holder.date.setText(mValues.get(position).getDate());
        holder.eq.setText(mValues.get(position).getEquipmentName());
        holder.reagentAmount.setText(Float.toString(mValues.get(position).getReagentAmount()));
        holder.reagentName.setText(mValues.get(position).getReagentName());
        holder.result.setText(mValues.get(position).getResult());
        holder.staff.setText(mValues.get(position).getStaffName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    public AnalysisUi getById(int id) {
        return mValues.get(id);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void update(List<AnalysisUi> data) {
        this.mValues.clear();
        this.mValues.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements Toolbar.OnMenuItemClickListener {
        public final View mView;
        private final OnEditListener editListener;
        private final OnDeleteListener deleteListener;

        @BindView(R.id.text_view_analysis_type)
        TextView type;

        @BindView(R.id.text_view_analysis_date)
        TextView date;

        @BindView(R.id.text_view_analysis_equipment)
        TextView eq;

        @BindView(R.id.text_view_analysis_result)
        TextView result;

        @BindView(R.id.text_view_analysis_staff)
        TextView staff;

        @BindView(R.id.text_view_analysis_reagentname)
        TextView reagentName;

        @BindView(R.id.text_view_analysis_reagentamount)
        TextView reagentAmount;

        @BindView(R.id.analysis_toolbar)
        Toolbar toolbar;

        public AnalysisUi mItem;

        public ViewHolder(View view, OnEditListener editListener, OnDeleteListener deleteListener) {
            super(view);
            mView = view;
            this.editListener = editListener;
            this.deleteListener = deleteListener;

            ButterKnife.bind(this, view);

            toolbar.inflateMenu(R.menu.analysis_context_menu);
            toolbar.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_edit:
                    editListener.onEdit(mItem);
                    return true;
                case R.id.action_delete:
                    deleteListener.onDelete(mItem);
                    return true;
                default:
                    throw new IllegalArgumentException("Unknown item menu item");
            }
        }

        @Override
        public String toString() {
            return "AAAAA";
        }
    }


    public interface OnDeleteListener {
        void onDelete(AnalysisUi item);
    }

    public interface OnEditListener {
        void onEdit(AnalysisUi item);
    }
}
