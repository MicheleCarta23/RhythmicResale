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

public class ProfileAliceFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_alice, container, false);

        // Trova il layout ad1
        View ad1 = view.findViewById(R.id.ad1);

        // Imposta un listener di clic
        ad1.setOnClickListener(v -> {
            // Naviga al layout "bass_insertion"
            navigateToBassInsertion();
        });

        return view;
    }

    private void navigateToBassInsertion() {
        Fragment bassInsertionFragment = new BassInsertionFragment();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, bassInsertionFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
