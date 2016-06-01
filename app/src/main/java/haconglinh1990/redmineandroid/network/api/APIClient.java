package haconglinh1990.redmineandroid.network.api;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import haconglinh1990.redmineandroid.activities.ultils.MainActivity;
import haconglinh1990.redmineandroid.models.IssueDetailObjectModel;
import haconglinh1990.redmineandroid.models.IssueObjectModel;
import haconglinh1990.redmineandroid.models.PermissionObjectModel;
import haconglinh1990.redmineandroid.models.RoleObjectModel;
import haconglinh1990.redmineandroid.models.UserObjectModel;
import haconglinh1990.redmineandroid.network.response.IssueDetailCallBack;
import haconglinh1990.redmineandroid.network.response.IssuesCallBack;
import haconglinh1990.redmineandroid.network.response.PermissionCallBack;
import haconglinh1990.redmineandroid.network.response.MembershipCallBack;
import haconglinh1990.redmineandroid.network.response.RolesCallBack;
import haconglinh1990.redmineandroid.network.response.UserCallback;
import haconglinh1990.redmineandroid.ultils.Remember;

/**
 * Created by haconglinh1990 on 05/04/2016.
 */
public class APIClient {
    public static final String PREFS_ID_PROJECT = "idofproject";
    public static final String MY_TAG = "message_from_meomeo";
    public static final String API_PROJECT = "projects/";
    public static final String API_BASE_URL = "http://192.168.1.59/";
    public static final String API_ROLE = API_BASE_URL + "roles.json";
    public static final String API_PERMISSION = API_BASE_URL + "roles/";
    public static final String API_USER = API_BASE_URL + "users/current.json";
    public static final String API_GET_ISSUE_ID = API_BASE_URL + "issues/";
    public static final String API_MEMBERSHIP_OF_CURRENT_USER = API_USER + "?include=memberships";
    public static final String API_ALL_TRACKERS = API_BASE_URL + "trackers.json";
    public static final String API_ALL_STATUS = API_BASE_URL + "issue_statuses.json";
    public static final String API_ALL_MEMBER_OF_PROJECT = API_BASE_URL + API_PROJECT;
    public static final String API_ISSUES_BY_PROJECT = API_BASE_URL + "issues.json?project_id=";
    public static final String API_ISSUES_BY_CURENT_USER = API_BASE_URL + "issues.json?assigned_to_id=me";

    private Context context;

    public APIClient(Context context) {
        this.context = context;
    }

