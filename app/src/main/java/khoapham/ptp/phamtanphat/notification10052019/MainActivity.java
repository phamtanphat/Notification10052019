package khoapham.ptp.phamtanphat.notification10052019;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText edtTime;
    Button btnPick;
    RecyclerView recyclerView;
    ArrayList<Schedule> manglichtrinh;
    ScheduleAdapter scheduleAdapter;
    Calendar calendar;
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


    }
}
