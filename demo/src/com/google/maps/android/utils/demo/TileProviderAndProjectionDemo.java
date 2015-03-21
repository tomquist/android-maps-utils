package com.google.maps.android.utils.demo;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import de.quist.app.maps.model.LatLng;
import de.quist.app.maps.model.Tile;
import de.quist.app.maps.model.TileProvider;
import de.quist.app.maps.utils.geometry.Point;
import de.quist.app.maps.utils.projection.SphericalMercatorProjection;

public class TileProviderAndProjectionDemo extends BaseDemoActivity {
    @Override
    protected void startDemo() {
        PointTileOverlay pto = new PointTileOverlay();
        pto.addPoint(BuildConfig.MAP_BINDING.newLatLng(0, 0));
        pto.addPoint(BuildConfig.MAP_BINDING.newLatLng(21, -10));
        getMap().addTileOverlay(BuildConfig.MAP_BINDING.newTileOverlayOptions().tileProvider(pto));
    }

    private class PointTileOverlay implements TileProvider {
        private List<Point> mPoints = new ArrayList<Point>();
        private int mTileSize = 256;
        private SphericalMercatorProjection mProjection = new SphericalMercatorProjection(mTileSize);
        private int mScale = 2;
        private int mDimension = mScale * mTileSize;

        @Override
        public Tile getTile(int x, int y, int zoom) {
            Matrix matrix = new Matrix();
            float scale = (float) Math.pow(2, zoom) * mScale;
            matrix.postScale(scale, scale);
            matrix.postTranslate(-x * mDimension, -y * mDimension);

            Bitmap bitmap = Bitmap.createBitmap(mDimension, mDimension, Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(bitmap);
            c.setMatrix(matrix);

            for (Point p : mPoints) {
                c.drawCircle((float) p.x, (float) p.y, 1, new Paint());
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            return BuildConfig.MAP_BINDING.newTile(mDimension, mDimension, baos.toByteArray());
        }

        public void addPoint(LatLng latLng) {
            mPoints.add(mProjection.toPoint(latLng));
        }
    }
}
