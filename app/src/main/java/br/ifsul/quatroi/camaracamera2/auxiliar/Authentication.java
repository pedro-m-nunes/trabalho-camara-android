package br.ifsul.quatroi.camaracamera2.auxiliar;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import br.ifsul.quatroi.camaracamera2.LoginActivity;
import br.ifsul.quatroi.camaracamera2.MainActivity;
import br.ifsul.quatroi.camaracamera2.StartActivity;
import br.ifsul.quatroi.camaracamera2.auxiliar.exceptions.AuthenticationException;

public class Authentication {

    public static void login(AppCompatActivity originActivity, String email, String password) {
        Context context = originActivity.getApplicationContext();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        Log.i("USER", FirebaseAuth.getInstance().getCurrentUser().toString());
                        originActivity.startActivity(new Intent(context, MainActivity.class));
                        originActivity.finish();
                    } else {
                        Exception exception = task.getException();
                        Toaster.longToast(context, exception.getMessage());
                    }
                });
    }

    public static void logout(AppCompatActivity originActivity) {
        FirebaseAuth.getInstance().signOut();
        originActivity.startActivity(new Intent(originActivity.getApplicationContext(), StartActivity.class));
        originActivity.finish();
    }

    public static boolean checkIfLoggedInAndRedirectToStartIfNot(AppCompatActivity originActivity) {
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            originActivity.startActivity(new Intent(originActivity.getApplicationContext(), StartActivity.class));
            originActivity.finish();
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkIfAlreadyLoggedInAndRedirectToContentIfTrue(AppCompatActivity originActivity) {
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            return false;
        } else {
            originActivity.startActivity(new Intent(originActivity.getApplicationContext(), MainActivity.class));
            originActivity.finish();
            return true;
        }
    }

    public static void register(AppCompatActivity originActivity, String email, String password, String passwordConfirmation) throws AuthenticationException {
        if(!password.equals(passwordConfirmation))
            throw new AuthenticationException("A senha não pôde ser confirmada");
        else {
            Context context = originActivity.getApplicationContext();

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()) {
                            Toaster.shortToast(context, "Usuário cadastrado");

                            FirebaseAuth.getInstance().signOut();

                            Intent intent = new Intent(context, LoginActivity.class);
                            intent.putExtra(IntentExtraNames.USER_EMAIL, email);
                            intent.putExtra(IntentExtraNames.USER_PASSWORD, password);
                            originActivity.startActivity(intent);
                            originActivity.finish();
                        } else {
                            Exception exception = task.getException();
                            Toaster.longToast(context, exception.getMessage());
                        }
                    });
        }
    }

}
