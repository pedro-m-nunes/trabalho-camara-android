package br.ifsul.quatroi.camaracamera2.auxiliar;

import android.view.MenuItem;

import br.ifsul.quatroi.camaracamera2.ConfigActivity;
import br.ifsul.quatroi.camaracamera2.MainActivity;
import br.ifsul.quatroi.camaracamera2.R;
import br.ifsul.quatroi.camaracamera2.auxiliar.exceptions.BottomNavigationMenuException;

public class BottomNavigationMenu {

    public static Class<?> onItemSelected(MenuItem item) throws BottomNavigationMenuException {
        int id = item.getItemId();

        if(id == R.id.nav_partidos)
            return MainActivity.class;
        else if(id == R.id.nav_config)
            return ConfigActivity.class;
        else
            throw new BottomNavigationMenuException("Strange id");
    }

}
