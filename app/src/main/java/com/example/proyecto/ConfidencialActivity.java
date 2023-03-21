package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.proyecto.fragment.FingerFragment;
import com.example.proyecto.fragment.ProfileFragment;
import com.example.proyecto.modeloCrud.CrudFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ConfidencialActivity extends AppCompatActivity {

    BottomNavigationView botton_navegation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confidencial);

        //navegacion
        botton_navegation = (BottomNavigationView) findViewById(R.id.botton_navegation);
        botton_navegation.setOnItemSelectedListener(item -> {
            menuItemNavigation(item);
            return true;
        });

        replaceFragment(new fragment_confidencial());

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.commit();
    }

    private void menuItemNavigation(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                replaceFragment(new fragment_confidencial());
                break;
            case R.id.profile:
                replaceFragment(new CrudFragment());
                break;
            case R.id.person:
                replaceFragment(new item_list_pokemon());
                break;
            default:
                break;
        }

    }

}