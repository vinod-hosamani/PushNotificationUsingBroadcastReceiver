package com.example.mgs1982.broadcasereceiver3;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatEditText editTextSetReminder;
    AppCompatButton btnSave;
    AppCompatButton buttnsetReminder;
    DatePickerDialog datePicker;
    TimePickerDialog timepicker;

    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    TimePickerDialog.OnTimeSetListener time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        final AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
        notificationIntent.addCategory("android.intent.category.DEFAULT");

        final PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        btnSave = (AppCompatButton) findViewById(R.id.btnClickMe);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                long milliSecond = (int) (myCalendar.getTimeInMillis() - System.currentTimeMillis());

                int second = (int) milliSecond / 1000;

              //  Toast.makeText(MainActivity.this, second + "", Toast.LENGTH_SHORT).show();

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, second);

                alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
                //  Toast.makeText(MainActivity.this, "gone to notificatoin", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initView() {
        myCalendar = Calendar.getInstance();
        editTextSetReminder = (AppCompatEditText) findViewById(R.id.editTextSetReminderID);
        buttnsetReminder=(AppCompatButton)findViewById(R.id.setReminderBtnID);
        buttnsetReminder.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setReminderBtnID:
                myCalendar = Calendar.getInstance();

                time = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                        myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        myCalendar.set(Calendar.MINUTE, minute);
                        myCalendar.set(Calendar.SECOND, 00);
                        updateLabel();
                    }
                };

                date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        timepicker = new TimePickerDialog(MainActivity.this, time,
                                myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true);

                        timepicker.show();
                    }
                };

                datePicker = new DatePickerDialog(this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));

                datePicker.getDatePicker().setMinDate(System.currentTimeMillis());

                datePicker.show();
                break;
        }
    }

    private void updateLabel() {
        String myFormat = getString(R.string.date_format_month);
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        editTextSetReminder.setText(sdf.format(myCalendar.getTime()));
    }
}
