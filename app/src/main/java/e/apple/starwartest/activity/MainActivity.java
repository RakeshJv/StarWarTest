package e.apple.starwartest.activity;

import android.net.Uri;
import android.os.StrictMode;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import e.apple.starwartest.R;
import e.apple.starwartest.constant.Constant;
import e.apple.starwartest.customview.CustomDialog;
import e.apple.starwartest.fragment.CharacterFragment;
import e.apple.starwartest.interfaces.DialogClickListner;
import e.apple.starwartest.model.Character;
import e.apple.starwartest.model.Responce;
import e.apple.starwartest.network.ApiService;
import retrofit2.Callback;

public class MainActivity extends BaseActivity implements CharacterFragment.OnFragmentInteractionListener
        , DialogClickListner {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private CustomDialog customDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customDialog = new CustomDialog(this, this);
        init();
        getDataInRetrofit();
    }

    void init() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
    }


    void setDataInFragment(List<Character> list) {
        CharacterFragment characterFragment = CharacterFragment.newInstance(list);
        transaction.add(R.id.frames, characterFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void getDataInRetrofit() {
        retrofit2.Call<Responce> call = ApiService.getAPIInterfaceServeice().getCharacterList();
        call.enqueue(new Callback<Responce>() {
            @Override
            public void onResponse(retrofit2.Call<Responce> call, retrofit2.Response<Responce> response) {
                if (response.isSuccessful())
                    try {
                        Responce severResponce = response.body();
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                        Log.d("", "Success--->" + severResponce.getResults().size());
                        setDataInFragment(severResponce.getResults());
                    } catch (
                            Exception e) {
                        e.printStackTrace();
                        customDialog.show();
                       // Toast.makeText(MainActivity.this, "Exception--" + e.getMessage(), Toast.LENGTH_LONG).show();
                        Log.d("", "HHH" + e.getMessage());
                    }
            }

            @Override
            public void onFailure(retrofit2.Call<Responce> call, Throwable t)
            {
            //    Toast.makeText(MainActivity.this, "Exception-" + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("TAG", "Exception-->" + t.getMessage());
                customDialog.show();

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onButtonClick(String clickType) {
        if (clickType.equalsIgnoreCase(Constant.YES)) {

            customDialog.dismiss();
            getDataInRetrofit();


        } else {

            customDialog.dismiss();
            return;
        }

    }
}

