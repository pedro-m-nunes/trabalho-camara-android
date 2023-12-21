package br.ifsul.quatroi.camaracamera2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import br.ifsul.quatroi.camaracamera2.auxiliar.BottomNavigationMenu;
import br.ifsul.quatroi.camaracamera2.auxiliar.IntentExtraNames;
import br.ifsul.quatroi.camaracamera2.auxiliar.Toaster;
import br.ifsul.quatroi.camaracamera2.auxiliar.api.APICaller;
import br.ifsul.quatroi.camaracamera2.auxiliar.api.CallbackData;
import br.ifsul.quatroi.camaracamera2.auxiliar.api.models.Deputado;
import br.ifsul.quatroi.camaracamera2.auxiliar.api.models.Partido;

public class PartidoDeputadosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partido_deputados);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation_menu);
        bottomNavigation.setOnItemSelectedListener(item -> BottomNavigationMenu.listener(this, item));

        MaterialToolbar appbar = findViewById(R.id.appbar_deputados_do_partido);
        final String partidoSigla = getIntent().getStringExtra(IntentExtraNames.PARTIDO_SIGLA);
        appbar.setTitle("Deputados(as) do " + partidoSigla);

        ListView deputados = findViewById(R.id.list_deputados_partido);
        ArrayAdapter<Deputado> deputadosAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        deputados.setAdapter(deputadosAdapter);

        final int partidoId = getIntent().getIntExtra(IntentExtraNames.PARTIDO_ID, 0);

        deputados.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(getApplicationContext(), DeputadoPerfilActivity.class);
            intent.putExtra(IntentExtraNames.DEPUTADO_ID, deputadosAdapter.getItem(i).getId());
            intent.putExtra(IntentExtraNames.PARTIDO_ID, partidoId);
            intent.putExtra(IntentExtraNames.PARTIDO_SIGLA, partidoSigla);
            startActivity(intent);
            finish();
        });

        APICaller apiCaller = new APICaller();
        apiCaller.getDeputadosByPartido(partidoId, new CallbackData<>() {
            @Override
            public void onSuccess(List<Deputado> data) {
                deputadosAdapter.clear();
                deputadosAdapter.addAll(data);
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