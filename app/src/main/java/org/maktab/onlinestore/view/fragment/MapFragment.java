package org.maktab.onlinestore.view.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.maktab.onlinestore.viewmodel.SettingViewModel;
import org.maktab.onlinestore.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapFragment extends SupportMapFragment {

    public static final String TAG = "LocatrFragment";
    private static final int REQUEST_CODE_PERMISSION_LOCATION = 0;
    private static final int NUMBER_OF_IMAGES = 3;

    private SettingViewModel mSettingViewModel;
    private GoogleMap mMap;
    ArrayList markerPoints = new ArrayList();
    MarkerOptions mMarkerOptions;
    Marker mMarker;

    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mSettingViewModel = new ViewModelProvider(this).get(SettingViewModel.class);
        mMarkerOptions = new MarkerOptions();


        mSettingViewModel.getMyLocation().observe(this, new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                updateUI();
            }
        });

        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                updateUI();
                listeners();
            }
        });
    }
//////////////////////////////
    private void listeners() {
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                /*if (markerPoints.size() == 1) {
                    markerPoints.clear();
                    mMap.clear();
                }

                // Adding new item to the ArrayList
                markerPoints.add(latLng);*/

                // Creating MarkerOptions

                mMarker.setPosition(latLng);
                getAddress(latLng.latitude,latLng.longitude);

//                    mMarkerOptions.position(latLng);
//                    mMap.addMarker(mMarkerOptions);


                // Setting the position of the marker


                // Add new marker to the Google Map Android API V2


            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_fragment_location, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_location:
                if (hasLocationAccess()) {
                    requestLocation();
                } else {
                    //request Location access permission
                    requestLocationAccessPermission();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_LOCATION:
                if (grantResults == null || grantResults.length == 0)
                    return;

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    requestLocation();
                /*else
                    Toast.makeText(
                            getContext(),
                            "We do not have the location permission",
                            Toast.LENGTH_LONG).show();*/

                return;
        }
    }

    private boolean hasLocationAccess() {
        boolean isFineLocation = ContextCompat.checkSelfPermission(
                getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        boolean isCoarseLocation = ContextCompat.checkSelfPermission(
                getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        return isFineLocation && isCoarseLocation;
    }

    private void requestLocationAccessPermission() {
        String[] permissions = new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        requestPermissions(permissions, REQUEST_CODE_PERMISSION_LOCATION);
    }

    @SuppressLint("MissingPermission")
    private void requestLocation() {
        if (!hasLocationAccess())
            return;

        mSettingViewModel.requestLocation();
    }

    private void updateUI() {
        Location location = mSettingViewModel.getMyLocation().getValue();
        if (location == null || mMap == null )
            return;

        LatLng myLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        LatLngBounds latLngBounds = new LatLngBounds.Builder()
                .include(myLatLng)
                .build();

        mMarkerOptions
                .position(myLatLng)
                .title("My Location");

        /*MarkerOptions myMarkerOptions = new MarkerOptions()
                .position(myLatLng)
                .title("My Location");*/

        if (mMarker == null){

            mMarker = mMap.addMarker(mMarkerOptions);
        }else {
            mMarker.setPosition(myLatLng);

        }

        getAddress(myLatLng.latitude,myLatLng.longitude);

        int margin = getResources().getDimensionPixelSize(R.dimen.map_inset_margin);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(latLngBounds, margin);
        mMap.animateCamera(cameraUpdate);
    }

    public void getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
            add = add + "\n" + obj.getCountryName();
            add = add + "\n" + obj.getCountryCode();
            add = add + "\n" + obj.getAdminArea();
            add = add + "\n" + obj.getPostalCode();
            add = add + "\n" + obj.getSubAdminArea();
            add = add + "\n" + obj.getLocality();
            add = add + "\n" + obj.getSubThoroughfare();

            Log.v("IGA", "Address" + add);
            Toast.makeText(getActivity(), add, Toast.LENGTH_SHORT).show();
            // Toast.makeText(this, "Address=>" + add,
            // Toast.LENGTH_SHORT).show();

            // TennisAppActivity.showDialog(add);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}