package e.apple.starwartest.activity;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import e.apple.starwartest.R;
import e.apple.starwartest.adapter.CharacterListAdapter;
import e.apple.starwartest.constant.Constant;
import e.apple.starwartest.customview.CustomDialog;
import e.apple.starwartest.fragment.CharacterProfileFragmnt;
import e.apple.starwartest.interfaces.DialogClickListner;
import e.apple.starwartest.interfaces.ItemClickListener;
import e.apple.starwartest.model.Character;
import e.apple.starwartest.model.Responce;
import e.apple.starwartest.network.ApiService;
import e.apple.starwartest.presenter.CharacterListView;
import e.apple.starwartest.presenter.IserverOperation;
import e.apple.starwartest.presenter.ServerOperation;
import retrofit2.Callback;

public class MainActivity extends BaseActivity implements
        ItemClickListener, DialogClickListner, CharacterListView {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private CustomDialog customDialog;
    private ProgressDialog progress;
    private CharacterListAdapter characterListAdapter;
    private RecyclerView recyclerView;
    private List<Character> characterList;
    IserverOperation iServerOperation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Character Name");
        init();
    }

    void init() {
        customDialog = new CustomDialog(this, this);
        iServerOperation = new ServerOperation(this);
        iServerOperation.loadData();
        recyclerView = (RecyclerView) findViewById(R.id.characterList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);


    }


    void setDataInFragment(List<Character> list) {
        characterList = list;
        characterListAdapter = new CharacterListAdapter(list, R.layout.character_list);
        characterListAdapter.setClickListener(this);
        recyclerView.setAdapter(characterListAdapter);

    }

    @Override
    public void onButtonClick(String clickType) {
        if (clickType.equalsIgnoreCase(Constant.YES)) {
            iServerOperation.loadData();
            customDialog.dismiss();

        } else {

            customDialog.dismiss();
            return;
        }

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
            recyclerView.setVisibility(View.VISIBLE);
            backArrowInvisible(false);
            setTitle("Character Name");
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


    @Override
    public void hideProgressBar() {
        progress.dismiss();
    }

    @Override
    public void setError(Exception e) {
        customDialog.show();
    }

    @Override
    public void setThrowableError(Throwable t) {
        customDialog.show();
    }

    @Override
    public void loadData(Responce responce) {
        characterList = responce.getResults();
        if (characterList.isEmpty()) {
            Toast.makeText(MainActivity.this, "Data Not Available", Toast.LENGTH_LONG).show();

        } else {
            setDataInFragment(characterList);

        }

    }

    @Override
    public void showProgressBar() {
        progress = new ProgressDialog(this);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.setMessage("Downloading  data please wait...");
        progress.show();
    }


    @Override
    public void onClick(View view, int position) {
        recyclerView.setVisibility(View.GONE);
        backArrowInvisible(true);
        setTitle("Character Detail");
        Character character = characterList.get(position);
        CharacterProfileFragmnt characterFragment = CharacterProfileFragmnt.newInstance(character);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.frames, characterFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public CustomDialog getCustomDialog() {
        return customDialog;
    }
}

