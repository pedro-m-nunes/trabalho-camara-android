package br.ifsul.quatroi.camaracamera2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.ifsul.quatroi.camaracamera2.auxiliar.Authentication;
import br.ifsul.quatroi.camaracamera2.auxiliar.BottomNavigationMenu;
import br.ifsul.quatroi.camaracamera2.auxiliar.exceptions.BottomNavigationMenuException;

public class ConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        // nav
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation_menu);
        bottomNavigation.setSelectedItemId(R.id.nav_config);
        bottomNavigation.setOnItemSelectedListener(item -> BottomNavigationMenu.listener(this, item));

        // logout
        Button logout = findViewById(R.id.button_logout);
        logout.setOnClickListener(view -> Authentication.logout(this));

    }
}