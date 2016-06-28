package haconglinh1990.redmineandroid.Model.DataResponse;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.Model.ObjectModel.Issue;


/**
 * Created by haconglinh1990 on 09/05/2016.
 */
public interface IssuesCallBack {
    void IssuesCallBackCompleted(int statusCode, ArrayList<Issue> issueArrayList);
}
