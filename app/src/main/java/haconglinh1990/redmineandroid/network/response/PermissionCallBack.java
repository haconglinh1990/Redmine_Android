package haconglinh1990.redmineandroid.network.response;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import haconglinh1990.redmineandroid.models.Role;

/**
 * Created by haconglinh1990 on 31/05/2016.
 */
public interface PermissionCallBack {
    void onCompleted(int statusCode, ArrayList<String> stringArrayList);
}
