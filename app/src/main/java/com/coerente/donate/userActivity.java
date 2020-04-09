package com.coerente.donate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class userActivity extends AppCompatActivity {

    final Fragment frag1 =  new Home_Fragment();

    private BottomNavigationView btm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        btm = findViewById(R.id.btmbar);
        btm.setSelectedItemId(R.id.home);

        btm.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();


                if (id==R.id.home){
                    /*Intent homeintent = new Intent(userActivity.this,Home_Activity.class);
                    startActivity(homeintent);
                    overridePendingTransition(0,0);
                    finish();*/

                    Home_Fragment frag = new Home_Fragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame,frag);
                    fragmentTransaction.commit();
                }
                if (id==R.id.account){
                    Account_Fragment frag2 = new Account_Fragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame,frag2);
                    fragmentTransaction.commit();

                }
                if (id==R.id.about){
                    About_Fragment frag3 = new About_Fragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame,frag3);
                    fragmentTransaction.commit();


                }


                return true;
            }
        });




    }

}
