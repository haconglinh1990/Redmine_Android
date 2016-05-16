package haconglinh1990.redmineandroid.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import haconglinh1990.redmineandroid.R;
import haconglinh1990.redmineandroid.models.Project;

/**
 * Created by haconglinh1990 on 14/04/2016.
 */
public class ProjectViewHolder extends RecyclerView.ViewHolder {
    public ImageView viewIconProject;
    public TextView viewNameProject;

    public ProjectViewHolder(View itemView) {
        super(itemView);
        viewIconProject = (ImageView) itemView.findViewById(R.id.icon_project);
        viewNameProject = (TextView) itemView.findViewById(R.id.name_project);

    }
}
