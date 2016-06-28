package haconglinh1990.redmineandroid.Model.DataQuery;

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

import java.util.ArrayList;

import haconglinh1990.redmineandroid.Model.DataResponse.IssueDetailCallBack;
import haconglinh1990.redmineandroid.Model.DataResponse.IssuesCallBack;
import haconglinh1990.redmineandroid.Model.DataResponse.MembersCallBack;
import haconglinh1990.redmineandroid.Model.DataResponse.MembershipsCallBack;
import haconglinh1990.redmineandroid.Model.DataResponse.PermissionCallBack;
import haconglinh1990.redmineandroid.Model.DataResponse.ProjectCallBack;
import haconglinh1990.redmineandroid.Model.DataResponse.RolesCallBack;
import haconglinh1990.redmineandroid.Model.DataResponse.TrackersCallBack;
import haconglinh1990.redmineandroid.Model.DataResponse.UserCallback;
import haconglinh1990.redmineandroid.Model.DataResponse.VersionsCallBack;
import haconglinh1990.redmineandroid.Model.ObjectModel.IssueDetailObjectModel;
import haconglinh1990.redmineandroid.Model.ObjectModel.IssueObjectModel;
import haconglinh1990.redmineandroid.Model.ObjectModel.MembersObjectModel;
import haconglinh1990.redmineandroid.Model.ObjectModel.Membership;
import haconglinh1990.redmineandroid.Model.ObjectModel.PermissionObjectModel;
import haconglinh1990.redmineandroid.Model.ObjectModel.Project;
import haconglinh1990.redmineandroid.Model.ObjectModel.ProjectDetailObjectModel;
import haconglinh1990.redmineandroid.Model.ObjectModel.Role;
import haconglinh1990.redmineandroid.Model.ObjectModel.User;
import haconglinh1990.redmineandroid.Model.ObjectModel.UserObjectModel;
import haconglinh1990.redmineandroid.Model.ObjectModel.VersionObjectModel;
import haconglinh1990.redmineandroid.Presenter.Remember;
import haconglinh1990.redmineandroid.View.Main.MainActivity;


/**
 * Created by haconglinh1990 on 05/04/2016.
 */
public class APIClient {
    public static final String PREFS_ID_PROJECT = "idofproject";
    public static final String MY_TAG = "message_from_meomeo";
    public static final String API_BASE_URL = "http://192.168.1.59/";
    public static final String API_PROJECT = API_BASE_URL + "projects/";
    public static final String API_ROLE = API_BASE_URL + "roles.json";
    public static final String API_PERMISSION = API_BASE_URL + "roles/";
    public static final String API_USER = API_BASE_URL + "users/current.json";
    public static final String API_GET_ISSUE_ID = API_BASE_URL + "issues/";
    public static final String API_MEMBERSHIP_OF_CURRENT_USER = API_USER + "?include=memberships";
    public static final String API_TRACKERS_BY_PROJECT = API_BASE_URL + "trackers.json";
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

