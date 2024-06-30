package it.unica.rhythmicresale;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

public class HeaderFragment extends Fragment {

    private TextView titleTextView;
    private ImageButton profileButton;
    private ImageButton optionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_header, container, false);
        titleTextView = view.findViewById(R.id.header_title);
        profileButton = view.findViewById(R.id.profile);
        optionButton = view.findViewById(R.id.option);
        ImageButton logoButton = view.findViewById(R.id.logo);

        optionButton.setOnClickListener(this::showPopupMenu);

        profileButton.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).checkLoginAndNavigate(new ProfileFragment(), "Profile");
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
                logout();
                return true;
            }
            return false;
        });
        popupMenu.show();
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
