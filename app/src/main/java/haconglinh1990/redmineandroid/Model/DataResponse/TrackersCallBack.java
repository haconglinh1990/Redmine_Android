package haconglinh1990.redmineandroid.Model.DataResponse;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.Model.ObjectModel.Tracker;


/**
 * Created by haconglinh1990 on 07/06/2016.
 */
public interface TrackersCallBack {
    void getTrackerCompleted(int status, ArrayList<Tracker> trackerArrayList);
}
