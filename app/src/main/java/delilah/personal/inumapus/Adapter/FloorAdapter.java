package delilah.personal.inumapus.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import delilah.personal.inumapus.OfficeActivity;
import delilah.personal.inumapus.R;

public class FloorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<String> items;
    private String buildingId;

    public FloorAdapter(Context context, ArrayList<String> items, String number) {
        this.context = context;
        this.items = items;
        this.buildingId = number;
    }

    private static class FloorViewHolder extends RecyclerView.ViewHolder {
        TextView floor;

        private FloorViewHolder(@NonNull View itemView) {
            super(itemView);

            floor = itemView.findViewById(R.id.floor_number);
        }
    }

    @NonNull
    @Override
    public FloorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row_floor, viewGroup, false);

        return new FloorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final FloorViewHolder viewHolder = (FloorViewHolder) holder;
        final String floor = items.get(position);

        viewHolder.floor.setText(floor);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OfficeActivity.class);

                intent.putExtra("buildingId", buildingId); // buildingId
                intent.putExtra("floor", floor); // floorId

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}