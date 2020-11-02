package com.trinity.a20201031_marcregistre_nycschools.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.trinity.a20201031_marcregistre_nycschools.R;
import com.trinity.a20201031_marcregistre_nycschools.model.NycHighSchool;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HighSchoolAdapter extends RecyclerView.Adapter<HighSchoolAdapter.ViewHolder> {

    private List<NycHighSchool> mData;
    private final LayoutInflater mInflater;
    private static ItemClickListener mClickListener;

    public HighSchoolAdapter(Context context, List<NycHighSchool> data) {
        this.mInflater = LayoutInflater.from(context);
        mData = data;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.schools_list_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NycHighSchool data = mData.get(position);
        holder.school_name.setText(data.getSchool_name());
        holder.overview_paragraph.setText(data.getOverview_paragraph());
        holder.language_offered.setText(data.getLanguage_classes());
        holder.location_text.setText(data.getLocation());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView school_name;
        TextView overview_paragraph;
        TextView language_offered;
        TextView location_text;

        ViewHolder(View itemView) {
            super(itemView);
            school_name = itemView.findViewById(R.id.school_name);
            overview_paragraph = itemView.findViewById(R.id.overview_paragraph);
            language_offered = itemView.findViewById(R.id.language_offered);
            location_text = itemView.findViewById(R.id.location_text);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public NycHighSchool getItem(int id) {
        return mData.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void addData(List<NycHighSchool> data) {
        mData = data;
        notifyDataSetChanged();
    }
}
