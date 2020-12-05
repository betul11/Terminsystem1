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

    public static class facultyViewHolder extends RecyclerView.ViewHolder{
    public TextView facultyNameText;
        public facultyViewHolder(@NonNull View itemView) {
            super(itemView);
            facultyNameText = itemView.findViewById(R.id.facultyTextView);
        }
    }

    public facultyAdapter (ArrayList<faculty> facultyList){
        facultyArrayList = facultyList;

    }

    @NonNull
    @Override
    public facultyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.faculty_example,parent,false);
    facultyViewHolder fvh = new facultyViewHolder(v);
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
