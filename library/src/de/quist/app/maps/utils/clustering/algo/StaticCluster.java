package de.quist.app.maps.utils.clustering.algo;

import de.quist.app.maps.utils.clustering.Cluster;
import de.quist.app.maps.utils.clustering.ClusterItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.quist.app.maps.model.LatLng;

/**
 * A cluster whose center is determined upon creation.
 */
public class StaticCluster<T extends ClusterItem> implements Cluster<T> {
    private final LatLng mCenter;
    private final List<T> mItems = new ArrayList<T>();

    public StaticCluster(LatLng center) {
        mCenter = center;
    }

    public boolean add(T t) {
        return mItems.add(t);
    }

    @Override
    public LatLng getPosition() {
        return mCenter;
    }

    public boolean remove(T t) {
        return mItems.remove(t);
    }

    @Override
    public Collection<T> getItems() {
        return mItems;
    }

    @Override
    public int getSize() {
        return mItems.size();
    }

    @Override
    public String toString() {
        return "StaticCluster{" +
                "mCenter=" + mCenter +
                ", mItems.size=" + mItems.size() +
                '}';
    }
}