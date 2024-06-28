package it.unica.rhythmicresale;

import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    private ImageButton homeButton, messageButton, sellButton, cartButton;
    private HeaderFragment headerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inizializza i pulsanti del footer
        homeButton = findViewById(R.id.home_button);
        messageButton = findViewById(R.id.message_button);
        sellButton = findViewById(R.id.sell_button);
        cartButton = findViewById(R.id.cart_button);

        homeButton.setOnClickListener(v -> {
            navigateToFragment(new InsertionsFragment(), "Home", false);
            updateButtonIcons(R.id.home_button);
        });
        messageButton.setOnClickListener(v -> {
            navigateToFragment(new MessagesFragment(), "Messaggi", false);
            updateButtonIcons(R.id.message_button);
        });
        sellButton.setOnClickListener(v -> {
            navigateToFragment(new AddAdFragment(), "Vendi", false);
            updateButtonIcons(R.id.sell_button);
        });
        cartButton.setOnClickListener(v -> {
            navigateToFragment(new FavoritesFragment(), "Preferiti", false);
            updateButtonIcons(R.id.cart_button);
        });

        // Carica il fragment iniziale e l'header
        if (savedInstanceState == null) {
            headerFragment = new HeaderFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.header_container, headerFragment)
                    .commit();

            // Esegui l'azione con un piccolo ritardo per assicurarti che il fragment sia inizializzato
            getSupportFragmentManager().executePendingTransactions();
            navigateToFragment(new InsertionsFragment(), "Home", false);
            updateButtonIcons(R.id.home_button);
        }
    }

    public void navigateToFragment(Fragment fragment, String title, boolean showOptionIcon) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
        getSupportFragmentManager().executePendingTransactions();
        if (headerFragment != null) {
            headerFragment.setTitle(title);
            headerFragment.showOptionIcon(showOptionIcon);
        }
    }

    private void updateButtonIcons(int activeButtonId) {
        homeButton.setImageResource(activeButtonId == R.id.home_button ? R.drawable.home_icon_active : R.drawable.home_icon);
        messageButton.setImageResource(activeButtonId == R.id.message_button ? R.drawable.message_icon_active : R.drawable.message_icon);
        sellButton.setImageResource(activeButtonId == R.id.sell_button ? R.drawable.plus_icon_active : R.drawable.plus_icon);
        cartButton.setImageResource(activeButtonId == R.id.cart_button ? R.drawable.cart_icon_active : R.drawable.cart_icon);
    }
}
