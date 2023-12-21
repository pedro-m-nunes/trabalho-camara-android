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
import br.ifsul.quatroi.camaracamera2.auxiliar.api.models.Deputado;

public class DeputadoPerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deputado_perfil);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation_menu);
        bottomNavigation.setOnItemSelectedListener(item -> BottomNavigationMenu.listener(this, item));

        MaterialToolbar appbar = findViewById(R.id.appbar_deputado);

        TextView nome = findViewById(R.id.deputado_nome);
        TextView siglaPartido = findViewById(R.id.deputado_sigla_partido);
        TextView siglaUf = findViewById(R.id.deputado_sigla_uf);
        TextView email = findViewById(R.id.deputado_email);

        final int deputadoId = getIntent().getIntExtra(IntentExtraNames.DEPUTADO_ID, 0);
        Deputado deputado = new Deputado();

        APICaller apiCaller = new APICaller();
        apiCaller.getDeputado(deputadoId, new CallbackData<>() {
            @Override
            public void onSuccess(Deputado data) {
                deputado.set(data);
                appbar.setTitle(deputado.getUltimoStatus().getNome());
                nome.setText("Nome: " + deputado.getUltimoStatus().getNome());
                siglaPartido.setText("Partido: " + deputado.getUltimoStatus().getSiglaPartido());
                siglaUf.setText("UF: " + deputado.getUltimoStatus().getSiglaUf());
                email.setText("E-mail: " + deputado.getUltimoStatus().getEmail());
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

        final int partidoId = getIntent().getIntExtra(IntentExtraNames.PARTIDO_ID, 0);
        final String partidoSigla = getIntent().getStringExtra(IntentExtraNames.PARTIDO_SIGLA);

        Button voltar = findViewById(R.id.button_voltar);
        voltar.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PartidoDeputadosActivity.class);
            intent.putExtra(IntentExtraNames.PARTIDO_ID, partidoId);
            intent.putExtra(IntentExtraNames.PARTIDO_SIGLA, partidoSigla);
            startActivity(intent);
            finish();
        });

    }
}