package com.coerente.donate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class Home_Activity extends AppCompatActivity {
    private BottomNavigationView btm1;
    private static final String url = "http://nomizocoders.000webhostapp.com/infodel.php";
    ArrayList<String> Name = new ArrayList<>();
    ArrayList<String> Description = new ArrayList<>();
    RecyclerView recyclerView;
    ArrayList<Info> infos;
    private LinearLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);

        btm1 = findViewById(R.id.btmbar1);
        final FrameLayout frameLayout = findViewById(R.id.frame2);
        btm1.setSelected(true);
        getInfo();
        ListView lv = findViewById(R.id.lst);
        ArrayAdapter<String> lvad = new ArrayAdapter<>(Home_Activity.this,android.R.layout.simple_list_item_1,Description);
        lv.setAdapter(lvad);


        btm1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id==R.id.home){
                    Intent hi = new Intent(Home_Activity.this,Home_Activity.class);
                    startActivity(hi);
                    overridePendingTransition(0,0);
                }
                if (id==R.id.account){
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    Account_Fragment frag2 = new Account_Fragment();
                    fragmentTransaction.replace(R.id.frame2,frag2);
                    fragmentTransaction.commit();
                }
                if (id==R.id.about){
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    About_Fragment  frag3 = new About_Fragment();
                    fragmentTransaction.replace(R.id.frame2,frag3);
                    fragmentTransaction.commit();

                }
                return true;
            }
        });




    }
    private void getInfo(){
        RequestQueue requestQueue = Volley.newRequestQueue(Home_Activity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {



                try{
                    for(int i = 0;i<response.length();i++){
                        JSONObject post = response.getJSONObject(i);
                        String nm = post.getString("Name");
                        String dp = post.getString("Description");
                        //Toast.makeText(Home_Activity.this, "Name:"+Name, Toast.LENGTH_SHORT).show();
                        Description.add(dp);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Home_Activity.this,error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
