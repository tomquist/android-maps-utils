package com.google.maps.android.utils.demo.model;

import com.google.maps.android.utils.demo.BuildConfig;

import de.quist.app.maps.model.LatLng;
import de.quist.app.maps.utils.clustering.ClusterItem;

public class MyItem implements ClusterItem {
    private final LatLng mPosition;

    public MyItem(double lat, double lng) {
        mPosition = BuildConfig.MAP_BINDING.newLatLng(lat, lng);
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }
}
