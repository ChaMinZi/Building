package com.example.building;

import android.Manifest;
<<<<<<< HEAD
import android.app.FragmentManager;
=======
>>>>>>> 46acfa233c9730a0dfac4c6f0d5c8d3de5e286c0
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.opengl.Matrix;
import android.os.Build;
import android.os.Bundle;
<<<<<<< HEAD
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
=======
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
>>>>>>> 46acfa233c9730a0dfac4c6f0d5c8d3de5e286c0
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;
<<<<<<< HEAD
import android.widget.Toast;

import com.example.building.model.ARCamera;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
=======
import android.widget.TextView;
import android.widget.Toast;

import com.example.building.model.ARCamera;

>>>>>>> 46acfa233c9730a0dfac4c6f0d5c8d3de5e286c0
import static android.hardware.SensorManager.AXIS_MINUS_X;
import static android.hardware.SensorManager.AXIS_MINUS_Y;
import static android.hardware.SensorManager.AXIS_X;
import static android.hardware.SensorManager.AXIS_Y;
import static android.hardware.SensorManager.SENSOR_DELAY_NORMAL;
import static android.hardware.SensorManager.getOrientation;
import static android.hardware.SensorManager.getRotationMatrixFromVector;
import static android.hardware.SensorManager.remapCoordinateSystem;
import static android.view.Surface.ROTATION_180;
import static android.view.Surface.ROTATION_270;
import static android.view.Surface.ROTATION_90;

<<<<<<< HEAD
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.compat.GeoDataClient;
import com.google.android.libraries.places.compat.PlaceDetectionClient;
import com.google.android.libraries.places.compat.PlaceLikelihood;
import com.google.android.libraries.places.compat.PlaceLikelihoodBufferResponse;
import com.google.android.libraries.places.compat.Places;


public class MainActivity extends AppCompatActivity implements SensorEventListener, LocationListener, OnMapReadyCallback {
=======
public class MainActivity extends AppCompatActivity implements SensorEventListener, LocationListener {
>>>>>>> 46acfa233c9730a0dfac4c6f0d5c8d3de5e286c0

    final static String TAG = "ARActivity";
    private SurfaceView surfaceView;
    private FrameLayout cameraContainerLayout;
    private AROverlayView arOverlayView;
    private Camera camera;
    private ARCamera arCamera;
<<<<<<< HEAD
=======
    private TextView tvCurrentLocation;
    private TextView tvBearing;
>>>>>>> 46acfa233c9730a0dfac4c6f0d5c8d3de5e286c0

    private SensorManager sensorManager;
    private final static int REQUEST_CAMERA_PERMISSIONS_CODE = 11;
    public static final int REQUEST_LOCATION_PERMISSIONS_CODE = 0;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0; // 10 meters
    private static final long MIN_TIME_BW_UPDATES = 0;//1000 * 60 * 1; // 1 minute

    private LocationManager locationManager;
    public Location location;
    boolean isGPSEnabled;
    boolean isNetworkEnabled;
    boolean locationServiceAvailable;
    private float declination;

<<<<<<< HEAD
    private GoogleMap mMap;
    PlaceDetectionClient placeDetectionClient;
    private boolean mLocationPermissionGranted;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Location mLastKnownLocation;

    String[] cur_place_name;
    String[] cur_place_address;
    float[] cur_place_rating;
    LatLng[] cur_place_latlng;
=======
    boolean InfoVisible = false;
    private InfoFragment infoFragment;
>>>>>>> 46acfa233c9730a0dfac4c6f0d5c8d3de5e286c0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
=======
        if (savedInstanceState == null) {
            infoFragment = new InfoFragment();
        }
>>>>>>> 46acfa233c9730a0dfac4c6f0d5c8d3de5e286c0
        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        cameraContainerLayout = findViewById(R.id.camera_container_layout);
        surfaceView = findViewById(R.id.surface_view);
        arOverlayView = new AROverlayView(this);
<<<<<<< HEAD

        placeDetectionClient = Places.getPlaceDetectionClient(this);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
=======
        tvCurrentLocation = findViewById(R.id.tv_current_location);
        tvBearing = findViewById(R.id.tv_bearing);
    }

