package e.apple.starwartest.activity;

import android.app.ProgressDialog;
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
import e.apple.starwartest.fragment.CharacterProfileFragmnt;
import e.apple.starwartest.interfaces.DialogClickListner;
import e.apple.starwartest.model.Character;
import e.apple.starwartest.model.Responce;
import e.apple.starwartest.network.ApiService;
import retrofit2.Callback;

public class MainActivity extends BaseActivity implements
        CharacterFragment.OnFragmentInteractionListener, DialogClickListner, CharacterProfileFragmnt.OnFragmentInteractionListener {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private CustomDialog customDialog;
    ProgressDialog progress;

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
        transaction = manager.beginTransaction();
        transaction.add(R.id.frames, characterFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void getDataInRetrofit() {
        showProgressBar();
        retrofit2.Call<Responce> call = ApiService.getAPIInterfaceServeice().getCharacterList();
        call.enqueue(new Callback<Responce>() {
            @Override
            public void onResponse(retrofit2.Call<Responce> call, retrofit2.Response<Responce> response) {
                if (response.isSuccessful())
                    try {
                        Responce severResponce = response.body();
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                        Log.d("", "Success--->" + severResponce.getResults().size());
                        progress.dismiss();
                        setDataInFragment(severResponce.getResults());
                    } catch (
                            Exception e) {
                        e.printStackTrace();
                        progress.dismiss();
                        customDialog.show();
                        Log.d("", "Exception --> " + e.getMessage());
                    }
            }

            @Override
            public void onFailure(retrofit2.Call<Responce> call, Throwable t) {
                //    Toast.makeText(MainActivity.this, "Exception-" + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("TAG", "Exception-->" + t.getMessage());
                progress.dismiss();
                customDialog.show();

            }
        });
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

    @Override
    public void onFragmentInteraction(Character character) {
        backArrowInvisible(true);
        CharacterProfileFragmnt characterFragment = CharacterProfileFragmnt.newInstance(character);
        transaction = manager.beginTransaction();
        transaction.replace(R.id.frames, characterFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public boolean onSupportNavigateUp() {
        Log.d("", "test-->");
        backArrowInvisible(false);
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {

        if (manager.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            backArrowInvisible(false);
            manager.popBackStack();
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }

    void backArrowInvisible(boolean flag) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(flag);
        getSupportActionBar().setDisplayShowHomeEnabled(flag);
    }

    public void showProgressBar() {
        progress = new ProgressDialog(this);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.setMessage("Downloading  data please wait...");
        progress.show();
    }
}

