package br.ifsul.quatroi.camaracamera2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Arrays;

import br.ifsul.quatroi.camaracamera2.auxiliar.BottomNavigationMenu;
import br.ifsul.quatroi.camaracamera2.auxiliar.api.APIDealer;
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

        // list
        ListView partidos = findViewById(R.id.list_partidos);
        // String temp
        ArrayAdapter<String> partidosAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        partidos.setAdapter(partidosAdapter);

        partidosAdapter.addAll(Arrays.asList("SHREK", // placeholder
                "Once upon a time there was a lovely",
                "princess. But she had an enchantment",
                "upon her of a fearful sort which could",
                "only be broken by love's first kiss.",
                "She was locked away in a castle guarded",
                "by a terrible fire-breathing dragon.",
                "Many brave knights had attempted to",
                "free her from this dreadful prison,",
                "but non prevailed. She waited in the",
                "dragon's keep in the highest room of",
                "the tallest tower for her true love",
                "and true love's first kiss. (laughs)",
                "Like that's ever gonna happen. What",
                "a load of - (toilet flush)",
                "Allstar - by Smashmouth begins to play. Shrek goes about his",
                "day. While in a nearby town, the villagers get together to go",
                "after the ogre."));

        APIDealer apiDealer = new APIDealer(); // temp
//        apiDealer.getPartido(36898);
        apiDealer.getAllPartidos();

    }
}