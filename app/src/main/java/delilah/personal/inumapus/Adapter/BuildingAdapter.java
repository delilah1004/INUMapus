package delilah.personal.inumapus.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import delilah.personal.inumapus.Info.BuildingListInfo;
import delilah.personal.inumapus.OfficeActivity;
import delilah.personal.inumapus.R;
import delilah.personal.inumapus.model.BuildingModel;
import retrofit2.Callback;

public class BuildingAdapter extends BaseAdapter {

    private Callback<ArrayList<BuildingModel>> b_context;
    LayoutInflater inflater;

    private List<BuildingListInfo> items = null;
    private ArrayList<BuildingListInfo> buildingInfoArrayList;

    public BuildingAdapter(Callback<ArrayList<BuildingModel>> context, List<BuildingListInfo> items) {
        this.b_context = context;
        this.items = items;
        // inflater = LayoutInflater.from(context);
        buildingInfoArrayList = new ArrayList<BuildingListInfo>();
        buildingInfoArrayList.addAll(items);
    }

    public static class BuildingViewHolder {
        TextView building_title, building_number;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public BuildingListInfo getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final BuildingViewHolder buildingViewHolder;
        final BuildingListInfo item = items.get(position);

        /* 'list_view_row_building_list' Layout을 inflate하여 view 참조 획득 */
        if (view == null) {
            buildingViewHolder = new BuildingViewHolder();
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_view_row_building_list,null);

            /* 'list_view_row_building_list'에 정의된 위젯에 대한 참조 획득 */
            buildingViewHolder.building_title = (TextView) view.findViewById(R.id.building_title);
            buildingViewHolder.building_number = (TextView) view.findViewById(R.id.building_number);

        } else {
            buildingViewHolder = (BuildingViewHolder) view.getTag();
        }

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        buildingViewHolder.building_title.setText(item.getBuildingTitle());
        buildingViewHolder.building_number.setText(String.valueOf(item.getBuilingNumber()));

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..) */
        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Toast.makeText(context, item.getBuildingTitle() + " " + item.getBuilingNumber() + "호관", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, OfficeActivity.class);
                intent.putExtra("number", item.getBuilingNumber());
                context.startActivity(intent);
            }
        });

        /*
        final BuildingListInfo item = items.get(position);

        BuildingViewHolder buildingViewHolder = (BuildingViewHolder) viewHolder;

        buildingViewHolder.buildingTitle.setText(item.getBuildingTitle());
        buildingViewHolder.buildingNumber.setText(String.valueOf(item.getBuilingNumber()));

        buildingViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Toast.makeText(context, item.getBuildingTitle() + " " + item.getBuilingNumber() + "호관", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, OfficeActivity.class);

                intent.putExtra("number", item.getBuilingNumber());

                context.startActivity(intent);
            }
        });
         */

        return view;
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
                } else if (number.toLowerCase().contains(charText)) {
                    items.add(buildingListInfo);
                }
            }
        }
        notifyDataSetChanged();
    }
}