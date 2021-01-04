package com.example.terminsystem1.Presenter.Adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.example.terminsystem1.Models.academic;
import com.example.terminsystem1.R;

import java.sql.SQLException;
import java.util.ArrayList;


public class academicAdapter extends RecyclerView.Adapter <academicAdapter.academicViewHolder> {

    private ArrayList<academic> academicArrayList;
    private academicAdapter.onItemClickListener academicListener;
    public interface onItemClickListener{
        void onItemClick (int position) throws SQLException, ClassNotFoundException;
    }
    public void setOnItemClickListener (academicAdapter.onItemClickListener listener){
        // listener function to be implemented in activity class

        academicListener = listener;
    }

    public static class academicViewHolder extends RecyclerView.ViewHolder{
        public TextView academicNameText;
        public academicViewHolder(@NonNull View itemView, academicAdapter.onItemClickListener listener) {
            super(itemView);
            academicNameText = itemView.findViewById(R.id.facultyTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            try {
                                listener.onItemClick(position);
                            } catch (SQLException | ClassNotFoundException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
    }

    public academicAdapter (ArrayList<academic> academicList){
        academicArrayList = academicList;

    }

    @NonNull
    @Override
    public academicAdapter.academicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflated view (academic objects) using the faculty_example template
        // to be used in RecyclerView

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.faculty_example,parent,false);
        academicAdapter.academicViewHolder avh = new academicAdapter.academicViewHolder(v,academicListener);
        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull academicAdapter.academicViewHolder holder, int position) {
        // new unused view holders filled with the updated following data:

        academic currentAcademic = academicArrayList.get(position);
        holder.academicNameText.setText(currentAcademic.getAcademicName());
        // int position refers to the position of the current card
    }

    @Override
    public int getItemCount() {
        return academicArrayList.size();
    }









}