    public void logInUseAPI(String address, final String username, final String password) {
        Ion.with(context)
                .load(address)
                .basicAuthentication(username, password)
                .as(new TypeToken<UserObjectModel>() {
                })
                .withResponse()
                .setCallback(new FutureCallback<Response<UserObjectModel>>() {
                    @Override
                    public void onCompleted(Exception e, Response<UserObjectModel> result) {

                        if (result.getHeaders().code() == 200 && result.getResult().getUser().getApiKey() != null) {
                            Remember.saveUser(context, username, password);
                            Remember.saveApiKey(context, result.getResult().getUser().getApiKey());
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

    public void getRoles(final RolesCallBack rolesCallBack){

        Remember.restoreUser(context, new UserCallback() {
            @Override
            public void User(String username, String password, boolean check) {
                if (check == true) {

                    Ion.with(context)
                            .load(API_ROLE)
                            .basicAuthentication(username, password)
                            .as(new TypeToken<RoleObjectModel>() {
                            })
                            .withResponse()
                            .setCallback(new FutureCallback<Response<RoleObjectModel>>() {
                                @Override
                                public void onCompleted(Exception e, Response<RoleObjectModel> result) {
                                    int status = result.getHeaders().code();
                                    if (status == 200) {
                                        Log.d(MY_TAG, "Get Roles successfull");
                                        rolesCallBack.onCompleted(status, result.getResult().getRoles());
                                    } else {
                                        Log.d(MY_TAG, "Error when get Roles from API");
                                        rolesCallBack.onCompleted(status, null);
                                    }



                                }
                            });

                    //Log.d(MY_TAG, "Username: " + username + " ,Pass: " + password);
                } else {
                    Log.d(MY_TAG, "Can not get username, pass");
                }

            }
        });
    }

    public void getPermissions(final int idRole, final PermissionCallBack permissionCallBack){
        Remember.restoreUser(context, new UserCallback() {
            @Override
            public void User(String username, String password, boolean check) {
                if (check == true) {

                    Ion.with(context)
                            .load(API_PERMISSION + idRole + ".json")
                            .basicAuthentication(username, password)
                            .as(new TypeToken<PermissionObjectModel>() {
                            })
                            .withResponse()
                            .setCallback(new FutureCallback<Response<PermissionObjectModel>>() {
                                @Override
                                public void onCompleted(Exception e, Response<PermissionObjectModel> result) {
                                    int status = result.getHeaders().code();
                                    if (status == 200) {
                                        Log.d(MY_TAG, "Get permission successfull");
                                        permissionCallBack.onCompleted(status, result.getResult().getRole().getPermissions());
                                    } else {
                                        Log.d(MY_TAG, "Error when get permission from API");
                                        permissionCallBack.onCompleted(status, null);
                                    }
                                }
                            });

                    //Log.d(MY_TAG, "Username: " + username + " ,Pass: " + password);
                } else {
                    Log.d(MY_TAG, "Can not get username, pass");
                }
            }
        });

    }

    public void getMembershipOfCurentUser(final MembershipCallBack membershipCallBack) {

        Remember.restoreUser(context, new UserCallback() {
            @Override
            public void User(String username, String password, boolean check) {

                if (check == true) {

                    Ion.with(context)
                            .load(API_MEMBERSHIP_OF_CURRENT_USER)
                            .basicAuthentication(username, password)
                            .as(new TypeToken<UserObjectModel>() {
                            })
                            .withResponse()
                            .setCallback(new FutureCallback<Response<UserObjectModel>>() {
                                @Override
                                public void onCompleted(Exception e, Response<UserObjectModel> result) {
                                    int status = result.getHeaders().code();
                                    Log.v(MY_TAG, " " + result.getResult().toString() + "Status: " + status);

                                    if (status == 200) {
                                        Log.v(MY_TAG, "Get Membership from Server successfull");
                                        membershipCallBack.onCompleted(status, result.getResult().getUser().getMemberships());

                                    } else {
                                        Log.v(MY_TAG, "Status is not 200. Can't get membership from Server");
                                        membershipCallBack.onCompleted(status, null);
                                    }
                                }
                            });

                    //Log.d(MY_TAG, "Username: " + username + " ,Pass: " + password);
                } else {
                    Log.d(MY_TAG, "Can not get username, pass");
                }
            }
        });


    }

    public void getIssues(final String urlQuery, final IssuesCallBack issuesCallBack) {

        Remember.restoreUser(context, new UserCallback() {
            @Override
            public void User(String username, String password, boolean check) {
                Log.d(MY_TAG, "In getIssuesofCurentUser API, Username = " + username + ", Password: " +
                        password + ", Check: " + check + ", Url " + urlQuery);

                if (check == true) {
                    Ion.with(context)
                            .load(urlQuery)
                            .basicAuthentication(username, password)
                            .as(new TypeToken<IssueObjectModel>() {
                            })
                            .withResponse()
                            .setCallback(new FutureCallback<Response<IssueObjectModel>>() {
                                @Override
                                public void onCompleted(Exception e, Response<IssueObjectModel> result) {
                                    int status = result.getHeaders().code();

                                    if (status == 200) {
                                        Log.d(MY_TAG, "Get issues successfull");
                                        issuesCallBack.onCompleted(status, result.getResult().getIssues());
                                    } else {
                                        Log.d(MY_TAG, "Error when get issues from API");
                                        issuesCallBack.onCompleted(status, null);
                                    }
                                }
                            });
                } else {
                    Log.d(MY_TAG, "Can not get username, pass");
                }
            }
        });
    }

    public void getDetailIssue(final String urlQuery, final IssueDetailCallBack issueDetailCallBack) {

        Remember.restoreUser(context, new UserCallback() {
            @Override
            public void User(String username, String password, boolean check) {
                Log.d(MY_TAG, "In getIssuesofCurentUser API, Username = " + username + ", Password: " + password + ", Check: " + check);
                if (check == true) {

                    Ion.with(context)
                            .load(urlQuery)
                            .basicAuthentication(username, password)
                            .as(new TypeToken<IssueDetailObjectModel>() {
                            })
                            .withResponse()
                            .setCallback(new FutureCallback<Response<IssueDetailObjectModel>>() {
                                @Override
                                public void onCompleted(Exception e, Response<IssueDetailObjectModel> result) {
                                    int status = result.getHeaders().code();

                                    if (status == 200) {
                                        Log.d(MY_TAG, "Get issue detail successfull");
                                        issueDetailCallBack.onCompleted(status, result.getResult().getIssue());

                                    } else {
                                        Log.d(MY_TAG, "Error when get issue detail from API");
                                        issueDetailCallBack.onCompleted(status, null);
                                    }

                                }
                            });

                } else {
                    Log.d(MY_TAG, "Can not get username, pass");
                }
            }
        });
    }

    public void putIssue(final String urlQuery, final IssueObjectModel issueObjectModel) {
        Remember.restoreUser(context, new UserCallback() {
            @Override
            public void User(String username, String password, boolean check) {
                Log.d(MY_TAG, "In putIssue API, Username = " + username + ", Password: " + password + ", Check: " + check);
                if (check == true) {
                    Ion.with(context)
                            .load(urlQuery)
                            .basicAuthentication(username, password)
                            .setJsonPojoBody(issueObjectModel)
                            .as(new TypeToken<IssueObjectModel>() {
                            })
                            .withResponse()
                            .setCallback(new FutureCallback<Response<IssueObjectModel>>() {
                                @Override
                                public void onCompleted(Exception e, Response<IssueObjectModel> result) {
                                    if (result.getHeaders().code() == 200) {
                                        Log.d(MY_TAG, "Get issues successfull");


                                    } else {
                                        Log.d(MY_TAG, "Error when get issues from API");
                                    }

                                }
                            });
                } else {
                    Log.d(MY_TAG, "Can not get username, pass");
                }

            }
        });

    }

    public void logOut() {
        //
    }

    public void addnewIssue(final String urlQuery, final IssuesCallBack issuesCallBack) {
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
                                issuesCallBack.onCompleted(status, issuesObjectModel.getIssues());

                            } else {
                                Log.d(MY_TAG, "Error when get issues from API");
                                issuesCallBack.onCompleted(status, null);
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
