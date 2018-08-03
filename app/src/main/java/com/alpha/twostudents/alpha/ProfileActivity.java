////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Initial activity fot the user. Displays the Home fragment initially.
// Displays after login is successful
//
////////////////////////////////////////////////////////////////////////////////////////////////////
package com.alpha.twostudents.alpha;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button buttonLogout;
    //Bottom navigation view allows user to jump to timeline, camera and profile fragment
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_camera:
                    selectedFragment = new CameraFragment();
                    break;
                case R.id.nav_profile:
                    selectedFragment = new ProfileFragment();
                    break;
            }

            //jumping to the selected fragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_contrainer, selectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getting saved state if any
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Declaring navigation listeners and initialising fragment for the current state i.e home
        BottomNavigationView bottomNav =  findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_contrainer, new HomeFragment()).commit();


        firebaseAuth = FirebaseAuth.getInstance();

        // User is not logged in
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        //Displaying the user email id on the top
        //TODO get rid of this once menu bar on the left is finished
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText("Welcome " + user.getEmail());
        buttonLogout = (Button) findViewById(R.id.buttonSignOut);
        buttonLogout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == buttonLogout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));

        }
    }
}