package haconglinh1990.redmineandroid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.fragment.Filter_Fragment;
import haconglinh1990.redmineandroid.fragment.MyTask_Fragment;
import haconglinh1990.redmineandroid.fragment.Project_Fragment;
import haconglinh1990.redmineandroid.fragment.Setting_Fragment;

/**
 * Created by haconglinh1990 on 12/04/2016.
 */


public class TabAdapter extends FragmentStatePagerAdapter {
    private static final String MY_TAG = "message_from_meomeo";
    int numberOfTab;

    public TabAdapter(FragmentManager fm, int numberOfTab) {
        super(fm);
        this.numberOfTab = numberOfTab;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Log.d(MY_TAG, "on TabAdapter before create Project Fragment");
                Project_Fragment project_fragment = new Project_Fragment();
                Log.d(MY_TAG, "on TabAdapter after create Project Fragment");
                return project_fragment;
            case 1:
                MyTask_Fragment myTask_fragment = new MyTask_Fragment();
                return myTask_fragment;
            case 2:
                Filter_Fragment filter_fragment = new Filter_Fragment();
                return filter_fragment;
            case 3:
                Setting_Fragment setting_fragment = new Setting_Fragment();
                return setting_fragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTab;
    }
}