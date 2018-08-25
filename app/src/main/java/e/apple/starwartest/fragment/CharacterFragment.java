package e.apple.starwartest.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import e.apple.starwartest.R;
import e.apple.starwartest.adapter.CharacterListAdapter;
import e.apple.starwartest.interfaces.ItemClickListener;
import e.apple.starwartest.model.Character;


public class CharacterFragment extends Fragment implements ItemClickListener {

    private OnFragmentInteractionListener mListener;
    private RecyclerView characterList;
    private View view;
    List<Character> items;
    CharacterListAdapter characterListAdapter;
    RecyclerView recyclerView;
    public CharacterFragment() {

    }

    public static CharacterFragment newInstance(List<Character> items) {
        CharacterFragment f = new CharacterFragment();
        Bundle args = new Bundle();
        args.putSerializable("data", (Serializable) items);
        f.setArguments(args);
        return f;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("", "");
        if (getArguments() != null) {
            this.items = (List<Character>) getArguments().getSerializable("data");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        view = inflater.inflate(R.layout.fragment_character, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.characterList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        characterListAdapter = new CharacterListAdapter(items, R.layout.character_list);
        characterListAdapter.setClickListener(this);
        recyclerView.setAdapter(characterListAdapter);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view, int position)
    {
        Character character = items.get(position);
        Toast.makeText( getActivity(),"position--"+position,Toast.LENGTH_LONG).show();

        if (mListener != null) {
            mListener.onFragmentInteraction(character);
        }
    }

    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction( Character character);
    }
}
