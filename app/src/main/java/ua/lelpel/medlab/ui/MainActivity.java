package ua.lelpel.medlab.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.lelpel.medlab.R;
import ua.lelpel.medlab.ui.analyzes.AnalysisFragment;
import ua.lelpel.medlab.ui.equipment.EquipmentFragment;
import ua.lelpel.medlab.ui.reagents.ReagentFragment;
import ua.lelpel.medlab.ui.staff.StaffFragment;
import ua.lelpel.medlab.ui.types.TypeFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.bottom_navigation)
    BottomNavigationView navigation;
//
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ButterKnife.bind(this);

        navigation.setOnNavigationItemSelectedListener(this);
        navigation.setSelectedItemId(R.id.nav_analyzes);
//
//        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        String tag;
        switch (item.getItemId()) {
            case R.id.nav_analyzes:
                fragment = AnalysisFragment.newInstance();
                tag = "ANALYZES";
                break;
            case R.id.nav_types:
                fragment = TypeFragment.newInstance();
                tag = "TYPES";
                break;
            case R.id.nav_eq:
                fragment = new EquipmentFragment();
                tag = "EQ";
                break;
            case R.id.nav_reagents:
                fragment = new ReagentFragment();
                tag = "REAGENTS";
                break;
            case R.id.nav_staff:
                fragment = new StaffFragment();
                tag = "STAFF";
                break;
            default:
                throw new UnsupportedOperationException("Unhandled navigation item");
        }
        getFragmentManager().beginTransaction().replace(R.id.container, fragment, tag).commit();
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        EntityFragment fragment = (EntityFragment) getFragmentManager().findFragmentByTag("ANALYZES");
//
//        switch (item.getItemId()) {
//            case R.id.action_settings:
//                // User chose the "Settings" item, show the app settings UI...
//                return true;
//
//            case R.id.action_sort_asc:
//                // User chose the "Favorite" action, mark the current item
//                // as a favorite...
//                fragment.sort(EntityFragment.ASC);
//                return true;
//
//            case R.id.action_sort_desc:
//                // User chose the "Favorite" action, mark the current item
//                // as a favorite...
//                fragment.sort(EntityFragment.DESC);
//                return true;
//
//            default:
//                // If we got here, the user's action was not recognized.
//                // Invoke the superclass to handle it.
//                return super.onOptionsItemSelected(item);
//
//        }
//    }
}
