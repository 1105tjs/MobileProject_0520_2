package kr.ac.yeonsung.mp1.mobileproject_0520_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    RadioGroup rg;
    CalendarView calendar;
    TimePicker time;
    Chronometer timer;
    int selectedYear, selectedMonth, selectedDay;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg = findViewById(R.id.rg);
        calendar = findViewById(R.id.calendar);
        time = findViewById(R.id.time);
        timer = findViewById(R.id.timer);
        textResult = findViewById(R.id.text_result);

        Button btnStart = findViewById(R.id.btn_start);
        Button btnDone = findViewById(R.id.btn_done);

        btnStart.setOnClickListener(btnListener);
        btnDone.setOnClickListener(btnListener);

        rg.setOnCheckedChangeListener(rgListener);
        calendar.setVisibility(View.INVISIBLE);
        calendar.setOnDateChangeListener(calendarListener);
    }

    CalendarView.OnDateChangeListener calendarListener = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
            selectedYear = year;
            selectedMonth = month +1;
            selectedDay = day;
        }
    };

    //예약 시작/종료버튼 클릭시 리스너
    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_start:
                    timer.setBase(SystemClock.elapsedRealtime());
                    timer.start();
                    timer.setTextColor(Color.RED);
                    break;
                case R.id.btn_done:
                    timer.stop();
                    timer.setTextColor(Color.BLUE);
                    textResult.setText(selectedYear + "년 " + selectedMonth + "월 " + selectedDay + "일 ");
                    textResult.append(time.getCurrentHour() + "시 ");    //현재 시간 append
                    textResult.append(time.getCurrentMinute() + "분 예약 완료됨");    //현재 분  append
                    break;
            }
        }
        };
        RadioGroup.OnCheckedChangeListener rgListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int radioId) {
                calendar.setVisibility(View.INVISIBLE);
                time.setVisibility(View.INVISIBLE);
                switch (radioId) {
                    case R.id.radio_calendar:
                        calendar.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radio_time:
                        time.setVisibility(View.VISIBLE);
                        break;
                }
            }
        };
    }
