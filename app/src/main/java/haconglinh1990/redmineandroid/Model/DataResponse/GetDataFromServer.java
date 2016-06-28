package haconglinh1990.redmineandroid.Model.DataResponse;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.Model.ObjectModel.Issue;
import haconglinh1990.redmineandroid.Model.ObjectModel.Project;
import haconglinh1990.redmineandroid.Model.ObjectModel.Tracker;
import haconglinh1990.redmineandroid.Model.ObjectModel.User;
import haconglinh1990.redmineandroid.Model.ObjectModel.Version;


/**
 * Created by haconglinh1990 on 24/06/2016.
 */
public final class GetDataFromServer implements ProjectCallBack, TrackersCallBack,
        MembersCallBack, VersionsCallBack, IssuesCallBack, PermissionCallBack {
    private ArrayList<Project> projectArrayList;
    private ArrayList<String> permissionArrayList;
    private ArrayList<Tracker> trackerArrayList;

    private ArrayList<Version> versionArrayList;
    private ArrayList<Issue> issueArrayList;
    private ArrayList<User> userArrayList;

    public ArrayList<Project> getProjectArrayList() {
        return projectArrayList;
    }

    public void setProjectArrayList(ArrayList<Project> projectArrayList) {
        this.projectArrayList = projectArrayList;
    }

    public ArrayList<Tracker> getTrackerArrayList() {
        return trackerArrayList;
    }

    public void setTrackerArrayList(ArrayList<Tracker> trackerArrayList) {
        this.trackerArrayList = trackerArrayList;
    }

    @Override
    public void getProjectCompleted(int statusCode, ArrayList<Project> projectArrayList) {
        this.projectArrayList = projectArrayList;

    }

    @Override
    public void getTrackerCompleted(int status, ArrayList<Tracker> trackerArrayList) {
        this.trackerArrayList = trackerArrayList;
    }

    @Override
    public void IssuesCallBackCompleted(int statusCode, ArrayList<Issue> issueArrayList) {
        this.issueArrayList = issueArrayList;

    }

    @Override
    public void MemberCallBackCompleted(int statusCode, ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    public ArrayList<String> getPermissionArrayList() {
        return permissionArrayList;
    }

    public void setPermissionArrayList(ArrayList<String> permissionArrayList) {
        this.permissionArrayList = permissionArrayList;
    }

    public ArrayList<Version> getVersionArrayList() {
        return versionArrayList;
    }

    public void setVersionArrayList(ArrayList<Version> versionArrayList) {
        this.versionArrayList = versionArrayList;
    }

    public ArrayList<Issue> getIssueArrayList() {
        return issueArrayList;
    }

    public void setIssueArrayList(ArrayList<Issue> issueArrayList) {
        this.issueArrayList = issueArrayList;
    }

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    @Override
    public void VersionCallBackCompleted(int statusCode, ArrayList<Version> versionArrayList) {
        this.versionArrayList = versionArrayList;

    }

    @Override
    public void PermissionCallBackCompleted(int statusCode, ArrayList<String> permissionArrayList) {
        this.permissionArrayList = permissionArrayList;

    }
}
