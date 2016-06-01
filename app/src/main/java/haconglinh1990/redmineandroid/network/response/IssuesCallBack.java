package haconglinh1990.redmineandroid.network.response;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.models.Issue;
import haconglinh1990.redmineandroid.models.Project;

/**
 * Created by haconglinh1990 on 09/05/2016.
 */
public interface IssuesCallBack {
    void onCompleted(int statusCode, ArrayList<Issue> issueArrayList);
}
