package br.ifsul.quatroi.camaracamera2.auxiliar;

import com.google.android.material.textfield.TextInputEditText;

import br.ifsul.quatroi.camaracamera2.auxiliar.exceptions.InputException;

public class InputGetter {

    public static boolean hasInput(TextInputEditText editText) {
        return !editText.getText().toString().isBlank();
    }

    public static String getInput(TextInputEditText editText) throws InputException {
        if(!hasInput(editText))
            throw new InputException("No input");
        else
            return editText.getText().toString();
    }

    // validatePassword(?) // ?

}
