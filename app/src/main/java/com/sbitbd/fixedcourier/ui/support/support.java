package com.sbitbd.fixedcourier.ui.support;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sbitbd.fixedcourier.Config.config;
import com.sbitbd.fixedcourier.R;
import com.squareup.picasso.Picasso;


public class support extends Fragment {

    private ImageView imageView;
    private TextView des;
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_support, container, false);
        initView();
        return root;
    }

    private void initView(){
        try {
            imageView = root.findViewById(R.id.help_img);
            des = root.findViewById(R.id.des);

            Picasso.get().load(config.HELP_IMG)
                    .fit().centerInside()
//                            .placeholder(R.drawable.water)
//                            .error(R.drawable.water)
                    .into(imageView);
        }catch (Exception e){
        }
    }
}