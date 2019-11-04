package com.vullpes.testapplication.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vullpes.testapplication.apiConnection.HttpConection;
import com.vullpes.testapplication.R;
import com.vullpes.testapplication.parsers.RacaParcer;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ProgressBar progressBar;
    RecyclerView rv;
    RacaAdapter racaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Raças");
        LoadRacas load = new LoadRacas();
        load.execute();
       /** try {
            List<String>  dados = load.get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        **/
    }

    class LoadRacas extends AsyncTask<String,String,List<String>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<String> doInBackground(String... strings) {
            String json = HttpConection.getDados();
            List<String> racas = RacaParcer.parseDados(json);
            return racas;
        }

        @Override
        protected void onPostExecute(List<String> o) {
            super.onPostExecute(o);
            progressBar.setVisibility(View.INVISIBLE);
            rv.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
            rv.setLayoutManager(llm);
            racaAdapter = new RacaAdapter(o);
            rv.setAdapter(racaAdapter);
        }
    }

}
