package haconglinh1990.redmineandroid.View.Main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import haconglinh1990.redmineandroid.Model.DataQuery.APIClient;
import haconglinh1990.redmineandroid.Model.DataResponse.ProjectCallBack;
import haconglinh1990.redmineandroid.Model.ObjectModel.Project;
import haconglinh1990.redmineandroid.Presenter.RecyclerViewProjectAdapter;
import haconglinh1990.redmineandroid.R;

public class ProjectFragment extends Fragment {
    private static final String MY_TAG = "message_from_meomeo";
    RecyclerView recyclerView;
    private static RecyclerViewProjectAdapter recyclerViewProjectAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        new APIClient(getContext()).getProjectOfCurrentUser(new ProjectCallBack() {
            @Override
            public void getProjectCompleted(int statusCode, ArrayList<Project> projectArrayList) {
                if (projectArrayList == null) {
                    Log.d(MY_TAG, "membership in ProjectFragment is null, fuck off");
                } else {
                    recyclerViewProjectAdapter = new RecyclerViewProjectAdapter(getContext(), projectArrayList);
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
