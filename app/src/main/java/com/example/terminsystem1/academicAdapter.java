package com.example.terminsystem1;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.sql.SQLException;
import java.util.ArrayList;


public class academicAdapter extends RecyclerView.Adapter <academicAdapter.academicViewHolder> {

    private ArrayList<academic> academicArrayList;
    private academicAdapter.onItemClickListener academicListener;
    public interface onItemClickListener{
        void onItemClick (int position) throws SQLException, ClassNotFoundException;
    }
    public void setOnItemClickListener (academicAdapter.onItemClickListener listener){
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.faculty_example,parent,false);
        academicAdapter.academicViewHolder avh = new academicAdapter.academicViewHolder(v,academicListener);
        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull academicAdapter.academicViewHolder holder, int position) {
        academic currentAcademic = academicArrayList.get(position);
        holder.academicNameText.setText(currentAcademic.getAcademicName());
    }

    @Override
    public int getItemCount() {
        return academicArrayList.size();
    }









}
