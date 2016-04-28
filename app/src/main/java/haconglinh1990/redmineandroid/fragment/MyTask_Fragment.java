package haconglinh1990.redmineandroid.fragment;

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

import haconglinh1990.redmineandroid.API.APIClient;
import haconglinh1990.redmineandroid.R;
import haconglinh1990.redmineandroid.adapter.RecyclerViewMyTaskAdapter;
import haconglinh1990.redmineandroid.model.Issue;


public class MyTask_Fragment extends Fragment {
    private static final String MY_TAG = "message_from_meomeo";
    RecyclerView recyclerView;
    public static ArrayList<Issue> issuesList;
    public static RecyclerViewMyTaskAdapter recyclerViewMyTaskAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(MY_TAG, "onCreated My Task Fragment before call API");
        issuesList = new APIClient(getContext()).getIssuesofCurentUser();
        Log.d(MY_TAG, "onCreated My Task Fragment after call API, before put to adapter");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(MY_TAG, "onCreateView Project Fragment");
        View view = inflater.inflate(R.layout.project_layout, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewProject);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewMyTaskAdapter = new RecyclerViewMyTaskAdapter(getContext(), issuesList);
        recyclerView.setAdapter(recyclerViewMyTaskAdapter);
        return view;
    }
}
