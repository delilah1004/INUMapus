package delilah.personal.inumapus.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import delilah.personal.inumapus.R;
import delilah.personal.inumapus.model.OfficeModel;

public class OfficeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<OfficeModel> items;

    public OfficeAdapter(Context context, ArrayList<OfficeModel> items) {
        this.context = context;
        this.items = items;
    }

    private static class OfficeViewHolder extends RecyclerView.ViewHolder {
        TextView roomId, officeTitle;

        private OfficeViewHolder(@NonNull View itemView) {
            super(itemView);

            roomId = itemView.findViewById(R.id.office_number);
            officeTitle = itemView.findViewById(R.id.office_title);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(context).inflate(R.layout.recyclerview_row_office, viewGroup, false);

        return new OfficeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final OfficeViewHolder viewHolder = (OfficeViewHolder) holder;
        final OfficeModel item = items.get(position);

        viewHolder.roomId.setText(item.getRoomId());
        viewHolder.officeTitle.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        items.clear();
        if (charText.length() == 0) {
            items.addAll(items);
        } else {
            for (OfficeModel office : items) {
                String title = office.getTitle();
                String number = office.getRoomId();
                if (title.toLowerCase().contains(charText)) {
                    items.add(office);
                }
                else if (number.toLowerCase().contains(charText)){
                    items.add(office);
                }
            }
        }
        notifyDataSetChanged();
    }
}