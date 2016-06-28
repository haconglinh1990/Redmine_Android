package haconglinh1990.redmineandroid.Presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.Model.ObjectModel.Tracker;
import haconglinh1990.redmineandroid.R;


/**
 * Created by haconglinh1990 on 14/04/2016.
 */
public class RecyclerViewTrackersAdapter extends RecyclerView.Adapter<RecyclerViewTrackersAdapter.TrackerViewHolder>{
    Context context;
    ArrayList<Tracker> trackersArrayList;
    Tracker tracker;

    // Define listener member variable
    private static OnItemClickListener listener;
    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public RecyclerViewTrackersAdapter(Context context, ArrayList<Tracker> trackersArrayList) {
        this.context = context;
        this.trackersArrayList = trackersArrayList;
    }

    @Override
    public TrackerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_tracker_list, parent, false);
        return new TrackerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrackerViewHolder holder, int position) {
        tracker = trackersArrayList.get(position);
        holder.viewNameTracker.setText(tracker.getName());
    }

    @Override
    public int getItemCount() {
        return trackersArrayList.size();
    }

    public class TrackerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public RadioButton rbTracker;
        public TextView viewNameTracker;

        public TrackerViewHolder(View itemView) {
            super(itemView);
            rbTracker = (RadioButton) itemView.findViewById(R.id.card_view_tracker_rbTracker);
            viewNameTracker = (TextView) itemView.findViewById(R.id.card_view_tracker_name_tracker);

        }

        @Override
        public void onClick(View v) {

        }
    }

}
