package haconglinh1990.redmineandroid.Model.DataResponse;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.Model.ObjectModel.User;


/**
 * Created by haconglinh1990 on 21/06/2016.
 */
public interface MembersCallBack {
    void MemberCallBackCompleted(int statusCode, ArrayList<User> userArrayList);

}
