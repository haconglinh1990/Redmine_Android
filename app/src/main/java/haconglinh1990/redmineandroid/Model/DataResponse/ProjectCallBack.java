package haconglinh1990.redmineandroid.Model.DataResponse;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.Model.ObjectModel.Project;


/**
 * Created by haconglinh1990 on 09/05/2016.
 */
public interface ProjectCallBack {
    void getProjectCompleted(int statusCode, ArrayList<Project> projectArrayList);

}
