package com.technogenis.iotbasedsmartwater;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class LoginActivity extends AppCompatActivity {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "1122";
    private static final String IMAGE_URL = "https://upload.wikimedia.org/wikipedia/commons/2/2d/Astronotus_ocellatus_-_closeup_%28aka%29.jpg";

    ImageView imageView;
    Button btnLogin;
    EditText etUserName,etUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        btnLogin=findViewById(R.id.btnLogin);
        etUserName=findViewById(R.id.etUserName);
        etUserPassword=findViewById(R.id.etUserPassword);
        imageView=findViewById(R.id.imageView);


        Picasso.get()
                .load(IMAGE_URL)
                .into(imageView);

        btnLogin.setOnClickListener(v -> handleLogin());


    }

    private void handleLogin() {
        String inputUsername = etUserName.getText().toString().trim();
        String inputPassword = etUserPassword.getText().toString().trim();

        if (inputUsername.equals(USERNAME)) {
            if (inputPassword.equals(PASSWORD)) {
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish(); // Close login activity
            } else {
                showError(etUserPassword, "Incorrect password");
            }
        } else {
            showError(etUserName, "Incorrect username");
        }
    }

    private void showError(EditText field, String message) {
        field.setError(message);
        field.requestFocus();
        field.setText("");
    }
}