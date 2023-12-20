package br.ifsul.quatroi.camaracamera2.auxiliar.api.models;

import com.google.gson.annotations.Expose;

public class APIResponse<T> {

    @Expose
    private T dados;

    public APIResponse(T dados) {
        this.dados = dados;
    }

    public T getDados() {
        return dados;
    }

    public void setDados(T dados) {
        this.dados = dados;
    }

    @Override
    public String toString() {
        return "APIResponse{" +
                "dados=" + dados +
                '}';
    }

}
