package com.google.maps.android.utils.demo;

import de.quist.app.maps.utils.ui.IconGenerator;

import de.quist.app.maps.model.LatLng;
import de.quist.app.maps.model.MarkerOptions;

public class IconGeneratorDemoActivity extends BaseDemoActivity {

    @Override
    protected void startDemo() {
        getMap().moveCamera(BuildConfig.MAP_BINDING.cameraUpdateFactory().newLatLngZoom(BuildConfig.MAP_BINDING.newLatLng(-33.8696, 151.2094), 10));

        IconGenerator iconFactory = new IconGenerator(this);
        addIcon(iconFactory, "Default", BuildConfig.MAP_BINDING.newLatLng(-33.8696, 151.2094));

        iconFactory.setStyle(IconGenerator.STYLE_BLUE);
        addIcon(iconFactory, "Blue style", BuildConfig.MAP_BINDING.newLatLng(-33.9360, 151.2070));

        iconFactory.setRotation(90);
        iconFactory.setStyle(IconGenerator.STYLE_RED);
        addIcon(iconFactory, "Rotated 90 degrees", BuildConfig.MAP_BINDING.newLatLng(-33.8858, 151.096));

        iconFactory.setContentRotation(-90);
        iconFactory.setStyle(IconGenerator.STYLE_PURPLE);
        addIcon(iconFactory, "Rotate=90, ContentRotate=-90", BuildConfig.MAP_BINDING.newLatLng(-33.9992, 151.098));

        iconFactory.setRotation(0);
        iconFactory.setContentRotation(90);
        iconFactory.setStyle(IconGenerator.STYLE_GREEN);
        addIcon(iconFactory, "ContentRotate=90", BuildConfig.MAP_BINDING.newLatLng(-33.7677, 151.244));
    }

    private void addIcon(IconGenerator iconFactory, String text, LatLng position) {
        MarkerOptions markerOptions = BuildConfig.MAP_BINDING.newMarkerOptions().
                icon(BuildConfig.MAP_BINDING.bitmapDescriptorFactory().fromBitmap(iconFactory.makeIcon(text))).
                position(position).
                anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());

        getMap().addMarker(markerOptions);
    }
}
