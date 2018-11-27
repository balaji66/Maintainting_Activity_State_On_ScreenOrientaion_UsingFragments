package com.durga.balaji66.maintainting_activity_state_on_screenorientaion_usingfragments;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentActionListener{

    FragmentManager fragmentManager;
    String selectedCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.portrait)!= null){
            if(savedInstanceState==null){
                addCountriesFragment();
            }
            else {
                addCountryDescriptionFragment(savedInstanceState.getString("selectedCountry","India"));
            }
        }else if (findViewById(R.id.landScape)!=null){
            addCountriesFragment();
            if(savedInstanceState==null){
                addCountryDescriptionFragment(R.id.fragmentContainer2,savedInstanceState.getString("selectedCountry","India"));
            }
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("SelectedCountry",selectedCountry);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        selectedCountry = savedInstanceState.getString("SelectedCountry");
    }

    private void addCountriesFragment() {
        fragmentManager = getSupportFragmentManager();
        Countries_fragment countries_fragment = new Countries_fragment();
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
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i("hai hello", "landscape");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.i("hai hello", "portrait");
        }
    }
}
