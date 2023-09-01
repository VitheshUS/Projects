package Maps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dormease.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

import DOB_class.owner_dob;

public class MapsFragment extends Fragment {

    owner_dob owner_dob;
    double longitude;
    double latitude;
    public MapsFragment(owner_dob owner_dob){
        this.owner_dob=owner_dob;
    }
    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng position = new LatLng(latitude,longitude);
            googleMap.addMarker(new MarkerOptions().position(position).title(owner_dob.getMp().get("pgname")));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_maps, container, false);
        return view;

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Map<String,String>mp= owner_dob.getMp();
        longitude= Double.parseDouble(mp.get("longitude"));
        latitude= Double.parseDouble(mp.get("latitude"));
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}