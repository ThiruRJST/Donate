package com.coerente.donate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

public class registerActivity extends AppCompatActivity {

    private TextInputEditText i1,i2,i3,i4;
    private MaterialButton submit;
    private MaterialTextView loginlink;
    private static final String url = "https://nomizocoders.000webhostapp.com/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        i1 = findViewById(R.id.input1);
        i2 = findViewById(R.id.input2);
        i3 = findViewById(R.id.input3);
        i4 = findViewById(R.id.input4);
        submit = findViewById(R.id.signup);
        loginlink = findViewById(R.id.loglink);

        loginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lgindiv = new Intent(registerActivity.this,loginActivity.class);
                startActivity(lgindiv);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = i1.getText().toString().trim();
                final String phone = i2.getText().toString().trim();
                final String pass = i3.getText().toString().trim();
                final String city = i4.getText().toString().trim();
                registerUser(name,phone,pass,city,v);

            }
        });




    }
    private void registerUser(String a, String b, String c,String d, final View v1){
        RequestQueue rq = Volley.newRequestQueue(registerActivity.this);
        String url1 = url+"?Name="+a+"&Phone="+b+"&Password="+c+"&City="+d;
        StringRequest sr = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("User Already Exists")){
                    Snackbar snackbar = Snackbar.make(v1,"Already Existing User",BaseTransientBottomBar.LENGTH_SHORT);
                    snackbar.show();
                }
                if (response.equals("1")){
                    Snackbar sm = Snackbar.make(v1,"Registered Succefully", BaseTransientBottomBar.LENGTH_SHORT);
                    sm.show();
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent tologin =  new Intent(registerActivity.this,loginActivity.class);
                            startActivity(tologin);


                        }
                    },2000);

                }
                else{
                    Snackbar sm = Snackbar.make(v1,"Registration unsuccessful",BaseTransientBottomBar.LENGTH_SHORT);
                    sm.show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Snackbar sm = Snackbar.make(v1,error.toString(),BaseTransientBottomBar.LENGTH_INDEFINITE);
                sm.show();

            }

        });
        rq.add(sr);
    }
}
