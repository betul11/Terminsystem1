package com.example.terminsystem1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLException;
import java.util.ArrayList;


public class academicAppointmentsAdapter extends RecyclerView.Adapter <academicAppointmentsAdapter.academicAppointmentsViewHolder>{
    private ArrayList<appointment> academicAppointmentsArrayList;
    private onItemClickListener academicAppointmentsListener;

    database db = new database();

    public interface onItemClickListener{
        void onItemClick (int position) throws SQLException;
        void onAcceptClick (int position) throws SQLException, ClassNotFoundException;
        void onDenyClick (int position) throws SQLException, ClassNotFoundException;

    }
    public void setOnItemClickListener (onItemClickListener listener){
        academicAppointmentsListener = listener;
    }

    public static class academicAppointmentsViewHolder extends RecyclerView.ViewHolder{
        public TextView appointmentStudentNameText;
        public TextView appointmentDateText;
        public TextView appointmentStudentNumberText;
        public TextView appointmentTimeText;

        public ImageView deleteRequest;
        public ImageView acceptRequest;

        public academicAppointmentsViewHolder(@NonNull View itemView,onItemClickListener listener) {
            super(itemView);
            appointmentStudentNameText = itemView.findViewById(R.id.academic_appointment_studentName);
            appointmentDateText = itemView.findViewById(R.id.academic_appointment_date);
            appointmentStudentNumberText = itemView.findViewById(R.id.academic_appointment_studentNumber);
            appointmentTimeText = itemView.findViewById(R.id.academic_appointment_time);
            acceptRequest = itemView.findViewById(R.id.image_accept);
            deleteRequest = itemView.findViewById(R.id.image_delete);

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

            deleteRequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            try {
                                listener.onDenyClick(position);
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            });

            acceptRequest.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            try {
                                listener.onAcceptClick(position);
                                acceptRequest.setImageResource(0);
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }



            });
        }
    }

    public academicAppointmentsAdapter (ArrayList<appointment> academicAppointmentsList){
        academicAppointmentsArrayList = academicAppointmentsList;

    }

    @NonNull
    @Override
    public academicAppointmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.academic_appointment,parent,false);
        academicAppointmentsViewHolder avh = new academicAppointmentsViewHolder(v,academicAppointmentsListener);
        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull academicAppointmentsViewHolder holder, int position) {
        appointment currentAppointment = academicAppointmentsArrayList.get(position);
        int studentID = currentAppointment.getAppointmentStudentID();
        if(currentAppointment.getAppointmentStatus().equals("accepted")){
            holder.acceptRequest.setImageResource(0);
        }
        String studentName = "adapter";
        int studentNumber = 200;
        String stringStudentNumber = "";


        try {
            studentName = db.getStudentName(studentID);
            studentNumber= db.getStudentNumber(studentID);
            stringStudentNumber = String.valueOf(studentNumber);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        holder.appointmentStudentNameText.setText(studentName);
        holder.appointmentDateText.setText(currentAppointment.getAppointmentDate().toString());
        holder.appointmentStudentNumberText.setText(stringStudentNumber);
        holder.appointmentTimeText.setText(currentAppointment.getAppointmentTime());





    }

    @Override
    public int getItemCount() {
        return academicAppointmentsArrayList.size();
    }
}
