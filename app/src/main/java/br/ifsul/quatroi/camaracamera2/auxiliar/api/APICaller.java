package br.ifsul.quatroi.camaracamera2.auxiliar.api;

import java.util.List;

import br.ifsul.quatroi.camaracamera2.auxiliar.api.models.APIResponse;
import br.ifsul.quatroi.camaracamera2.auxiliar.api.models.Deputado;
import br.ifsul.quatroi.camaracamera2.auxiliar.api.models.Partido;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APICaller {

    private Retrofit retrofit;

    private APIService apiService;

    public APICaller() {
        retrofit = new Retrofit.Builder()
                .baseUrl(APIService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(APIService.class);
    }

    private <T> void enqueueCall(Call<APIResponse<T>> call, CallbackData<T> callbackData) {
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<APIResponse<T>> call, Response<APIResponse<T>> response) {
                if(response.isSuccessful())
                    callbackData.onSuccess(response.body().getDados());
                else
                    callbackData.onUnsuccess("Unsuccessful request");
            }

            @Override
            public void onFailure(Call<APIResponse<T>> call, Throwable t) {
                callbackData.onFailure("Failed request");
            }
        });
    }

    public void getAllPartidos(CallbackData<List<Partido>> callbackData) {
        Call<APIResponse<List<Partido>>> call = apiService.getAllPartidos();
        enqueueCall(call, callbackData);
    }

    public void getPartido(int id, CallbackData<Partido> callbackData) {
        Call<APIResponse<Partido>> call = apiService.getPartido(id);
        enqueueCall(call, callbackData);
    }

    public void getDeputadosByPartido(int partidoId, CallbackData<List<Deputado>> callbackData) {
        Call<APIResponse<List<Deputado>>> call = apiService.getDeputadosByPartido(partidoId);
        enqueueCall(call, callbackData);
    }

    public void getDeputado(int id, CallbackData<Deputado> callbackData) {
        Call<APIResponse<Deputado>> call = apiService.getDeputado(id);
        enqueueCall(call, callbackData);
    }

}
