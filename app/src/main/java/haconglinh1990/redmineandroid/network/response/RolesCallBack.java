package haconglinh1990.redmineandroid.network.response;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.models.Role;

/**
 * Created by haconglinh1990 on 31/05/2016.
 */
public interface RolesCallBack {
    void onCompleted(int statusCode, ArrayList<Role> roleArrayList);
}
