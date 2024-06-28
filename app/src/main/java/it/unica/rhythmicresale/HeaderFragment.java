package it.unica.rhythmicresale;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class HeaderFragment extends Fragment {

    private TextView titleTextView;
    private ImageButton profileButton, optionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_header, container, false);
        titleTextView = view.findViewById(R.id.header_title);
        profileButton = view.findViewById(R.id.profile);
        optionButton = view.findViewById(R.id.option);

        optionButton.setOnClickListener(v -> {
            // Implementa qui l'azione per l'icona option se necessario
        });

        profileButton.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).navigateToFragment(new ProfileFragment(), "Profilo", true);
            }
        });

        return view;
    }

    public void setTitle(String title) {
        if (titleTextView != null) {
            titleTextView.setText(title);
        }
    }

    public void showOptionIcon(boolean showOption) {
        if (profileButton != null && optionButton != null) {
            if (showOption) {
                profileButton.setVisibility(View.GONE);
                optionButton.setVisibility(View.VISIBLE);
            } else {
                profileButton.setVisibility(View.VISIBLE);
                optionButton.setVisibility(View.GONE);
            }
        }
    }
}
