package delilah.personal.inumapus.Bin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import delilah.personal.inumapus.Info.OfficeListInfo;
import delilah.personal.inumapus.R;
import delilah.personal.inumapus.model.OfficeModel;
import retrofit2.Callback;

public class FakeOfficeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Callback<ArrayList<OfficeModel>> o_context;

    private List<OfficeListInfo> items = null;
    private ArrayList<OfficeListInfo> officeListInfoArrayList;

    public FakeOfficeAdapter(Callback<ArrayList<OfficeModel>> context, List<OfficeListInfo> items) {
        this.o_context = context;
        this.items = items;
        officeListInfoArrayList = new ArrayList<>();
        officeListInfoArrayList.addAll(items);
    }

    // RecyclerView.ViewHolder 에 override 되는 methods
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.office_listview_row, null);

        return new OfficeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        final OfficeListInfo item = items.get(position);

        OfficeViewHolder officeViewHolder = (OfficeViewHolder) viewHolder;

        officeViewHolder.officeNumber.setText(item.getOfficeNumber());
        officeViewHolder.officeTitle.setText(item.getOfficeTitle());
    }


    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        items.clear();
        if (charText.length() == 0) {
            items.addAll(officeListInfoArrayList);
        } else {
            for (OfficeListInfo officeListInfo : officeListInfoArrayList) {
                String title = officeListInfo.getOfficeTitle();
                String number = officeListInfo.getOfficeNumber();
                if (title.toLowerCase().contains(charText)) {
                    items.add(officeListInfo);
                }
                else if (number.toLowerCase().contains(charText)){
                    items.add(officeListInfo);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class OfficeViewHolder extends RecyclerView.ViewHolder {

        TextView officeNumber, officeTitle;

        public OfficeViewHolder(@NonNull View itemView) {
            super(itemView);
            officeNumber = itemView.findViewById(R.id.office_number);
            officeTitle = itemView.findViewById(R.id.office_title);
        }
    }
}