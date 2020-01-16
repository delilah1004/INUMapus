package delilah.personal.inumapus;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import delilah.personal.inumapus.Adapter.BuildingAdapter;
import delilah.personal.inumapus.Info.BuildingListInfo;
import delilah.personal.inumapus.model.BuildingModel;
import delilah.personal.inumapus.network.NetworkController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuildingActivity extends AppCompatActivity {

    ListView buildingListView;

    private BuildingAdapter buildingAdapter;

    private EditText editSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);

        buildingListView = findViewById(R.id.building_list_view);
        editSearch = findViewById(R.id.building_search);

        BuildingInformation();

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editSearch.getText().toString()
                        .toLowerCase(Locale.getDefault());
                buildingAdapter.filter(text);
            }
        });
    }

    public void BuildingInformation() {
        Call<ArrayList<BuildingModel>> building = NetworkController.getInstance().getNetworkInterface().getBuildingInfo();

        building.enqueue(new Callback<ArrayList<BuildingModel>>() {
            @Override
            public void onResponse(Call<ArrayList<BuildingModel>> call, Response<ArrayList<BuildingModel>> response) {
                ArrayList<BuildingModel> building = response.body();

                List<BuildingListInfo> buildingInfoArrayList = new ArrayList<>();

                for (int i = 0; i < building.size(); i++) {

                    Log.d("건물.건물명", building.get(i).title);
                    Log.d("건물.번호", String.valueOf(building.get(i).id));

                    buildingInfoArrayList.add(new BuildingListInfo(building.get(i).title, building.get(i).id));
                }

                buildingAdapter = new BuildingAdapter(this, buildingInfoArrayList);
                buildingListView.setAdapter(buildingAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<BuildingModel>> call, Throwable t) {

            }
        });
    }
}