    protected void displayFragment(boolean visible){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (!infoFragment.isAdded())
            fragmentTransaction.add(R.id.fragmentView, infoFragment);

        if (!visible)
            fragmentTransaction.hide(infoFragment);
        else
            fragmentTransaction.show(infoFragment);

        fragmentTransaction.commit();
>>>>>>> 46acfa233c9730a0dfac4c6f0d5c8d3de5e286c0
    }

    @Override
    public void onResume() {
        super.onResume();
        requestCameraPermission();
        requestLocationPermission();
<<<<<<< HEAD
        //getDeviceLocation();
=======
>>>>>>> 46acfa233c9730a0dfac4c6f0d5c8d3de5e286c0
        registerSensors();
        initAROverlayView();
    }

    @Override
    public void onPause() {
        releaseCamera();
        super.onPause();
    }

    public void requestCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                this.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            this.requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSIONS_CODE);
        } else {
            initARCameraView();
        }
    }

    public void requestLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSIONS_CODE);
        } else {
            initLocationService();
<<<<<<< HEAD
            //mLocationPermissionGranted = true;
        }
    }



    /*
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSIONS_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
    }


    private void getDeviceLocation() {
        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
                            location = mLastKnownLocation;
                            updateLatestLocation();
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            /*
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);

                        }
                    }
                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }*/

=======
        }
    }

