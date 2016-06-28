package haconglinh1990.redmineandroid.Model.DataResponse;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.Model.ObjectModel.Version;


/**
 * Created by haconglinh1990 on 22/06/2016.
 */
public interface VersionsCallBack {
    void VersionCallBackCompleted(int statusCode, ArrayList<Version> versionArrayList);
}
