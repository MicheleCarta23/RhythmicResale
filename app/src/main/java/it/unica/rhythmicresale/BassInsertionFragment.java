package it.unica.rhythmicresale;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.widget.LinearLayout;

public class BassInsertionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bass_insertion, container, false);

        // Trova il pulsante utente
        LinearLayout userButton = view.findViewById(R.id.user_button);

        // Imposta un listener di clic
        userButton.setOnClickListener(v -> {
            // Naviga al layout "profile_alice"
            navigateToProfileAlice();
        });

        return view;
    }

    private void navigateToProfileAlice() {
        Fragment profileAliceFragment = new ProfileAliceFragment();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, profileAliceFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
