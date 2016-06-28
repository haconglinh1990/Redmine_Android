package haconglinh1990.redmineandroid.View.Issue;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

import haconglinh1990.redmineandroid.Model.DataQuery.APIClient;
import haconglinh1990.redmineandroid.Model.DataResponse.GetDataFromServer;
import haconglinh1990.redmineandroid.Model.ObjectModel.Issue;
import haconglinh1990.redmineandroid.Model.ObjectModel.Project;
import haconglinh1990.redmineandroid.Model.ObjectModel.Tracker;
import haconglinh1990.redmineandroid.Model.ObjectModel.User;
import haconglinh1990.redmineandroid.Model.ObjectModel.Version;
import haconglinh1990.redmineandroid.Presenter.RecyclerViewMemberAdapter;
import haconglinh1990.redmineandroid.Presenter.RecyclerViewProjectAdapter;
import haconglinh1990.redmineandroid.Presenter.RecyclerViewTrackersAdapter;
import haconglinh1990.redmineandroid.Presenter.RecyclerViewVersionAdapter;
import haconglinh1990.redmineandroid.R;

public class CreateIssueActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private static RecyclerViewProjectAdapter recyclerViewProjectAdapter;
    private static RecyclerViewTrackersAdapter recyclerViewTrackersAdapter;
    private static RecyclerViewVersionAdapter recyclerViewVersionAdapter;
    private static RecyclerViewMemberAdapter recyclerViewMemberAdapter;
    private GetDataFromServer getDataFromServer;
    private static Project project;
    private static int idProject = 0;
    DatePickerDialog startDatePicker, dueDatePicker;


    private String date;

    ImageView imgNavigationIcon, imgSaveIcon;
    LinearLayout btnProject, btnTracker, btnSubject, btnDescription, btnStatus,
            btnAssignedTo, btnPriority, btnDoneRatio, btnStartDate, btnDueDate,
            btnFixedVersion, btnEstimatedHours, btnParentIssueID, layoutAttachments;

    TextView textProject, textTracker, textSubject, textDescription, textStatus,
            textAssignedTo, textPriority, textDoneRatio, textStartDate, textDueDate,
            textVersion, textEstimatedHours, textParentIssueID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_issue);

        getAllField();
        setDefaultInforIssue();

        addEvent();
        new APIClient(getBaseContext()).getProjectOfCurrentUser(getDataFromServer);

        hideFieldBeforeChoseProject();
        checkAllDataBeforePushToServer();
        saveAndPushIssue();
    }

    private void getAllField() {
        imgNavigationIcon = (ImageView) findViewById(R.id.icon_navigation_left);
        imgSaveIcon = (ImageView) findViewById(R.id.activity_create_issue_icon_save);

        btnProject = (LinearLayout) findViewById(R.id.activity_create_btnProject);
        btnTracker = (LinearLayout) findViewById(R.id.activity_create_btnTracker);
        btnSubject = (LinearLayout) findViewById(R.id.btnSubject);
        btnDescription = (LinearLayout) findViewById(R.id.btnDescription);
        btnStatus = (LinearLayout) findViewById(R.id.btnStatus);
        btnAssignedTo = (LinearLayout) findViewById(R.id.btnAssignedTo);
        btnPriority = (LinearLayout) findViewById(R.id.btnPriority);
        btnDoneRatio = (LinearLayout) findViewById(R.id.btnDoneRatio);
        btnStartDate = (LinearLayout) findViewById(R.id.btnStartDate);
        btnDueDate = (LinearLayout) findViewById(R.id.btnDueDate);
        btnFixedVersion = (LinearLayout) findViewById(R.id.btnFixedVersion);
        btnEstimatedHours = (LinearLayout) findViewById(R.id.btnEstimatedHours);
        btnParentIssueID = (LinearLayout) findViewById(R.id.btnParentIssueID);
        //layoutAttachments = (LinearLayout) findViewById(R.id.layoutAttachment);

        textProject = (TextView) findViewById(R.id.textProject);
        textTracker = (TextView) findViewById(R.id.textTracker);
        textSubject = (TextView) findViewById(R.id.textSubject);
        textDescription = (TextView) findViewById(R.id.textDescription);
        textStatus = (TextView) findViewById(R.id.textStatus);
        textAssignedTo = (TextView) findViewById(R.id.textAssignedTo);
        textPriority = (TextView) findViewById(R.id.textPriority);
        textDoneRatio = (TextView) findViewById(R.id.textDoneRatio);
        textStartDate = (TextView) findViewById(R.id.textStartDate);
        textDueDate = (TextView) findViewById(R.id.textDueDate);
        textVersion = (TextView) findViewById(R.id.textFixedVersions);
        textEstimatedHours = (TextView) findViewById(R.id.textEstimatedHours);
        textParentIssueID = (TextView) findViewById(R.id.textParentIssueID);

    }

    private void setDefaultInforIssue(){
        textTracker.setText("Please choose");
        textSubject.setText("New Issue");
        textDescription.setText("No description");
        textStatus.setText("New");
        textAssignedTo.setText("<< Me >>");
        textPriority.setText("Normal");
        textDoneRatio.setText("0 %");
        textStartDate.setText(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                + "/" + Calendar.getInstance().get(Calendar.MONTH)
                + "/" + Calendar.getInstance().get(Calendar.YEAR));
        textDueDate.setText(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                + "/" + Calendar.getInstance().get(Calendar.MONTH)
                + "/" + Calendar.getInstance().get(Calendar.YEAR));
        textVersion.setText("1.0");
        textEstimatedHours.setText("0.0 hour");
        textParentIssueID.setText("No Parent");
    }

    private void checkAllDataBeforePushToServer(){


    }

    private void addEvent() {
        imgNavigationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        imgSaveIcon.setOnClickListener(new ProcessMyEvent());

        btnProject.setOnClickListener(new ProcessMyEvent());
        btnTracker.setOnClickListener(new ProcessMyEvent());
        btnSubject.setOnClickListener(new ProcessMyEvent());
        btnDescription.setOnClickListener(new ProcessMyEvent());
        //btnStatus.setOnClickListener(new ProcessMyEvent());
        btnAssignedTo.setOnClickListener(new ProcessMyEvent());
        btnPriority.setOnClickListener(new ProcessMyEvent());
        btnDoneRatio.setOnClickListener(new ProcessMyEvent());
        btnStartDate.setOnClickListener(new ProcessMyEvent());
        btnDueDate.setOnClickListener(new ProcessMyEvent());
        btnFixedVersion.setOnClickListener(new ProcessMyEvent());
        btnEstimatedHours.setOnClickListener(new ProcessMyEvent());
        btnParentIssueID.setOnClickListener(new ProcessMyEvent());
        //layoutAttachments.setOnClickListener(new ProcessMyEvent());

    }

    private void hideFieldBeforeChoseProject() {
        btnStartDate.setVisibility(View.INVISIBLE);
        btnDueDate.setVisibility(View.INVISIBLE);

        btnDescription.setVisibility(View.INVISIBLE);
        btnTracker.setVisibility(View.INVISIBLE);
        btnSubject.setVisibility(View.INVISIBLE);
        btnStatus.setVisibility(View.INVISIBLE);
        btnAssignedTo.setVisibility(View.INVISIBLE);
        btnPriority.setVisibility(View.INVISIBLE);
        btnDoneRatio.setVisibility(View.INVISIBLE);
        btnFixedVersion.setVisibility(View.INVISIBLE);
        btnEstimatedHours.setVisibility(View.INVISIBLE);
        btnParentIssueID.setVisibility(View.INVISIBLE);
//        layoutAttachments.setVisibility(View.INVISIBLE);

    }

    private void showFieldAfterChoseProject() {
        btnStartDate.setVisibility(View.VISIBLE);
        btnDueDate.setVisibility(View.VISIBLE);
        btnProject.setVisibility(View.VISIBLE);
        btnDescription.setVisibility(View.VISIBLE);
        btnTracker.setVisibility(View.VISIBLE);
        btnSubject.setVisibility(View.VISIBLE);
        btnStatus.setVisibility(View.VISIBLE);
        btnAssignedTo.setVisibility(View.VISIBLE);
        btnPriority.setVisibility(View.VISIBLE);
        btnDoneRatio.setVisibility(View.VISIBLE);
        btnFixedVersion.setVisibility(View.VISIBLE);
        btnEstimatedHours.setVisibility(View.VISIBLE);
        btnParentIssueID.setVisibility(View.VISIBLE);
        //layoutAttachments.setVisibility(View.VISIBLE);

    }

    private void showListOfProject(){
        final Dialog dialog = new Dialog(CreateIssueActivity.this);
        dialog.setContentView(R.layout.fragment_project);
        final RecyclerView recyclerViewProject = (RecyclerView) dialog.findViewById(R.id.recyclerViewProject);
        recyclerViewProject.setHasFixedSize(true);
        recyclerViewProject.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerViewProjectAdapter = new RecyclerViewProjectAdapter(getBaseContext(), getDataFromServer.getProjectArrayList());
        recyclerViewProject.setAdapter(recyclerViewProjectAdapter);
        recyclerViewProject.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                recyclerViewProject, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                setDefaultInforIssue();
                project = getDataFromServer.getProjectArrayList().get(position);
                checkPermissionOfProject();
                dialog.dismiss();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        dialog.setCancelable(true);
        dialog.setTitle("Project");
        dialog.show();
    }

    private void checkPermissionOfProject(){
        new APIClient(getBaseContext()).getPermissionsInProject(project.getId(), getDataFromServer);

        Log.d(APIClient.MY_TAG, "Can't create Issue  " + getDataFromServer.getPermissionArrayList().size());


        if (getDataFromServer.getPermissionArrayList().contains("add_issues")) {
            queryDataOfProject();
            showFieldAfterChoseProject();
            textProject.setText(project.getName().toString());
            Toast.makeText(getApplicationContext(),
                    "You are select " + project.getName().toString() + " !!!",
                    Toast                                                                                                                                                                                                                                                                                                                                                                       .LENGTH_SHORT).show();
            Log.d(APIClient.MY_TAG, "Can create Issue");
        } else {
            hideFieldBeforeChoseProject();
            Log.d(APIClient.MY_TAG, "Can't create Issue  " + getDataFromServer.getPermissionArrayList().size());
            Toast.makeText(getApplicationContext(),
                    "You have not Permission to Create Issue in project: "
                            + project.getName().toString(),
                    Toast.LENGTH_SHORT).show();

        }
    }

    private void queryDataOfProject(){
        new APIClient(getBaseContext()).getTrackersInProject(project.getId(), getDataFromServer);
        textTracker.setText(getDataFromServer.getTrackerArrayList().get(0).getName());

        new APIClient(getBaseContext()).getMembersInProject(project.getId(), getDataFromServer);
        textAssignedTo.setText(getDataFromServer.getUserArrayList().get(0).getName());

        new APIClient(getBaseContext()).getVersionsInProject(project.getId(), getDataFromServer);
        textVersion.setText(getDataFromServer.getVersionArrayList().get(0).getName());

        new APIClient(getBaseContext()).getIssues(APIClient.API_ISSUES_BY_PROJECT + idProject, getDataFromServer);

    }

    private void showListOfTrackers(){
        final Dialog dialog = new Dialog(CreateIssueActivity.this);
        dialog.setContentView(R.layout.list_issue_tracker);
        final RecyclerView recyclerViewTracker = (RecyclerView) dialog.findViewById(R.id.recyclerViewTracker);
        recyclerViewTracker.setHasFixedSize(true);
        recyclerViewTracker.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerViewTrackersAdapter = new RecyclerViewTrackersAdapter(getBaseContext(), getDataFromServer.getTrackerArrayList());
        recyclerViewTracker.setAdapter(recyclerViewTrackersAdapter);
        recyclerViewTracker.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                recyclerViewTracker, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Tracker tracker = getDataFromServer.getTrackerArrayList().get(position);
                textTracker.setText(tracker.getName().toString());
                Toast.makeText(getApplicationContext(),tracker.getName().toString() + " is selected!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        dialog.setCancelable(true);
        dialog.setTitle("Tracker");
        dialog.show();
    }

    private void onSetTextSubject() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(CreateIssueActivity.this);
        getApplicationContext();
        builder.setCancelable(true);
        builder.setInverseBackgroundForced(true);
        builder.setMessage("Enter your subject");
        final EditText input = new EditText(CreateIssueActivity.this);
        builder.setView(input);
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String txtSubject = input.getText().toString();
                        textSubject.setText(txtSubject.toString());
                        dialog.dismiss();
                    }
                });
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );
        AlertDialog alert = builder.create();
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        alert.show();
    }

    private void onSetTexDescription() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(CreateIssueActivity.this);
        getApplicationContext();
        builder.setCancelable(true);
        builder.setInverseBackgroundForced(true);
        builder.setMessage("Enter your description");
        final EditText input = new EditText(CreateIssueActivity.this);
        builder.setView(input);
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String txtDescription = input.getText().toString();
                        textDescription.setText(txtDescription.toString());
                        dialog.dismiss();
                    }
                });
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );
        AlertDialog alert = builder.create();
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        alert.show();
    }

    private void getListofStatus() {
//        final Dialog dialog = new Dialog(CreateIssueActivity.this);
//        dialog.setContentView(R.layout.list_issue_status);
//        final RecyclerView recyclerViewStatus = (RecyclerView) dialog.findViewById(R.id.recyclerViewStatus);
//        recyclerViewStatus.setHasFixedSize(true);
//        recyclerViewStatus.setLayoutManager(new LinearLayoutManager(getBaseContext()));
//        new APIClient(getBaseContext()).getAllStatus(new StatusCallBack() {
//            @Override
//            public void getTrackerCompleted(int statusCode, final ArrayList<IssueStatus> statusArrayList) {
//                if (statusArrayList == null) {
//                    Log.d(APIClient.MY_TAG, "statusArrayList in ProjectFragment is null, fuck off");
//                } else {
//                    Log.d(APIClient.MY_TAG, "statusArrayList in ProjectFragment is:" + statusArrayList.size());
//                    recyclerViewStatusAdapter = new RecyclerViewStatusAdapter(getBaseContext(), statusArrayList);
//                    recyclerViewStatus.setAdapter(recyclerViewStatusAdapter);
//                    recyclerViewStatus.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
//                            recyclerViewStatus, new ClickListener() {
//                        @Override
//                        public void onClick(View view, int position) {
//                            IssueStatus status = statusArrayList.get(position);
//                            textStatus.setText(status.getName().toString());
//                            Toast.makeText(getApplicationContext(), position + " is selected!", Toast.LENGTH_SHORT).show();
//                            dialog.dismiss();
//                        }
//
//                        @Override
//                        public void onLongClick(View view, int position) {
//                        }
//                    }));
//                }
//            }
//        });
//        dialog.setCancelable(true);
//        dialog.setTitle("IssueStatus");
//        dialog.show();
    }

    private void showMemberInProject(){
        final Dialog dialog = new Dialog(CreateIssueActivity.this);
        dialog.setContentView(R.layout.list_issue_assigned_to);
        final RecyclerView recyclerViewMember = (RecyclerView) dialog.findViewById(R.id.recyclerViewMemberShip);
        recyclerViewMember.setHasFixedSize(true);
        recyclerViewMember.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerViewMemberAdapter = new RecyclerViewMemberAdapter(getBaseContext(), getDataFromServer.getUserArrayList());
        recyclerViewMember.setAdapter(recyclerViewMemberAdapter);
        recyclerViewMember.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                recyclerViewMember, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                User user = getDataFromServer.getUserArrayList().get(position);
                textAssignedTo.setText(user.getName());
                Toast.makeText(getApplicationContext(),user.getName() + " is selected!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
        dialog.setCancelable(true);
        dialog.setTitle("Members");
        dialog.show();
    }

    private void getListPriority() {
        final Dialog dialog = new Dialog(CreateIssueActivity.this);
        dialog.setContentView(R.layout.list_issue_priority);
        final Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
        final RadioButton rb0 = (RadioButton) dialog.findViewById(R.id.rb0);
        final RadioButton rb1 = (RadioButton) dialog.findViewById(R.id.rb1);
        final RadioButton rb2 = (RadioButton) dialog.findViewById(R.id.rb2);
        final RadioButton rb3 = (RadioButton) dialog.findViewById(R.id.rb3);
        final RadioButton rb4 = (RadioButton) dialog.findViewById(R.id.rb4);
        rb0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPriority.setText("Low");
                dialog.dismiss();
            }
        });

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPriority.setText("Normal");
                dialog.dismiss();
            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPriority.setText("High");
                dialog.dismiss();
            }
        });

        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPriority.setText("Urgent");
                dialog.dismiss();
            }
        });

        rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPriority.setText("Immediate");
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.setTitle("Priority");
        dialog.show();
    }

    private void onSetDoneRatio() {

        final Dialog dialog = new Dialog(CreateIssueActivity.this);
        dialog.setContentView(R.layout.list_issue_done);
        final RadioButton rb0 = (RadioButton) dialog.findViewById(R.id.rb0);
        final RadioButton rb1 = (RadioButton) dialog.findViewById(R.id.rb1);
        final RadioButton rb2 = (RadioButton) dialog.findViewById(R.id.rb2);
        final RadioButton rb3 = (RadioButton) dialog.findViewById(R.id.rb3);
        final RadioButton rb4 = (RadioButton) dialog.findViewById(R.id.rb4);
        final RadioButton rb5 = (RadioButton) dialog.findViewById(R.id.rb5);
        final RadioButton rb6 = (RadioButton) dialog.findViewById(R.id.rb6);
        final RadioButton rb7 = (RadioButton) dialog.findViewById(R.id.rb7);
        final RadioButton rb8 = (RadioButton) dialog.findViewById(R.id.rb8);
        final RadioButton rb9 = (RadioButton) dialog.findViewById(R.id.rb9);
        final RadioButton rb10 = (RadioButton) dialog.findViewById(R.id.rb10);

        rb0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textDoneRatio.setText("0%");
                dialog.dismiss();
            }
        });

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textDoneRatio.setText("10%");
                dialog.dismiss();
            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textDoneRatio.setText("20%");
                dialog.dismiss();
            }
        });

        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textDoneRatio.setText("30%");
                dialog.dismiss();
            }
        });

        rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textDoneRatio.setText("40%");
                dialog.dismiss();
            }
        });
        rb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textDoneRatio.setText("50%");
                dialog.dismiss();
            }
        });
        rb6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textDoneRatio.setText("60%");
                dialog.dismiss();
            }
        });
        rb7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textDoneRatio.setText("70%");
                dialog.dismiss();
            }
        });
        rb8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textDoneRatio.setText("80%");
                dialog.dismiss();
            }
        });
        rb9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textDoneRatio.setText("90%");
                dialog.dismiss();
            }
        });
        rb10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textDoneRatio.setText("100%");
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.setTitle("Done ratio:");
        dialog.show();
    }

    private void onSetStartDate() {
        Calendar now = Calendar.getInstance();
        startDatePicker = DatePickerDialog.newInstance(
                CreateIssueActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

        startDatePicker.show(getFragmentManager(), "Start Date Picker");

    }

    private void onSetDueDate() {
        Calendar now = Calendar.getInstance();
        dueDatePicker = DatePickerDialog.newInstance(
                CreateIssueActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );



        dueDatePicker.show(getFragmentManager(), "End Date Picker");

    }
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        if(view == startDatePicker){
            textStartDate.setText(+dayOfMonth+"/"+(++monthOfYear)+"/"+year);
        } else if (view == dueDatePicker) {
            textDueDate.setText(+dayOfMonth + "/" + (++monthOfYear) + "/" + year);
        } else {
            Log.d(APIClient.MY_TAG, "Cant get date, fuckkkkkkkkkkkkkkkkk");
        }


//        textStartDate.setText(+dayOfMonth+"/"+(++monthOfYear)+"/"+year);

    }

    private void showListVersion(){
        final Dialog dialog = new Dialog(CreateIssueActivity.this);
        dialog.setContentView(R.layout.list_issue_version);
        final RecyclerView recyclerViewVersion = (RecyclerView) dialog.findViewById(R.id.recyclerViewVersion);
        recyclerViewVersion.setHasFixedSize(true);
        recyclerViewVersion.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerViewVersionAdapter = new RecyclerViewVersionAdapter(getBaseContext(), getDataFromServer.getVersionArrayList());
        recyclerViewVersion.setAdapter(recyclerViewVersionAdapter);
        recyclerViewVersion.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                recyclerViewVersion, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Version version = getDataFromServer.getVersionArrayList().get(position);
                textVersion.setText(version.getName());
                Toast.makeText(getApplicationContext(),version.getName() + " is selected!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
        dialog.setCancelable(true);
        dialog.setTitle("Versions");
        dialog.show();
    }

    private void onSetTextEstimate() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(CreateIssueActivity.this);
        getApplicationContext();
        builder.setCancelable(true);
        builder.setInverseBackgroundForced(true);
        builder.setMessage("Enter your estimate");
        final EditText input = new EditText(CreateIssueActivity.this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String txtEstimate = input.getText().toString();
                        textEstimatedHours.setText(txtEstimate.toString() + ".0 hour");
                        dialog.dismiss();
                    }
                });
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );
        AlertDialog alert = builder.create();
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        alert.show();
    }

    private void showIdParentTask(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(CreateIssueActivity.this);
        getApplicationContext();
        builder.setCancelable(true);
        builder.setInverseBackgroundForced(true);
        builder.setMessage("Enter your parent task");
        final AutoCompleteTextView input = new AutoCompleteTextView(CreateIssueActivity.this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);

        final ArrayList<Integer> idList = new ArrayList<Integer>();
        for (Issue issue : getDataFromServer.getIssueArrayList()) {
            idList.add(issue.getId());
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.select_dialog_item, idList);
        input.setThreshold(1);
        input.setAdapter(adapter);
        builder.setView(input);
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String txtParent = input.getText().toString();

                        if (idList.contains(Integer.parseInt(txtParent))) {
                            textParentIssueID.setText("#" + txtParent.toString());
                        } else {
                            textParentIssueID.setText("No Parent");
                            Toast.makeText(getApplicationContext(), "Has no Issue ID is " + txtParent, Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );
        AlertDialog alert = builder.create();
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        alert.show();
    }

    private void onSetAttachment() {

    }

    private void saveAndPushIssue() {

    }

    private interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    private static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private CreateIssueActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView,
                                     final CreateIssueActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    private class ProcessMyEvent implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.activity_create_btnProject:
                    showListOfProject();
                    break;
                case R.id.activity_create_btnTracker:
                    showListOfTrackers();
                    break;
                case R.id.btnSubject:
                    onSetTextSubject();
                    break;
                case R.id.btnDescription:
                    onSetTexDescription();
                    break;
                /*case R.id.btnStatus:
                    getListofStatus();
                    break;*/
                case R.id.btnAssignedTo:
                    showMemberInProject();
                    break;
                case R.id.btnPriority:
                    getListPriority();
                    break;
                case R.id.btnDoneRatio:
                    onSetDoneRatio();
                    break;
                case R.id.btnStartDate:
                    onSetStartDate();
                    break;
                case R.id.btnDueDate:
                    onSetDueDate();
                    break;
                case R.id.btnFixedVersion:
                    showListVersion();
                    break;
                case R.id.btnEstimatedHours:
                    onSetTextEstimate();
                    break;
                case R.id.btnParentIssueID:
                    showIdParentTask();
                    break;
                /*case R.id.layoutAttachment:
                    onSetAttachment();
                    break;*/
                /*case R.id.activity_create_issue_icon_save:
                    saveAndPushIssue();
                    break;*/
            }
        }
    }


}
