package haconglinh1990.redmineandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import haconglinh1990.redmineandroid.R;

public class IssueInProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_in_project_list);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundleData");
        int projectId = bundle.getInt("projectId");

    }
}
