package com.vullpes.testapplication.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vullpes.testapplication.parsers.FotosParcer;
import com.vullpes.testapplication.apiConnection.HttpConection;
import com.vullpes.testapplication.R;

import java.util.ArrayList;
import java.util.List;


public class PhotosActivity extends AppCompatActivity {
    RecyclerView rv;
    ProgressBar progressBar;
    FotosAdapter fotosAdapter;
    String nome;
    int qtdeItens = 10;
    int currentItems, totalItens, scrollOutItems;
    private boolean loading = true;
    LinearLayoutManager llm;
    List<Bitmap> fotos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        Toolbar toolbar1 = findViewById(R.id.toolbar);
        rv = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        llm = new LinearLayoutManager(PhotosActivity.this);


        Intent intent = getIntent();
        nome = (String) intent.getSerializableExtra("nomeRaca");

        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("Fotos da Raça "+nome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        final LoadFotos  loadFotos = new LoadFotos();
        loadFotos.execute(nome,"10");

        rv.setLayoutManager(llm);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    loading= true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if(dy > 0) //check for scroll down
                {
                    currentItems = llm.getChildCount();
                    totalItens = llm.getItemCount();
                    scrollOutItems = llm.findFirstVisibleItemPosition();

                    if (loading)
                    {
                        if ( (currentItems + scrollOutItems) == totalItens)
                        {
                            int qtdeItensPaginado = 10;
                            loading = false;
                            new  LoadFotos().execute(nome,String.valueOf(qtdeItensPaginado));

                        }
                    }
                }
            }
        });


    }

    class LoadFotos extends AsyncTask<String,String,List<Bitmap>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Bitmap> doInBackground(String... strings) {
            String json = HttpConection.getDados(strings[0],strings[1]);
            if(fotos.isEmpty()) {
                fotos = FotosParcer.parseDados(json);
            }else{
                fotos.addAll(FotosParcer.parseDados(json));
            }
            return fotos;
        }


        @Override
        protected void onPostExecute(List<Bitmap> o) {
            super.onPostExecute(o);
            progressBar.setVisibility(View.INVISIBLE);
            fotosAdapter = new FotosAdapter(o);
            rv.setHasFixedSize(true);
            rv.setLayoutManager(llm);
            rv.setAdapter(fotosAdapter);
        }
    }



}
