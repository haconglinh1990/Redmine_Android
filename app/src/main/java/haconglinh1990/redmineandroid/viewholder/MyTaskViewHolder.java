package haconglinh1990.redmineandroid.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import haconglinh1990.redmineandroid.R;

/**
 * Created by haconglinh1990 on 22/04/2016.
 */
public class MyTaskViewHolder extends RecyclerView.ViewHolder {

    public ImageView viewIconTracker, viewAvataUser, viewIconClock;
    public TextView viewNameIssue, viewIdIssue, viewNameProject, viewStatus, viewPriority, viewNameUser, viewStartDate;

    public MyTaskViewHolder(View itemView) {
        super(itemView);
        //viewIconProject = (ImageView) itemView.findViewById(R.id.icon_project);
        viewNameProject = (TextView) itemView.findViewById(R.id.name_project);

    }
}
