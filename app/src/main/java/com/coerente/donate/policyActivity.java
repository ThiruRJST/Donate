package com.coerente.donate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;

public class policyActivity extends AppCompatActivity {


    private MaterialCheckBox ck;
    private MaterialButton b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);


        ck = findViewById(R.id.checkid);
        b1 = findViewById(R.id.proce);
        b1.setEnabled(false);

        ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()){
                    b1.setEnabled(true);
                }
                else{
                    b1.setEnabled(false);
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("UserPolicy",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("Accept",1);
                editor.apply();
                Intent login = new Intent(policyActivity.this,loginActivity.class);
                startActivity(login);
            }
        });


    }
}
