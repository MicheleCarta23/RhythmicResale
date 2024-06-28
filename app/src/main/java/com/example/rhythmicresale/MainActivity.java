package com.example.rhythmicresale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inizializzazione degli elementi
        ImageButton logoButton = findViewById(R.id.logo);
        ImageButton optionButton = findViewById(R.id.option);

        // Search bar
        android.widget.SearchView searchView = findViewById(R.id.searchView);
        ImageButton filterButton = findViewById(R.id.filter);

        // Footer
        ImageButton homeButton = findViewById(R.id.home_button);
        ImageButton messageButton = findViewById(R.id.message_button);
        ImageButton sellButton = findViewById(R.id.sell_button);
        ImageButton cartButton = findViewById(R.id.cart_button);

        // vista degli annunci
        RelativeLayout ad1Layout = findViewById(R.id.ad1);
        RelativeLayout ad2Layout = findViewById(R.id.ad2);
        RelativeLayout ad3Layout = findViewById(R.id.ad3);
        RelativeLayout addAdLayout = findViewById(R.id.add_ad);

        // Ad1Layout
        com.google.android.material.imageview.ShapeableImageView ad1ImageView = ad1Layout.findViewById(R.id.ad1_image);
        TextView ad1NameTextView = ad1Layout.findViewById(R.id.ad1_name);
        ImageView eye1ImageView = ad1Layout.findViewById(R.id.eye1);

        // Ad2Layout
        com.google.android.material.imageview.ShapeableImageView ad2ImageView = ad2Layout.findViewById(R.id.ad2_image);
        TextView ad2NameTextView = ad2Layout.findViewById(R.id.ad2_name);
        ImageView eye2ImageView = ad2Layout.findViewById(R.id.eye2);

        // Ad3Layout
        com.google.android.material.imageview.ShapeableImageView ad3ImageView = ad3Layout.findViewById(R.id.ad3_image);
        TextView ad3NameTextView = ad3Layout.findViewById(R.id.ad3_name);
        ImageView eye3ImageView = ad3Layout.findViewById(R.id.eye3);

        // AddAdLayout
        ImageButton addAdImageButton = addAdLayout.findViewById(R.id.add_ad_image);
        TextView addAdNameTextView = addAdLayout.findViewById(R.id.add_ad_name);



        sellButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddAdActivity.class);
                startActivity(intent);
            }
        });

        addAdImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddAdActivity.class);
                startActivity(intent);
            }
        });



    }
}
