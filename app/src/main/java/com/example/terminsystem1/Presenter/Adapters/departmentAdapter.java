package com.example.terminsystem1.Presenter.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminsystem1.Models.department;
import com.example.terminsystem1.R;

import java.sql.SQLException;
import java.util.ArrayList;

public class departmentAdapter extends RecyclerView.Adapter <departmentAdapter.departmentViewHolder> {


    private ArrayList<department> departmentArrayList;
    private departmentAdapter.onItemClickListener departmentListener;
    public interface onItemClickListener{
        void onItemClick (int position) throws SQLException, ClassNotFoundException;
    }
    public void setOnItemClickListener (departmentAdapter.onItemClickListener listener){
        //to be implemented in activity class
        departmentListener = listener;
    }

    public static class departmentViewHolder extends RecyclerView.ViewHolder{
        public TextView departmentNameText;
        public departmentViewHolder(@NonNull View itemView, departmentAdapter.onItemClickListener listener) {
            super(itemView);
            departmentNameText = itemView.findViewById(R.id.facultyTextView);
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

    public departmentAdapter (ArrayList<department> departmentList){
        departmentArrayList = departmentList;

    }

    @NonNull
    @Override
    public departmentAdapter.departmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      // inflated view
        // to be used in RecyclerView
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.faculty_example,parent,false);
        departmentAdapter.departmentViewHolder dvh = new departmentAdapter.departmentViewHolder(v,departmentListener);
        return dvh;
    }

    @Override
    public void onBindViewHolder(@NonNull departmentAdapter.departmentViewHolder holder, int position) {
        // new unused view holders (cards) filled with the updated following data:

        department currentDepartment = departmentArrayList.get(position);
        holder.departmentNameText.setText(currentDepartment.getDepartmentName());
        // int position refers to the position of the current card

    }

    @Override
    public int getItemCount() {
        return departmentArrayList.size();
    }
}

