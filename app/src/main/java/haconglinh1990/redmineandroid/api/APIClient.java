package haconglinh1990.redmineandroid.api;

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
import haconglinh1990.redmineandroid.models.Issue;
import haconglinh1990.redmineandroid.models.IssuesObjectModel;
import haconglinh1990.redmineandroid.models.Membership;
import haconglinh1990.redmineandroid.models.UserObjectModel;
import haconglinh1990.redmineandroid.models.Project;
import haconglinh1990.redmineandroid.models.User;
import haconglinh1990.redmineandroid.ultils.Remember;

/**
 * Created by haconglinh1990 on 05/04/2016.
 */
public class APIClient {
    public static final String API_BASE_URL="http://192.168.1.59/";
    public static final String API_USER=API_BASE_URL + "users/current.json";
    public static final String API_PROJECT_MEMBER=API_USER + "?include=memberships&key=";
    //public static final String API_ALL_ISSUSE = API_USER + "?include=issues&key=";
    public static final String API_ALL_ISSUSE = API_BASE_URL+ "issues&key=";
    Context context;
    ArrayList<Project> projectList;
    ArrayList<Issue> issueList;
    IssuesObjectModel issuesObjectModel;

    private static final String MY_TAG = "message_from_meomeo";

    public APIClient(Context context) {
        this.context = context;
    }

    public void logInUseAPI(String address, String username, String password) {
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
                    Remember.saveApiKey(context, apiKey);
                    Toast.makeText(context, "Login successfull !!!", Toast.LENGTH_SHORT).show();
                    Log.v(MY_TAG, "APIKey is: " + apiKey);
                    context.startActivity(new Intent(context, MainActivity.class));
                } else {
                    Log.v(MY_TAG, "Status is not 200 or APIKey is null");
                    Toast.makeText(context, "Error Login, wrong username or password", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    public ArrayList<Project> getProjectofCurentUser(){
        projectList = new ArrayList<>();
        Log.d(MY_TAG, "In getProjectofCurentUser API");

        String urlQuery = API_PROJECT_MEMBER + Remember.apiKey(context);
        Log.d(MY_TAG, urlQuery);
        Ion.with(context).load(urlQuery).asJsonObject().withResponse().setCallback(new FutureCallback<Response<JsonObject>>() {
            @Override
            public void onCompleted(Exception e, Response<JsonObject> result) {

                int status = result.getHeaders().code();
                ArrayList<Membership> membershipArrayList;
                Gson gson = new Gson();
                //Log.v(MY_TAG, ""+result.getResult().toString());
                UserObjectModel userModel = gson.fromJson(result.getResult(), UserObjectModel.class);
                User user = userModel.getUser();
                membershipArrayList = user.getMemberships();

                if (status == 200) {
                    Log.v(MY_TAG, "Get project with apikey saved from Server successfull");

                    for (Membership membership : membershipArrayList) {
                        projectList.add(membership.getProject());
                    }
                    Log.v(MY_TAG, ""+projectList.size());

                } else {
                    Log.v(MY_TAG, "Status is not 200. Can't get project from Server");

                }
            }
        });
        return projectList;

    }

    public ArrayList<Issue> getIssuesofCurentUser(){
        issueList = new ArrayList<>();
        issuesObjectModel =  new IssuesObjectModel();
        Log.d(MY_TAG, "In getIssuesofCurentUser API");
        String urlQuery = API_ALL_ISSUSE + Remember.apiKey(context);
        Ion.with(context).load(urlQuery).asJsonObject().withResponse().setCallback(new FutureCallback<Response<JsonObject>>() {
            @Override
            public void onCompleted(Exception e, Response<JsonObject> result) {
                int status = result.getHeaders().code();
                Gson gson = new Gson();
                if (status == 200){
                    Log.d(MY_TAG, "Get issues successfull");
                    issuesObjectModel = gson.fromJson(result.getResult(), IssuesObjectModel.class);
                    issueList = issuesObjectModel.getIssues();

                } else {
                    Log.d(MY_TAG, "Error when get issues from API");
                }

            }
        });


        return issueList;
    }

}
