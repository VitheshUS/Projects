package PG_DETAILS.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dormease.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Map;




public class pgdetails_user extends Fragment {

    TextView oname,pgname,phone,email,address,rent,distance,available,description;
    FloatingActionButton call;
    ExtendedFloatingActionButton viewlc;

    Map<String,String> mp;

DOB_class.owner_dob owner_dob;
    public pgdetails_user(DOB_class.owner_dob owner_dob) {
        // Required empty public constructor
        this.owner_dob=owner_dob;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_pgdetails_user, container, false);

        oname=view.findViewById(R.id.oname);
        pgname=view.findViewById(R.id.pgname);
        phone=view.findViewById(R.id.phone);
        email=view.findViewById(R.id.email);
        address=view.findViewById(R.id.address);
        rent=view.findViewById(R.id.rent);
        distance=view.findViewById(R.id.distance);
        available=view.findViewById(R.id.available);
        description=view.findViewById(R.id.description);
        call=view.findViewById(R.id.call);
        viewlc=view.findViewById(R.id.view_license);

        System.out.println(owner_dob.getMp());

        String certificate=owner_dob.getVerification();

        viewlc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String license=owner_dob.getVerification();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(license));
                startActivity(intent);
            }
        });
        mp=owner_dob.getMp();

        oname.append("\n\t\t"+owner_dob.getName());
        pgname.append("\n\t\t"+mp.get("pgname"));
        phone.append("\n\t\t"+owner_dob.getPhone());
        email.append("\n\t\t"+owner_dob.getEmail());
        address.append("\n\t\t"+mp.get("address"));
        rent.append("\n\t\t"+mp.get("rent"));
        distance.append("\n\t\t"+mp.get("distance"));
        available.append("\n\t\t"+mp.get("available"));
        description.append("\n\t\t"+mp.get("description"));

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+owner_dob.getPhone()));
                startActivity(intent);
            }
        });
        return view;
    }
}