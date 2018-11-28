package com.durga.balaji66.maintainting_activity_state_on_screenorientaion_usingfragments;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements FragmentActionListener{

    FragmentManager fragmentManager;
    String selectedCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.portrait)!= null){
            addCountriesFragment();
        }else if (findViewById(R.id.landScape)!=null){
            addCountriesFragment();
            addCountryDescriptionFragment(R.id.fragmentContainer2,"India");
        }
    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString("SelectedCountry",selectedCountry);
//        Log.i("onSaveInstanceState:", selectedCountry);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        selectedCountry = savedInstanceState.getString("SelectedCountry","India");
//        Log.i("onRestoreInstanceState",selectedCountry);
//    }

    private void addCountriesFragment() {
        fragmentManager = getSupportFragmentManager();
        CountriesFragment countries_fragment = new CountriesFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        countries_fragment.setOnCountrySelected(this);
        transaction.add(R.id.fragmentContainer,countries_fragment,"countries");
        transaction.commit();
    }

    public void addCountryDescriptionFragment(String countryName) {
        fragmentManager =getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        CountriesDescriptionFragment fragment = new CountriesDescriptionFragment();
        Bundle bundle =new Bundle();
        bundle.putString(FragmentActionListener.KEY_SELECTED_COUNTRY,countryName);
        fragment.setArguments(bundle);
        ft.replace(R.id.fragmentContainer,fragment,"countryDescription");
        ft.addToBackStack("country Description");
        ft.commit();
    }
    public void addCountryDescriptionFragment(int fragmentId, String countryName)
    {
        fragmentManager =getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        CountriesDescriptionFragment fragment = new CountriesDescriptionFragment();
        Bundle bundle =new Bundle();
        fragmentManager.popBackStackImmediate();
        bundle.putString(FragmentActionListener.KEY_SELECTED_COUNTRY,countryName);
        fragment.setArguments(bundle);
        ft.replace(fragmentId,fragment,"countryDescription");
        ft.addToBackStack("country Description");
        ft.commit();
    }
    @Override
    public void onActionPerformed(String countryName) {
        selectedCountry = countryName;
        if(findViewById(R.id.landScape)==null){
            addCountryDescriptionFragment(countryName);
        }else {
            addCountryDescriptionFragment(R.id.fragmentContainer2,countryName);
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_main);
        if(newConfig.orientation ==Configuration.ORIENTATION_LANDSCAPE){
            Log.i("OrintationChange", "landscape");
            addCountriesFragment();
            if(selectedCountry==null){
                addCountryDescriptionFragment(R.id.fragmentContainer2,"India");
                Log.i("OrintationChange", "description india default");

            }else {
                addCountryDescriptionFragment(R.id.fragmentContainer2, selectedCountry);
                Log.i("OrintationChange", "description according to selection");

            }

        }else if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            Log.i("OrintationChange", "portrait");
            if(selectedCountry==null){
                addCountriesFragment();
            }else {
                addCountryDescriptionFragment(selectedCountry);
            }
        }

    }
}
