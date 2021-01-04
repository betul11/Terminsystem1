package com.example.terminsystem1.Presenter.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminsystem1.Models.faculty;
import com.example.terminsystem1.R;

import java.sql.SQLException;
import java.util.ArrayList;


public class facultyAdapter extends RecyclerView.Adapter <facultyAdapter.facultyViewHolder>{
   private ArrayList<faculty> facultyArrayList;
   private onItemClickListener facultyListener;
   public interface onItemClickListener{
       // to be implemented in activity class
       void onItemClick (int position) throws SQLException;
   }
   public void setOnItemClickListener (onItemClickListener listener){
       facultyListener = listener;
   }

    public static class facultyViewHolder extends RecyclerView.ViewHolder{
    public TextView facultyNameText;
        public facultyViewHolder(@NonNull View itemView,onItemClickListener listener) {
            super(itemView);
            facultyNameText = itemView.findViewById(R.id.facultyTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            try {
                                listener.onItemClick(position);
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
    }

    public facultyAdapter (ArrayList<faculty> facultyList){
        facultyArrayList = facultyList;

    }

    @NonNull
    @Override
    public facultyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // inflated view (card)
        // to be used in RecyclerView
View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.faculty_example,parent,false);
    facultyViewHolder fvh = new facultyViewHolder(v,facultyListener);
    return fvh;
    }

    @Override
    public void onBindViewHolder(@NonNull facultyViewHolder holder, int position) {
        // new unused view holders filled with the updated following data:

        faculty currentFaculty = facultyArrayList.get(position);
      holder.facultyNameText.setText(currentFaculty.getFacultyName());
      // int position refers to the position of the current card
    }

    @Override
    public int getItemCount() {
        return facultyArrayList.size();
    }
}
