package br.ifsul.quatroi.camaracamera2.auxiliar.api;

import java.util.List;

import br.ifsul.quatroi.camaracamera2.auxiliar.api.models.APIResponse;
import br.ifsul.quatroi.camaracamera2.auxiliar.api.models.Partido;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface APIService {

    String BASE_URL = "https://dadosabertos.camara.leg.br/api/v2/";

    @GET("partidos/{id}/")
    Call<APIResponse<Partido>> getPartido(@Path("id") int id);

    @GET("partidos?dataInicio=2023-01-01&dataFim=2023-12-31")
    Call<APIResponse<List<Partido>>> getAllPartidos();

}
