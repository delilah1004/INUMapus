package delilah.personal.inumapus;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import delilah.personal.inumapus.Adapter.PhoneBookAdapter;
import delilah.personal.inumapus.Info.PhoneBookInfo;
import delilah.personal.inumapus.model.EmployeeModel;
import delilah.personal.inumapus.network.NetworkController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneBookActivity extends AppCompatActivity {

    RecyclerView phoneBookRecyclerView;
    RecyclerView.LayoutManager phoneBookLayoutManager;

    private PhoneBookAdapter phoneBookAdapter;
    private EditText editSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebook);

        phoneBookRecyclerView = findViewById(R.id.phone_book_recycler_view);
        editSearch = findViewById(R.id.phone_search);

        phoneBookLayoutManager = new LinearLayoutManager(this);
        phoneBookRecyclerView.setHasFixedSize(true);
        phoneBookRecyclerView.setLayoutManager(phoneBookLayoutManager);

        PhoneBookInformation();

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = editSearch.getText().toString()
                        .toLowerCase(Locale.getDefault());
                phoneBookAdapter.filter(text);
            }
        });
    }

    public void PhoneBookInformation() {
        Call<ArrayList<EmployeeModel>> employee = NetworkController.getInstance().getNetworkInterface().getEmployeeInfo();
        employee.enqueue(new Callback<ArrayList<EmployeeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<EmployeeModel>> call, Response<ArrayList<EmployeeModel>> response) {
                ArrayList<EmployeeModel> employee = response.body();

                List<PhoneBookInfo> phoneBookInfoArrayList = new ArrayList<>();

                for (int i = 0; i < employee.size(); i++) {

                    Log.d("직원.아이디", String.valueOf(employee.get(i).id));
                    Log.d("직원.소속-단과대학이름", employee.get(i).detailOrgan);
                    Log.d("직원.직위", employee.get(i).position);
                    Log.d("직원.이름", employee.get(i).name);
                    Log.d("직원.전화번호", employee.get(i).telephone);

                    phoneBookInfoArrayList.add(new PhoneBookInfo(employee.get(i).detailOrgan, employee.get(i).position, employee.get(i).name, employee.get(i).telephone));
                }

                phoneBookAdapter = new PhoneBookAdapter(this, phoneBookInfoArrayList);
                phoneBookRecyclerView.setAdapter(phoneBookAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<EmployeeModel>> call, Throwable t) {

            }
        });
    }
}