package br.ifsul.quatroi.camaracamera2.auxiliar.api;

public interface CallbackData<T> {

    void onSuccess(T data);

    void onUnsuccess(String message);

    void onFailure(String message);

}
