package com.google.maps.android.utils.demo;

import android.widget.TextView;
import android.widget.Toast;

import de.quist.app.maps.utils.SphericalUtil;

import java.util.Arrays;

import de.quist.app.maps.Map;
import de.quist.app.maps.model.Marker;
import de.quist.app.maps.model.Polyline;

public class DistanceDemoActivity extends BaseDemoActivity implements Map.OnMarkerDragListener {
    private TextView mTextView;
    private Marker mMarkerA;
    private Marker mMarkerB;
    private Polyline mPolyline;

    @Override
    protected int getLayoutId() {
        return R.layout.distance_demo;
    }

    @Override
    protected void startDemo() {
        mTextView = (TextView) findViewById(R.id.textView);

        getMap().moveCamera(BuildConfig.MAP_BINDING.cameraUpdateFactory().newLatLngZoom(BuildConfig.MAP_BINDING.newLatLng(-33.8256, 151.2395), 10));
        getMap().setOnMarkerDragListener(this);

        mMarkerA = getMap().addMarker(BuildConfig.MAP_BINDING.newMarkerOptions().position(BuildConfig.MAP_BINDING.newLatLng(-33.9046, 151.155)).draggable(true));
        mMarkerB = getMap().addMarker(BuildConfig.MAP_BINDING.newMarkerOptions().position(BuildConfig.MAP_BINDING.newLatLng(-33.8291, 151.248)).draggable(true));
        mPolyline = getMap().addPolyline(BuildConfig.MAP_BINDING.newPolylineOptions().geodesic(true));

        Toast.makeText(this, "Drag the markers!", Toast.LENGTH_LONG).show();
        showDistance();
    }

    private void showDistance() {
        double distance = SphericalUtil.computeDistanceBetween(mMarkerA.getPosition(), mMarkerB.getPosition());
        mTextView.setText("The markers are " + formatNumber(distance) + " apart.");
    }

    private void updatePolyline() {
        mPolyline.setPoints(Arrays.asList(mMarkerA.getPosition(), mMarkerB.getPosition()));
    }

    private String formatNumber(double distance) {
        String unit = "m";
        if (distance < 1) {
            distance *= 1000;
            unit = "mm";
        } else if (distance > 1000) {
            distance /= 1000;
            unit = "km";
        }

        return String.format("%4.3f%s", distance, unit);
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        showDistance();
        updatePolyline();
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {
        showDistance();
        updatePolyline();
    }
}
