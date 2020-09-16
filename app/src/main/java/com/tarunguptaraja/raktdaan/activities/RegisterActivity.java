package com.tarunguptaraja.raktdaan.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.tarunguptaraja.raktdaan.R;
import com.tarunguptaraja.raktdaan.utils.Endpoints;
import com.tarunguptaraja.raktdaan.utils.VolleySingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private EditText name,city,pass,num,blood;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.name);
        city = findViewById(R.id.city);
        blood = findViewById(R.id.blood);
        num = findViewById(R.id.number);
        pass = findViewById(R.id.password);
        submit = findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String naam,add,bg,number,password;
                naam = name.getText().toString();
                add=city.getText().toString();
                number=num.getText().toString().trim();
                bg=blood.getText().toString().trim();
                password=pass.getText().toString();
                if(isValid(naam,add,bg,number,password)){
                    try {
                        register(naam,add,number,bg,password);
                    } catch (AuthFailureError authFailureError) {
                        authFailureError.printStackTrace();
                    }
                }
            }
        });
    }
    private void register (final String name, final String city, final String number, final String blood, final String password) throws AuthFailureError {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.register_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("Success")){
//                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit()
//                            .putString("add", add).apply();
                    Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, SignInActivity.class));
                    RegisterActivity.this.finish();
                }else{
                    Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, "Something went wrong:(", Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("city", city);
                params.put("blood", blood);
                params.put("number", number);
                params.put("password", password);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
    private boolean isValid(String name,String city, String bg, String num, String pass){
        List<String> validbg = new ArrayList<>();
        validbg.add("A+");      validbg.add("A-");
        validbg.add("B+");      validbg.add("B-");
        validbg.add("AB+");      validbg.add("AB-");
        validbg.add("O+");      validbg.add("O-");
        if(name.isEmpty()){
            showmessage("Name is Empty.");
            return false;
        }else if(city.isEmpty()){
            showmessage("City is Empty.");
            return false;
        }else if(num.length()!=10){
            showmessage("Mobile Number should be 10 digits.");
            return false;
        }else if(pass.length()<6){
            showmessage("Password length must be at least 6.");
            return false;
        }else if(!validbg.contains(bg)){
            showmessage("Blood Group invalid. Choose from "+validbg);
//            showmessage(bg);
            return false;
        }
        return true;
    }

    private void showmessage(String mgs){
        Toast.makeText(this,mgs,Toast.LENGTH_SHORT).show();
    }
}