>>>>>>> 46acfa233c9730a0dfac4c6f0d5c8d3de5e286c0
    public void initAROverlayView() {
        if (arOverlayView.getParent() != null) {
            ((ViewGroup) arOverlayView.getParent()).removeView(arOverlayView);
        }
        cameraContainerLayout.addView(arOverlayView);
    }

    public void initARCameraView() {
        reloadSurfaceView();

        if (arCamera == null) {
            arCamera = new ARCamera(this, surfaceView);
        }
        if (arCamera.getParent() != null) {
            ((ViewGroup) arCamera.getParent()).removeView(arCamera);
        }
        cameraContainerLayout.addView(arCamera);
        arCamera.setKeepScreenOn(true);
        initCamera();
    }

    private void initCamera() {
        int numCams = Camera.getNumberOfCameras();
        if(numCams > 0){
            try{
                camera = Camera.open();
                camera.startPreview();
                arCamera.setCamera(camera);
            } catch (RuntimeException ex){
                Toast.makeText(this, "Camera not found", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void reloadSurfaceView() {
        if (surfaceView.getParent() != null) {
            ((ViewGroup) surfaceView.getParent()).removeView(surfaceView);
        }

        cameraContainerLayout.addView(surfaceView);
    }

    private void releaseCamera() {
        if(camera != null) {
            camera.setPreviewCallback(null);
            camera.stopPreview();
            arCamera.setCamera(null);
            camera.release();
            camera = null;
        }
    }

    private void registerSensors() {
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),
                SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            float[] rotationMatrixFromVector = new float[16];
            float[] rotationMatrix = new float[16];
            getRotationMatrixFromVector(rotationMatrixFromVector, sensorEvent.values);
            final int screenRotation = this.getWindowManager().getDefaultDisplay()
                    .getRotation();

            switch (screenRotation) {
                case ROTATION_90:
                    remapCoordinateSystem(rotationMatrixFromVector,
                            AXIS_Y,
                            AXIS_MINUS_X, rotationMatrix);
                    break;
                case ROTATION_270:
                    remapCoordinateSystem(rotationMatrixFromVector,
                            AXIS_MINUS_Y,
                            AXIS_X, rotationMatrix);
                    break;
                case ROTATION_180:
                    remapCoordinateSystem(rotationMatrixFromVector,
                            AXIS_MINUS_X, AXIS_MINUS_Y,
                            rotationMatrix);
                    break;
                default:
                    remapCoordinateSystem(rotationMatrixFromVector,
                            AXIS_X, AXIS_Y,
                            rotationMatrix);
                    break;
            }

            float[] projectionMatrix = new float[16];
            if (arCamera != null) {
                projectionMatrix = arCamera.getProjectionMatrix();
            }
            float[] rotatedProjectionMatrix = new float[16];
            Matrix.multiplyMM(rotatedProjectionMatrix, 0, projectionMatrix, 0, rotationMatrix, 0);
            this.arOverlayView.updateRotatedProjectionMatrix(rotatedProjectionMatrix);

            //Heading
            float[] orientation = new float[3];
            getOrientation(rotatedProjectionMatrix, orientation);
<<<<<<< HEAD
=======
            double bearing = Math.toDegrees(orientation[0]) + declination;
            tvBearing.setText(String.format("Bearing: %s", bearing));
>>>>>>> 46acfa233c9730a0dfac4c6f0d5c8d3de5e286c0
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//        if (accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {
//            Log.w("DeviceOrientation", "Orientation compass unreliable");
//        }
    }

<<<<<<< HEAD

    private void initLocationService() {

=======
    private void initLocationService() {
>>>>>>> 46acfa233c9730a0dfac4c6f0d5c8d3de5e286c0
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            return  ;
        }

        try   {
            this.locationManager = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);

            // Get GPS and network status
            this.isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            this.isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isNetworkEnabled && !isGPSEnabled)    {
                // cannot get location
                this.locationServiceAvailable = false;
            }

            this.locationServiceAvailable = true;

            if (isNetworkEnabled) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                if (locationManager != null)   {
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    updateLatestLocation();
                }
            }

            if (isGPSEnabled)  {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                if (locationManager != null)  {
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    updateLatestLocation();
                }
            }
        } catch (Exception ex)  {
            Log.e(TAG, ex.getMessage());

        }
    }

    private void updateLatestLocation() {
        if (arOverlayView !=null && location != null) {
            arOverlayView.updateCurrentLocation(location);
<<<<<<< HEAD
            showCurrentPlace();
=======
            tvCurrentLocation.setText(String.format("lat: %s \nlon: %s \naltitude: %s \n",
                    location.getLatitude(), location.getLongitude(), location.getAltitude()));
>>>>>>> 46acfa233c9730a0dfac4c6f0d5c8d3de5e286c0
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        updateLatestLocation();
    }

<<<<<<< HEAD

    //현재 위치 주변의 건물을 탐색
    private void showCurrentPlace() {
        //권한 체크
        int permission = ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 0);
            return;
        }

        Task<PlaceLikelihoodBufferResponse> placeResult = placeDetectionClient.getCurrentPlace(null);

        placeResult.addOnCompleteListener(new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();

                int count = likelyPlaces.getCount();

                double[] gradient = new double[count];
                int[] sector = new int[count];


                cur_place_name = new String[count];
                cur_place_address = new String[count];
                cur_place_rating = new float[count];
                cur_place_latlng = new LatLng[count];

                int i = 0;
                for (PlaceLikelihood likelihood : likelyPlaces) {
                    cur_place_name[i] = likelihood.getPlace().getName().toString();
                    cur_place_address[i] = likelihood.getPlace().getAddress().toString();
                    cur_place_rating[i] = likelihood.getPlace().getRating();
                    cur_place_latlng[i] = likelihood.getPlace().getLatLng();


                    gradient[i] = (cur_place_latlng[i].latitude - location.getLatitude())
                            / (cur_place_latlng[i].longitude - location.getLongitude());

                    if(gradient[i] > 0){
                        if(cur_place_latlng[i].longitude > location.getLongitude()) sector[i] = 1;
                        else sector[i] = 3;
                    }
                    else{
                        if(cur_place_latlng[i].longitude > location.getLongitude()) sector[i] = 4;
                        else sector[i] = 2;
                    }

                    i++;
                }



                likelyPlaces.release();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng SEOUL = new LatLng(37.56, 126.97);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));

        FrameLayout mapLayout = (FrameLayout) findViewById(R.id.map);
        mapLayout.animate().translationY(900).translationX(900).setDuration(0);
    }


=======
>>>>>>> 46acfa233c9730a0dfac4c6f0d5c8d3de5e286c0
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
