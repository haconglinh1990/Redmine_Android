package haconglinh1990.redmineandroid.Model.DataResponse;


import haconglinh1990.redmineandroid.Model.ObjectModel.Issue;

/**
 * Created by haconglinh1990 on 27/05/2016.
 */
public interface IssueDetailCallBack {
    void onCompleted(int statusCode, Issue issue);
}
