package haconglinh1990.redmineandroid.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.models.Issue;
import haconglinh1990.redmineandroid.network.api.APIClient;
import haconglinh1990.redmineandroid.R;
import haconglinh1990.redmineandroid.adapters.RecyclerViewMyTaskAdapter;
import haconglinh1990.redmineandroid.network.response.IssueCallBack;
import haconglinh1990.redmineandroid.ultils.Remember;


public class MyTaskFragment extends Fragment {
    private static final String MY_TAG = "message_from_meomeo";
    public static RecyclerViewMyTaskAdapter recyclerViewMyTaskAdapter;
    RecyclerView recyclerView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d(MY_TAG, "onCreated My Task Fragment before call API");
        new APIClient(getContext()).getIssues(APIClient.API_ALL_ISSUSE, new IssueCallBack() {
            @Override
            public void onCompleted(int statusCode, ArrayList<Issue> issueArrayList) {
                if (issueArrayList == null) {
                    Log.d(MY_TAG, "issueArrayList in MyTaskFragment is null, fuck off");
                } else {
                    Log.d(MY_TAG, "onCreate MyTaskFragment after call API, before put to adapter");
                    recyclerViewMyTaskAdapter = new RecyclerViewMyTaskAdapter(getContext(), issueArrayList);
                }
            }
        });
        // Inflate the layout for this fragment
        Log.d(MY_TAG, "onCreateView Project Fragment");
        View view = inflater.inflate(R.layout.project_layout, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewProject);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(recyclerViewMyTaskAdapter);
        return view;
    }
}
