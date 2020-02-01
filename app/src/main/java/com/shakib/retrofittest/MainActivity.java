package com.shakib.retrofittest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shakib.retrofittest.doodle.Category;
import com.shakib.retrofittest.doodle.CategoryResponse;
import com.shakib.retrofittest.doodle.doodleApi;
import com.shakib.retrofittest.dota.Dota2Hero;
import com.shakib.retrofittest.dota.localhostAPi;
import com.shakib.retrofittest.helpers.RecyclerViewHeroAdapter;
import com.shakib.retrofittest.iota.iotaApi;
import com.shakib.retrofittest.iota.iotaTest;
import com.shakib.retrofittest.marvel.Api;
import com.shakib.retrofittest.marvel.Hero;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ListView listView;
    RecyclerView recyclerView;
    RecyclerViewHeroAdapter adapter;
    Button marvel, dota, iota, doodle;
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        textView = findViewById(R.id.text);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        container = findViewById(R.id.container);

        marvel = findViewById(R.id.marvel_btn);
        marvel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                getMarvelData();
                Toast.makeText(MainActivity.this, "Clicked on marvel", Toast.LENGTH_SHORT).show();
            }
        });

        dota = findViewById(R.id.dota_btn);
        dota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.GONE);
                listView.setVisibility(View.GONE);
                getDotaData();
                Toast.makeText(MainActivity.this, "Clicked on dota", Toast.LENGTH_SHORT).show();
            }
        });

        iota = findViewById(R.id.iota_btn);
        iota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
               getIotaData();
                Toast.makeText(MainActivity.this, "Clicked on iota", Toast.LENGTH_SHORT).show();
            }
        });

        doodle = findViewById(R.id.doodle_btn);
        doodle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.VISIBLE);
                getDoodleData();
                Toast.makeText(MainActivity.this, "Clicked on doodle", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getDoodleData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(doodleApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        doodleApi api = retrofit.create(doodleApi.class);

        Call<CategoryResponse> call = api.getCategoryList();

        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {

                CategoryResponse categoryResponse = response.body();
                List<Category> categoryList = categoryResponse.getCategories();

                String[] categoryNames = new String[categoryList.size()];

                for (int i = 0; i <categoryList.size(); i++){
                    categoryNames[i] = categoryList.get(i).getCategory_name();
                }

                listView.setAdapter(
                        new ArrayAdapter<>(
                                getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                categoryNames
                        )
                );
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
            }
        });
    }

    private void getDotaData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(localhostAPi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        localhostAPi api = retrofit.create(localhostAPi.class);

        Call<List<Dota2Hero>> call = api.getDota2Heroes();

        call.enqueue(new Callback<List<Dota2Hero>>() {
            @Override
            public void onResponse(Call<List<Dota2Hero>> call, Response<List<Dota2Hero>> response) {

                List<Dota2Hero> heroes = response.body();

                /*String[] heroNames = new String[heroes.size()];

                for (int i = 0; i <heroes.size(); i++){
                    heroNames[i] = heroes.get(i).getName();
                }

                listView.setAdapter(
                        new ArrayAdapter<>(
                                getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                heroNames
                        )
                );*/

                adapter = new RecyclerViewHeroAdapter(getApplicationContext(), heroes);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Dota2Hero>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
                Log.v("error", t.getMessage());
            }
        });
    }

    private void getMarvelData() {
        //for practice
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Hero>> call = api.getHeroes();

        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {

                List<Hero> heroes = response.body();

                String[] heroNames = new String[heroes.size()];

                for (int i = 0; i <heroes.size(); i++){
                    heroNames[i] = heroes.get(i).getName();
                }

                listView.setAdapter(
                        new ArrayAdapter<>(
                                getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                heroNames
                        )
                );
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
            }
        });
    }

    private void getIotaData() {
        //iota test
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(iotaApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        iotaApi api = retrofit.create(iotaApi.class);

        Call<iotaTest> call = api.getGroups();

        call.enqueue(new Callback<iotaTest>() {
            @Override
            public void onResponse(Call<iotaTest> call, Response<iotaTest> response) {

                iotaTest groups = response.body();

                String groupMembers = "Group A: "+groups.getGroupA()+"\n"+
                        "Group B: "+groups.getGroupB()+"\n"+
                        "Group C: "+groups.getGroupC()+"\n"+
                        "Group D: "+groups.getGroupD()+"\n"+
                        "Group E: "+groups.getGroupE()+"\n"+
                        "Group F: "+groups.getGroupF()+"\n"+
                        "Group G: "+groups.getGroupG();

                textView.setText(groupMembers);

            }

            @Override
            public void onFailure(Call<iotaTest> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}
