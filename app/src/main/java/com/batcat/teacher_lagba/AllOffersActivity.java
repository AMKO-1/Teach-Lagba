package com.batcat.teacher_lagba;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllOffersActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private OfferAdapter offerAdapter;
    private List<Offer> offerList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_offers);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        offerList = new ArrayList<>();
        offerAdapter = new OfferAdapter(offerList);
        recyclerView.setAdapter(offerAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("OfferDetails");

        fetchOffers();
    }

    private void fetchOffers() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                offerList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Offer offer = dataSnapshot.getValue(Offer.class);
                    if (offer != null) {
                        offerList.add(offer);
                    }
                }
                offerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AllOffersActivity.this, "Failed to load offers.", Toast.LENGTH_SHORT).show();
                Log.e("AllOffersActivity", "Error fetching data", error.toException());
            }
        });
    }
}
