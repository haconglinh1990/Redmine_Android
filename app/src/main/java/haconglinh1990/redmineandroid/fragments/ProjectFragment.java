package haconglinh1990.redmineandroid.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.models.Project;
import haconglinh1990.redmineandroid.network.api.APIClient;
import haconglinh1990.redmineandroid.R;
import haconglinh1990.redmineandroid.adapters.RecyclerViewProjectAdapter;
import haconglinh1990.redmineandroid.network.response.ProjectCallBack;
import haconglinh1990.redmineandroid.ultils.ProjectItemClickListener;

public class ProjectFragment extends Fragment {
    private static final String MY_TAG = "message_from_meomeo";

    RecyclerView recyclerView;
    private static RecyclerViewProjectAdapter recyclerViewProjectAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Log.d(MY_TAG, "onActivityCreated Project Fragment before call API");
        new APIClient(getContext()).getProjectofCurentUser(new ProjectCallBack() {
            @Override
            public void onCompleted(int statusCode, ArrayList<Project> projectArrayList) {
                if (projectArrayList == null) {
                    //Log.d(MY_TAG, "projectArrayList in ProjectFragment is null, fuck off");
                } else {
                    recyclerViewProjectAdapter = new RecyclerViewProjectAdapter(getContext(), projectArrayList);
                    recyclerView.setAdapter(recyclerViewProjectAdapter);
                }
            }
        });
        //Log.d(MY_TAG, "onCreateView Project Fragment");
        View view = inflater.inflate(R.layout.project_layout, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewProject);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


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
