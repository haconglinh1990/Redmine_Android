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
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.API.APIClient;
import haconglinh1990.redmineandroid.R;
import haconglinh1990.redmineandroid.adapter.RecyclerViewProjectAdapter;
import haconglinh1990.redmineandroid.model.Project;

public class Project_Fragment extends Fragment {
    private static final String MY_TAG = "message_from_meomeo";

    RecyclerView recyclerView;
    public static ArrayList<Project> projectList;
    public static RecyclerViewProjectAdapter recyclerViewProjectAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(MY_TAG, "onActivityCreated Project Fragment before call API");
        projectList = new APIClient(getContext()).getProjectofCurentUser();
        Log.d(MY_TAG, "onActivityCreated Project Fragment after call API, before put to adapter");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d(MY_TAG, "onCreateView Project Fragment");
        View view = inflater.inflate(R.layout.project_layout, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewProject);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewProjectAdapter = new RecyclerViewProjectAdapter(getContext(), projectList);
        recyclerView.setAdapter(recyclerViewProjectAdapter);

        /*LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);*/

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);


    }

}
