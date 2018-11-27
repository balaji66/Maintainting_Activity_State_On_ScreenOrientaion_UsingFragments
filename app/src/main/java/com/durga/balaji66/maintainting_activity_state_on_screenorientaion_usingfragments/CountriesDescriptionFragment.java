package com.durga.balaji66.maintainting_activity_state_on_screenorientaion_usingfragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountriesDescriptionFragment extends Fragment {
    View view;
    private TextView mCountryDescription;
    private String countryName;
    private String countryDescription;
    public CountriesDescriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_countries_description, container, false);
        mCountryDescription =view.findViewById(R.id.countryDescription);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null){
            countryName = savedInstanceState.getString("selectedCountry",countryName);
            countryDescription = getString(getStringId(countryName));
            Log.i("Description","countryName save Instance state");
        }else {
            Bundle bundle = getArguments();
            countryName = bundle.getString(FragmentActionListener.KEY_SELECTED_COUNTRY);
            countryDescription = getString(getStringId(countryName));
            Log.i("Description","countryName bundle");
        }    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        //super.onSaveInstanceState(outState);
        outState.putString("selectedCountry",countryName);
        Log.i("countryName",countryName);
    }


    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(countryName);
        mCountryDescription.setText(countryDescription);
    }

    public int getStringId(String countryName)
    {
        switch (countryName) {
            case "India":
                return R.string.India;
            case "China":
                return R.string.China;
            case "Pakistan":
                return R.string.Pakistan;
            case "Nepal":
                return R.string.Nepal;
            case "Bangladesh":
                return R.string.Bangladesh;
            case "Iran":
                return R.string.Iran;
            case "Australia":
                return R.string.Australia;
            case "Ireland":
                return R.string.Ireland;
            case "SriLanka":
                return R.string.SriLanka;
            case "Korea":
                return R.string.Korea;
            case "US":
                return R.string.US;
                default:
        }
        return R.string.India;
    }
}
