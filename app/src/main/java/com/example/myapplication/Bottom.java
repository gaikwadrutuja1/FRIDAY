package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.agribyte.friday.R;
import com.agribyte.friday.accountFragment;
import com.agribyte.friday.homeFragment;
import com.agribyte.friday.productFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Bottom extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_bottombar );

        bottomNavigationView = findViewById ( R.id.bottomNavigationView );
        bottomNavigationView.setOnNavigationItemSelectedListener ( this );
        fab = findViewById ( R.id.add_fab );
        fab.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i = new Intent ( Bottom.this, Addlocation.class );
                startActivity ( i );
            }
        } );

    }

    @Override
    protected void onResume() {
        super.onResume ();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.home) {
            Toast.makeText(Bottom.this, "Home clicked", Toast.LENGTH_SHORT).show();
            loadfragment ( new homeFragment() );

            return true;
        } else if (itemId == R.id.product) {
            Toast.makeText(Bottom.this, "Food item clicked", Toast.LENGTH_SHORT).show();
            loadfragment ( new productFragment() );
            return true;
        } else if (itemId == R.id.account) {
            Toast.makeText(Bottom.this, "Profile Clicked", Toast.LENGTH_SHORT).show();
            loadfragment ( new accountFragment() );
            return true;
        }

        return false;
    }

    public void loadfragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager ();
        fm.beginTransaction ().replace ( R.id.flFragment, fragment ).commit ();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged ( hasCapture );
    }
}




