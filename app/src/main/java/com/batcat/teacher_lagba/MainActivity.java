package com.batcat.teacher_lagba;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FirebaseDatabase db = FirebaseDatabase.getInstance();
//        DatabaseReference ref = db.getReference("1");

/*        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("tuitionOffers");
        String offerId = databaseReference.push().getKey();

        Offer offer = new Offer();
        offer.setSubject("English");
        offer.setDescription("Expert tuition for high school English.");
        offer.setTeacherId("teacher123");
        offer.setTimestamp(System.currentTimeMillis());

        databaseReference.child(offerId).setValue(offer);*/

        FloatingActionButton floatingActionButton = findViewById(R.id.f_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddOfferActivity.class);
                startActivity(intent);
            }
        });

        Button btn = findViewById(R.id.logout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("is_logged_in", false);
                editor.apply();

                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

    }



}
