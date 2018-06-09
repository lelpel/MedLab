package ua.lelpel.medlab.ui.reagents;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.lelpel.medlab.R;
import ua.lelpel.medlab.db.entities.ReagentDb;
import ua.lelpel.medlab.ui.dummy.DummyContent.DummyItem;
import ua.lelpel.medlab.ui.entities.AnalysisUi;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * TODO: Replace the implementation with code for your data type.
 */
public class ReagentRecyclerViewAdapter extends RecyclerView.Adapter<ReagentRecyclerViewAdapter.ViewHolder> {
    private final List<ReagentDb> mValues;
    private OnEditListener editL;
    private OnDeleteListener deleteL;

    public ReagentRecyclerViewAdapter(List<ReagentDb> items, OnEditListener editL, OnDeleteListener deleteL) {
        mValues = items;
        this.editL = editL;
        this.deleteL = deleteL;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reagent, parent, false);
        return new ViewHolder(view, editL, deleteL);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        //TODO: связать данные из обьекта с текстовыми надписями
        holder.reagentAmount.setText(Float.toString(mValues.get(position).getAmount()));
        holder.reagentName.setText(mValues.get(position).getName());
    }

    public ReagentDb getById(int id) {
        return mValues.get(id);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void update(List<ReagentDb> data) {
        this.mValues.clear();
        this.mValues.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements Toolbar.OnMenuItemClickListener {
        public final View mView;
        private final OnEditListener editListener;
        private final OnDeleteListener deleteListener;

        @BindView(R.id.text_view_reagent_name)
        TextView reagentName;

        @BindView(R.id.text_view_reagent_amount)
        TextView reagentAmount;

        @BindView(R.id.reagent_toolbar)
        Toolbar toolbar;

        public ReagentDb mItem;

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
    }

    public interface OnDeleteListener {
        void onDelete(ReagentDb item);
    }

    public interface OnEditListener {
        void onEdit(ReagentDb item);
    }
}
