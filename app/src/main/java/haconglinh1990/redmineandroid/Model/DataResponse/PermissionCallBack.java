package haconglinh1990.redmineandroid.Model.DataResponse;

import java.util.ArrayList;

/**
 * Created by haconglinh1990 on 31/05/2016.
 */
public interface PermissionCallBack {
    void PermissionCallBackCompleted(int statusCode, ArrayList<String> permissionArrayList);
}
