package com.example.reproductor2.activity;

import static com.example.reproductor2.adapter.VideosAdapter.videoFolder;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.reproductor2.R;

public class VideoPlayer extends AppCompatActivity {

    int posicion = -1;
    private VideoView videoView;
    LinearLayout one, two, three, four;
    RelativeLayout zoomLayout;
    boolean isOpen = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        videoView = findViewById(R.id.video_View);
        zoomLayout = findViewById(R.id.zoom_layout);
        one = findViewById(R.id.videoView_one_layout);
        two = findViewById(R.id.videoView_two_layout);
        three = findViewById(R.id.videoView_three_layout);
        four = findViewById(R.id.videoView_four_layout);

        zoomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOpen){
                    hideDefaultControles();
                    isOpen = false;
                }else{
                    showDefaultControles();
                    isOpen = true;
                }
            }
        });

        posicion = getIntent().getIntExtra("p", -1);

        String patch = videoFolder.get(posicion).getPath();

        if(patch != null){
            videoView.setVideoPath(patch);
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    videoView.start();
                }
            });
        }else{
            Toast.makeText(this, "Video no Existe", Toast.LENGTH_SHORT).show();
        }

    }

    private void hideDefaultControles(){
        one.setVisibility(View.GONE);
        two.setVisibility(View.GONE);
        three.setVisibility(View.GONE);
        four.setVisibility(View.GONE);

        final Window window = this.getWindow();

        if(window == null ){
            return;
        }

        window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final View decorView = window.getDecorView();

        if (decorView != null){
            int uiOpciones = decorView.getSystemUiVisibility();

            if(Build.VERSION.SDK_INT >= 14){
                uiOpciones |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
            }

            if(Build.VERSION.SDK_INT >= 16){
                uiOpciones |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            }

            if(Build.VERSION.SDK_INT >= 19){
                uiOpciones |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            }

            decorView.setSystemUiVisibility(uiOpciones);

        }

    }

    private void showDefaultControles(){
        one.setVisibility(View.VISIBLE);
        two.setVisibility(View.VISIBLE);
        three.setVisibility(View.VISIBLE);
        four.setVisibility(View.VISIBLE);

        final Window window = this.getWindow();

        if(window == null ){
            return;
        }

        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        final View decorView = window.getDecorView();

        if (decorView != null){
            int uiOpciones = decorView.getSystemUiVisibility();

            if(Build.VERSION.SDK_INT >= 14){
                uiOpciones |= ~View.SYSTEM_UI_FLAG_LOW_PROFILE;
            }

            if(Build.VERSION.SDK_INT >= 16){
                uiOpciones |= ~View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            }

            if(Build.VERSION.SDK_INT >= 19){
                uiOpciones |= ~View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            }

            decorView.setSystemUiVisibility(uiOpciones);

        }

    }

}