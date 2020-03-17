package delilah.personal.inumapus;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import delilah.personal.inumapus.Adapter.FloorAdapter;
import delilah.personal.inumapus.model.FloorModel;
import delilah.personal.inumapus.network.NetworkController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FloorActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloorAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        FloorInformation();
    }

    public void FloorInformation() {
        Intent intent = getIntent();
        String number = intent.getExtras().getString("number");

        NetworkController.getInstance().getApiService().getFloor(number).enqueue(new Callback<ArrayList<FloorModel>>() {
            @Override
            public void onResponse(Call<ArrayList<FloorModel>> call, Response<ArrayList<FloorModel>> response) {
                ArrayList<FloorModel> floorModel = response.body();

                String[] floorList = null;
                String buildingId = null;
                int max;
                int index;

                for (int i = 0; i < floorModel.size(); i++) {
                    buildingId = floorModel.get(i).buildingId;
                    max = floorModel.get(i).max;

                    if (floorModel.get(i).basement == 1) {
                        floorList = new String[max + 1];
                        floorList[0] = "지하 1";
                        index = 1;
                    } else {
                        floorList = new String[max];
                        index = 0;
                    }
                    for (int j = 1; j <= floorModel.get(i).max; j++,index++) {
                        floorList[index] = String.valueOf(j);
                    }
                }
                adapter = new FloorAdapter(FloorActivity.this, floorList, buildingId);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<FloorModel>> call, Throwable t) {

            }
        });
    }
}