package com.rj.rjxecommerce.home;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.rj.rjxecommerce.R;
import com.rj.rjxecommerce.fragments.EmptyFragment;
import com.rj.rjxecommerce.productlist.ProductListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();
        defaultConf();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void defaultConf() {
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, new ProductListFragment()).commit();

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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Toast.makeText(this, "Work in progress", Toast.LENGTH_SHORT).show();
        if (id == R.id.nav_myaccount) {
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, new EmptyFragment()).commit();
            // Handle the camera action
        } else if (id == R.id.nav_offer) {
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, new EmptyFragment()).commit();
        } else if (id == R.id.nav_myorder) {
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, new EmptyFragment()).commit();

        } else if (id == R.id.nav_logout) {
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, new EmptyFragment()).commit();

        }else {
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, new ProductListFragment()).commit();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
