package ua.lelpel.medlab.ui;

/**
 * Created by bruce on 04.12.2017.
 */

public interface EntityFragment {
    void sort(int type);
    void search();
    void filter();

    int ASC = 1;
    int DESC = 0;
}
