package e.apple.starwartest.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import e.apple.starwartest.R;
import e.apple.starwartest.model.Character;

public class CharacterProfileFragmnt extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    TextView name, height, mass, creationDate, creationTime;
    Character character;
    private OnFragmentInteractionListener mListener;

    public CharacterProfileFragmnt()
    {
        // Required empty public constructor
    }
    public static CharacterProfileFragmnt newInstance(Character character) {
        CharacterProfileFragmnt fragment = new CharacterProfileFragmnt();
        Bundle args = new Bundle();
        args.putSerializable("Character", character);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            character = (Character) getArguments().getSerializable("Character");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character_profile_fragmnt, container, false);
        name = (TextView) view.findViewById(R.id.name);
        height = (TextView) view.findViewById(R.id.height);
        mass = (TextView) view.findViewById(R.id.mass);
        creationDate = (TextView) view.findViewById(R.id.creationDate);
        creationTime = (TextView) view.findViewById(R.id.creationTime);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        name.setText(character.getName());
        height.setText(character.getHeight());
        mass.setText(character.getMass());
        creationDate.setText(character.getCreated());
        creationTime.setText(character.getCreated());
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
