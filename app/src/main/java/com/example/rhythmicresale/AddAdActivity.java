package com.example.rhythmicresale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ListAdapter;

import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class AddAdActivity extends Activity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ad);



        ImageButton homeButton = findViewById(R.id.home_button);
        Spinner spinnerCategories = findViewById(R.id.category);
        Spinner spinnerConditions = findViewById(R.id.conditions);


        // Rende il pulsante più color orange perché per qualche motivo dal file xml non lo fa  >:(
        ImageButton sellButton = findViewById(R.id.sell_button);
        sellButton.setColorFilter(ContextCompat.getColor(this, R.color.orange), PorterDuff.Mode.SRC_IN);



        //Mi riporta alla schermata home premendo il tasto home
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAdActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        });



        spinnerCategories.setPrompt(getString(R.string.select_category));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, R.layout.spinner_item_layout);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(adapter);



        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Azioni da eseguire quando non viene selezionato nulla
            }
        });






        spinnerConditions.setPrompt(getString(R.string.select_category));

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.conditions, R.layout.spinner_item_layout);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConditions.setAdapter(adapter1);


        spinnerConditions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedConditions = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Azioni da eseguire quando non viene selezionato nulla
            }
        });





    }
}
