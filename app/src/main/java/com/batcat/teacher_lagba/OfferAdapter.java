package com.batcat.teacher_lagba;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {

    private List<Offer> offerList;

    public OfferAdapter(List<Offer> offerList) {
        this.offerList = offerList;
    }

    @NonNull
    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_offer, parent, false);
        return new OfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferViewHolder holder, int position) {
        Offer offer = offerList.get(position);
        holder.titleTextView.setText(offer.getTitle());
        holder.classTextView.setText("Class: " + offer.getCls());
        holder.rateTextView.setText("Rate: " + offer.getRate());
        holder.descriptionTextView.setText("Description: " + offer.getDes());
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    static class OfferViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, classTextView, rateTextView, descriptionTextView;

        public OfferViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            classTextView = itemView.findViewById(R.id.classTextView);
            rateTextView = itemView.findViewById(R.id.rateTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
