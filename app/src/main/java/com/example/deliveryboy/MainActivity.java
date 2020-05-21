package com.example.deliveryboy;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(this);
        loadFragment(new HomeFragment());
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
    }

    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentcontainer, fragment).addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int op = 0;

        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_order:
                fragment = new OrderFragment();
                break;
            case R.id.nav_request:
                fragment = new RequestFragment();
                break;

            case R.id.nav_acc:
                if (getloginprefference() == true) {
                    fragment = new ProfileFragment();
                    break;
                }
                //else {

                  //  Intent login = new Intent(MainActivity.this, Login.class);
                   // startActivity(login);
               // }
        }

        if (op != 1)
            return loadFragment(fragment);
        return false;
    }

    private boolean getloginprefference() {

        loginPreferences = getSharedPreferences("loginPref", MODE_PRIVATE);

        loginPrefsEditor = loginPreferences.edit();
        String username = loginPreferences.getString("username", "");
        String pass = loginPreferences.getString("password", "");
        if (username != null || pass != null) {
            return true;
        } else {
            return false;
        }

    }
}

