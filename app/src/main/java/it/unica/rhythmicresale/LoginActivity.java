package it.unica.rhythmicresale;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private final Map<String, String> validCredentials = new HashMap<>();
    private RelativeLayout loginLayout;
    private RelativeLayout registerLayout;
    private Button showLoginButton;
    private Button showRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        validCredentials.put("ste_meraviglia50", "66145");

        loginLayout = findViewById(R.id.login_layout);
        registerLayout = findViewById(R.id.register_layout);

        EditText usernameEditText = findViewById(R.id.mail_username);
        EditText passwordEditText = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.login_button);
        showRegisterButton = findViewById(R.id.show_register_button);
        showLoginButton = findViewById(R.id.show_login_button);

        EditText registerUsernameEditText = findViewById(R.id.register_username);
        EditText registerMailEditText = findViewById(R.id.register_mail);
        EditText registerPasswordEditText = findViewById(R.id.register_password);
        EditText registerConfirmPasswordEditText = findViewById(R.id.register_confirm_password);
        Button registerButton = findViewById(R.id.register_button);

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().toLowerCase(); // Non case-sensitive
            String password = passwordEditText.getText().toString();

            if (validCredentials.containsKey(username) && Objects.equals(validCredentials.get(username), password)) {
                SharedPreferences.Editor editor = getSharedPreferences(MainActivity.PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean(MainActivity.IS_LOGGED_IN, true);
                editor.putString("username", username); // Save the username
                editor.apply();

                Toast.makeText(LoginActivity.this, "Login effettuato con successo", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Nome utente o Password errata/i", Toast.LENGTH_SHORT).show();
            }
        });

        registerButton.setOnClickListener(v -> {
            String username = registerUsernameEditText.getText().toString().toLowerCase(); // Non case-sensitive
            String mail = registerMailEditText.getText().toString();
            String password = registerPasswordEditText.getText().toString();
            String confirmPassword = registerConfirmPasswordEditText.getText().toString();

            if (username.isEmpty() || mail.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Compila tutti i campi", Toast.LENGTH_SHORT).show();
            } else if (!Objects.equals(password, confirmPassword)) {
                Toast.makeText(LoginActivity.this, "Le password non coincidono", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, "Registrazione avvenuta con successo", Toast.LENGTH_SHORT).show();
                toggleLoginRegister(true);
            }
        });

        showRegisterButton.setOnClickListener(v -> toggleLoginRegister(false));
        showLoginButton.setOnClickListener(v -> toggleLoginRegister(true));

        initializeFooterButtons();
    }

    private void toggleLoginRegister(boolean showLogin) {
        if (showLogin) {
            loginLayout.setVisibility(View.VISIBLE);
            registerLayout.setVisibility(View.GONE);
            showLoginButton.setVisibility(View.GONE);
            showRegisterButton.setVisibility(View.VISIBLE);
        } else {
            loginLayout.setVisibility(View.GONE);
            registerLayout.setVisibility(View.VISIBLE);
            showLoginButton.setVisibility(View.VISIBLE);
            showRegisterButton.setVisibility(View.GONE);
        }
    }

    private void initializeFooterButtons() {
        ImageButton homeButton = findViewById(R.id.home_button);
        ImageButton messageButton = findViewById(R.id.message_button);
        ImageButton sellButton = findViewById(R.id.sell_button);
        ImageButton favoritesButton = findViewById(R.id.favorite_button);
        ImageButton profileButton = findViewById(R.id.profile_button);

        if (homeButton != null) {
            homeButton.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, MainActivity.class)));
        }
        if (messageButton != null) {
            messageButton.setOnClickListener(v -> {
                if (isUserLoggedIn()) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Devi effettuare il login per accedere ai messaggi", Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (sellButton != null) {
            sellButton.setOnClickListener(v -> {
                if (isUserLoggedIn()) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Devi effettuare il login per vendere", Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (favoritesButton != null) {
            favoritesButton.setOnClickListener(v -> {
                if (isUserLoggedIn()) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Devi effettuare il login per accedere ai preferiti", Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (profileButton != null) {
            profileButton.setOnClickListener(v -> {
                if (isUserLoggedIn()) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Devi effettuare il login per accedere al profilo", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean isUserLoggedIn() {
        SharedPreferences preferences = getSharedPreferences(MainActivity.PREFS_NAME, MODE_PRIVATE);
        return preferences.getBoolean(MainActivity.IS_LOGGED_IN, false);
    }
}
