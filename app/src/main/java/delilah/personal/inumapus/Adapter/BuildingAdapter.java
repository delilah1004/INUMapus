package delilah.personal.inumapus.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import delilah.personal.inumapus.Info.BuildingListInfo;
import delilah.personal.inumapus.OfficeActivity;
import delilah.personal.inumapus.R;
import delilah.personal.inumapus.model.BuildingModel;
import retrofit2.Callback;

public class BuildingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Callback<ArrayList<BuildingModel>> b_context;

    private List<BuildingListInfo> items = null;
    private ArrayList<BuildingListInfo> buildingInfoArrayList;

    public BuildingAdapter(Callback<ArrayList<BuildingModel>> context, List<BuildingListInfo> items) {
        this.b_context = context;
        this.items = items;
        buildingInfoArrayList = new ArrayList<BuildingListInfo>();
        buildingInfoArrayList.addAll(items);
    }

    // RecyclerView.ViewHolder 에 override 되는 methods
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.building_recyclerview_row, null);

        return new BuildingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final BuildingListInfo item = items.get(position);

        BuildingViewHolder buildingViewHolder = (BuildingViewHolder) viewHolder;

        buildingViewHolder.buildingTitle.setText(item.getBuildingTitle());
        buildingViewHolder.buildingNumber.setText(String.valueOf(item.getBuilingNumber()));

        buildingViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Toast.makeText(context, item.getBuildingTitle() + " " + item.getBuilingNumber() + "호관", Toast.LENGTH_SHORT).show();

                view.setBackgroundColor(Color.GRAY);

                Intent intent = new Intent(context, OfficeActivity.class);

                intent.putExtra("number", item.getBuilingNumber());

                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        items.clear();
        if (charText.length() == 0) {
            items.addAll(buildingInfoArrayList);
        } else {
            for (BuildingListInfo buildingListInfo : buildingInfoArrayList) {
                String title = buildingListInfo.getBuildingTitle();
                String number = String.valueOf(buildingListInfo.getBuilingNumber());
                if (title.toLowerCase().contains(charText)) {
                    items.add(buildingListInfo);
                }
                else if (number.toLowerCase().contains(charText)){
                    items.add(buildingListInfo);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class BuildingViewHolder extends RecyclerView.ViewHolder {

        TextView buildingTitle, buildingNumber;

        public BuildingViewHolder(@NonNull View itemView) {
            super(itemView);

            buildingTitle = itemView.findViewById(R.id.building_title);
            buildingNumber = itemView.findViewById(R.id.building_number);
        }
    }

}