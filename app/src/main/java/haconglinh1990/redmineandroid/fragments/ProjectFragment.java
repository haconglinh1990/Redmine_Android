package haconglinh1990.redmineandroid.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.models.Membership;
import haconglinh1990.redmineandroid.models.Project;
import haconglinh1990.redmineandroid.models.Role;
import haconglinh1990.redmineandroid.network.api.APIClient;
import haconglinh1990.redmineandroid.R;
import haconglinh1990.redmineandroid.adapters.RecyclerViewProjectAdapter;
import haconglinh1990.redmineandroid.network.response.MembershipCallBack;

public class ProjectFragment extends Fragment {
    private static final String MY_TAG = "message_from_meomeo";
    RecyclerView recyclerView;
    private static RecyclerViewProjectAdapter recyclerViewProjectAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        new APIClient(getContext()).getMembershipOfCurentUser(new MembershipCallBack() {
            @Override
            public void onCompleted(int statusCode, ArrayList<Membership> membershipArrayList) {
                if (membershipArrayList == null) {
                    Log.d(MY_TAG, "membership in ProjectFragment is null, fuck off");
                } else {
                    ArrayList<Project> projectArrayList = new ArrayList<>();
                    for (Membership membership : membershipArrayList) {
                        projectArrayList.add(membership.getProject());
                    }
                    recyclerViewProjectAdapter = new RecyclerViewProjectAdapter(getContext(), membershipArrayList);
                    recyclerView.setAdapter(recyclerViewProjectAdapter);
                }
            }
        });
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewProject);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

}
