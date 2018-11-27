package com.durga.balaji66.maintainting_activity_state_on_screenorientaion_usingfragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class Countries_fragment extends Fragment {

    View rootView;
    FragmentActionListener fragmentActionListener;

    public Countries_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).setTitle("hai");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =inflater.inflate(R.layout.fragment_countries_fragment, container, false);
        init();
        return rootView;
    }

    private void init() {
        Context context = getActivity();
        final String[] countries = getResources().getStringArray(R.array.countriesNames);
        ListView mListView = rootView.findViewById(R.id.listView);
        ArrayAdapter<String> mCountriesNameAdapter = new ArrayAdapter<String>(Objects.requireNonNull(context), android.R.layout.simple_list_item_1, countries);
        mListView.setAdapter(mCountriesNameAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(fragmentActionListener!=null)
                {
                    fragmentActionListener.onActionPerformed(countries[position]);
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("OrintationChange","CountriesFragment onSaveInstanceState");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null){
            fragmentActionListener = (MainActivity)getActivity();
        }
    }

    public void setOnCountrySelected(FragmentActionListener fragmentActionListener)
    {
        this.fragmentActionListener =fragmentActionListener;
    }
}