    public void getMemberShipOfCurrentUser(final MembershipsCallBack membershipsCallBack) {
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
                                    if (status == 200) {
                                        Log.v(MY_TAG, "Get Membership from Server successfull");
                                        membershipsCallBack.onCompleted(status, result.getResult().getUser().getMemberships());

                                    } else {
                                        Log.v(MY_TAG, "Status is not 200. Can't get membership from Server");
                                        membershipsCallBack.onCompleted(status, null);
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

    public void getProjectOfCurrentUser(final ProjectCallBack projectCallBack) {

        getMemberShipOfCurrentUser(new MembershipsCallBack() {
            @Override
            public void onCompleted(int statusCode, ArrayList<Membership> membershipArrayList) {
                ArrayList<Project> projectArrayList = new ArrayList<>();
                for (Membership membership : membershipArrayList) {
                    projectArrayList.add(membership.getProject());
                }
                projectCallBack.getProjectCompleted(statusCode, projectArrayList);
            }
        });
    }

    public void getRolesInProject(final int idProject, final RolesCallBack rolesCallBack) {
        getMemberShipOfCurrentUser(new MembershipsCallBack() {
            @Override
            public void onCompleted(int statusCode, ArrayList<Membership> membershipArrayList) {
                //Log.v(MY_TAG, "Get Membership from Server successfull");
                for (Membership membership : membershipArrayList) {
                    Log.d(MY_TAG, "idProject out = " + membership.getProject().getId());
                    if (membership.getProject().getId() == idProject) {
                        Log.d(MY_TAG, "Get Roles successfull, idProject = " + membership.getProject().getId());
                        rolesCallBack.onCompleted(statusCode, membership.getRoles());
                    }
                }

            }
        });
    }

    public void getPermissionsInProject(final int idProject, final PermissionCallBack permissionCallBack) {

        Remember.restoreUser(context, new UserCallback() {
            @Override
            public void User(final String username, final String password, boolean check) {
                if (check == true) {
                    getRolesInProject(idProject, new RolesCallBack() {
                        @Override
                        public void onCompleted(int statusCode, final ArrayList<Role> roleArrayList) {
                            final ArrayList<String> arrayListPermission = new ArrayList<>();
                            for (int i = 0; i < roleArrayList.size(); i++) {
                                Role role = roleArrayList.get(i);

                                final int finalI = i;
                                Ion.with(context)
                                        .load(API_PERMISSION + role.getId().toString() + ".json")
                                        .basicAuthentication(username, password)
                                        .as(new TypeToken<PermissionObjectModel>() {
                                        })
                                        .withResponse()
                                        .setCallback(new FutureCallback<Response<PermissionObjectModel>>() {
                                            @Override
                                            public void onCompleted(Exception e, Response<PermissionObjectModel> result) {
                                                int status = result.getHeaders().code();
                                                if (status == 200) {
                                                    arrayListPermission.addAll(result.getResult().getRole().getPermissions());
                                                    if (finalI == (roleArrayList.size() - 1)) {
                                                        Log.d(MY_TAG, "Get permission successfull  ");
                                                        permissionCallBack.PermissionCallBackCompleted(200, arrayListPermission);

                                                    }
                                                } else {
                                                    Log.d(MY_TAG, "Error when get permission from API");
                                                    permissionCallBack.PermissionCallBackCompleted(status, null);
                                                }
                                            }
                                        });
                            }

                        }
                    });

                } else {
                    Log.d(MY_TAG, "Can not get username, pass");
                }
            }
        });

    }

    public void getTrackersInProject(final int idProject, final TrackersCallBack trackersCallBack) {
        Remember.restoreUser(context, new UserCallback() {

            @Override
            public void User(String username, String password, boolean check) {
                Log.d(MY_TAG, API_PROJECT + idProject + ".json?include=trackers");


                if (check == true) {
                    Ion.with(context)
                            .load(API_PROJECT + idProject + ".json?include=trackers")
                            .basicAuthentication(username, password)
                            .as(new TypeToken<ProjectDetailObjectModel>() {
                            })
                            .withResponse()
                            .setCallback(new FutureCallback<Response<ProjectDetailObjectModel>>() {
                                @Override
                                public void onCompleted(Exception e, Response<ProjectDetailObjectModel> result) {
                                    int status = result.getHeaders().code();
                                    //Log.v(MY_TAG, " " + result.getResult().toString() + "Status: " + status);

                                    if (status == 200) {
                                        Log.v(MY_TAG, "Get Membership from Server successfull");
                                        trackersCallBack.getTrackerCompleted(status, result.getResult().getProject().getTrackers());

                                    } else {
                                        Log.v(MY_TAG, "Status is not 200. Can't get membership from Server" + status);
                                        trackersCallBack.getTrackerCompleted(status, null);
                                    }
                                }
                            });
                } else {
                    Log.d(MY_TAG, "Can not get username, pass");
                }
            }
        });

    }

    public void getMembersInProject(final int idProject, final MembersCallBack membersCallBack) {
        Remember.restoreUser(context, new UserCallback() {
            @Override
            public void User(String username, String password, boolean check) {
                if (check == true) {

                    Ion.with(context)
                            .load(API_PROJECT + idProject + "/memberships.json")
                            .basicAuthentication(username, password)

                            .as(new TypeToken<MembersObjectModel>() {
                            })
                            .withResponse()
                            .setCallback(new FutureCallback<Response<MembersObjectModel>>() {
                                @Override
                                public void onCompleted(Exception e, Response<MembersObjectModel> result) {
                                    int status = result.getHeaders().code();
                                    //Log.v(MY_TAG, " " + result.getResult().toString() + "Status: " + status);

                                    if (status == 200) {
                                        ArrayList<User> userArrayList = new ArrayList<>();
                                        for (Membership membership : result.getResult().getMemberships()) {
                                            userArrayList.add(membership.getUser());
                                            Log.v(MY_TAG, membership.getUser().getName());

                                        }
                                        Log.v(MY_TAG, "Get list member from Server successfull");
                                        membersCallBack.MemberCallBackCompleted(status, userArrayList);

                                    } else {
                                        Log.v(MY_TAG, "Status is not 200. Can't get list member from Server" + status);
                                        membersCallBack.MemberCallBackCompleted(status, null);
                                    }
                                }
                            });

                } else {
                    Log.d(MY_TAG, "Can not get username, pass");
                }
            }
        });

    }

    public void getVersionsInProject(final int idProject, final VersionsCallBack versionsCallBack) {
        Remember.restoreUser(context, new UserCallback() {
            @Override
            public void User(String username, String password, boolean check) {
                if (check == true) {

                    Ion.with(context)
                            .load(API_PROJECT + idProject + "/versions.json")
                            .basicAuthentication(username, password)
                            .as(new TypeToken<VersionObjectModel>() {
                            })
                            .withResponse()
                            .setCallback(new FutureCallback<Response<VersionObjectModel>>() {
                                @Override
                                public void onCompleted(Exception e, Response<VersionObjectModel> result) {
                                    int status = result.getHeaders().code();
                                    //Log.v(MY_TAG, " " + result.getResult().toString() + "Status: " + status);

                                    if (status == 200) {

                                        Log.v(MY_TAG, "Get list versions from Server successfull");
                                        versionsCallBack.VersionCallBackCompleted(status, result.getResult().getVersions());

                                    } else {
                                        Log.v(MY_TAG, "Status is not 200. Can't get list version from Server" + status);
                                        versionsCallBack.VersionCallBackCompleted(status, null);
                                    }
                                }
                            });

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
                                        issuesCallBack.IssuesCallBackCompleted(status, result.getResult().getIssues());
                                    } else {
                                        Log.d(MY_TAG, "Error when get issues from API");
                                        issuesCallBack.IssuesCallBackCompleted(status, null);
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
                                issuesCallBack.IssuesCallBackCompleted(status, issuesObjectModel.getIssues());

                            } else {
                                Log.d(MY_TAG, "Error when get issues from API");
                                issuesCallBack.IssuesCallBackCompleted(status, null);
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
