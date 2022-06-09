package com.sbitbd.couriermerchant.admin_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.sbitbd.couriermerchant.R;

public class admin extends AppCompatActivity {

    RecyclerView support_rec;
    ImageView steam_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        initView();
    }

    private void initView(){
        try {
            support_rec = findViewById(R.id.support_rec);
            steam_back = findViewById(R.id.steam_back);

            steam_back.setOnClickListener(view -> {
                onBackPressed();
                finish();
            });
        }catch (Exception e){
        }
    }
}