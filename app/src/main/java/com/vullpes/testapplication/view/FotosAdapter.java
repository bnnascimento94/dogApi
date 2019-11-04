package com.vullpes.testapplication.view;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vullpes.testapplication.R;
import com.vullpes.testapplication.Utils;

import java.util.List;

public class FotosAdapter extends RecyclerView.Adapter<FotosAdapter.FotoViewHolder> {
    private View v;
    private List<Bitmap> valores;
    public FotosAdapter(List<Bitmap> valores){
        this.valores = valores;
    }


    @NonNull
    @Override
    public FotosAdapter.FotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fotos_item, parent,false);
        FotosAdapter.FotoViewHolder fotoViewHolder = new FotosAdapter.FotoViewHolder(v);
        return fotoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FotosAdapter.FotoViewHolder holder, int position) {
        holder.imageView.setImageBitmap(valores.get(position));
    }

    @Override
    public int getItemCount() {
        return valores.size();
    }

    public static class FotoViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        FotoViewHolder(final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

}

