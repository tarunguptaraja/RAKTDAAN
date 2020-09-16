package com.tarunguptaraja.raktdaan.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


    }
    public void register (View v){
        startActivity(new Intent(SignInActivity.this, RegisterActivity.class));
        SignInActivity.this.finish();
    }

    public void login(View v){
        EditText phone = findViewById(R.id.mobile);
        EditText password = (EditText) findViewById(R.id.password);
        phone.setError(null);
        password.setError(null);
        final String num,pass;
        num=phone.getText().toString();
        pass=password.getText().toString();
        if(isvalid(num,pass)){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.login_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (!response.equals("Invalid Credentials")) {
                        Toast.makeText(SignInActivity.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignInActivity.this, MainActivity.class));
                        SignInActivity.this.finish();
                    } else {
                        Toast.makeText(SignInActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(SignInActivity.this, "Something went wrong: "+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String,String> getParams() throws AuthFailureError{
                    Map<String, String> params = new HashMap<>();
                    params.put("password", pass);
                    params.put("number", num);
                    return params;
                }
            };
            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        }
    }
    public boolean isvalid(String num, String pass){
        if(num.length()!=10){
            showmassege("Invalid Mobile Number.");
            return false;
        }else if(pass.length()<6){
            showmassege("Invalid Password.");
            return false;
        }
        return true;
    }

    private void showmassege(String mgs) {
        Toast.makeText(SignInActivity.this, mgs,Toast.LENGTH_SHORT).show();
    }
}