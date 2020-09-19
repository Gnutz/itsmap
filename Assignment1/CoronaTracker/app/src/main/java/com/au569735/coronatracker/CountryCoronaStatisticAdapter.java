package com.au569735.coronatracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CountryCoronaStatisticAdapter extends RecyclerView.Adapter<CountryCoronaStatisticAdapter.CountyCoronaStatisticViewHolder> {


    public interface ICountryCoronaStatisticItemClickedListener {
        void onStatItemClicked(int index);
    }

    ICountryCoronaStatisticItemClickedListener listener;

    ArrayList<CountryCoronaStatistic> countryCoronaStatistics;


    public CountryCoronaStatisticAdapter(ICountryCoronaStatisticItemClickedListener listener){
        this.listener = listener;
    }

    void updateCStatistics(ArrayList<CountryCoronaStatistic> newStats){
        countryCoronaStatistics = newStats;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountyCoronaStatisticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view;
       view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_country_stat_item, parent, false);
       CountyCoronaStatisticViewHolder viewHolder = new CountyCoronaStatisticViewHolder(view, listener);
       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountyCoronaStatisticViewHolder holder, int position) {

        CountryCoronaStatistic stats = countryCoronaStatistics.get(position);
        holder.txtCountryName.setText(stats.Country);
        holder.txtCasesAndDeaths.setText("" + stats.NumberOfCases + " / " + stats.NumberOfDeaths);
        holder.txtRating.setText(""+stats.UserRating);
        holder.imgFlag.setImageResource(stats.FlagIconId);

    }

    @Override
    public int getItemCount() {
        return countryCoronaStatistics.size();
    }

    public class CountyCoronaStatisticViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        ImageView imgFlag;
        TextView txtCountryName, txtCasesAndDeaths, txtRating;

        ICountryCoronaStatisticItemClickedListener listener;

        public CountyCoronaStatisticViewHolder(@NonNull View itemView, ICountryCoronaStatisticItemClickedListener listener) {
            super(itemView);

            txtCountryName = itemView.findViewById(R.id.txtItemCountry);
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
