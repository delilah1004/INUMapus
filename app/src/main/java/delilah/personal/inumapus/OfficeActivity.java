package delilah.personal.inumapus;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

import delilah.personal.inumapus.Adapter.OfficeAdapter;
import delilah.personal.inumapus.model.OfficeModel;
import delilah.personal.inumapus.network.NetworkController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfficeActivity extends AppCompatActivity {

    RecyclerView officeRecyclerView;
    RecyclerView.LayoutManager officeLayoutManager;

    private OfficeAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office);

        officeRecyclerView = findViewById(R.id.office_recycler_view);

        officeLayoutManager = new LinearLayoutManager(this);
        officeRecyclerView.setHasFixedSize(true);
        officeRecyclerView.setLayoutManager(officeLayoutManager);

        Intent intent = getIntent();
        String number = Objects.requireNonNull(intent.getExtras()).getString("buildingId");
        String floor = intent.getExtras().getString("floor");

        OfficeInformation(number, floor);
    }

    public void OfficeInformation(String buildingId, String floorId) {
        NetworkController.getInstance().getApiService().getOffice(buildingId, floorId).enqueue(new Callback<ArrayList<OfficeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<OfficeModel>> call, Response<ArrayList<OfficeModel>> response) {
                ArrayList<OfficeModel> officeModels = response.body();

                adapter = new OfficeAdapter(OfficeActivity.this, officeModels);
                officeRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<OfficeModel>> call, Throwable t) {

            }
        });
    }
}