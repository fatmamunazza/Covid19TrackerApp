package com.example.covid19trackerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid19trackerapp.Fragment.About;
import com.example.covid19trackerapp.Fragment.AllCountryList;
import com.example.covid19trackerapp.Fragment.AllStateList;
import com.example.covid19trackerapp.Fragment.Country;
import com.example.covid19trackerapp.Fragment.Home;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainScreen extends AppCompatActivity{

    FrameLayout containerMainscreen;
    private AdView mAdView;
    NavigationView navigationDrawerLayout;
    public DrawerLayout drawerLayout;
    RelativeLayout actionLayout;
    ImageView india,usa,search,menu;
    Boolean homeFlag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        ConnectivityManager cm = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null && !networkInfo.isConnected()) {
            Toast.makeText(this, "No Internet Detected...Please Check Your Internet Connection!!!", Toast.LENGTH_SHORT).show();
        }

        navigationDrawerLayout=findViewById(R.id.navigation_drawer_view);
        drawerLayout=findViewById(R.id.drawerLayout);
        actionLayout=findViewById(R.id.actionLayout);
        menu=actionLayout.findViewById(R.id.menu_img);
        india=actionLayout.findViewById(R.id.india);
        usa=actionLayout.findViewById(R.id.usa);
       // search=actionLayout.findViewById(R.id.search);

        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                        INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        india.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fr=new Country();
                Bundle bundle=new Bundle();
                bundle.putString("screenName","Other");
                bundle.putString("country","INDIA");
                fr.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.container_mainscreen,fr).commit();
                homeFlag=false;
            }
        });

      /*  search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainScreen.this,SearchActivity.class);
                startActivity(i);
            }
        });*/

        usa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fr=new Country();
                Bundle bundle=new Bundle();
                bundle.putString("screenName","Other");
                bundle.putString("country","USA");
                fr.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.container_mainscreen,fr).commit();
                homeFlag=false;
            }
        });

        Fragment fr=new Home();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_mainscreen,fr).addToBackStack(null).commit();



        navigationDrawerLayout.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fr;
                switch (menuItem.getItemId()) {
                    case R.id.home_drawer: {

                        if(!homeFlag) {
                            fr = new Home();
                            getSupportFragmentManager().beginTransaction().replace(R.id.container_mainscreen, fr).addToBackStack(null).commit();
                           homeFlag=true;
                        }
                        else{
                            fr = new Home();
                            getSupportFragmentManager().beginTransaction().replace(R.id.container_mainscreen, fr).commit();

                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return false;

                    }
                    case R.id.country_list_drawer: {
                        fr=new AllCountryList();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_mainscreen,fr).commit();
                        homeFlag=false;
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return false;

                    }
                    case R.id.india_drawer: {
                        fr=new Country();
                        Bundle bundle=new Bundle();
                        bundle.putString("screenName","Other");
                        bundle.putString("country","INDIA");
                        fr.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_mainscreen,fr).commit();
                        homeFlag=false;
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return false;

                    }
                    case R.id.usa_drawer: {
                        fr=new Country();
                        Bundle bundle=new Bundle();
                        bundle.putString("screenName","Other");
                        bundle.putString("country","USA");
                        fr.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_mainscreen,fr).commit();
                        homeFlag=false;
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return false;

                    }
                    case R.id.india_state_drawer: {
                        fr=new AllStateList();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_mainscreen,fr).commit();
                        homeFlag=false;
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return false;

                    }
                   /* case R.id.about_drawer: {
                        fr=new About();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_mainscreen,fr).addToBackStack(null).commit();
                        homeFlag=false;
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return false;

                    }*/
                    case R.id.feedback_drawer: {
                      /* fr=new feedback();
                       getSupportFragmentManager().beginTransaction().replace(R.id.container_mainscreen,fr).addToBackStack(null).commit();
                       fit.setText("FEEDBACK FORM");
                       gym.setVisibility(View.GONE);
                       drawerLayout.closeDrawer(GravityCompat.START);*/

                        String[] s={"aliofatima786@gmail.com"};
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                        intent.putExtra(Intent.EXTRA_EMAIL, s);
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback or any Complaints");
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(MainScreen.this, "You have no email clients installed in your mobile.", Toast.LENGTH_SHORT).show();
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        homeFlag=false;
                        return false;
                    }
                    case R.id.rate_drawer: {
                       /* Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("market://details?id=com.facebook.katana"));
                        startActivity(intent);*/
                        homeFlag=false;
                    }
                    case R.id.share_drawer: {
                        String share_string = getString(R.string.share_content);

                        Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.share);
                        String  path = MediaStore.Images.Media.insertImage(getContentResolver(), b, "Title", null);


                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//
                            Intent share = new Intent(Intent.ACTION_SEND);
                            share.setType("text/*");
                            Uri file = Uri.parse(path);
                            share.putExtra(Intent.EXTRA_STREAM,file);
                            share.putExtra(Intent.EXTRA_TEXT, share_string);
                            startActivity(Intent.createChooser(share, "Share Via"));
                        }
                        else{
                            ActivityCompat.requestPermissions(MainScreen.this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    200);
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        homeFlag=false;
                        return false;
                    }
                    default: {
                        return false;
                    }

                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }

}
