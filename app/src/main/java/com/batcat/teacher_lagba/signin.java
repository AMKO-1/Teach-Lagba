package com.batcat.teacher_lagba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signin extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private DatabaseReference counterReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signin);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        counterReference = FirebaseDatabase.getInstance().getReference("user_counter");

        Button signup = findViewById(R.id.signup);
        CheckBox checkBox1 = findViewById(R.id.checkBox);
        CheckBox checkBox2 = findViewById(R.id.checkBox2);

        checkBox1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkBox2.setChecked(false);
            }
        });

        checkBox2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkBox1.setChecked(false);
            }
        });

        EditText txt_name = findViewById(R.id.editText6);
        EditText txt_mail = findViewById(R.id.editText7);
        EditText txt_pass = findViewById(R.id.editText8);
        EditText txt_pass_match = findViewById(R.id.editText9);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txt_name.getText().toString().trim();
                String mail = txt_mail.getText().toString().trim();
                String pass = txt_pass.getText().toString();
                String passMatch = txt_pass_match.getText().toString();

                //Do pass Hashing Here --> kona

                if (name.isEmpty() || mail.isEmpty() || pass.isEmpty() || passMatch.isEmpty()) {
                    Toast.makeText(signin.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!pass.equals(passMatch)) {
                    Toast.makeText(signin.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                String role = "";
                if (checkBox1.isChecked()) {
                    role = "teacher";
                } else if (checkBox2.isChecked()) {
                    role = "student";
                } else {
                    Toast.makeText(signin.this, "Please select a role", Toast.LENGTH_SHORT).show();
                    return;
                }

                String finalRole = role;
                counterReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Integer currentId = snapshot.getValue(Integer.class);

                        if (currentId != null) {
                            int newUserId = currentId + 1;
                            counterReference.setValue(newUserId);

                            SigninHelper signinHelper = new SigninHelper(name, mail, pass, finalRole);
                            databaseReference.child(String.valueOf(newUserId)).setValue(signinHelper)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(signin.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(signin.this, Login.class);
                                                startActivity(intent);
                                            } else {
                                                Toast.makeText(signin.this, "SignUp Failed", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(signin.this, "Error generating user ID", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
