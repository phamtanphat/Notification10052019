package khoapham.ptp.phamtanphat.notification10052019;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.Viewholder>{

    ArrayList<Schedule> arraylistLichtrinh;

    public ScheduleAdapter(ArrayList<Schedule> arraylistLichtrinh) {
        this.arraylistLichtrinh = arraylistLichtrinh;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_schedule,viewGroup,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        Schedule schedule = arraylistLichtrinh.get(i);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        viewholder.txtTime.setText(simpleDateFormat.format(schedule.getTime()));
        viewholder.toggleButton.setChecked(schedule.isPicked());
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
