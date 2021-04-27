package com.example.health;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class appointment extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    TextView textView;
    Button button, book_button, back;
    Spinner sp;
    EditText username;
    DBHelper DB;
    int day, month, year, hour, minute;
    int myday, myMonth, myYear, myHour, myMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        textView = findViewById(R.id.date_time);
        button = findViewById(R.id.date);
        book_button=findViewById(R.id.book);
        back= (Button) findViewById(R.id.back_home);
        sp = (Spinner) findViewById(R.id.clinic_view);
        username= (EditText) findViewById(R.id.id);
        DB = new DBHelper(this);

        String user = username.getText().toString();




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(appointment.this, appointment.this,year, month,day);
                datePickerDialog.show();
            }
        });

        book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selected_clinic = sp.getSelectedItem().toString();
                String d_t = textView.getText().toString();
                String user = username.getText().toString();


                Boolean insert2 = DB.insertData2(selected_clinic, d_t, user);
                if (selected_clinic.equals("") || d_t.equals("") || user.equals(""))
                    Toast.makeText(appointment.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    if (insert2 == true) {
                        Toast.makeText(appointment.this, "Appointment Booked", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), homeActivity.class);
                        startActivity(intent);

                    } else {Boolean bookedOrnot = DB.alreadybooked(user, d_t);
                        if(bookedOrnot==true) {
                            Toast.makeText(appointment.this, "Already booked for this schedule", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), appointment.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(appointment.this, "Appointment Booked", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), homeActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), homeActivity.class);
                startActivity(intent);
            }
        });


    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        myYear = year;
        myday = day;
        myMonth = month;
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(appointment.this, appointment.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }



    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        myHour = hourOfDay;
        myMinute = minute;
        textView.setText("Date: " + myMonth+"-"+ myday +"-"+ myYear + "\n" +
                "Time: " + myHour+":" + myMinute);


    }
}