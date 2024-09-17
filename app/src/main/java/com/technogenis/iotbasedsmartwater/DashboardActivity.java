package com.technogenis.iotbasedsmartwater;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.technogenis.iotbasedsmartwater.fragment.FoodInfoFragment;
import com.technogenis.iotbasedsmartwater.fragment.HistoryFragment;
import com.technogenis.iotbasedsmartwater.fragment.HomeFragment;
import com.technogenis.iotbasedsmartwater.fragment.PumpInfoFragment;
import com.technogenis.iotbasedsmartwater.fragment.TDSFragment;
import com.technogenis.iotbasedsmartwater.fragment.TurbidityFragment;
import com.technogenis.iotbasedsmartwater.fragment.WaterLevelFragment;
import com.technogenis.iotbasedsmartwater.fragment.WaterTempFragment;

public class DashboardActivity extends AppCompatActivity {

    NavigationView navMenu;
    ActionBarDrawerToggle toggle;
    DrawerLayout drayerLayout;


    FragmentManager fragmentManager;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar=findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        navMenu=findViewById(R.id.navMenu);
        drayerLayout=findViewById(R.id.drawerlayout);


        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new HomeFragment()).commit();


        toggle=new ActionBarDrawerToggle(this,drayerLayout,toolbar,R.string.app_name,R.string.app_name);
        drayerLayout.addDrawerListener(toggle);
        toggle.syncState();

        fragmentManager = getSupportFragmentManager();

        navMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                if (item.getItemId() == R.id.menuHome){
                    fragment = new HomeFragment();
                    drayerLayout.closeDrawer(GravityCompat.START);

                }else  if (item.getItemId() == R.id.menuWaterTemp){
                    fragment = new WaterTempFragment();
                    drayerLayout.closeDrawer(GravityCompat.START);
                }

                else if(item.getItemId() == R.id.menuTurbidity){
                    fragment = new TurbidityFragment();
                    drayerLayout.closeDrawer(GravityCompat.START);
                }

                else if(item.getItemId() == R.id.menuTds){
                    fragment = new TDSFragment();
                    drayerLayout.closeDrawer(GravityCompat.START);
                }

                else if(item.getItemId() == R.id.menuWaterLevel){
                    fragment = new WaterLevelFragment();
                    drayerLayout.closeDrawer(GravityCompat.START);
                }

                else if(item.getItemId() == R.id.menuPump){
                    fragment = new PumpInfoFragment();
                    drayerLayout.closeDrawer(GravityCompat.START);
                }

                else if(item.getItemId() == R.id.menuFood){
                    fragment = new FoodInfoFragment();
                    drayerLayout.closeDrawer(GravityCompat.START);
                }

                else if(item.getItemId() == R.id.menuHistory){
                    fragment = new HistoryFragment();
                    drayerLayout.closeDrawer(GravityCompat.START);
                }

                else if(item.getItemId() == R.id.menuExit){
                    System.exit(0);
                    drayerLayout.closeDrawer(GravityCompat.START);
                }
                else if(item.getItemId() == R.id.menu_logout){
                    Intent logIntent=new Intent(DashboardActivity.this,LoginActivity.class);
                    startActivity(logIntent);
                    finish();
                    Toast.makeText(DashboardActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                    drayerLayout.closeDrawer(GravityCompat.START);
                }

                if (fragment != null) {
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.main_frame, fragment);
                    transaction.addToBackStack(null); // Optional: to add fragment to back stack
                    transaction.commit();
                }

                drayerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

    }
}