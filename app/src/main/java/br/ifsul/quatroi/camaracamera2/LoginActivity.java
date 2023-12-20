package br.ifsul.quatroi.camaracamera2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import br.ifsul.quatroi.camaracamera2.auxiliar.Authentication;
import br.ifsul.quatroi.camaracamera2.auxiliar.InputGetter;
import br.ifsul.quatroi.camaracamera2.auxiliar.Toaster;
import br.ifsul.quatroi.camaracamera2.auxiliar.exceptions.AuthenticationException;
import br.ifsul.quatroi.camaracamera2.auxiliar.exceptions.InputException;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // email
        TextInputEditText email = findViewById(R.id.input_user_identification);

        // senha
        TextInputEditText password = findViewById(R.id.input_password);

        // logar
        Button logar = findViewById(R.id.button_logar);
        logar.setOnClickListener(view -> {
            try {
                Authentication.login(
                        InputGetter.getInput(email),
                        InputGetter.getInput(password)
                );
            } catch (AuthenticationException e) {
                Toaster.shortToast(getApplicationContext(), e.getMessage()); // temp
                // ...
            } catch (InputException e) {
                Toaster.shortToast(getApplicationContext(), "Informe seus dados");
            }
        });

        // cadastro
        Button naoTenhoLogin = findViewById(R.id.button_nao_tenho_login);
        naoTenhoLogin.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), CadastroActivity.class));
            finish();
        });

    }
}