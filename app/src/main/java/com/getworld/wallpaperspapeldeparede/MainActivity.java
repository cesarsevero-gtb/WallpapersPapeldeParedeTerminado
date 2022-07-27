package com.getworld.wallpaperspapeldeparede;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.getworld.wallpaperspapeldeparede.model.ImageModel;
import com.getworld.wallpaperspapeldeparede.model.SearchModel;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageModel> modelClasslist;
    private RecyclerView recyclerView;
    Adapter adapter;
    CardView mnature,mbus,mcar,mtrain,mtrending,manime;
    EditText editText;
    ImageButton search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        recyclerView=findViewById(R.id.recyclerview);
        mnature=findViewById(R.id.nature);
        mbus=findViewById(R.id.bus);
        mcar=findViewById(R.id.car);
        mtrain=findViewById(R.id.train);
        mtrending=findViewById(R.id.treinding);
        manime=findViewById(R.id.anime);
        editText=findViewById(R.id.edittext);
        search=findViewById(R.id.search);

        modelClasslist=new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        adapter=new Adapter(getApplicationContext(),modelClasslist);
        recyclerView.setAdapter(adapter);
        findphotos();


        mnature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="nature";
                getsearchimage(query);
            }
        });
        mbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="bus";
                getsearchimage(query);
            }
        });
        mcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="car";
                getsearchimage(query);
            }
        });
        mtrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="train";
                getsearchimage(query);
            }
        });
        mtrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findphotos();
            }
        });
        manime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="anime";
                getsearchimage(query);
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query=editText.getText().toString().trim().toLowerCase();
                if(query.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter something", Toast.LENGTH_LONG).show();
                }else
                {
                    getsearchimage(query);
                }
            }
        });



    }

    private void getsearchimage(String query) {
        ApiUtilities.getApiInterface().getSearchImage(query,1,80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelClasslist.clear();
                if (response.isSuccessful())
                {
                    modelClasslist.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }else
                {
                    Toast.makeText(getApplicationContext(),"Not able to get",Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }

    private void findphotos() {


        modelClasslist.clear();
        ApiUtilities.getApiInterface().getImage(1,80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {

                if (response.isSuccessful())
                {
                    modelClasslist.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }else
                {
                    Toast.makeText(getApplicationContext(),"Not able to get",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });

    }
}