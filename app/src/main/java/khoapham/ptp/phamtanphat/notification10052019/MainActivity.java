package khoapham.ptp.phamtanphat.notification10052019;


import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText edtTime;
    Button btnPick;
    RecyclerView recyclerView;
    ArrayList<Schedule> manglichtrinh;
    ScheduleAdapter scheduleAdapter;
    Calendar calendar;
    TimePickerDialog timePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTime = findViewById(R.id.edittextTime);
        btnPick = findViewById(R.id.buttonPicktime);
        recyclerView = findViewById(R.id.recyclerView);
        calendar = Calendar.getInstance();
        manglichtrinh = new ArrayList<>();
        manglichtrinh.add(new Schedule(calendar.getTimeInMillis(),false));

        scheduleAdapter = new ScheduleAdapter(manglichtrinh);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(scheduleAdapter);

        edtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(0,0,0,hourOfDay,minute);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                        edtTime.setText(simpleDateFormat.format(calendar.getTimeInMillis()));
                    }
                },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true);
                timePickerDialog.show();
            }
        });

        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edtTime.getText().toString();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                String timegetText = simpleDateFormat.format(calendar.getTimeInMillis());
                if (!TextUtils.isEmpty(text) && text.equals(timegetText) ){
                    manglichtrinh.add(new Schedule(calendar.getTimeInMillis(),true));
                    scheduleAdapter.notifyDataSetChanged();
                }
            }
        });


    }
}
