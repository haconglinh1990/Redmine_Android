package haconglinh1990.redmineandroid.Presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.Model.ObjectModel.Issue;
import haconglinh1990.redmineandroid.R;
import haconglinh1990.redmineandroid.View.Issue.IssueDetailActivity;


/**
 * Created by haconglinh1990 on 22/04/2016.
 */
public class RecyclerViewIssueAdapter extends RecyclerView.Adapter<RecyclerViewIssueAdapter.IssueViewHolder> {
    Context context;
    ArrayList<Issue> issuesList;


    public RecyclerViewIssueAdapter(Context context, ArrayList<Issue> issuesList) {
        this.context = context;
        this.issuesList = issuesList;
    }

    @Override
    public IssueViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.card_view_issue_list, viewGroup, false);
        return new IssueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IssueViewHolder holder, int position) {
        int color, textColor;
        Issue issue = issuesList.get(position);


        holder.viewNameIssue.setText(issue.getSubject());
        //Log.v(APIClient.MY_TAG, "Version: " + issue.getFixedVersion().getName());
        holder.viewIdIssue.setText("#" + issue.getId());
        holder.viewStatus.setText("  " + issue.getStatus().getName());

        switch (issue.getPriority().getName()) {
            case "Low":
                textColor = Color.rgb(156,39,176);
                break;

            case "Normal":
                textColor = Color.rgb(33,150,243);
                break;
            case "High":
                textColor = Color.rgb(244,67,54);
                break;
            case "Urgent":
                textColor = Color.rgb(51,105,30);
                break;
            case "Immediate":
                textColor = Color.rgb(0, 0, 0);
                break;

            default:
                textColor = Color.rgb(121,85,72);
                break;
        }

        holder.viewPriority.setTextColor(textColor);
        holder.viewNameUserAssigned.setText(" " + issue.getAssignedTo().getName());
        holder.viewPriority.setText("  " + issue.getPriority().getName());
        holder.viewDueDate.setText(" " + issue.getDueDate());
        holder.viewNameProject.setText(issue.getProject().getName());
        holder.viewVersionProject.setText("  v0.1");
        holder.viewNumberPercent.setText(issue.getDoneRatio() + "%");

        TextDrawable iconUserDrawable = TextDrawable.builder()
                .beginConfig()
                .fontSize(16) /* size in px */
                .bold()
                .endConfig()
                .buildRound(issue.getAuthor().getName().substring(0, 1), ColorGenerator.MATERIAL.getRandomColor());
        holder.viewIconUser.setImageDrawable(iconUserDrawable);

        switch (issue.getTracker().getName()) {
            case "Feature":
                color = Color.rgb(0, 0, 255);
                break;
            case "Develop":
                color = Color.rgb(0, 191, 255);
                break;
            case "Design":
                color = Color.rgb(0, 255, 255);
                break;
            case "Support":
                color = Color.rgb(0, 255, 128);
                break;
            case "Test":
                color = Color.rgb(64, 255, 0);
                break;
            case "Bug":
                color = Color.rgb(255, 64, 0);
                break;
            case "Research":
                color = Color.rgb(255, 255, 0);
                break;

            default:
                color = Color.rgb(255, 230, 230);
                break;
        }


        TextDrawable iconTrackerDrawable = TextDrawable.builder()
                .beginConfig()
                .textColor(Color.WHITE)
                .fontSize(20) /* size in px */
                .bold()
                .endConfig()
                .buildRoundRect(issue.getTracker().getName(), color, 4);

        holder.viewIconTracker.setImageDrawable(iconTrackerDrawable);
        holder.viewIconClock.setImageResource(R.drawable.icon_clock);

        holder.progressBarIssue.setProgress(issue.getDoneRatio());


    }

    @Override
    public int getItemCount() {
        return issuesList.size();
    }


    public class IssueViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TableLayout container;
        public ProgressBar progressBarIssue;
        public ImageView viewIconTracker, viewIconUser, viewIconClock;
        public TextView viewNameIssue, viewIdIssue, viewNameProject, viewVersionProject,
                viewStatus, viewPriority, viewNameUserAssigned, viewDueDate, viewNumberPercent;

        public IssueViewHolder(View itemView) {
            super(itemView);

            viewIconUser = (ImageView) itemView.findViewById(R.id.card_view_imgv_icon_user);
            viewIconTracker = (ImageView) itemView.findViewById(R.id.card_view_imgv_icon_tracker);
            viewIconClock = (ImageView) itemView.findViewById(R.id.card_view_imgv_icon_clock);

            progressBarIssue = (ProgressBar) itemView.findViewById(R.id.card_view_progressbar_issue);

            viewNameIssue = (TextView) itemView.findViewById(R.id.card_view_tv_name_issue);
            viewIdIssue = (TextView) itemView.findViewById(R.id.card_view_tv_id_issue);
            viewStatus = (TextView) itemView.findViewById(R.id.card_view_tv_status_issue);
            viewNameUserAssigned = (TextView) itemView.findViewById(R.id.card_view_tv_name_user_assigned);
            viewPriority = (TextView) itemView.findViewById(R.id.card_view_tv_priority);
            viewDueDate = (TextView) itemView.findViewById(R.id.card_view_tv_due_date);
            viewNameProject = (TextView) itemView.findViewById(R.id.card_view_tv_name_project);
            viewVersionProject = (TextView) itemView.findViewById(R.id.card_view_tv_version_project);
            viewNumberPercent = (TextView) itemView.findViewById(R.id.card_view_tv_number_percent);

            container = (TableLayout) itemView.findViewById(R.id.card_view_issue_container);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, IssueDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("issueId", issuesList.get(getLayoutPosition()).getId());
            bundle.putString("issueName", issuesList.get(getLayoutPosition()).getSubject());
            intent.putExtra("issueData", bundle);
            context.startActivity(intent);
        }
    }
}
