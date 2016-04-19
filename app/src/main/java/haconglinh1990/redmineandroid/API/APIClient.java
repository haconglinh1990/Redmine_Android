package haconglinh1990.redmineandroid.API;

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

import haconglinh1990.redmineandroid.activity.MainActivity;
import haconglinh1990.redmineandroid.model.Membership;
import haconglinh1990.redmineandroid.model.ObjectModel;
import haconglinh1990.redmineandroid.model.Project;
import haconglinh1990.redmineandroid.model.User;
import haconglinh1990.redmineandroid.preference.Remember;

/**
 * Created by haconglinh1990 on 05/04/2016.
 */
public class APIClient {
    public  static  final String API_BASE_URL="http://192.168.1.51/";
    public  static final String API_USER=API_BASE_URL+"users/current.json";
    public static final String API_PROJECT_MEMBER=API_BASE_URL+"users/current.json?include=memberships&key=";

    Context context;
    ArrayList<Project> projectList;

    private static final String MY_TAG = "message_from_meomeo";

    public APIClient(Context context) {
        this.context = context;
    }

    public void logInUseAPI(String address, String username, String password) {
        Ion.with(context).load(address).basicAuthentication(username, password).asString().withResponse().setCallback(
                new FutureCallback<Response<String>>() {

            @Override
            public void onCompleted(Exception e, Response<String> result) {
                int status = result.getHeaders().code();
                Gson gson = new Gson();
                ObjectModel userModel = gson.fromJson(result.getResult().toString(), ObjectModel.class);
                User user = userModel.getUser();
                String apiKey = user.getApiKey();

                if (status == 200 && apiKey != null) {
                    Remember.saveApiKey(context, apiKey);
                    Toast.makeText(context, "Get data from Server successfull", Toast.LENGTH_SHORT).show();
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
        Ion.with(context).load(urlQuery).asString().withResponse().setCallback(new FutureCallback<Response<String>>() {
            @Override
            public void onCompleted(Exception e, Response<String> result) {
                int status = result.getHeaders().code();
                Gson gson = new Gson();
                ObjectModel userModel = gson.fromJson(result.getResult().toString(), ObjectModel.class);
                User user = userModel.getUser();
                Log.v(MY_TAG, "Get project with apikey saved from Server successfull" + status);
                if (status == 200) {
                    Log.v(MY_TAG, "Get project with apikey saved from Server successfull");

                    for (Membership membership : user.getMemberships()) {
                        projectList.add(membership.getProject());

                    }

                } else {
                    Log.v(MY_TAG, "Status is not 200. Can't get project from Server");

                }

            }
        });
        return projectList;

    }
}
