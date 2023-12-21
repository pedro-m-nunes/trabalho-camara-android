package br.ifsul.quatroi.camaracamera2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import br.ifsul.quatroi.camaracamera2.auxiliar.Authentication;
import br.ifsul.quatroi.camaracamera2.auxiliar.BottomNavigationMenu;
import br.ifsul.quatroi.camaracamera2.auxiliar.IntentExtraNames;
import br.ifsul.quatroi.camaracamera2.auxiliar.Toaster;
import br.ifsul.quatroi.camaracamera2.auxiliar.api.APICaller;
import br.ifsul.quatroi.camaracamera2.auxiliar.api.CallbackData;
import br.ifsul.quatroi.camaracamera2.auxiliar.api.models.Partido;

public class MainActivity extends AppCompatActivity { // Home/Partidos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Authentication.checkIfLoggedInAndRedirectToStartIfNot(this)) {
            BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation_menu);
            bottomNavigation.setSelectedItemId(R.id.nav_partidos);
            bottomNavigation.setOnItemSelectedListener(item -> BottomNavigationMenu.listener(this, item));

            ListView partidos = findViewById(R.id.list_partidos);
            ArrayAdapter<Partido> partidosAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
            partidos.setAdapter(partidosAdapter);

            partidos.setOnItemClickListener((adapterView, view, i, l) -> {
                Intent intent = new Intent(getApplicationContext(), PartidoPerfilActivity.class);
                intent.putExtra(IntentExtraNames.PARTIDO_ID, partidosAdapter.getItem(i).getId());
                startActivity(intent);
                finish();
            });

            APICaller apiCaller = new APICaller();
            apiCaller.getAllPartidos(new CallbackData<>() {
                @Override
                public void onSuccess(List<Partido> data) {
                    partidosAdapter.clear();
                    partidosAdapter.addAll(data);
                }

                @Override
                public void onUnsuccess(String message) {
                    Toaster.shortToast(getApplicationContext(), message);
                }

                @Override
                public void onFailure(String message) {
                    Toaster.shortToast(getApplicationContext(), message);
                }
            });
        }

    }
}