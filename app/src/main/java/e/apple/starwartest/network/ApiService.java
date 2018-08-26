package e.apple.starwartest.network;
import com.google.gson.Gson;
import e.apple.starwartest.model.Responce;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService implements APIInterface {

    public static String  URL = "https://swapi.co/api/";
    APIInterface apiInterface;
    public ApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create(new Gson())).build();
            apiInterface = retrofit.create(APIInterface.class);
        this.apiInterface = retrofit.create(APIInterface.class);
    }
    @Override
    public retrofit2.Call<Responce> getCharacterList(){
        return apiInterface.getCharacterList();
    }
}