package com.au569735.coronatracker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.au569735.coronatracker.R;
import com.au569735.coronatracker.model.CountryStatistic;

import java.util.ArrayList;
import java.util.List;

/* reference */
// The implementation of this adaptor is adapted from the code shown in the video "Demo 2: RecyclerView in action"
// from the lecture "L3: Android UI". The video can be found on blackboard.au.dk.
// This implementation follows the implementation in the video closely.

public class CountryStatisticAdapter extends RecyclerView.Adapter<CountryStatisticAdapter.CountyStatisticViewHolder> {


    public interface ICountryStatisticItemClickedListener {
        void onStatItemClicked(int index);
    }

    ICountryStatisticItemClickedListener listener;

    List<CountryStatistic> countryStatistics = new ArrayList<CountryStatistic>();


    public CountryStatisticAdapter(ICountryStatisticItemClickedListener listener){
        this.listener = listener;
    }

    public void updateStatistics(List<CountryStatistic> newStats){
        countryStatistics = newStats;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountyStatisticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view;
       view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_country_stat_item, parent, false);
        return new CountyStatisticViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CountyStatisticViewHolder holder, int position) {

        CountryStatistic stats = countryStatistics.get(position);
        holder.txtCountry.setText(stats.getCountry());
        holder.txtCasesAndDeaths.setText("" + stats.getCases() + " / " + stats.getDeaths());
        holder.txtRating.setText(String.format("%.1f", stats.getRating()));
        holder.imgFlag.setImageResource(stats.getFlagIconId());

    }

    @Override
    public int getItemCount() {
        return countryStatistics.size();
    }

    public class CountyStatisticViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        ImageView imgFlag;
        TextView txtCountry, txtCasesAndDeaths, txtRating;

        ICountryStatisticItemClickedListener listener;

        public CountyStatisticViewHolder(@NonNull View itemView, ICountryStatisticItemClickedListener listener) {
            super(itemView);

            txtCountry = itemView.findViewById(R.id.txtItemCountry);
            txtCasesAndDeaths = itemView.findViewById(R.id.txtItemCaseAndDeaths);
            txtRating = itemView.findViewById(R.id.txtItemRating);
            imgFlag = itemView.findViewById(R.id.imgItemFlagIcon);
            this.listener = listener;

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            listener.onStatItemClicked(getAdapterPosition());
        }
    }
}
