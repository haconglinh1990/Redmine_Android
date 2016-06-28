package haconglinh1990.redmineandroid.Model.DataResponse;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.Model.ObjectModel.Role;


/**
 * Created by haconglinh1990 on 31/05/2016.
 */
public interface RolesCallBack {
    void onCompleted(int statusCode, ArrayList<Role> roleArrayList);
}
