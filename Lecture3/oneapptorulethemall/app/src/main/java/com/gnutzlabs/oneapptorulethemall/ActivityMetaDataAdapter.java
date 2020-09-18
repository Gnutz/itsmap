package com.gnutzlabs.oneapptorulethemall;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActivityMetaDataAdapter extends RecyclerView.Adapter<ActivityMetaDataAdapter.ActivityMetaDataViewHolder> {

    public interface IActivityMetaDataItemClickedListener{
        void onActivityMetadataClicked(int index);
    }

    IActivityMetaDataItemClickedListener listener;

    ArrayList<ActivityMetaData> activityList;

    public ActivityMetaDataAdapter(IActivityMetaDataItemClickedListener listener){
        this.listener = listener;
    }

    void updateActivitiesList(ArrayList<ActivityMetaData> list){
        activityList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ActivityMetaDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ActivityMetaDataViewHolder viewHolder = new ActivityMetaDataViewHolder(view, listener);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityMetaDataViewHolder holder, int position) {
        holder.txtName.setText(activityList.get(position).Name);
        holder.txtDescription.setText(activityList.get(position).Description);
        switch (position){
            case 0:
                holder.imgIcon.setImageResource(R.mipmap.ic_activity_picker);
                break;
            case 1:
                holder.imgIcon.setImageResource(R.mipmap.ic_activity_edit);
                break;
            case 2:
                holder.imgIcon.setImageResource(R.mipmap.ic_activity_sliders);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }

    public class ActivityMetaDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtName, txtDescription;
        ImageView imgIcon;

        IActivityMetaDataItemClickedListener listener;

        public ActivityMetaDataViewHolder(@NonNull View itemView, IActivityMetaDataItemClickedListener listener) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            imgIcon = itemView.findViewById(R.id.activityIcon);
            this.listener = listener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listener.onActivityMetadataClicked(getAdapterPosition());
        }
    }
}
