package com.tarunguptaraja.raktdaan.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.net.sip.SipSession;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.tarunguptaraja.raktdaan.R;
import com.tarunguptaraja.raktdaan.adapters.RequestAdaptor;
import com.tarunguptaraja.raktdaan.dataModels.RequestDataModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<RequestDataModel> requestDataModels;
    private RequestAdaptor requestAdaptor;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override            public boolean onMenuItemClick(MenuItem menuItem) {
//                if(menuItem.getItemId() == R.id.search_button){
//                    startActivity(new Intent(MainActivity.this,SearchActivity.class));
//                }
//                return false;
//            }
//        });
        recyclerView=findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        requestAdaptor = new RequestAdaptor(requestDataModels,this);
        recyclerView.setAdapter(requestAdaptor);
        populateHomePage();

    }
    private void populateHomePage(){
        RequestDataModel requestDataModel = new RequestDataModel("This site can’t be reached www.google.com took too long to respond. Try: Checking the connection Checking the proxy and the firewall Running Windows Network Diagnostics + ERR_CONNECTION_TIMED_OUT","https://cdn.pixabay.com/photo/2014/12/28/13/20/wordpress-581849_960_720.jpg");
        requestDataModels.add(requestDataModel);
//        requestDataModels.add(requestDataModel);    requestDataModels.add(requestDataModel);
//        requestDataModels.add(requestDataModel);    requestDataModels.add(requestDataModel);    requestDataModels.add(requestDataModel);
//        requestDataModels.add(requestDataModel);    requestDataModels.add(requestDataModel);    requestDataModels.add(requestDataModel);
//        requestDataModels.add(requestDataModel);    requestDataModels.add(requestDataModel);    requestDataModels.add(requestDataModel);
        requestAdaptor.notifyDataSetChanged();
    }

}