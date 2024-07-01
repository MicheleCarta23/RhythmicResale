package it.unica.rhythmicresale;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

public class InsertionsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.insertions, container, false);

        // Trova il pulsante filter
        View filterButton = view.findViewById(R.id.filter_button);
        filterButton.setOnClickListener(v -> showFilterDialog());

        // Trova il RelativeLayout con id ad3
        RelativeLayout ad3 = view.findViewById(R.id.ad3);

        // Imposta un listener di click su ad3
        ad3.setOnClickListener(v -> navigateToBassInsertion());

        return view;
    }

    private void showFilterDialog() {
        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.filter_button_sheet);

        // Imposta lo sfondo del dialogo a trasparente
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);

        SeekBar seekBarDistance = dialog.findViewById(R.id.seekbar_distance);
        TextView textDistance = dialog.findViewById(R.id.text_distance);
        Spinner spinnerCategory = dialog.findViewById(R.id.spinner_category);
        Spinner spinnerType = dialog.findViewById(R.id.spinner_type);
        Button buttonApply = dialog.findViewById(R.id.button_apply);

        seekBarDistance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress < 1) {
                    textDistance.setText(R.string.distance_less_than_1);
                } else if (progress > 300) {
                    textDistance.setText(R.string.distance_greater_than_300);
                } else {
                    textDistance.setText(getString(R.string.distance_km, progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        ArrayAdapter<CharSequence> adapterCategory = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.ad_categories,
                android.R.layout.simple_spinner_item
        );
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategory);

        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.ad_types,
                android.R.layout.simple_spinner_item
        );
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapterType);

        buttonApply.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
        // Assicurati che il dialogo sia centrato e ridimensionato
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout((int) (getResources().getDisplayMetrics().widthPixels * 0.9), ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.CENTER);
        }
    }



    private void navigateToBassInsertion() {
        Fragment bassInsertionFragment = new BassInsertionFragment();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, bassInsertionFragment);
        transaction.addToBackStack("Bass Insertion");
        transaction.commit();
    }
}
