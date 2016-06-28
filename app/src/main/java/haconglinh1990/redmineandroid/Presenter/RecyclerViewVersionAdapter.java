package haconglinh1990.redmineandroid.Presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.Model.ObjectModel.Version;
import haconglinh1990.redmineandroid.R;


/**
 * Created by haconglinh1990 on 14/04/2016.
 */
public class RecyclerViewVersionAdapter extends RecyclerView.Adapter<RecyclerViewVersionAdapter.VersionViewHolder>{
    Context context;
    ArrayList<Version> versionArrayList;

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

    public RecyclerViewVersionAdapter(Context context, ArrayList<Version> versionArrayList) {
        this.context = context;
        this.versionArrayList = versionArrayList;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.card_view_version_list, viewGroup, false);
        return new VersionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VersionViewHolder holder, int position) {
        Version version = versionArrayList.get(position);
        holder.tvNameVersion.setText(version.getName());
    }

    @Override
    public int getItemCount() {
        return versionArrayList.size();
    }

    public class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout container;
        public RadioButton rbVersion;
        public TextView tvNameVersion;

        public VersionViewHolder(View itemView) {
            super(itemView);
            rbVersion = (RadioButton) itemView.findViewById(R.id.rbVersion);
            tvNameVersion = (TextView) itemView.findViewById(R.id.tv_name_version);
            container = (LinearLayout) itemView.findViewById(R.id.card_view_version_container);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
