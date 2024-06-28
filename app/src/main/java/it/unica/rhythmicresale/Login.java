package it.unica.rhythmicresale;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ListAdapter;

import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;


public class Login extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        RelativeLayout header = findViewById(R.id.header);

        LinearLayout sellLayout = findViewById(R.id.sell_layout);
        ImageView logoImage = findViewById(R.id.logo);

        RelativeLayout loginLayout = findViewById(R.id.login_layout);
        TextView loginText = findViewById(R.id.login);
        EditText mailUsername = findViewById(R.id.mail_username);
        EditText password = findViewById(R.id.password);

        RelativeLayout registerLayout = findViewById(R.id.register_layout);
        TextView registerText = findViewById(R.id.register);
        EditText mailRegister = findViewById(R.id.mail_register);
        EditText usernameRegister = findViewById(R.id.username_register);
        EditText passwordRegister = findViewById(R.id.password_register);
        EditText passwordRegister2 = findViewById(R.id.password_register2);

        ImageButton registerButton = findViewById(R.id.register_button);

        LinearLayout footerLayout = findViewById(R.id.footer);
        ImageButton homeButton = findViewById(R.id.home_button);
        ImageButton messageButton = findViewById(R.id.message_button);
        ImageButton sellButton = findViewById(R.id.sell_button);
        ImageButton cartButton = findViewById(R.id.cart_button);




        registerLayout.setVisibility(View.GONE);


    }
}
