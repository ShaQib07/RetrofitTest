package com.shakib.retrofittest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        textView = findViewById(R.id.text);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getDotaData();

        //getMarvelData();

       //getIotaData();

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
                    heroNames[i] = heroes.get(i).getImageurl();
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

            }
        });
    }
}
