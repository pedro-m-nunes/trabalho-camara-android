package br.ifsul.quatroi.camaracamera2.auxiliar.api;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.ifsul.quatroi.camaracamera2.auxiliar.api.models.APIResponse;
import br.ifsul.quatroi.camaracamera2.auxiliar.api.models.Partido;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIDealer {

    private Retrofit retrofit;

    private APICallService apiCallService;

    public APIDealer() {
        retrofit = new Retrofit.Builder()
                .baseUrl(APICallService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiCallService = retrofit.create(APICallService.class);
    }

    public Partido getPartido(int id) { // funcionando
        final Partido[] partido = new Partido[1];

        Call<APIResponse<Partido>> call = apiCallService.getPartido(id);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<APIResponse<Partido>> call, Response<APIResponse<Partido>> response) {
                if (response.isSuccessful()) {
                    partido[0] = response.body().getDados();
                    Log.d("Successful response", "Partido: " + partido[0]);
                } else
                    Log.d("Unsuccessful response", response.message());

                Log.d("Response", response.toString());
            }

            @Override
            public void onFailure(Call<APIResponse<Partido>> call, Throwable t) {
                Log.d("Response failure", t.getMessage());
            }
        });

        return partido[0];
    }

    public List<Partido> getAllPartidos() { // não funcionando // 400 bad request // arrumar // JSONArray?
        final List<Partido>[] partidos = new ArrayList[1];

        Call<APIResponse<List<Partido>>> call = apiCallService.getAllPartidos();

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<APIResponse<List<Partido>>> call, Response<APIResponse<List<Partido>>> response) {
                if (response.isSuccessful()) {
                    partidos[0] = response.body().getDados();

                    for(Partido partido : partidos[0]) {
                        Log.d("Partido", "Partido: " + partido);
                    }

                    Log.d("Successful response", "Partidos: " + partidos[0]);
                } else
                    Log.d("Unsuccessful response", response.message());

                Log.d("Response", response.toString());
            }

            @Override
            public void onFailure(Call<APIResponse<List<Partido>>> call, Throwable t) {
                Log.d("Response failure", t.getMessage());
            }
        });

        return partidos[0];
    }

}
