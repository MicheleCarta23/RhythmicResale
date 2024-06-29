package it.unica.rhythmicresale;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "user_prefs";
    public static final String IS_LOGGED_IN = "logged_in";
    private ImageButton homeButton, messageButton, sellButton, cartButton, profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize footer buttons and set their click listeners
        initializeFooterButtons();

        // Aggiungi il Fragment Header
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.header_container, new HeaderFragment(), "HEADER_FRAGMENT")
                .commit();

        // Naviga al fragment Insertions
        navigateToFragment(new InsertionsFragment(), "Insertions", false);

        // Assicurati che il titolo venga aggiornato correttamente
        getSupportFragmentManager().addOnBackStackChangedListener(this::updateHeaderTitleOnBackStackChanged);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateHeaderTitleOnBackStackChanged();
    }

    private void initializeFooterButtons() {
        homeButton = findViewById(R.id.home_button);
        messageButton = findViewById(R.id.message_button);
        sellButton = findViewById(R.id.sell_button);
        cartButton = findViewById(R.id.cart_button);
        profileButton = findViewById(R.id.profile);

        if (homeButton != null) {
            homeButton.setOnClickListener(v -> navigateToFragment(new InsertionsFragment(), "Insertions", false));
        }
        if (messageButton != null) {
            messageButton.setOnClickListener(v -> checkLoginAndNavigate(new MessagesFragment(), "Messaggi"));
        }
        if (sellButton != null) {
            sellButton.setOnClickListener(v -> checkLoginAndNavigate(new AddAdFragment(), "Aggiungi Annuncio"));
        }
        if (cartButton != null) {
            cartButton.setOnClickListener(v -> checkLoginAndNavigate(new FavoritesFragment(), "Preferiti"));
        }
        if (profileButton != null) {
            profileButton.setOnClickListener(v -> checkLoginAndNavigate(new ProfileFragment(), "Profilo"));
        }
    }

    public void checkLoginAndNavigate(Fragment fragment, String title) {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean(IS_LOGGED_IN, false);

        if (isLoggedIn || fragment instanceof InsertionsFragment) {
            navigateToFragment(fragment, title, true);
        } else {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    public void navigateToFragment(Fragment fragment, String title, boolean addToBackStack) {
        // Controlla se l'utente è loggato per determinati frammenti
        if (!(fragment instanceof InsertionsFragment) && !isUserLoggedIn()) {
            // Reindirizza a LoginActivity se non è loggato
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);

        if (addToBackStack) {
            transaction.addToBackStack(title);
        }

        transaction.commit();

        updateFooterIcons(title);
        updateHeader(fragment);
    }

    private boolean isUserLoggedIn() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return preferences.getBoolean(IS_LOGGED_IN, false);
    }

    private void updateFooterIcons(String currentFragmentTitle) {
        int defaultColor = ContextCompat.getColor(this, R.color.white);
        int selectedColor = ContextCompat.getColor(this, R.color.orange);

        if (homeButton != null) homeButton.setColorFilter(currentFragmentTitle.equals("Insertions") ? selectedColor : defaultColor);
        if (messageButton != null) messageButton.setColorFilter(currentFragmentTitle.equals("Messaggi") ? selectedColor : defaultColor);
        if (sellButton != null) sellButton.setColorFilter(currentFragmentTitle.equals("Aggiungi Annuncio") ? selectedColor : defaultColor);
        if (cartButton != null) cartButton.setColorFilter(currentFragmentTitle.equals("Preferiti") ? selectedColor : defaultColor);
        if (profileButton != null) profileButton.setColorFilter(currentFragmentTitle.equals("Profilo") ? selectedColor : defaultColor);
    }

    private void updateHeader(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        HeaderFragment headerFragment = (HeaderFragment) fragmentManager.findFragmentById(R.id.header_container);
        if (headerFragment != null) {
            if (fragment instanceof ProfileFragment) {
                headerFragment.showOptionIcon(true);
                headerFragment.setTitle("Profilo");
            } else if (fragment instanceof MessagesFragment) {
                headerFragment.showOptionIcon(false);
                headerFragment.setTitle("Messaggi");
            } else if (fragment instanceof AddAdFragment) {
                headerFragment.showOptionIcon(false);
                headerFragment.setTitle("Aggiungi Annuncio");
            } else if (fragment instanceof FavoritesFragment) {
                headerFragment.showOptionIcon(false);
                headerFragment.setTitle("Preferiti");
            } else if (fragment instanceof InsertionsFragment) {
                headerFragment.showOptionIcon(false);
                headerFragment.setTitle("Home");
            } else {
                headerFragment.showOptionIcon(false);
                headerFragment.setTitle("");
            }
        }
    }

    private void updateHeaderTitleOnBackStackChanged() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (currentFragment != null) {
            updateHeader(currentFragment);
        }
    }
}
