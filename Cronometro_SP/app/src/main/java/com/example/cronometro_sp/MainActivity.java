package com.example.cronometro_sp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private int segundos = 0;
    private boolean running = true;
    private List<String> tiempoVuelta = new ArrayList<>();
    private int numeroVuelta = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startTimer();

    }

    public void reiniciar(View view) {
        running = false;
        segundos=0;
        tiempoVuelta.clear(); // Vaciar la lista de tiempos de vuelta
        ListView listView = findViewById(R.id.lv_vuelta);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tiempoVuelta);
        listView.setAdapter(adapter);
        numeroVuelta = 1;
    }

    public void empezar(View view) {
        running = true;
    }

    public void startTimer(){
        final TextView tiempo = findViewById(R.id.tv_tiempo);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int horas = segundos/3600;
                int minutos = (segundos%3600)/60;
                int sec = segundos%60;
                String tiempo_string = String.format("%02d:%02d:%02d", horas, minutos, sec);
                tiempo.setText(tiempo_string);
                if(running){
                    segundos++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

    public void vuelta(View view) {
        String tiempoDeVuelta = ((TextView) findViewById(R.id.tv_tiempo)).getText().toString();

        tiempoDeVuelta = numeroVuelta + ".   " + tiempoDeVuelta;
        tiempoVuelta.add(tiempoDeVuelta);

        numeroVuelta++;

        // Crear un adaptador ArrayAdapter para la lista de tiempos de vuelta
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tiempoVuelta);

        // Obtener el ListView y establecer el adaptador como su adaptador de datos
        ListView listView = findViewById(R.id.lv_vuelta);
        listView.setAdapter(adapter);
    }
}