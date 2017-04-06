package in.anuraggoel.testo.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import in.anuraggoel.testo.R;
import in.anuraggoel.testo.fragments.HomeFragment;
import in.anuraggoel.testo.fragments.OrderFragment;
import in.anuraggoel.testo.fragments.ProductFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int mSelectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setToolbar();
        initView();

        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //default it set first item as selected
        mSelectedId = savedInstanceState == null ? R.id.nav_home : savedInstanceState.getInt("SELECTED_ID");
        itemSelection(mSelectedId);

    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    private void initView() {
        mDrawer = (NavigationView) findViewById(R.id.nav_view);
        mDrawer.setNavigationItemSelectedListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    private void itemSelection(int mSelectedId) {
        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (mSelectedId) {

            case R.id.nav_home:
                fragment = new ProductFragment();
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_order:
                fragment = new OrderFragment();
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_logout:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;


        }
        fragmentManager.beginTransaction()
                .replace(R.id.flContent, fragment)
                .commit();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mSelectedId = menuItem.getItemId();
        itemSelection(mSelectedId);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //save selected item so it will remains same even after orientation change
        outState.putInt("SELECTED_ID", mSelectedId);
    }

}