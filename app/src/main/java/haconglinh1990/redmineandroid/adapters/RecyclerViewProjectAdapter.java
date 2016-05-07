package haconglinh1990.redmineandroid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.R;
import haconglinh1990.redmineandroid.models.Project;
import haconglinh1990.redmineandroid.viewholder.ProjectViewHolder;

/**
 * Created by haconglinh1990 on 14/04/2016.
 */
public class RecyclerViewProjectAdapter extends RecyclerView.Adapter<ProjectViewHolder>{
    Context context;
    ArrayList<Project> projectList;
    Project project;

    public RecyclerViewProjectAdapter(Context context, ArrayList<Project> projectList) {
        this.context = context;
        this.projectList = projectList;
    }

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View view = inflater.inflate(R.layout.cardview_project_list, parent, false);
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_project_list, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        project = projectList.get(position);
        holder.viewIconProject.setImageResource(R.drawable.icon_finish);
        holder.viewNameProject.setText(project.getName());

    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }
}
