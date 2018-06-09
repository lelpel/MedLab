package ua.lelpel.medlab.ui.staff;

import android.content.Context;
import android.util.Log;

import org.joda.time.LocalDate;

import java.util.List;

import ua.lelpel.medlab.App;
import ua.lelpel.medlab.db.AppDb;
import ua.lelpel.medlab.db.dao.ReagentDao;
import ua.lelpel.medlab.db.dao.StaffDao;
import ua.lelpel.medlab.db.entities.StaffMemberDb;
import ua.lelpel.medlab.ui.EntityFragment;

/**
 * Created by bruce on 02.12.2017.
 */

public class StaffRepository {
    private Context context;
    private AppDb db;
    private StaffDao dao;

    public StaffRepository(Context context) {
        this.context = context;
         db = ((App) context).getDb();
         dao = db.staffDao();
    }

    public List<StaffMemberDb> getData() {
        return dao.loadAll();
    }

    public void newStaffMember(String fn, String ln, LocalDate date) {
        StaffMemberDb item = new StaffMemberDb(fn, ln, date);
        dao.insert(item);
    }

    public void deleteItem(StaffMemberDb mItem) {
        StaffMemberDb dbItem = dao.findById(mItem.getId());
        dao.delete(dbItem);
    }

    public List<StaffMemberDb> sortName(int type) {
        if (type == EntityFragment.ASC) return dao.sortNameAsc();
            else return dao.sortNameDsc();
    }

    public List<StaffMemberDb> sortDate(int type) {
        if (type == EntityFragment.ASC) return dao.sortDateAsc();
        else return dao.sortDateDsc();
    }


    public List<StaffMemberDb> search(String arg) {
        Log.i("FIND", dao.find(arg).get(0).toString());
        return dao.find(arg);
    }

    public void update(int editId, String fn, String ln, LocalDate date) {
        StaffMemberDb item = new StaffMemberDb(editId, fn, ln, date);
        dao.update(item);
    }
}
