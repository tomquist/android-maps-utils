package de.quist.app.maps.utils.clustering;

import java.util.Collection;

import de.quist.app.maps.model.LatLng;

/**
 * A collection of ClusterItems that are nearby each other.
 */
public interface Cluster<T extends ClusterItem> {
    public LatLng getPosition();

    Collection<T> getItems();

    int getSize();
}