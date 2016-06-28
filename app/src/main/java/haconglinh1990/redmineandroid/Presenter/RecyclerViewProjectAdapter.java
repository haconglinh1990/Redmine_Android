package haconglinh1990.redmineandroid.Presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.Model.ObjectModel.Project;
import haconglinh1990.redmineandroid.R;
import haconglinh1990.redmineandroid.View.Project.ProjectActivity;


/**
 * Created by haconglinh1990 on 14/04/2016.
 */
public class RecyclerViewProjectAdapter extends RecyclerView.Adapter<RecyclerViewProjectAdapter.ProjectViewHolder> {
    Context context;
    ArrayList<Project> projectArrayList;


    public RecyclerViewProjectAdapter(Context context, ArrayList<Project> projectArrayList) {
        this.context = context;
        this.projectArrayList = projectArrayList;
    }

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.card_view_project_list, viewGroup, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        Project project = projectArrayList.get(position);
        holder.viewNameProject.setText(project.getName());

        TextDrawable iconUserDrawable = TextDrawable.builder()
                .beginConfig()
                .fontSize(30) /* size in px */
                .bold()
                .endConfig()
                .buildRound(project.getName().substring(0, 1), ColorGenerator.MATERIAL.getRandomColor());
        holder.viewIconProject.setImageDrawable(iconUserDrawable);

    }

    @Override
    public int getItemCount() {
        return projectArrayList.size();
    }

    public class ProjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout container;
        public ImageView viewIconProject;
        public TextView viewNameProject;

        public ProjectViewHolder(View itemView) {
            super(itemView);

            container = (LinearLayout) itemView.findViewById(R.id.card_view_project_container);
            viewIconProject = (ImageView) itemView.findViewById(R.id.card_view_icon_project);
            viewNameProject = (TextView) itemView.findViewById(R.id.card_view_name_project);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        /*    final ArrayList<String> totalPermission = new ArrayList<>();
            ArrayList<Role> roleArrayList = membershipArrayList.get(getLayoutPosition()).getRolesInProject();
            for (Role role : roleArrayList) {
                new APIClient(context).getPermissionsInProject(role.getId(), new PermissionCallBack() {
                    @Override
                    public void getTrackerCompleted(int statusCode, ArrayList<String> stringArrayList) {
                        Log.d(APIClient.MY_TAG, "Size Array permission: " + totalPermission.size() +
                        " ,arrayapi " + stringArrayList);
                        for (String s : stringArrayList) {
                            totalPermission.add(s);
                        }
                    }
                });
            }*/

            //Log.d(APIClient.MY_TAG, "Size Array permission: " + totalPermission.size());Home Projects Calendar Holiday Issues Gantt People Spent time My page Adm

            Intent intent = new Intent(context, ProjectActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("projectId", projectArrayList.get(getLayoutPosition()).getId());
            bundle.putString("projectName", projectArrayList.get(getLayoutPosition()).getName());
            intent.putExtra("projectData", bundle);
            context.startActivity(intent);

          /*  for (String s : totalPermission) {
                if (s.equalsIgnoreCase("view_issues")) {
                    Intent intent = new Intent(context, ProjectActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("projectId", projectArrayList.get(getLayoutPosition()).getId());
                    bundle.putString("projectName", projectArrayList.get(getLayoutPosition()).getName());
                    intent.putExtra("projectData", bundle);
                    context.startActivity(intent);
                    break;

                } else {
                    Toast.makeText(context, "You're not have Permission in this Project !!!", Toast.LENGTH_SHORT).show();
                }
            }*/
        }
    }

}

