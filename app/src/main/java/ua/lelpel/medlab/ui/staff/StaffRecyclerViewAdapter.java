package ua.lelpel.medlab.ui.staff;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.lelpel.medlab.R;
import ua.lelpel.medlab.db.entities.StaffMemberDb;
import ua.lelpel.medlab.ui.dummy.DummyContent.DummyItem;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * TODO: Replace the implementation with code for your data type.
 */
public class StaffRecyclerViewAdapter extends RecyclerView.Adapter<StaffRecyclerViewAdapter.ViewHolder> {
    private final List<StaffMemberDb> mValues;
    private OnEditListener editL;
    private OnDeleteListener deleteL;

    public StaffRecyclerViewAdapter(List<StaffMemberDb> items, OnEditListener editL, OnDeleteListener deleteL) {
        mValues = items;
        this.editL = editL;
        this.deleteL = deleteL;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_staff, parent, false);
        return new ViewHolder(view, editL, deleteL);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        //TODO: связать данные из обьекта с текстовыми надписями
        holder.nameTv.setText(String.format("%s %s", holder.mItem.getFirst_name(), holder.mItem.getLast_name()));
        holder.dateTv.setText(holder.mItem.getDate().toString());
    }

    public StaffMemberDb getById(int id) {
        return mValues.get(id);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void update(List<StaffMemberDb> data) {
        this.mValues.clear();
        this.mValues.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements Toolbar.OnMenuItemClickListener {
        public final View mView;
        private final OnEditListener editListener;
        private final OnDeleteListener deleteListener;

        @BindView(R.id.text_view_staff_full_name) TextView nameTv;
        @BindView(R.id.text_view_staff_date) TextView dateTv;
        @BindView(R.id.staff_item_toolbar) Toolbar toolbar;

        public StaffMemberDb mItem;

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
        void onDelete(StaffMemberDb item);
    }

    public interface OnEditListener {
        void onEdit(StaffMemberDb item);
    }
}
