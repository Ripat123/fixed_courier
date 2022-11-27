package com.sbitbd.fixedcourier.card_details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.color.DynamicColors;
import com.sbitbd.fixedcourier.Config.config;
import com.sbitbd.fixedcourier.R;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class details_card extends AppCompatActivity {

    private ImageView imageView,deimg;
    private TextView titel,details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_card);
        DynamicColors.applyToActivityIfAvailable(this);
        initview();
    }

    private void initview(){
        try {
            imageView = findViewById(R.id.details_back);
            titel = findViewById(R.id.textView2);
            details = findViewById(R.id.deta);
            deimg = findViewById(R.id.de_img);

            titel.setText(getIntent().getStringExtra("title"));
            details.setText(getIntent().getStringExtra("details"));

            Picasso.get().load(config.SERVICES_IMG + getIntent().getStringExtra("img"))
                    .fit().centerCrop()
                    .transform(new RoundedCornersTransformation(10,0))
//                            .placeholder(R.drawable.water)
//                            .error(R.drawable.water)
                    .into(deimg);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                    finish();
                }
            });
        }catch (Exception e){
        }
    }

    @Override
    protected void onDestroy() {
        System.gc();
        super.onDestroy();
    }
}