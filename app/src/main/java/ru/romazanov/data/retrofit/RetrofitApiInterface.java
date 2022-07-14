package ru.romazanov.data.retrofit;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import ru.romazanov.data.model.character.CharacterAnswer;
import ru.romazanov.data.model.episode.EpisodeAnswer;
import ru.romazanov.data.model.location.LocationAnswer;

public interface RetrofitApiInterface {

    @GET("character")
    Call<CharacterAnswer> getCharacterList(@QueryMap Map<String, String> option);

    @GET("episode")
    Call<EpisodeAnswer> getEpisodeList(@QueryMap Map<String, String> option);

    @GET("location")
    Call<LocationAnswer> getLocationList(@QueryMap Map<String, String> option);

}
