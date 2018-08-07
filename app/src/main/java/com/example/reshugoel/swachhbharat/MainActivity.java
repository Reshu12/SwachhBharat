package com.example.reshugoel.swachhbharat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView= (ImageView)findViewById(R.id.imagesplash);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.mytransition);
        imageView.startAnimation(animation);
        firebaseAuth=FirebaseAuth.getInstance();
        Thread timer= new Thread(){
            @Override
            public void run() {
                try{
                    sleep(4000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    if (firebaseAuth.getCurrentUser()!=null){
                       final Intent intent= new Intent(MainActivity.this,HomePage.class);
                        startActivity(intent);
                    }
                    else
                    { final Intent intent= new Intent(MainActivity.this,Login.class);
                        startActivity(intent);
                    }
                    finish();
                }
            }
        };

        timer.start();
    }

}
