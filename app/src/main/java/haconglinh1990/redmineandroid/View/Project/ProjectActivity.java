package haconglinh1990.redmineandroid.View.Project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.Model.DataQuery.APIClient;
import haconglinh1990.redmineandroid.Model.DataResponse.IssuesCallBack;
import haconglinh1990.redmineandroid.Model.ObjectModel.Issue;
import haconglinh1990.redmineandroid.Presenter.RecyclerViewIssueAdapter;
import haconglinh1990.redmineandroid.R;
import haconglinh1990.redmineandroid.View.Issue.CreateIssueActivity;

public class ProjectActivity extends AppCompatActivity {
    RecyclerViewIssueAdapter recyclerViewIssueAdapter;
    RecyclerView recyclerView;
    TextView tvHeaderName, tvprojectName;
    ImageView imgNavigationIcon, imgPlusIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("projectData");
        final int projectId = bundle.getInt("projectId");
        final String projectName = bundle.getString("projectName");

        imgNavigationIcon = (ImageView) findViewById(R.id.icon_navigation_left);
        imgNavigationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        imgPlusIcon = (ImageView) findViewById(R.id.activity_project_icon_plus);
        imgPlusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProjectActivity.this, CreateIssueActivity.class);
                startActivity(intent);
            }
        });
        tvHeaderName = (TextView) findViewById(R.id.activity_project_tvHeaderName);
        tvprojectName = (TextView) findViewById(R.id.activity_project_tv_name_project);
        tvprojectName.setText(projectName);
        recyclerView = (RecyclerView) findViewById(R.id.activity_project_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new APIClient(ProjectActivity.this).getIssues(APIClient.API_ISSUES_BY_PROJECT + projectId, new IssuesCallBack() {
            @Override
            public void IssuesCallBackCompleted(int statusCode, ArrayList<Issue> issueArrayList) {
                if (issueArrayList == null) {
                    Log.d(APIClient.MY_TAG, "issueArrayList in onCreate on ProjectActivity is null, fuck off");
                } else {
                    Log.d(APIClient.MY_TAG, "onCreate MyTaskFragment after call API, before put to adapter: " + issueArrayList.size());
                    tvHeaderName.setText("Total Issue: " + issueArrayList.size());
                    recyclerViewIssueAdapter = new RecyclerViewIssueAdapter(ProjectActivity.this, issueArrayList);
                    recyclerView.setAdapter(recyclerViewIssueAdapter);
                }
            }
        });



    }
}
