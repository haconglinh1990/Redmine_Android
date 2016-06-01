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

import haconglinh1990.redmineandroid.models.Issue;
import haconglinh1990.redmineandroid.models.Role;
import haconglinh1990.redmineandroid.network.api.APIClient;
import haconglinh1990.redmineandroid.R;
import haconglinh1990.redmineandroid.adapters.RecyclerViewIssueAdapter;
import haconglinh1990.redmineandroid.network.response.IssuesCallBack;
import haconglinh1990.redmineandroid.network.response.RolesCallBack;


public class MyTaskFragment extends Fragment {
    private static final String MY_TAG = "message_from_meomeo";
    public static RecyclerViewIssueAdapter recyclerViewIssueAdapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(MY_TAG, "onCreated My Task Fragment before call API");

        new APIClient(getContext()).getRoles(new RolesCallBack() {
            @Override
            public void onCompleted(int statusCode, ArrayList<Role> roleArrayList) {

            }
        });

        new APIClient(getContext()).getIssues(APIClient.API_ISSUES_BY_CURENT_USER, new IssuesCallBack() {
            @Override
            public void onCompleted(int statusCode, ArrayList<Issue> issueArrayList) {
                if (issueArrayList == null) {
                    Log.d(MY_TAG, "issueArrayList in MyTaskFragment is null, fuck off");
                } else {
                    Log.d(MY_TAG, "onCreate MyTaskFragment after call API, before put to adapter: " + issueArrayList.size());
                    recyclerViewIssueAdapter = new RecyclerViewIssueAdapter(getContext(), issueArrayList);
                    recyclerView.setAdapter(recyclerViewIssueAdapter);
                }
            }
        });
        View view = inflater.inflate(R.layout.fragment_my_task, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewMyTask);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

}
