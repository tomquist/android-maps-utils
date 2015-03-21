package com.google.maps.android.utils.demo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import de.quist.app.maps.Map;
import de.quist.app.maps.SupportMapFragment;

public abstract class BaseDemoActivity extends FragmentActivity {
    private Map mMap;

    protected int getLayoutId() {
        return R.layout.map;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap != null) {
            return;
        }
        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        if (mMap != null) {
            startDemo();
        }
    }

    /**
     * Run the demo-specific code.
     */
    protected abstract void startDemo();

    protected Map getMap() {
        setUpMapIfNeeded();
        return mMap;
    }
}
