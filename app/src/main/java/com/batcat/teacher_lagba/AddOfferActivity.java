package com.batcat.teacher_lagba;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddOfferActivity extends AppCompatActivity {
    private EditText titleEditText, subjectEditText, classEditText, descriptionEditText;
    private DatabaseReference database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offer);
        EditText t1 = findViewById(R.id.editText);
        EditText t2 = findViewById(R.id.editText2);
        EditText t3 = findViewById(R.id.editText3);
        EditText t4 = findViewById(R.id.editText5);
        EditText t5 = findViewById(R.id.editText4);

        Button btn = findViewById(R.id.postbutton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = t1.getText().toString();
                String id = t2.getText().toString();
                String cls = t3.getText().toString();
                String rate = t4.getText().toString();
                String des = t5.getText().toString();

                Offer offer = new Offer();
                offer.setTitle(title);
                offer.setId(id);
                offer.setCls(cls);
                offer.setRate(rate);
                offer.setDes(des);

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("OfferDetails");
                String offerId = ref.push().getKey();

                ref.child(offerId).setValue(offer).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(
                                AddOfferActivity.this,"added", Toast.LENGTH_SHORT
                        ).show();
                        t1.getText().clear();
                        t2.getText().clear();
                        t3.getText().clear();
                        t4.getText().clear();
                        t5.getText().clear();
                    }
                });
            }
        });
    }
}
