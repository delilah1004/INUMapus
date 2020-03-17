package delilah.personal.inumapus;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import delilah.personal.inumapus.Adapter.PhoneBookAdapter;
import delilah.personal.inumapus.model.PhoneModel;
import delilah.personal.inumapus.network.NetworkController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneBookActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PhoneBookAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebook);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.phone_book_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        PhoneBookInformation();

        EditText editSearch = findViewById(R.id.phone_search);
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void PhoneBookInformation() {
        NetworkController.getInstance().getApiService().getPhoneInfo().enqueue(new Callback<ArrayList<PhoneModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PhoneModel>> call, Response<ArrayList<PhoneModel>> response) {
                ArrayList<PhoneModel> phone = response.body();

                adapter = new PhoneBookAdapter(PhoneBookActivity.this, phone);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<PhoneModel>> call, Throwable t) {

            }
        });
    }
}