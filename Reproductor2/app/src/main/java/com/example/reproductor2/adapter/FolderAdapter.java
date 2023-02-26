package com.example.reproductor2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reproductor2.R;
import com.example.reproductor2.activity.VideosFolder;
import com.example.reproductor2.VideosModelo;

import java.util.ArrayList;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.MyViewHolder> {

    private ArrayList<String> folderNombre;
    private ArrayList<VideosModelo> videosModelos;
    private Context context;

    public FolderAdapter(ArrayList<String> folderNombre, ArrayList<VideosModelo> videosModelos, Context context) {
        this.folderNombre = folderNombre;
        this.videosModelos = videosModelos;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.folder_view, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        int index = folderNombre.get(position).lastIndexOf("/");
        String folderNombres = folderNombre.get(position).substring(index+1);
        holder.nombre.setText(folderNombres);
        holder.cantidad.setText(String.valueOf(cantidadVideos(folderNombre.get(position))));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VideosFolder.class);
                intent.putExtra("folderNombre", folderNombre.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return folderNombre.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nombre, cantidad;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.folderNombre);
            cantidad = itemView.findViewById(R.id.videosCantidad);

        }
    }

    int cantidadVideos(String folders){
        int contar = 0;

        for(VideosModelo model: videosModelos){

            if(model.getPath().substring(0, model.getPath()
                    .lastIndexOf("/")).endsWith(folders)){
                contar++;
            }


        }
        return contar;
    }
}
