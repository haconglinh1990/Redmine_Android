package haconglinh1990.redmineandroid.View.Main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import haconglinh1990.redmineandroid.R;


public class FilterFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //getActivity().getActionBar().setTitle("Filter");
        // ((MainActivity) getActivity()).setActionBarTitle("Filter");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }


}
