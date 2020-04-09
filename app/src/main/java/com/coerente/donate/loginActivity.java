package com.coerente.donate;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;


public class loginActivity extends AppCompatActivity {

    private TextInputEditText inp1,inp2;
    private MaterialButton login;
    private MaterialTextView link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        inp1 = findViewById(R.id.input1);
        inp2 = findViewById(R.id.input2);
        link = findViewById(R.id.reglink);
        login = findViewById(R.id.sign);

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(loginActivity.this,registerActivity.class);
                startActivity(reg);
                finish();
            }
        });




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String phone = inp1.getText().toString();
                final String pass = inp2.getText().toString();

                LoginUser(phone,pass,v);






    }


    private boolean validate(String name, String pass) {


        if (TextUtils.isEmpty(name)){
            inp1.setError("Username cannot be empty");
        }
        else if(TextUtils.isEmpty(pass)){
            inp2.setError("Password cannot be empty");
        }else {
            return true;

        }
        return false;
    }
    void LoginUser(final String a, final String b, final View v){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                RequestQueue queue = Volley.newRequestQueue(loginActivity.this);
                String url = "https://nomizocoders.000webhostapp.com/check.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equals("1")) {
                            Intent serman = new Intent(loginActivity.this,serviceActivity.class);
                            startActivity(serman);
                            finish();

                        }
                        if (response.equals("0")){
                            Intent user = new Intent(loginActivity.this,userActivity.class);
                            startActivity(user);
                            finish();
                        }
                        if(response.equals("-1")){
                            Snackbar snackbar = Snackbar.make(v,"You haven't registered yet",BaseTransientBottomBar.LENGTH_SHORT);
                            snackbar.show();
                            snackbar.setAction("Register", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent regdiv = new Intent(loginActivity.this,registerActivity.class);
                                    startActivity(regdiv);
                                    finish();
                                }
                            });
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        final Snackbar snackbar = Snackbar.make(v,error.toString(),BaseTransientBottomBar.LENGTH_INDEFINITE);
                        snackbar.setAction("OKAY", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                snackbar.dismiss();
                            }
                        });
                        snackbar.show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> map = new HashMap<>();
                        map.put("Phone",a);
                        map.put("Password",b);
                        return map;

                    }
                };
                queue.add(stringRequest);



            }
        },2500);


    }
        });
    }
}
