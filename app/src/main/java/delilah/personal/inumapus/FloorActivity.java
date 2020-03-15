package delilah.personal.inumapus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

import delilah.personal.inumapus.Adapter.FloorAdapter;
import delilah.personal.inumapus.model.FloorModel;
import delilah.personal.inumapus.network.NetworkController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FloorActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloorAdapter adapter;

    String number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        number = Objects.requireNonNull(intent.getExtras()).getString("number");
        Log.d("확인.get",number);

        FloorInformation();
    }

    public void FloorInformation() {
        NetworkController.getInstance().getApiService().getFloor(number).enqueue(new Callback<FloorModel>() {
            @Override
            public void onResponse(@NonNull Call<FloorModel> call, @NonNull Response<FloorModel> response) {
                FloorModel floorModel = response.body();
                ArrayList<String> floorList = new ArrayList<>();

                Log.d("확인.f", String.valueOf(floorModel));

                if (floorModel.getBasement() == 1) {
                    floorList.add("지하1");
                }
                for (int i = 1; i <= floorModel.getMax(); i++) {
                    floorList.add(String.valueOf(i));
                }
                adapter = new FloorAdapter(FloorActivity.this, floorList, floorModel.getNumber());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<FloorModel> call, Throwable t) {

            }
        });
    }
}