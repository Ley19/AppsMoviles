package com.example.reproductor2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.reproductor2.R;
import com.example.reproductor2.VideosModelo;
import com.example.reproductor2.adapter.VideosAdapter;

import java.util.ArrayList;
import java.util.Locale;

public class VideosFolder extends AppCompatActivity implements SearchView.OnQueryTextListener {
    
    RecyclerView recyclerView;
    private String nombre;
    private ArrayList<VideosModelo> videosModeloArrayList = new ArrayList<>();
    private VideosAdapter videosAdapter;
    Toolbar toolbar1;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_folder);

        recyclerView = findViewById(R.id.video_recyclerview);

        nombre = getIntent().getStringExtra("folderNombre");
        toolbar1 = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.flecha_iz));
        int index = nombre.lastIndexOf("/");
        String onlyFolderNombre = nombre.substring(index+1);
        toolbar1.setTitle(onlyFolderNombre);
        
        loadVideos();
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_toobar, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        ImageView ivClose = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        ivClose.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.rosita),
                android.graphics.PorterDuff.Mode.SRC_IN);
        searchView.setQueryHint("Buscar Nombre del Video");
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {



        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        String input = newText.toLowerCase();
        ArrayList<VideosModelo> buscarLista = new ArrayList<>();

        for(VideosModelo modelo : videosModeloArrayList){
            if(modelo.getTitulo().toLowerCase().contains(input)){
                buscarLista.add(modelo);
            }
        }
        videosAdapter.actualizarBusquedaLista(buscarLista);

        return false;
    }

    private void loadVideos() {

        videosModeloArrayList = getallVideoFromFolder(this, nombre);
        
        if(nombre != null && videosModeloArrayList.size()>0){
            
            videosAdapter = new VideosAdapter(videosModeloArrayList, this);
            recyclerView.setAdapter(videosAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,
                    false));
        }else{
            Toast.makeText(this, "No se encontro ningun video", Toast.LENGTH_SHORT).show();
        }
        
        
    }

    private ArrayList<VideosModelo> getallVideoFromFolder(Context context, String nombre) {

        ArrayList<VideosModelo> list = new ArrayList<>();

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
                MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Video.Media.RESOLUTION
        };

        String seleccion = MediaStore.Video.Media.DATA + " like?";
        String[] seleccionArg = new String[]{"%"+ nombre + "%"};

        Cursor cursor = context.getContentResolver().query(uri, proyeccion, seleccion, seleccionArg, ordenarPor);

        if(cursor != null){
            while(cursor.moveToNext()){

                String id = cursor.getString(0);
                String path = cursor.getString(1);
                String titulo = cursor.getString(2);
                int size = cursor.getInt(3);
                String resolucion = cursor.getString(4);
                int duracion = cursor.getInt(5);
                String disNombre = cursor.getString(6);
                String BUCKET_DISPLAY_NAME = cursor.getString(7);
                String width_height = cursor.getString(8);

                String human_can_read = null;

                if(size < 1024){
                    human_can_read = String.format(context.getString(R.string.size_in_b), (double) size);
                } else if (size < Math.pow(1024, 2)) {
                    human_can_read = String.format(context.getString(R.string.size_in_kb), (double) size);
                }else if (size < Math.pow(1024, 3)) {
                    human_can_read = String.format(context.getString(R.string.size_in_mb), (double) size);
                }else{
                    human_can_read = String.format(context.getString(R.string.size_in_gb), (double) size);
                }

                String duracion_for;
                int sec = (duracion/100)%60;
                int mit = (duracion/(1000 * 60))%60;
                int hrs = duracion / (1000 * 60 * 60);

                if (hrs == 0){
                    duracion_for = String.valueOf(mit).concat(":".concat(String.format(Locale.UK, "%02d", sec)));
                }else {
                    duracion_for = String.valueOf(hrs).concat(":".concat(String.format(Locale.UK, "%02d", mit)
                            .concat(":".concat(String.format(Locale.UK, "%02d", mit)))));
                }


                VideosModelo Files = new VideosModelo(id, path, titulo, human_can_read, resolucion, duracion_for, disNombre, width_height);

                if(nombre.endsWith(BUCKET_DISPLAY_NAME)){
                    list.add(Files);
                }

            }
            cursor.close();
        }

        return list;

    }

}