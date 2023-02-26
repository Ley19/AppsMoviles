package com.example.reproductor2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.reproductor2.R;
import com.example.reproductor2.VideosModelo;
import com.example.reproductor2.adapter.FolderAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> folderLista = new ArrayList<>();
    private ArrayList<VideosModelo> videoLista = new ArrayList<>();
    FolderAdapter folderAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.folder_recyclerView);
        videoLista = buscarVideos(this);
        if (folderLista!=null && folderLista.size()>0 && videoLista!=null){
            folderAdapter = new FolderAdapter(folderLista, videoLista, this);
            recyclerView.setAdapter(folderAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this,
                    RecyclerView.VERTICAL, false));
        }else{
            Toast.makeText(this, "No se encontro ningun video en el folder", Toast.LENGTH_SHORT).show();
        }


    }

    private ArrayList<VideosModelo> buscarVideos(Context context){
        ArrayList<VideosModelo> videosModelos = new ArrayList<>();

        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        String ordenarPor = MediaStore.Video.Media.DATE_ADDED + " DESC";

        String[] proyeccion = {
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.SIZE,
                MediaStore.Video.Media.HEIGHT,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.RESOLUTION
        };

        Cursor cursor = context.getContentResolver().query(uri, proyeccion, null, null, ordenarPor);
        
        if(cursor != null){
            while(cursor.moveToNext()){
                String id = cursor.getString(0);
                String path = cursor.getString(1);
                String titulo = cursor.getString(2);
                String size = cursor.getString(3);
                String resolucion = cursor.getString(4);
                String duracion = cursor.getString(5);
                String displayName = cursor.getString(6);
                String width_height = cursor.getString(7);
                
                VideosModelo videoFiles = new VideosModelo(id, path, titulo, size, resolucion, duracion, displayName, width_height);
                
                int slashFirsIndex = path.lastIndexOf("/");
                String subString = path.substring(0, slashFirsIndex);
                
                if(!folderLista.contains(subString)){
                    folderLista.add(subString);
                }
                
                videosModelos.add(videoFiles);
                
            }
            cursor.close();
        }

        return videosModelos;

    }


}