package haconglinh1990.redmineandroid.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.API.APIClient;
import haconglinh1990.redmineandroid.R;
import haconglinh1990.redmineandroid.adapter.TabAdapter;
import haconglinh1990.redmineandroid.fragment.Filter_Fragment;
import haconglinh1990.redmineandroid.fragment.MyTask_Fragment;
import haconglinh1990.redmineandroid.fragment.Project_Fragment;
import haconglinh1990.redmineandroid.fragment.Setting_Fragment;
import haconglinh1990.redmineandroid.model.Project;

public class MainActivity extends AppCompatActivity {
    private static final String MY_TAG = "message_from_meomeo";
    ViewPager viewPager;
    TabLayout tabLayout;
    Toolbar toolbar;
    ArrayList<Project> projectList;

    TabAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(MY_TAG, "onCreate MainActivity before set toolbar");
        toolbar= (Toolbar) findViewById(R.id.toolbarMainActivity);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tabLayoutMainActivity);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addTab(tabLayout.newTab().setText("Project"));
        tabLayout.addTab(tabLayout.newTab().setText("My Task"));
        tabLayout.addTab(tabLayout.newTab().setText("Filter"));
        tabLayout.addTab(tabLayout.newTab().setText("Setting"));
        Log.d(MY_TAG, "onCreate MainActivity after set toolbar, before connect with API!!!");

        projectList = new APIClient(MainActivity.this).getProjectofCurentUser();

        Log.d(MY_TAG, "onCreate MainActivity after connect with API get project List back, before connect with API!!!");
        tabAdapter = new TabAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        Log.d(MY_TAG, "onCreate MainActivity after create adapter, before set adapter !!!");

        viewPager = (ViewPager) findViewById(R.id.viewPagerMainActivity);
        viewPager.setAdapter(tabAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

}
