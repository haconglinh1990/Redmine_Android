package haconglinh1990.redmineandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.R;
import haconglinh1990.redmineandroid.model.Issue;
import haconglinh1990.redmineandroid.viewholder.MyTaskViewHolder;


/**
 * Created by haconglinh1990 on 22/04/2016.
 */
public class RecyclerViewMyTaskAdapter extends RecyclerView.Adapter<MyTaskViewHolder>{
    Context context;
    ArrayList<Issue> issuesList;
    Issue issues;

    public RecyclerViewMyTaskAdapter(Context context, ArrayList<Issue> issuesList) {
        this.context = context;
        this.issuesList = issuesList;
    }

    @Override
    public MyTaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_issue_layout, parent, false);
        return new MyTaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyTaskViewHolder holder, int position) {
        issues = issuesList.get(position);
        //holder.viewIconProject

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
