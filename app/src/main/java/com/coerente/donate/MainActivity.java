package com.coerente.donate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Animation LA;
    TextView Logo;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Full screen method
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);



        Logo = findViewById(R.id.logo);

        LA = AnimationUtils.loadAnimation(this,R.anim.logo_animation);
        LA.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                SharedPreferences sh = getSharedPreferences("UserPolicy",MODE_PRIVATE);
                int in = sh.getInt("Accept",0);
                if (in==1){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent login = new Intent(MainActivity.this,loginActivity.class);
                            startActivity(login);
                            finish();

                        }
                    },2000);

                }
                else {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent policy = new Intent(MainActivity.this, policyActivity.class);
                            startActivity(policy);
                            finish();
                        }
                    }, 2000);

                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        Logo.setAnimation(LA);






    }
}
