package com.ht.home.ui;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ht.home.R;
import com.ht.home.bl.UserManager;
import com.mikepenz.iconics.context.IconicsLayoutInflater;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        verifyStoragePermissions(this);

//        boolean isServiceRunning = isMyServiceRunning(MQTTService.class);
//        LOG.debug("isServiceRunning: " + isServiceRunning);
//        if (!isServiceRunning) {
//            Intent intentBeacon = new Intent(this, MQTTService.class);
//            startService(intentBeacon);
//        }

        loadDefaultFragment();

        //UserManager.getInstance().login("admin", "admin");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {

            showDashboard();
            closeDrawer();
            fab.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_resources) {

            showMenu(R.id.resources_menu, true);

        } else if (id == R.id.nav_action_board) {

        } else if (id == R.id.nav_status) {

        } else if (id == R.id.nav_utilities) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_gateways) {

            showGateways();
            closeDrawer();
            setTitle(getString(R.string.title_gateways));
            fab.setVisibility(View.GONE);

        } else if (id == R.id.nav_nodes) {

            showNodes();
            closeDrawer();
            setTitle(getString(R.string.title_nodes));
            fab.setVisibility(View.GONE);

        } else if (id == R.id.nav_back) {

            showMenu(R.id.main_menu, false);

        }

        return true;
    }

    private void showMenu(int id, boolean showBack) {

        navigationView.getMenu().setGroupVisible(R.id.main_menu, false);
        navigationView.getMenu().setGroupVisible(R.id.resources_menu, false);
        navigationView.getMenu().setGroupVisible(R.id.back_menu, showBack);

        navigationView.getMenu().setGroupVisible(id, true);

    }

    private void closeDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void loadDefaultFragment() {

        if (UserManager.getInstance().isLoggedIn()) {
            showDashboard();
        } else {
            showLogin();
        }
    }

    public void showLogin() {
        switchFragment(new LoginFragment(), false);
        setTitle(R.string.title_login);
    }

    public void showDashboard() {
        switchFragment(new DashboardFragment(), false);
        setTitle(R.string.title_dashboard);
    }

    public void showNodes() {
        switchFragment(new NodeListFragment(), false);
        setTitle(R.string.title_resources);
    }

    public void showGateways() {
        switchFragment(new GatewayListFragment(), false);
        setTitle(R.string.title_resources);
    }

    public void switchFragment(Fragment fragment, boolean addBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.content_frame, fragment);
        if (addBackStack)
            fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commitAllowingStateLoss();


//        if (addBackStack && getZippyToolbar() != null) {
//            getZippyToolbar().showBackButton(true);
//        }
    }

    // Storage Permissions variables
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    //persmission method.
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
