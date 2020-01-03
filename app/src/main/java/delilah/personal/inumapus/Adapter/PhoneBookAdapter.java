package delilah.personal.inumapus.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import delilah.personal.inumapus.Info.PhoneBookInfo;
import delilah.personal.inumapus.R;
import delilah.personal.inumapus.model.EmployeeModel;
import retrofit2.Callback;

public class PhoneBookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Callback<ArrayList<EmployeeModel>> e_context;

    private List<PhoneBookInfo> items = null;
    private ArrayList<PhoneBookInfo> phoneBookInfoArrayList;

    public PhoneBookAdapter(Callback<ArrayList<EmployeeModel>> context, List<PhoneBookInfo> items) {
        this.e_context = context;
        this.items = items;
        phoneBookInfoArrayList = new ArrayList<PhoneBookInfo>();
        phoneBookInfoArrayList.addAll(items);
    }

    // RecyclerView.ViewHolder 에 override 되는 methods
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.phonebook_recyclerview_row, null);

        return new PhoneBookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final PhoneBookInfo item = items.get(position);

        PhoneBookViewHolder phoneBookViewHolder = (PhoneBookViewHolder) viewHolder;

        phoneBookViewHolder.detailOrgan.setText(item.getDetailOrgan());
        phoneBookViewHolder.position.setText(item.getPosition());
        phoneBookViewHolder.name.setText(item.getName());
        phoneBookViewHolder.phoneNumber.setText(item.getPhoneNumber());

        phoneBookViewHolder.btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Toast.makeText(context, item.getName() + "의 전화번호입니다.", Toast.LENGTH_SHORT).show();

                String phone = "tel:" + item.getPhoneNumber();
                //Log.d("callcheck",phone);

                Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse(phone));

                context.startActivity(call);
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
            items.addAll(phoneBookInfoArrayList);
        } else {
            for (PhoneBookInfo phoneBookInfo : phoneBookInfoArrayList) {
                String organ = phoneBookInfo.getDetailOrgan();
                String position = phoneBookInfo.getPosition();
                String name = phoneBookInfo.getName();
                String number = phoneBookInfo.getPhoneNumber();
                if (organ.toLowerCase().contains(charText)) {
                    items.add(phoneBookInfo);
                }
                else if (position.toLowerCase().contains(charText)){
                    items.add(phoneBookInfo);
                }
                else if (name.toLowerCase().contains(charText)){
                    items.add(phoneBookInfo);
                }
                else if (number.toLowerCase().contains(charText)){
                    items.add(phoneBookInfo);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class PhoneBookViewHolder extends RecyclerView.ViewHolder {

        TextView detailOrgan, position, name, phoneNumber;
        ImageButton btn_call;

        public PhoneBookViewHolder(@NonNull View itemView) {
            super(itemView);

            detailOrgan = itemView.findViewById(R.id.detail_organ);
            position = itemView.findViewById(R.id.position);
            name = itemView.findViewById(R.id.name_person);
            phoneNumber = itemView.findViewById(R.id.phone_number);

            btn_call = itemView.findViewById(R.id.btn_call_page);
        }
    }
}