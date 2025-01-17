package com.cryptech.demoapp.activities;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.cryptech.demoapp.R;
import com.cryptech.demoapp.fragments.HomeFragment;
import com.cryptech.demoapp.fragments.MyAccountFragment;
import com.cryptech.demoapp.fragments.MyBookingFragment;
import com.cryptech.demoapp.fragments.MyCartFragment;
import com.cryptech.demoapp.fragments.MyOrdersFragment;
import com.cryptech.demoapp.fragments.MyRewardFragment;
import com.cryptech.demoapp.fragments.MyWishListFragment;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int HOME_FRAGMENT = 0;
    private static final int CART_FRAGMENT = 1;
    private static final int ORDERS_FRAGMENT = 2;
    private static final int WISHLIST_FRAGMENT = 3;
    private static final int REWARD_FRAGMENT = 4;
    private static final int ACCOUNT_FRAGMENT = 5;
    private static final int BOOKING_FRAGMENT = 6;

    public static Boolean showCart = false;

    private FrameLayout frameLayout;
    private static int currentFragment = -1;
    private NavigationView navigationView;
    private ImageView actionBarLogo;
  
    private DrawerLayout drawer;

    private Window window;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        actionBarLogo = findViewById(R.id.action_bar_logo);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        drawer = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        frameLayout = findViewById(R.id.main_framelayout);


        if (showCart) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            goToFragment("My Cart", new MyCartFragment(), -2);
        } else {

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
            setFragment(new HomeFragment(), HOME_FRAGMENT);
        }




//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
//                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);

    }
//    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//if (activeNetwork != null) {
//        // connected to the internet
//        if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
//            // connected to wifi
//        } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
//            // connected to mobile data
//        }
//    } else {
//        // not connected to the internet
//    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (currentFragment == HOME_FRAGMENT) {
                currentFragment = -1;
                super.onBackPressed();
            } else {
                actionBarLogo.setVisibility(View.VISIBLE);
                invalidateOptionsMenu();
                setFragment(new HomeFragment(), HOME_FRAGMENT);
                navigationView.getMenu().getItem(0).setChecked(true);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (currentFragment == HOME_FRAGMENT) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.main_search_icon) {
            //todo: search
            return true;
        } else if (id == R.id.main_notification_icon) {
            //todo: notification system
            return true;
        } else if (id == R.id.main_cart_icon) {
            goToFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void goToFragment(String title, Fragment fragment, int fragmentNo) {
        actionBarLogo.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        invalidateOptionsMenu();
        setFragment(fragment, fragmentNo);
        if (fragmentNo == CART_FRAGMENT) {
            navigationView.getMenu().getItem(3).setChecked(true);
        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_my_demoApp) {
            actionBarLogo.setVisibility(View.VISIBLE);
            invalidateOptionsMenu();
            setFragment(new HomeFragment(), HOME_FRAGMENT);
           // navigationView.getMenu().getItem(0).setChecked(true);

        } else if (id == R.id.nav_my_orders) {
            goToFragment("My Orders", new MyOrdersFragment(), ORDERS_FRAGMENT);
        } else if (id == R.id.nav_my_rewards) {

            goToFragment("My Rewards", new MyRewardFragment(), REWARD_FRAGMENT);
        } else if (id == R.id.nav_my_cart) {
            goToFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);

        } else if (id == R.id.nav_my_wishlist) {
            goToFragment("My WishList",new MyWishListFragment(), WISHLIST_FRAGMENT);

        } else if (id == R.id.nav_booking) {
            goToFragment("Booking", new MyBookingFragment(), BOOKING_FRAGMENT);
        } else if (id == R.id.nav_account) {
            goToFragment("My Account", new MyAccountFragment(), ACCOUNT_FRAGMENT);

        } else if (id == R.id.nav_signOut) {

        }

//        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

//        switch (item.getItemId()) {
//            case R.id.nav_my_demoApp:
//                actionBarLogo.setVisibility(View.VISIBLE);
//                setFragment(new HomeFragment(), HOME_FRAGMENT);
//                break;
//
//            case R.id.nav_my_orders:
//                goToFragment("My Orders", new MyOrdersFragment(), ORDERS_FRAGMENT);
//                break;
//            case R.id.nav_my_cart:
//                goToFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);
//                break;
//        }

        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void setFragment(Fragment fragment, int fragmentNo) {
        if (fragmentNo != currentFragment) {

            if (fragmentNo == REWARD_FRAGMENT) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(Color.parseColor("#5B04B1"));
                    toolbar.setBackgroundColor(Color.parseColor("#5B04B1"));
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
                    toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
            currentFragment = fragmentNo;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
            fragmentTransaction.replace(frameLayout.getId(), fragment);
            fragmentTransaction.commit();
        }
    }
}
