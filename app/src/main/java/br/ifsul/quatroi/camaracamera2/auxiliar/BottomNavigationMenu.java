package br.ifsul.quatroi.camaracamera2.auxiliar;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import br.ifsul.quatroi.camaracamera2.ConfigActivity;
import br.ifsul.quatroi.camaracamera2.MainActivity;
import br.ifsul.quatroi.camaracamera2.R;
import br.ifsul.quatroi.camaracamera2.auxiliar.exceptions.BottomNavigationMenuException;

public class BottomNavigationMenu {

    private static Class<?> onItemSelected(MenuItem item) throws BottomNavigationMenuException {
        int id = item.getItemId();

        if(id == R.id.nav_partidos)
            return MainActivity.class;
        else if(id == R.id.nav_config)
            return ConfigActivity.class;
        else
            throw new BottomNavigationMenuException("Strange id");
    }

    public static boolean listener(AppCompatActivity activity, MenuItem item) {
        try {
            Class<?> newActivity = BottomNavigationMenu.onItemSelected(item);
            activity.startActivity(new Intent(activity.getApplicationContext(), newActivity));
            activity.finish();
            return true;
        } catch (BottomNavigationMenuException e) {
            return false;
        }
    }

}
