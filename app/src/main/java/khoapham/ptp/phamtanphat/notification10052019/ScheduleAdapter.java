package khoapham.ptp.phamtanphat.notification10052019;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.Viewholder>{

    ArrayList<Schedule> arraylistLichtrinh;
    Context context;

    public ScheduleAdapter(ArrayList<Schedule> arraylistLichtrinh , Context context) {
        this.arraylistLichtrinh = arraylistLichtrinh;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_schedule,viewGroup,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder viewholder, final int i) {
        final Schedule schedule = arraylistLichtrinh.get(i);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        viewholder.txtTime.setText(simpleDateFormat.format(schedule.getTime()));
        viewholder.toggleButton.setChecked(schedule.isPicked());
        viewholder.toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Cap nhat trang thai cho toggle
                schedule.setPicked(!schedule.isPicked());
                arraylistLichtrinh.set(i , schedule);
                notifyDataSetChanged();

                Intent intent = new Intent(context,NotificationAlarm.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context,i,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,schedule.getTime(),pendingIntent);
                }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP,schedule.getTime(),pendingIntent);
                }else{
                    alarmManager.set(AlarmManager.RTC_WAKEUP,schedule.getTime(),pendingIntent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return arraylistLichtrinh.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{
        TextView txtTime;
        ToggleButton toggleButton;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txtTime = itemView.findViewById(R.id.textviewTime);
            toggleButton = itemView.findViewById(R.id.toggleButton);
        }
    }
}
