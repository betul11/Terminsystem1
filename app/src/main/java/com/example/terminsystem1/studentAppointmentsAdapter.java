package com.example.terminsystem1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLException;
import java.util.ArrayList;


public class studentAppointmentsAdapter extends RecyclerView.Adapter <studentAppointmentsAdapter.studentAppointmentsViewHolder>{
    private ArrayList<appointment> studentAppointmentsArrayList;
    private onItemClickListener studentAppointmentsListener;
    public interface onItemClickListener{
        void onItemClick (int position) throws SQLException;
    }
    public void setOnItemClickListener (onItemClickListener listener){
        studentAppointmentsListener = listener;
    }

    public static class studentAppointmentsViewHolder extends RecyclerView.ViewHolder{
        public TextView appointmentAcademicText;
        public TextView appointmentDateText;
        public TextView appointmentStatusText;

        public studentAppointmentsViewHolder(@NonNull View itemView,onItemClickListener listener) {
            super(itemView);
            appointmentAcademicText = itemView.findViewById(R.id.student_appointment_academicName);
            appointmentDateText = itemView.findViewById(R.id.student_appointment_date);
                appointmentStatusText = itemView.findViewById(R.id.student_appointment_status);
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

    public studentAppointmentsAdapter (ArrayList<appointment> studentAppointmentsList){
        studentAppointmentsArrayList = studentAppointmentsList;

    }

    @NonNull
    @Override
    public studentAppointmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.faculty_example,parent,false);
        studentAppointmentsViewHolder fvh = new studentAppointmentsViewHolder(v,studentAppointmentsListener);
        return fvh;
    }

    @Override
    public void onBindViewHolder(@NonNull studentAppointmentsViewHolder holder, int position) {
        appointment currentAppointment = studentAppointmentsArrayList.get(position);
        database db = new database();
        int academicID = currentAppointment.getAppointmentAcademicID();
        String academicName = null;
        try {
            academicName = db.getAcademic(academicID).getAcademicName();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        holder.appointmentAcademicText.setText(academicName);
        holder.appointmentDateText.setText(currentAppointment.getAppointmentDate().toString());
        holder.appointmentStatusText.setText(currentAppointment.getAppointmentStatus());

        // ADD TIME PARAMETER!!!!!!!!!!! AND SEARCH FOR ACADEMIC NAME
    }

    @Override
    public int getItemCount() {
        return studentAppointmentsArrayList.size();
    }
}
