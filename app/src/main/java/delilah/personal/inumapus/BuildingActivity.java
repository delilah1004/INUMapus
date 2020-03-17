package delilah.personal.inumapus;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import delilah.personal.inumapus.Adapter.BuildingAdapter;
import delilah.personal.inumapus.model.BuildingModel;
import delilah.personal.inumapus.network.NetworkController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuildingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BuildingAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        BuildingInformation();
    }

    public void BuildingInformation() {
        NetworkController.getInstance().getApiService().getBuilding().enqueue(new Callback<ArrayList<BuildingModel>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<BuildingModel>> call, @NonNull Response<ArrayList<BuildingModel>> response) {
                ArrayList<BuildingModel> building = response.body();

                adapter = new BuildingAdapter(BuildingActivity.this, building);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<BuildingModel>> call, Throwable t) {

            }
        });
    }
}