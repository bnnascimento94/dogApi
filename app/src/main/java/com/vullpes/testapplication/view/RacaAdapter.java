package com.vullpes.testapplication.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vullpes.testapplication.R;

import java.util.List;

public class RacaAdapter extends RecyclerView.Adapter<RacaAdapter.RacaViewHolder>  {
    List<String> dados;
    Bitmap raw;

    public RacaAdapter(List<String> dados){
        this.dados = dados;
    }


    public static class RacaViewHolder extends RecyclerView.ViewHolder {
        TextView valor;
        public View view;
        Context context;
        RacaViewHolder(final View itemView) {
            super(itemView);
            this.view = itemView;
            valor = itemView.findViewById(R.id.txt_raca);
            context = itemView.getContext();
        }
    }


    @NonNull
    @Override
    public RacaAdapter.RacaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.raca_item, viewGroup, false);
        RacaViewHolder cvh = new RacaViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final RacaAdapter.RacaViewHolder racaViewHolder, final int i) {
        racaViewHolder.valor.setText(dados.get(i));
        racaViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProFormulario = new Intent(view.getContext(), PhotosActivity.class );
                intentVaiProFormulario.putExtra("nomeRaca",dados.get(i));
                view.getContext().startActivity(intentVaiProFormulario);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
}
