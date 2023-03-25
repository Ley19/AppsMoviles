package com.example.practica_parcial3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment = new HomeFragment();
    VideoFragmento videoFragmento = new VideoFragmento();
    ComprasFragmento comprasFragmento = new ComprasFragmento();
    NotificacionesFragment notificacionesFragment = new NotificacionesFragment();
    PerfilFragment perfilFragment = new PerfilFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(homeFragment);

    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.primer_Fragment:
                        loadFragment(homeFragment);
                        return  true;

                case R.id.segundo_Fragment:
                    loadFragment(videoFragmento);
                    return  true;

                case R.id.tercer_Fragment:
                    loadFragment(comprasFragmento);
                    return  true;

                case R.id.cuarto_Fragment:
                    loadFragment(notificacionesFragment);
                    return  true;

                case R.id.quinto_Fragment:
                    loadFragment(perfilFragment);
                    return  true;
            }
            return false;
        }
    };

    public void loadFragment (Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container,fragment);
        fragmentTransaction.commit();

    }
}