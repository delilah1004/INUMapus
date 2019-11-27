package delilah.personal.inumapus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import delilah.personal.inumapus.Adapter.OfficeAdapter;
import delilah.personal.inumapus.Info.OfficeListInfo;
import delilah.personal.inumapus.model.OfficeModel;
import delilah.personal.inumapus.network.NetworkController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfficeActivity extends AppCompatActivity {

    RecyclerView officeRecyclerView;
    RecyclerView.LayoutManager officeLayoutManager;

    private OfficeAdapter officeAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office);

        officeRecyclerView = findViewById(R.id.office_recycler_view);

        officeLayoutManager = new LinearLayoutManager(this);
        officeRecyclerView.setHasFixedSize(true);
        officeRecyclerView.setLayoutManager(officeLayoutManager);

        OfficeInformation();
    }

    public void OfficeInformation() {

        Intent intent = getIntent();
        int number = intent.getExtras().getInt("number");
        Log.d("intent값 확인", String.valueOf(number));

        Call<ArrayList<OfficeModel>> office = NetworkController.getInstance().getNetworkInterface().getOfficeListByBuilding(number);

        office.enqueue(new Callback<ArrayList<OfficeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<OfficeModel>> call, Response<ArrayList<OfficeModel>> response) {
                ArrayList<OfficeModel> office = response.body();

                ArrayList<OfficeListInfo> officeInfoArrayList = new ArrayList<>();

                for (int i = 0; i < office.size(); i++) {

                    Log.d("사무실.번호", office.get(i).roomId);
                    Log.d("사무실.이름", String.valueOf(office.get(i).title));

                    officeInfoArrayList.add(new OfficeListInfo(office.get(i).roomId, String.valueOf(office.get(i).title)));
                }

                officeAdapter = new OfficeAdapter(this, officeInfoArrayList);
                officeRecyclerView.setAdapter(officeAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<OfficeModel>> call, Throwable t) {

            }
        });
    }
}

