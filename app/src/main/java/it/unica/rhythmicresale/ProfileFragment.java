package it.unica.rhythmicresale;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    private static final String ARG_PROFILE_ID = "profile_id";
    private String profileId;

    public static ProfileFragment newInstance(String profileId) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PROFILE_ID, profileId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            profileId = getArguments().getString(ARG_PROFILE_ID);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;

        if (Objects.equals(profileId, "ste_meraviglia50")) {
            view = inflater.inflate(R.layout.profile_stefano, container, false);

            // Listener per il layout che contiene l'immagine e la scritta
            RelativeLayout addAdButton = view.findViewById(R.id.add_ad);
            addAdButton.setOnClickListener(v -> navigateToAddAdFragment());

            // Listener per l'immagine
            ImageButton addAdImage = view.findViewById(R.id.add_ad_image);
            addAdImage.setOnClickListener(v -> navigateToAddAdFragment());

            // Listener per la scritta
            TextView addAdText = view.findViewById(R.id.add_ad_name);
            addAdText.setOnClickListener(v -> navigateToAddAdFragment());
        }

        return view;
    }

    private void navigateToAddAdFragment() {
        Fragment addAdFragment = new AddAdFragment();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, addAdFragment);
        transaction.addToBackStack("Aggiungi Annuncio");
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setSellButton();
        }
        transaction.commit();

    }
}
