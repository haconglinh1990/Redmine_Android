package haconglinh1990.redmineandroid.View.Issue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;


import haconglinh1990.redmineandroid.Model.DataQuery.APIClient;
import haconglinh1990.redmineandroid.Model.DataResponse.IssueDetailCallBack;
import haconglinh1990.redmineandroid.Model.ObjectModel.Issue;
import haconglinh1990.redmineandroid.Model.ObjectModel.Journal;
import haconglinh1990.redmineandroid.R;

public class IssueDetailActivity extends AppCompatActivity {
    TextView tvIssueName, textIssueID, textCreateDateFromNow, textSubject,
            textDescription, textProject, textStatus, textPriority, textAssignedTo,
            textDoneRatio, textStartDate, textDueDate, textAuthorName,
            textCreateDate, textUpdateDate, textEstimatedHours;;

    DateFormat formatter = null;
    ImageView imgNavigationIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("issueData");
        int issueId = bundle.getInt("issueId");
        String issueName = bundle.getString("issueName");

        tvIssueName = (TextView) findViewById(R.id.activity_create_issue_add_name);
        tvIssueName.setText(issueName);

        imgNavigationIcon = (ImageView) findViewById(R.id.icon_navigation_left);
        imgNavigationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        loadAllField();

        new APIClient(IssueDetailActivity.this).getDetailIssue(APIClient.API_GET_ISSUE_ID
                        + issueId + ".json?include=attachments,relations,journals,children",
                new IssueDetailCallBack() {
                    @Override
                    public void onCompleted(int statusCode, Issue issue) {
                        if (statusCode == 200) {
                            Log.d(APIClient.MY_TAG, "onCreate DetailIssue after call API, before put to adapter is success");
                            textIssueID.setText("#" + issue.getId().toString());
                            textCreateDateFromNow.setText(issue.getCreatedOn().toString());
                            textSubject.setText(issue.getSubject().toString());
                            try {
                                textDescription.setText(issue.getDescription().toString());
                            } catch (Exception e) {
                                textDescription.setText("");
                            }

                            textProject.setText(issue.getProject().getName().toString());
                            textStatus.setText(issue.getStatus().getName().toString());
                            textPriority.setText(issue.getPriority().getName().toString());
                            textAssignedTo.setText(issue.getAssignedTo().getName().toString());
                            textDoneRatio.setText(issue.getDoneRatio().toString()+"%");
                            textStartDate.setText(issue.getStartDate().toString());
                            try {
                                textDueDate.setText(issue.getDueDate().toString());
                            } catch (Exception e) {
                                textDueDate.setText("");
                            }
                            textAuthorName.setText(issue.getAuthor().getName().toString());
                            textCreateDate.setText(issue.getCreatedOn().toString());
                            textUpdateDate.setText(issue.getUpdatedOn().toString());

                            if (issue.getSpentHours() != null) {
                                textEstimatedHours.setText(issue.getSpentHours() + "");
                            } else {
                                textEstimatedHours.setText("0.0");
                            }

                            ArrayList<Journal> journals = (ArrayList<Journal>) issue.getJournals();
                            Log.d(APIClient.MY_TAG, "Total List Journal: " + journals.size());

                            for (Journal journal : journals) {
                                Log.d(APIClient.MY_TAG, "List ID Journal: " + journal.getId());
                            }

                        } else

                        {
                            Log.d(APIClient.MY_TAG, "onCreate DetailIssue after call API, failse");
                        }


                    }
                }

        );

    }

    public void loadAllField() {
        textIssueID = (TextView) findViewById(R.id.textIssueID);
        textCreateDateFromNow = (TextView) findViewById(R.id.textCreateDateFromNow);
        textSubject = (TextView) findViewById(R.id.textSubject);
        textDescription = (TextView) findViewById(R.id.textDescription);
        textProject = (TextView) findViewById(R.id.textProject);
        textStatus = (TextView) findViewById(R.id.textStatus);
        textPriority = (TextView) findViewById(R.id.textPriority);
        textAssignedTo = (TextView) findViewById(R.id.textAssignedTo);
        textDoneRatio = (TextView) findViewById(R.id.textDoneRatio);
        textStartDate = (TextView) findViewById(R.id.textStartDate);
        textDueDate = (TextView) findViewById(R.id.textDueDate);
        textAuthorName = (TextView) findViewById(R.id.textAuthorName);
        textCreateDate = (TextView) findViewById(R.id.textCreateDate);
        textUpdateDate = (TextView) findViewById(R.id.textUpdateDate);
        textEstimatedHours = (TextView) findViewById(R.id.textEstimatedHours);
    }

}
