package haconglinh1990.redmineandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.ArrayList;
import haconglinh1990.redmineandroid.R;
import haconglinh1990.redmineandroid.adapters.FragmentAdapter;
import haconglinh1990.redmineandroid.fragments.FilterFragment;
import haconglinh1990.redmineandroid.fragments.MyTaskFragment;
import haconglinh1990.redmineandroid.fragments.ProjectFragment;
import haconglinh1990.redmineandroid.fragments.SettingFragment;

public class MainActivity extends AppCompatActivity {
    private static final String MY_TAG = "message_from_meomeo";
    ViewPager viewPager;
    TabLayout tabLayout;
    Toolbar toolbar;

    FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(MY_TAG, "onCreate MainActivity before set toolbar");
        toolbar = (Toolbar) findViewById(R.id.toolbarMainActivity);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tabLayoutMainActivity);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addTab(tabLayout.newTab().setText("Project"));
        tabLayout.addTab(tabLayout.newTab().setText("My Task"));
        tabLayout.addTab(tabLayout.newTab().setText("Filter"));
        tabLayout.addTab(tabLayout.newTab().setText("Setting"));
        Log.d(MY_TAG, "onCreate MainActivity after set toolbar, before connect with API!!!");

        //projectList = new APIClient(MainActivity.this).getProjectofCurentUser();

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new ProjectFragment());
        fragmentArrayList.add(new MyTaskFragment());
        fragmentArrayList.add(new FilterFragment());
        fragmentArrayList.add(new SettingFragment());
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentArrayList);

        Log.d(MY_TAG, "onCreate MainActivity after connect with API get project List back, before connect with API!!!");
        //fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        Log.d(MY_TAG, "onCreate MainActivity after create adapter, before set adapter !!!");

        viewPager = (ViewPager) findViewById(R.id.viewPagerMainActivity);
        viewPager.setAdapter(fragmentAdapter);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
