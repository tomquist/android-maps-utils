package com.google.maps.android.utils.demo.model;

import de.quist.app.maps.utils.clustering.ClusterItem;

import de.quist.app.maps.model.LatLng;

public class Person implements ClusterItem {
    public final String name;
    public final int profilePhoto;
    private final LatLng mPosition;

    public Person(LatLng position, String name, int pictureResource) {
        this.name = name;
        profilePhoto = pictureResource;
        mPosition = position;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }
}
