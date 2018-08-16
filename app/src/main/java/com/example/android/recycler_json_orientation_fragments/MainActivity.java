package com.example.android.recycler_json_orientation_fragments;


import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null)
        {
            fragment = (RecyclerFragment)getSupportFragmentManager().findFragmentByTag("MyFragment1");
        }
        else {
            fragment = new RecyclerFragment();
        }
        if (!fragment.isInLayout()) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.mainActivity, fragment, "MyFragment1");
            fragmentTransaction.commit();
        }
        /*fragment = new RecyclerFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainActivity,fragment);
        fragmentTransaction.commit();*/
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().popBackStack();
    }

    /*@Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        getSupportFragmentManager().putFragment(outState,"MyFragment",fragment);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        fragment = getSupportFragmentManager().getFragment(savedInstanceState,"MyFragment");
    }*/
}