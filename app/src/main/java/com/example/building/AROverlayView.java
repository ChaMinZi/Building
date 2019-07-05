package com.example.building;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.location.Location;
import android.opengl.Matrix;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.building.helper.LocationHelper;

import java.util.ArrayList;
import java.util.List;

public class AROverlayView extends View {

    Context context;
    private float[] rotatedProjectionMatrix = new float[16];
    private Location currentLocation;
    private List<ARPoint> arPoints;

    private List<PointF> displayPointF = new ArrayList<>();;
    private List<ARPoint> displayARPoint = new ArrayList<>();
    final int radius = 30;

    public AROverlayView(Context context) {
        super(context);
        this.context = context;

        //Demo points
        arPoints = new ArrayList<ARPoint>() {{
            add(new ARPoint("S", 10.0404856, 108.2262447, 0, "4.0"));
            add(new ARPoint("L", 16.1072989, 108.2343984, 0, "2.1"));
        }};
    }

    public void updateRotatedProjectionMatrix(float[] rotatedProjectionMatrix) {
        this.rotatedProjectionMatrix = rotatedProjectionMatrix;
        this.invalidate();
    }

    public void updateCurrentLocation(Location currentLocation){
        this.currentLocation = currentLocation;
        this.invalidate();
    }

    private double PointFDistance(PointF start, PointF end){
        return Math.sqrt(Math.pow(start.x-end.x, 2)+Math.pow(start.y-end.y, 2));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        final int action = event.getAction();
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                final float x = event.getX();
                final float y = event.getY();
                if (displayPointF == null) break;
                for(int i=0 ; i<displayPointF.size(); i++){
                    if(PointFDistance(new PointF(x,y), displayPointF.get(i))<=radius){
                        Log.e("touchShelter", displayARPoint.get(i).getName());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (currentLocation == null) {
            return;
        }

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(60);

        displayPointF.clear();
        displayARPoint.clear();
        for (int i = 0; arPoints != null && i <  arPoints.size(); i ++) {
            float[] currentLocationInECEF = LocationHelper.WSG84toECEF(currentLocation);
            float[] pointInECEF = LocationHelper.WSG84toECEF(arPoints.get(i).getLocation());
            float[] pointInENU = LocationHelper.ECEFtoENU(currentLocation, currentLocationInECEF, pointInECEF);

            float[] cameraCoordinateVector = new float[4];
            Matrix.multiplyMV(cameraCoordinateVector, 0, rotatedProjectionMatrix, 0, pointInENU, 0);

            // cameraCoordinateVector[2] is z, that always less than 0 to display on right position
            // if z > 0, the point will display on the opposite
            if (cameraCoordinateVector[2] < 0) {
                float x  = (0.5f + cameraCoordinateVector[0]/cameraCoordinateVector[3]) * canvas.getWidth();
                float y = (0.5f - cameraCoordinateVector[1]/cameraCoordinateVector[3]) * canvas.getHeight();

                displayPointF.add(new PointF(x, y));
                displayARPoint.add(arPoints.get(i));
                canvas.drawCircle(x, y, radius, paint);
                canvas.drawText(arPoints.get(i).getName(), x - (30 * arPoints.get(i).getName().length() / 2), y - 100, paint);
                canvas.drawText(arPoints.get(i).getOpertime(), x - (30 * arPoints.get(i).getName().length() / 2), y-30, paint);
            }
        }
    }
}
