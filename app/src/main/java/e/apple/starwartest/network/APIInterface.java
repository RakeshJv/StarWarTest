package e.apple.starwartest.network;

import e.apple.starwartest.model.Responce;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("people")
    retrofit2.Call<Responce> getCharacterList();
}
