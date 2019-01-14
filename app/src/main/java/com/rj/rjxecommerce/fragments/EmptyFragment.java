package com.rj.rjxecommerce.fragments;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rj.rjxecommerce.R;


public class EmptyFragment extends Fragment {

    public EmptyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_comming_soon, container, false);

        // Inflate the layout for this fragment
        return rootView;
    }



    @Override
    public void onDetach() {
        super.onDetach();
    }
}
