package com.example.reproductor2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.reproductor2.R;
import com.example.reproductor2.VideosModelo;
import com.example.reproductor2.activity.VideoPlayer;
import com.example.reproductor2.activity.VideosFolder;

import java.util.ArrayList;


public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.MyHolder> {


   public static ArrayList<VideosModelo> videoFolder = new ArrayList<>();
   Context context;


    public VideosAdapter(ArrayList<VideosModelo> videoFolder, Context context) {
        this.videoFolder = videoFolder;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.files_view, parent, false);

        return new VideosAdapter.MyHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        Glide.with(context).load(videoFolder.get(position).getPath()).into(holder.thumbnail);
        holder.titulo.setText(videoFolder.get(position).getTitulo());
        holder.duracion.setText(videoFolder.get(position).getDuracion());
        holder.resolucion.setText(videoFolder.get(position).getResolucion());
        holder.size.setText(videoFolder.get(position).getSize());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VideoPlayer.class);
                intent.putExtra("p", position);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return videoFolder.size();
    }

    public void actualizarBusquedaLista(ArrayList<VideosModelo> buscarLista) {

        videoFolder= new ArrayList<>();
        videoFolder.addAll(buscarLista);
        notifyDataSetChanged();
    }


    public class MyHolder extends RecyclerView.ViewHolder{

        ImageView thumbnail;
        TextView titulo, size, duracion, resolucion;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.thumbnail);
            size = itemView.findViewById(R.id.txt_videoSize);
            duracion = itemView.findViewById(R.id.txt_duracionVideo);
            resolucion = itemView.findViewById(R.id.txt_quality);
            titulo = itemView.findViewById(R.id.txt_tituloVideo);

        }
    }

}
