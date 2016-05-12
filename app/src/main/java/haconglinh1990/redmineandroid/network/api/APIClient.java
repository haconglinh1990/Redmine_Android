package haconglinh1990.redmineandroid.network.api;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.activities.MainActivity;
import haconglinh1990.redmineandroid.models.IssueObjectModel;
import haconglinh1990.redmineandroid.models.ProjectObjectModel;
import haconglinh1990.redmineandroid.models.User;
import haconglinh1990.redmineandroid.models.UserObjectModel;
import haconglinh1990.redmineandroid.network.response.IssueCallBack;
import haconglinh1990.redmineandroid.network.response.ProjectCallBack;
import haconglinh1990.redmineandroid.ultils.Remember;
import haconglinh1990.redmineandroid.network.response.UserCallback;

/**
 * Created by haconglinh1990 on 05/04/2016.
 */
public class APIClient {
    public static final String MY_TAG = "message_from_meomeo";
    public static final String API_BASE_URL = "http://192.168.1.59/";
    public static final String API_USER = API_BASE_URL + "users/current.json";
    public static final String API_ALL_PROJECT = API_BASE_URL + "projects.json";
    public static final String API_ALL_ISSUSE = API_BASE_URL + "issues.json";
    //private String username_restore, password_restore;

    Context context;

    //ArrayList<Issue> issueList;


    public APIClient(Context context) {
        this.context = context;
    }

    public void logInUseAPI(String address, final String username, final String password) {
        Ion.with(context).load(address).basicAuthentication(username, password).asJsonObject().withResponse().setCallback(
                new FutureCallback<Response<JsonObject>>() {

                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> result) {
                        int status = result.getHeaders().code();
                        Gson gson = new Gson();
                        UserObjectModel userModel = gson.fromJson(result.getResult(), UserObjectModel.class);
                        User user = userModel.getUser();
                        String apiKey = user.getApiKey();

                        if (status == 200 && apiKey != null) {
                            Remember.saveUser(context, username, password);
                            Remember.saveApiKey(context, apiKey);
                            Toast.makeText(context, "Login successfull !!!", Toast.LENGTH_SHORT).show();
                            //Log.v(MY_TAG, "APIKey is: " + apiKey + ", Username: " + username + ", Password: " + password);
                            context.startActivity(new Intent(context, MainActivity.class));
                        } else {
                            //Log.v(MY_TAG, "Status is not 200 or APIKey is null");
                            Toast.makeText(context, "Error Login, wrong username or password", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }

    public void getProjectofCurentUser(final ProjectCallBack projectCallBack) {
        //String username_restore, password_restore;
        //ArrayList<Project> projectList = new ArrayList<>();
        //Log.d(MY_TAG, "In getProjectofCurentUser API");

        Remember.restoreUser(context, new UserCallback() {
            @Override
            public void User(String username, String password, boolean check) {
                //Log.v(MY_TAG, "In getProjectofCurentUser, Username: " + username + ", Password: " + password + ", Check: " + check);
                if (check == true) {

                    Ion.with(context).load(API_ALL_PROJECT).basicAuthentication(username, password)
                            .asJsonObject().withResponse().setCallback(new FutureCallback<Response<JsonObject>>() {
                        @Override
                        public void onCompleted(Exception e, Response<JsonObject> result) {

                            int status = result.getHeaders().code();
                            Gson gson = new Gson();
                            Log.v(MY_TAG, " " + result.getResult().toString() + "Status: " + status);
                            ProjectObjectModel projectObjectModel = gson.fromJson(result.getResult(), ProjectObjectModel.class);

                            if (status == 200) {
                                //Log.v(MY_TAG, "Get project with apikey saved from Server successfull");
                                projectCallBack.onCompleted(status, projectObjectModel.getProjects());

                            } else {
                                //Log.v(MY_TAG, "Status is not 200. Can't get project from Server");
                                projectCallBack.onCompleted(status, null);
                            }
                        }
                    });

                    //Log.d(MY_TAG, "Username: " + username + " ,Pass: " + password);
                } else {
                    //Log.d(MY_TAG, "Can not get username, pass");
                }
            }
        });


    }

    public void getIssues(final String urlQuery, final IssueCallBack issueCallBack) {
//        issueList = new ArrayList<>();
//        issuesObjectModel = new IssuesObjectModel();


        Remember.restoreUser(context, new UserCallback() {
            @Override
            public void User(String username, String password, boolean check) {
                Log.d(MY_TAG, "In getIssuesofCurentUser API, Username = " + username + ", Password: " + password + ", Check: " + check);
                if (check == true) {

                    Ion.with(context).load(urlQuery).basicAuthentication(username, password)
                            .asJsonObject().withResponse().setCallback(new FutureCallback<Response<JsonObject>>() {
                        @Override
                        public void onCompleted(Exception e, Response<JsonObject> result) {
                            int status = result.getHeaders().code();
                            Gson gson = new Gson();

                            IssueObjectModel issuesObjectModel = gson.fromJson(result.getResult(), IssueObjectModel.class);
                            if (status == 200) {
                                Log.d(MY_TAG, "Get issues successfull");
                                issueCallBack.onCompleted(status, issuesObjectModel.getIssues());

                            } else {
                                Log.d(MY_TAG, "Error when get issues from API");
                                issueCallBack.onCompleted(status, null);
                            }

                        }
                    });

                } else {
                    Log.d(MY_TAG, "Can not get username, pass");
                }
            }
        });
    }
}
