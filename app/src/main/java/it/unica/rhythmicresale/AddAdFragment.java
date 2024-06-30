package it.unica.rhythmicresale;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddAdFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_ad, container, false);

        // Popolare il dropdown delle categorie
        Spinner categorySpinner = view.findViewById(R.id.category);
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.categories, R.layout.spinner_item_layout);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        // Popolare il dropdown delle condizioni
        Spinner conditionSpinner = view.findViewById(R.id.conditions);
        ArrayAdapter<CharSequence> conditionAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.conditions, R.layout.spinner_item_layout);
        conditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conditionSpinner.setAdapter(conditionAdapter);

        // Pulsante conferma
        view.findViewById(R.id.confirm_button).setOnClickListener(v -> {
            if (areFieldsValid(view)) {
                // Resetta i campi di input
                resetInputs(view);

                // Mostra messaggio di conferma
                Toast.makeText(getContext(), "Prodotto aggiunto con successo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Per favore compila tutti i campi", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private boolean areFieldsValid(View view) {
        EditText adTitle = view.findViewById(R.id.ad_title);
        EditText adBrand = view.findViewById(R.id.ad_brand);
        EditText description = view.findViewById(R.id.description);
        EditText adPrice = view.findViewById(R.id.ad_price);
        Spinner categorySpinner = view.findViewById(R.id.category);
        Spinner conditionSpinner = view.findViewById(R.id.conditions);

        return !adTitle.getText().toString().trim().isEmpty() &&
                !adBrand.getText().toString().trim().isEmpty() &&
                !description.getText().toString().trim().isEmpty() &&
                !adPrice.getText().toString().trim().isEmpty() &&
                categorySpinner.getSelectedItemPosition() != 0 &&
                conditionSpinner.getSelectedItemPosition() != 0;
    }

    private void resetInputs(View view) {
        EditText adTitle = view.findViewById(R.id.ad_title);
        EditText adBrand = view.findViewById(R.id.ad_brand);
        EditText description = view.findViewById(R.id.description);
        EditText adPrice = view.findViewById(R.id.ad_price);

        adTitle.setText("");
        adBrand.setText("");
        description.setText("");
        adPrice.setText("");

        Spinner categorySpinner = view.findViewById(R.id.category);
        Spinner conditionSpinner = view.findViewById(R.id.conditions);

        categorySpinner.setSelection(0);
        conditionSpinner.setSelection(0);
    }
}
