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
    CardView ccachorro,cgato,ccarros,ccidades,cpaisagem,cpopulares;
    EditText editText;
    ImageButton search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        recyclerView=findViewById(R.id.recyclerview);
        ccachorro=findViewById(R.id.cachorro);
        cgato=findViewById(R.id.gato);
        ccarros=findViewById(R.id.carro);
        ccidades=findViewById(R.id.cidade);
        cpaisagem=findViewById(R.id.paisagem);
        cpopulares=findViewById(R.id.populares);
        editText=findViewById(R.id.edittext);
        search=findViewById(R.id.search);

        modelClasslist=new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        adapter=new Adapter(getApplicationContext(),modelClasslist);
        recyclerView.setAdapter(adapter);
        findphotos();


        ccachorro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="dog";
                getsearchimage(query);
            }
        });
        cgato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="cat";
                getsearchimage(query);
            }
        });
        ccidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="city";
                getsearchimage(query);
            }
        });
        cpaisagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="landscape";
                getsearchimage(query);
            }
        });
        cpopulares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findphotos();
            }
        });
        ccarros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="car";
                getsearchimage(query);
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query=editText.getText().toString().trim().toLowerCase();
                if(query.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Digite ALgo", Toast.LENGTH_LONG).show();
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