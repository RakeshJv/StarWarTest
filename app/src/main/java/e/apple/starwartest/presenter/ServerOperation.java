package e.apple.starwartest.presenter;

import android.util.Log;
import android.widget.Toast;

import e.apple.starwartest.activity.MainActivity;
import e.apple.starwartest.model.Responce;
import e.apple.starwartest.network.ApiService;
import retrofit2.Callback;

public class ServerOperation implements IserverOperation {

    CharacterListView characterListView;
    public ServerOperation(CharacterListView characterListView)
    {
        this.characterListView = characterListView;
    }

    @Override
    public void loadData()
    {

        characterListView.showProgressBar();
            new ApiService().getCharacterList().enqueue(new Callback<Responce>() {
            @Override
            public void onResponse(retrofit2.Call<Responce> call, retrofit2.Response<Responce> response) {
                if (response.isSuccessful())
                    try {
                        Responce severResponce = response.body();
                        //Log.d("", "Success--->" + severResponce.getResults().size());

                        characterListView.loadData(severResponce);
                        characterListView.hideProgressBar();

                    } catch (
                            Exception e)
                    {
                        e.printStackTrace();
                        characterListView.hideProgressBar();
                        characterListView.setError(e);
                        Log.d("", "Exception --> " + e.getMessage());
                    }
            }

            @Override
            public void onFailure(retrofit2.Call<Responce> call, Throwable t) {
                characterListView.hideProgressBar();
                characterListView.setThrowableError(t);
                Log.d("TAG", "Exception-->" + t.getMessage());
            }

        });
 }
}
