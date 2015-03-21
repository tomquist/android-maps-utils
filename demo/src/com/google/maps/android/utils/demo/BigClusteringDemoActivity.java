package com.google.maps.android.utils.demo;

import java.io.InputStream;
import java.util.List;

import org.json.JSONException;

import android.widget.Toast;

import de.quist.app.maps.utils.clustering.ClusterManager;
import com.google.maps.android.utils.demo.model.MyItem;

import de.quist.app.maps.model.LatLng;

public class BigClusteringDemoActivity extends BaseDemoActivity {
    private ClusterManager<MyItem> mClusterManager;

    @Override
    protected void startDemo() {
        getMap().moveCamera(BuildConfig.MAP_BINDING.cameraUpdateFactory().newLatLngZoom(BuildConfig.MAP_BINDING.newLatLng(51.503186, -0.126446), 10));

        mClusterManager = new ClusterManager<MyItem>(this, BuildConfig.MAP_BINDING, getMap());

        getMap().setOnCameraChangeListener(mClusterManager);
        try {
            readItems();
        } catch (JSONException e) {
            Toast.makeText(this, "Problem reading list of markers.", Toast.LENGTH_LONG).show();
        }
    }

    private void readItems() throws JSONException {
        InputStream inputStream = getResources().openRawResource(R.raw.radar_search);
        List<MyItem> items = new MyItemReader().read(inputStream);
        for (int i = 0; i < 10; i++) {
            double offset = i / 60d;
            for (MyItem item : items) {
                LatLng position = item.getPosition();
                double lat = position.latitude() + offset;
                double lng = position.longitude() + offset;
                MyItem offsetItem = new MyItem(lat, lng);
                mClusterManager.addItem(offsetItem);
            }
        }
    }
}