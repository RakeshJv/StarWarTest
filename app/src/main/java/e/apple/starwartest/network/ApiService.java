package e.apple.starwartest.network;
import com.google.gson.Gson;
import e.apple.starwartest.model.Responce;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class ApiService {

    public static String url = "https://swapi.co/api/";
    public static APIInterface apiInterface = null;

    public static APIInterface getAPIInterfaceServeice() {
        if (apiInterface == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(new Gson())).build();
            apiInterface = retrofit.create(APIInterface.class);
        }
        return apiInterface;
    }

    public interface APIInterface {
        @GET("people")
        Call<Responce> getCharacterList();
    }
}