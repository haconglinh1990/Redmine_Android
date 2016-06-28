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

import haconglinh1990.redmineandroid.Model.ObjectModel.User;
import haconglinh1990.redmineandroid.R;


/**
 * Created by haconglinh1990 on 14/04/2016.
 */
public class RecyclerViewMemberAdapter extends RecyclerView.Adapter<RecyclerViewMemberAdapter.MemberViewHolder>{
    Context context;
    ArrayList<User> userArrayList;

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

    public RecyclerViewMemberAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.card_view_member_list, viewGroup, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MemberViewHolder holder, int position) {
        User user = userArrayList.get(position);
        holder.tvNameUser.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout container;
        public RadioButton rbMember;
        public TextView tvNameUser;

        public MemberViewHolder(View itemView) {
            super(itemView);
            rbMember = (RadioButton) itemView.findViewById(R.id.rbMembers);
            tvNameUser = (TextView) itemView.findViewById(R.id.tv_name_user);
            container = (LinearLayout) itemView.findViewById(R.id.card_view_member_container);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
