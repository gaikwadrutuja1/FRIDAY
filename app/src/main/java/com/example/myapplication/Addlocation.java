package com.example.myapplication;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.agribyte.friday.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Addlocation extends Bottom implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener{
    private GoogleMap mMap;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();


    EditText productname,productDescription,address,quantity;
    Button save;
    LatLng finallatLng;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.addlocation);
        productname = findViewById(R.id.productDesc);
        productDescription = findViewById(R.id.productDesc);
        address = findViewById(R.id.add);
        quantity = findViewById(R.id.quantity);
        save = findViewById(R.id.btnAdd);

        save.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Itemdetails item=new Itemdetails ();
                if(finallatLng !=null) {
                    FirebaseAuth autho=FirebaseAuth.getInstance();
                    FirebaseUser user= autho.getCurrentUser();
                    assert user != null;
                    item.setUserId ( user.getUid () );
                    String strproductname=""+productname.getText ().toString ();
                    String straddress=""+address.getText ().toString ();
                    String strdescription=""+productDescription.getText ().toString ();
                    String strquantity = ""+quantity.getText().toString();
                    item.setDesc ( strdescription );
                    item.setAdd ( straddress );
                    item.setProductname( strproductname );
                    item.setQuantity ( strquantity );


                    DatabaseReference mdatabase= FirebaseDatabase.getInstance().getReference();
                    String uid=mdatabase.push ().getKey ();
                    mdatabase.child( "Item details" ).child( uid ).setValue( item );


                }
                else{
                    Toast.makeText ( Addlocation.this,"Select location first",Toast.LENGTH_SHORT ).show ();


                }


            }
        } );




        // Initialize FusedLocationProviderClient


        // Set up click listener for the button




// Obtain the SupportMapFragment and get notified when the map is ready to beused.
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener( Addlocation.this);
// Add a marker in Sydney and move the camera
        LatLng Barshi = new LatLng(18.2334, 75.6941);
        mMap.addMarker(new MarkerOptions ().position(Barshi).title("Marker in Barshi"));
        mMap.moveCamera( CameraUpdateFactory.newLatLng(Barshi));
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        finallatLng=latLng;
        mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));

        // Show a Toast message with the latitude and longitude
        String message = "Latitude: " + latLng.latitude + ", Longitude: " + latLng.longitude;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}








    }
}
