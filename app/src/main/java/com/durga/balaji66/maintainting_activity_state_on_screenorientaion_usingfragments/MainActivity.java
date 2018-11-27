package com.durga.balaji66.maintainting_activity_state_on_screenorientaion_usingfragments;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentActionListener{

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addCountriesFragment();
    }

    private void addCountriesFragment() {
        fragmentManager = getSupportFragmentManager();
        Countries_fragment countries_fragment = new Countries_fragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        countries_fragment.setOnCountrySelected(this);
        transaction.add(R.id.fragmentContainer,countries_fragment,"countries");
        transaction.commit();
    }

    public void addCountryDescriptionFragment(String countryName)
    {
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
    @Override
    public void onCountrySelected(String country) {
        addCountryDescriptionFragment(country);
        Toast.makeText(getApplicationContext(),"Trigger other fragment",Toast.LENGTH_LONG).show();
    }
}
