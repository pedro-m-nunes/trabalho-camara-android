package br.ifsul.quatroi.camaracamera2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import br.ifsul.quatroi.camaracamera2.auxiliar.Authentication;
import br.ifsul.quatroi.camaracamera2.auxiliar.InputGetter;
import br.ifsul.quatroi.camaracamera2.auxiliar.IntentExtraNames;
import br.ifsul.quatroi.camaracamera2.auxiliar.Toaster;
import br.ifsul.quatroi.camaracamera2.auxiliar.exceptions.AuthenticationException;
import br.ifsul.quatroi.camaracamera2.auxiliar.exceptions.InputException;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        // email
        TextInputEditText email = findViewById(R.id.input_cadastro_user_identification);

        final String passedEmail = getIntent().getStringExtra(IntentExtraNames.USER_EMAIL);
        if(passedEmail != null)
            email.setText(passedEmail);

        // senha
        TextInputEditText password = findViewById(R.id.input_cadastro_password);

        // confirmar senha
        TextInputEditText passwordConfirmation = findViewById(R.id.input_confirm_password);

        // cadastrar
        Button cadastrar = findViewById(R.id.button_cadastrar);
        cadastrar.setOnClickListener(view -> {
            try {
                String stringEmail = InputGetter.getInput(email);
                String stringPassword = InputGetter.getInput(password);
                String stringPasswordConfirmation = InputGetter.getInput(passwordConfirmation);

                Authentication.register(this, stringEmail, stringPassword, stringPasswordConfirmation);
            } catch (InputException e) {
                Toaster.shortToast(getApplicationContext(), "Informe os dados solicitados");
            } catch (AuthenticationException e) {
                Toaster.shortToast(getApplicationContext(), e.getMessage());
            }
        });

        // login
        Button jaTenhoCadastro = findViewById(R.id.button_ja_tenho_cadastro);
        jaTenhoCadastro.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            try {
                intent.putExtra(IntentExtraNames.USER_EMAIL, InputGetter.getInput(email));
            } catch (InputException e) {}
            startActivity(intent);
            finish();
        });

    }
}