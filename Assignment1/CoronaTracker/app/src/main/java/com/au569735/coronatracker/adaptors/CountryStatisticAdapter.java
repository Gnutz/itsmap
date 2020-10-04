package com.au569735.coronatracker.adaptors;

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

public class CountryStatisticAdapter extends RecyclerView.Adapter<CountryStatisticAdapter.CountyStatisticViewHolder> {


    public interface ICountryStatisticItemClickedListener {
        void onStatItemClicked(int index);
    }

    ICountryStatisticItemClickedListener listener;

    ArrayList<CountryStatistic> countryStatistics;


    public CountryStatisticAdapter(ICountryStatisticItemClickedListener listener){
        this.listener = listener;
    }

    public void updateStatistics(ArrayList<CountryStatistic> newStats){
        countryStatistics = newStats;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountyStatisticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view;
       view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_country_stat_item, parent, false);
       CountyStatisticViewHolder viewHolder = new CountyStatisticViewHolder(view, listener);
       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountyStatisticViewHolder holder, int position) {

        CountryStatistic stats = countryStatistics.get(position);
        holder.txtCountry.setText(stats.Country);
        holder.txtCasesAndDeaths.setText("" + stats.Cases + " / " + stats.Deaths);
        holder.txtRating.setText(String.format("%.1f", stats.Rating));
        holder.imgFlag.setImageResource(stats.FlagIconId);

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
