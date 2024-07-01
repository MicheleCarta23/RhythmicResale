package it.unica.rhythmicresale;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class HeaderFragment extends Fragment {

    private TextView titleTextView;
    private ImageButton profileButton;
    private ImageButton optionButton;
    private ImageView arrowTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_header, container, false);
        titleTextView = view.findViewById(R.id.header_title);
        profileButton = view.findViewById(R.id.profile_button);
        optionButton = view.findViewById(R.id.option_button);
        arrowTitle = view.findViewById(R.id.arrow_title);  // Assicurati di inizializzare arrowTitle
        ImageButton logoButton = view.findViewById(R.id.logo);

        optionButton.setOnClickListener(this::showPopupMenu);

        profileButton.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).navigateToProfileStefano();
            }
        });

        logoButton.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).navigateToFragment(new InsertionsFragment(), "Insertions", false);
            }
        });

        return view;
    }

    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(requireActivity(), v);
        popupMenu.getMenuInflater().inflate(R.menu.profile_option_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_logout) {
                showLogoutConfirmationDialog();
                return true;
            } else {
                showOptionSelectedToast(Objects.requireNonNull(item.getTitle()).toString());
                return true;
            }
        });
        popupMenu.show();

        // Modifica il colore del testo dell'elemento di menu "Logout"
        MenuItem logoutItem = popupMenu.getMenu().findItem(R.id.menu_logout);
        if (logoutItem != null) {
            SpannableString s = new SpannableString(logoutItem.getTitle());
            s.setSpan(new ForegroundColorSpan(Color.RED), 0, s.length(), 0);
            logoutItem.setTitle(s);
        }
    }

    private void showOptionSelectedToast(String option) {
        Toast.makeText(requireContext(), option + " selezionato", Toast.LENGTH_SHORT).show();
    }

    private void showLogoutConfirmationDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Conferma Logout")
                .setMessage("Sei sicuro di voler effettuare il logout?")
                .setPositiveButton("Sì", (dialog, which) -> logout())
                .setNegativeButton("No", null)
                .show();
    }

    private void logout() {
        SharedPreferences preferences = requireActivity().getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(MainActivity.IS_LOGGED_IN, false);
        editor.apply();

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }

    public void setTitle(String title) {
        if (titleTextView != null) {
            titleTextView.setText(title);
        }
    }

    public void showArrow(boolean showArrow) {
        if (arrowTitle != null) {
            arrowTitle.setVisibility(showArrow ? View.VISIBLE : View.GONE);
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
