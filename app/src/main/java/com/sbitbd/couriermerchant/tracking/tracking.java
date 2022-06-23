package com.sbitbd.couriermerchant.tracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sbitbd.couriermerchant.R;
import com.sbitbd.couriermerchant.databinding.ActivityTrackingBinding;
import com.sbitbd.couriermerchant.model.trackModel;

public class tracking extends AppCompatActivity {

    private ActivityTrackingBinding binding;
    private String trackingID;
    private DatabaseReference databaseReference;
    private trackModel trackmodel = new trackModel();
    private MaterialCardView back;
    private Marker marker;
    private TextView trackt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrackingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initView();
    }

    private void initView() {
        try {
            trackingID = getIntent().getStringExtra("id");
            back = binding.backId;
            trackt = binding.trackt;
            back.setOnClickListener(v -> finish());

            SupportMapFragment mapFragment =
                    (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            if (mapFragment != null) {
                mapFragment.getMapAsync(callback);
            }
        } catch (Exception e) {
        }
    }

    private OnMapReadyCallback callback = googleMap -> {
        UiSettings mUiSettings = googleMap.getUiSettings();
        googleMap.setTrafficEnabled(true);
        googleMap.setBuildingsEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(true);
        mUiSettings.setTiltGesturesEnabled(true);
        mUiSettings.setRotateGesturesEnabled(true);
        googleMap.setPadding(0, 120, 0, 0);
        getLocation(googleMap);
    };

    private void getLocation(GoogleMap googleMap) {
        try {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("order").child(trackingID);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.getValue(trackModel.class) != null) {
                        trackmodel = snapshot.getValue(trackModel.class);
                        LatLng location = new LatLng(Double.parseDouble(trackmodel.getLat()), Double.parseDouble(trackmodel.getLon()));
                        if (marker == null) {
                            marker = googleMap.addMarker(new MarkerOptions().position(location).title("Rider Location")
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo_icon))
                                    .anchor(0.5f, 0.5f).flat(false));
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
                        } else {
                            MerkerAnimation.animateMarkerToGB(marker, location, new LatLngInterpolator.Spherical());
                            googleMap.animateCamera(CameraUpdateFactory.newLatLng(location));
                        }
                    }else {
                        Toast.makeText(tracking.this, "Tracking Info not found.", Toast.LENGTH_SHORT).show();
                        trackt.setText(R.string.track_not);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("ddd", error.getMessage());
                }
            });
        } catch (Exception e) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        System.gc();
    }
}