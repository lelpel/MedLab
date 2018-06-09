package ua.lelpel.medlab.ui.types;

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
import ua.lelpel.medlab.db.entities.AnalysisTypeDb;

/**
 * {@link RecyclerView.Adapter} that can display a  and makes a call to the
 * specified {@link TypeFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class TypeRecyclerViewAdapter extends RecyclerView.Adapter<TypeRecyclerViewAdapter.ViewHolder> {

    private final List<AnalysisTypeDb> mValues;
    private OnEditListener editL;
    private OnDeleteListener deleteL;

    public TypeRecyclerViewAdapter(List<AnalysisTypeDb> items, OnEditListener editL, OnDeleteListener deleteL) {
        mValues = items;
        this.editL = editL;
        this.deleteL = deleteL;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_type, parent, false);
        return new ViewHolder(view, editL, deleteL);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        //TODO: связать данные из обьекта с текстовыми надписями
        holder.type.setText(mValues.get(position).getName());
    }

    public AnalysisTypeDb getById(int id) {
        return mValues.get(id);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void update(List<AnalysisTypeDb> data) {
        this.mValues.clear();
        this.mValues.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements Toolbar.OnMenuItemClickListener {
        public final View mView;
        private final OnEditListener editListener;
        private final OnDeleteListener deleteListener;

        @BindView(R.id.text_view_type_card_name)
        TextView type;

        @BindView(R.id.type_item_toolbar)
        Toolbar toolbar;

        public AnalysisTypeDb mItem;

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
        void onDelete(AnalysisTypeDb item);
    }

    public interface OnEditListener {
        void onEdit(AnalysisTypeDb item);
    }
}
