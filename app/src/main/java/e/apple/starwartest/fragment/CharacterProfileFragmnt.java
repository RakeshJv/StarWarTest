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
import e.apple.starwartest.util.DateTimeUtil;

public class CharacterProfileFragmnt extends Fragment {
    TextView name, height, mass, creationDate, creationTime;
    Character character;

    public CharacterProfileFragmnt() {

    }

    public static CharacterProfileFragmnt newInstance(Character character) {
        CharacterProfileFragmnt fragment = new CharacterProfileFragmnt();
        Bundle args = new Bundle();
        args.putSerializable("character", character);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            character = (Character) getArguments().getSerializable("character");
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
        String dateTime = DateTimeUtil.convertToNewFormat(character.getCreated());
        if (dateTime == null || dateTime.equals("") || dateTime.length() == 0) {
            creationDate.setText("Creation data not recorded");
            creationTime.setText("Creation data not recorded");
        } else {
            String dataNtime[] = dateTime.split(" ");
            creationDate.setText(dataNtime[0]);
            creationTime.setText(dataNtime[1] +" "+dataNtime[2]);

        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
