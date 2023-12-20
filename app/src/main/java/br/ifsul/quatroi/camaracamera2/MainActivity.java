package br.ifsul.quatroi.camaracamera2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import br.ifsul.quatroi.camaracamera2.auxiliar.BottomNavigationMenu;
import br.ifsul.quatroi.camaracamera2.auxiliar.api.APICaller;
import br.ifsul.quatroi.camaracamera2.auxiliar.api.CallbackData;
import br.ifsul.quatroi.camaracamera2.auxiliar.api.models.Partido;
import br.ifsul.quatroi.camaracamera2.auxiliar.exceptions.BottomNavigationMenuException;

public class MainActivity extends AppCompatActivity { // Home/Partidos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // if not logged in
//        startActivity(new Intent(getApplicationContext(), StartActivity.class));
//        finish();
        // else

        // list
        APICaller apiCaller = new APICaller();
        ArrayAdapter<Partido> partidosAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);

        apiCaller.getAllPartidos(new CallbackData<>() {
            @Override
            public void onSuccess(List<Partido> data) {
                partidosAdapter.clear();
                partidosAdapter.addAll(data);
            }

            @Override
            public void onUnsuccess(String message) {
                Log.e(message, "Deu pala"); // ""?
            }

            @Override
            public void onFailure(String message) {
                Log.e(message, "Deu pala"); // ""?
            }
        });

        ListView partidos = findViewById(R.id.list_partidos);
        partidos.setAdapter(partidosAdapter);

        // nav
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation_menu);
        bottomNavigation.setSelectedItemId(R.id.nav_partidos);
        bottomNavigation.setOnItemSelectedListener(item -> {
            try {
                Class<?> activity = BottomNavigationMenu.onItemSelected(item);
                startActivity(new Intent(getApplicationContext(), activity));
                finish();
                return true;
            } catch (BottomNavigationMenuException e) {
                return false;
            }
        });

    }
}