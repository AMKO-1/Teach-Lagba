package com.batcat.teacher_lagba;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        EditText txtEmail = findViewById(R.id.editText9);
        EditText txtPassword = findViewById(R.id.editText10);
        Button btnLogin = findViewById(R.id.login);
        Button signup = findViewById(R.id.signup2);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, signin.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString();
                //Do pass Hashing Here --> kona

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                loginUser(email, password);
            }
        });
    }

    private void loginUser(String email, String password) {
        // Query all users to find one with matching email and password
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean foundUser = false;

                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    SigninHelper user = userSnapshot.getValue(SigninHelper.class);

                    if (user != null && user.getMail().equals(email) && user.getPass().equals(password)) {
                        foundUser = true;
                        break;
                    }
                }
                if (foundUser) {
                    // Login was successful, save login state
                    SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("is_logged_in", true);
                    editor.apply();

                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    // Navigate to main screen or home activity
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Optional: Close login activity
                }

                if (!foundUser) {
                    Toast.makeText(Login.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Login.this, "Login failed. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
