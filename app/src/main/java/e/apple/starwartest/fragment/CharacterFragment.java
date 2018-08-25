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

import java.io.Serializable;
import java.util.List;

import e.apple.starwartest.R;
import e.apple.starwartest.adapter.CharacterList;
import e.apple.starwartest.model.Character;

public class CharacterFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView characterList;
    private View view;
    List<Character> items;
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
        if (getArguments() != null)
        {
            this.items = (List<Character>) getArguments().getSerializable("data");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_character, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.characterList);
        recyclerView.setAdapter(new CharacterList(items, R.layout.character_list));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
