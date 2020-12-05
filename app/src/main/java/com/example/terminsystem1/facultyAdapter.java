package com.example.terminsystem1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class facultyAdapter extends RecyclerView.Adapter <facultyAdapter.facultyViewHolder>{
   private ArrayList<faculty> facultyArrayList;
   private onItemClickListener facultyListener;
   public interface onItemClickListener{
       void onItemClick (int position);
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
                            listener.onItemClick(position);
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
View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.faculty_example,parent,false);
    facultyViewHolder fvh = new facultyViewHolder(v,facultyListener);
    return fvh;
    }

    @Override
    public void onBindViewHolder(@NonNull facultyViewHolder holder, int position) {
      faculty currentFaculty = facultyArrayList.get(position);
      holder.facultyNameText.setText(currentFaculty.getFacultyName());
    }

    @Override
    public int getItemCount() {
        return facultyArrayList.size();
    }
}
