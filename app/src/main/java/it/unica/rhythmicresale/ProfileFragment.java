package it.unica.rhythmicresale;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SharedPreferences preferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String username = preferences.getString("username", "");

        View view = null;
        if ("Ste_Meraviglia50".equals(username)) {
            view = inflater.inflate(R.layout.profile_stefano, container, false);
        } else if ("Ali_Chiavi81".equals(username)) {
            view = inflater.inflate(R.layout.profile_alice, container, false);
        }

        assert view != null;
        ImageButton addAdButton = view.findViewById(R.id.add_ad_image);

        addAdButton.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).navigateToFragment(new AddAdFragment(), "Aggiungi Annuncio", true);
            }
        });

        return view;
    }
}
