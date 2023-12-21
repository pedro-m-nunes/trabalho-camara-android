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
import br.ifsul.quatroi.camaracamera2.auxiliar.exceptions.InputException;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(!Authentication.checkIfAlreadyLoggedInAndRedirectToContentIfTrue(this)) {
            TextInputEditText email = findViewById(R.id.input_user_identification);

            final String passedEmail = getIntent().getStringExtra(IntentExtraNames.USER_EMAIL);
            if(passedEmail != null)
                email.setText(passedEmail);

            TextInputEditText password = findViewById(R.id.input_password);

            final String registeredPassword = getIntent().getStringExtra(IntentExtraNames.USER_PASSWORD);
            if(registeredPassword != null)
                password.setText(registeredPassword);

            Button logar = findViewById(R.id.button_logar);
            logar.setOnClickListener(view -> {
                try {
                    String stringEmail = InputGetter.getInput(email);
                    String stringSenha = InputGetter.getInput(password);
                    Authentication.login(this, stringEmail, stringSenha);
                } catch(InputException e) {
                    Toaster.shortToast(getApplicationContext(), "Informe seus dados");
                }
            });

            Button naoTenhoLogin = findViewById(R.id.button_nao_tenho_login);
            naoTenhoLogin.setOnClickListener(view -> {
                Intent intent = new Intent(getApplicationContext(), CadastroActivity.class);
                try {
                    intent.putExtra(IntentExtraNames.USER_EMAIL, InputGetter.getInput(email));
                } catch (InputException e) {}
                startActivity(intent);
                finish();
            });
        }

    }

}