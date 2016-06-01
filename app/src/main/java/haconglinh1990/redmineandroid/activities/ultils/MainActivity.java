package haconglinh1990.redmineandroid.activities.ultils;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import haconglinh1990.redmineandroid.R;
import haconglinh1990.redmineandroid.activities.issue.CreateIssueActivity;
import haconglinh1990.redmineandroid.adapters.FragmentAdapter;
import haconglinh1990.redmineandroid.fragments.FilterFragment;
import haconglinh1990.redmineandroid.fragments.MyTaskFragment;
import haconglinh1990.redmineandroid.fragments.ProjectFragment;
import haconglinh1990.redmineandroid.fragments.SettingFragment;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    Toolbar toolbar;
    TextView tvTitleFragment;
    ImageView imgIconSearch, imgIconPlus, imgIconApp;
    private FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
        tvTitleFragment = (TextView) findViewById(R.id.activity_main_tv_name_fragment);
        tvTitleFragment.setText("Project");

        tabLayout = (TabLayout) findViewById(R.id.activity_main_tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addTab(tabLayout.newTab().setText("Project"));
        tabLayout.addTab(tabLayout.newTab().setText("My Task"));
        tabLayout.addTab(tabLayout.newTab().setText("Filter"));
        tabLayout.addTab(tabLayout.newTab().setText("Setting"));

        imgIconPlus = (ImageView) findViewById(R.id.activity_main_icon_plus);
        imgIconSearch = (ImageView) findViewById(R.id.activity_main_icon_search);
        imgIconPlus.setVisibility(View.INVISIBLE);
        imgIconSearch.setVisibility(View.INVISIBLE);
        imgIconApp = (ImageView) findViewById(R.id.activity_main_icon_app);
        imgIconApp.setImageResource(R.drawable.icon_voz1);

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new ProjectFragment());
        fragmentArrayList.add(new MyTaskFragment());
        fragmentArrayList.add(new FilterFragment());
        fragmentArrayList.add(new SettingFragment());
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentArrayList);

        viewPager = (ViewPager) findViewById(R.id.activity_main_viewPager);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tvTitleFragment.setText(tab.getText());

                switch (tab.getText().toString()) {
                    case "Project":
                        imgIconPlus.setVisibility(View.INVISIBLE);
                        imgIconSearch.setVisibility(View.INVISIBLE);
                        imgIconApp.setImageResource(R.drawable.icon_voz1);
                        break;

                    case "My Task":
                        imgIconPlus.setVisibility(View.VISIBLE);
                        imgIconPlus.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(MainActivity.this, CreateIssueActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("projectId", 0);
                                bundle.putString("projectName", null);
                                intent.putExtra("projectData", bundle);
                                startActivity(intent);
                            }
                        });
                        imgIconSearch.setVisibility(View.VISIBLE);
                        imgIconApp.setImageResource(R.drawable.icon_voz2);
                        break;

                    case "Filter":
                        imgIconPlus.setVisibility(View.INVISIBLE);
                        imgIconSearch.setVisibility(View.INVISIBLE);
                        imgIconApp.setImageResource(R.drawable.icon_voz3);
                        break;

                    case "Setting":
                        imgIconPlus.setVisibility(View.INVISIBLE);
                        imgIconSearch.setVisibility(View.INVISIBLE);
                        imgIconApp.setImageResource(R.drawable.icon_voz4);
                        break;

                }
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
