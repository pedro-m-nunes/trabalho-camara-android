package br.ifsul.quatroi.camaracamera2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.ifsul.quatroi.camaracamera2.auxiliar.BottomNavigationMenu;
import br.ifsul.quatroi.camaracamera2.auxiliar.IntentExtraNames;
import br.ifsul.quatroi.camaracamera2.auxiliar.Toaster;
import br.ifsul.quatroi.camaracamera2.auxiliar.api.APICaller;
import br.ifsul.quatroi.camaracamera2.auxiliar.api.CallbackData;
import br.ifsul.quatroi.camaracamera2.auxiliar.api.models.Partido;

public class PartidoPerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partido_perfil);

        // nav
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation_menu);
        bottomNavigation.setOnItemSelectedListener(item -> BottomNavigationMenu.listener(this, item));

        // appbar
        MaterialToolbar appbar = findViewById(R.id.appbar_partido);

        TextView nome = findViewById(R.id.partido_nome);
        TextView sigla = findViewById(R.id.partido_sigla);

        // api
        final int partidoId = getIntent().getIntExtra(IntentExtraNames.PARTIDO_ID, 0);
        Partido partido = new Partido();

        APICaller apiCaller = new APICaller();
        apiCaller.getPartido(partidoId, new CallbackData<>() {
            @Override
            public void onSuccess(Partido data) {
                partido.set(data);
                appbar.setTitle(partido.toString());
                nome.setText("Nome: " + partido.getNome());
                sigla.setText("Sigla: " + partido.getSigla());
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

        Button deputados = findViewById(R.id.button_deputados_do_partido);
        deputados.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PartidoDeputadosActivity.class);
            intent.putExtra(IntentExtraNames.PARTIDO_ID, partidoId);
            intent.putExtra(IntentExtraNames.PARTIDO_SIGLA, partido.getSigla());
            startActivity(intent);
            finish();
        });

    }
}