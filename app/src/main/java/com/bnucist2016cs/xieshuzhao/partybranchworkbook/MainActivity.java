package com.bnucist2016cs.xieshuzhao.partybranchworkbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bnucist2016cs.xieshuzhao.partybranchworkbook.base.BasicActivity;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.base.User;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.data.Book;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.data.BookAdapter;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.data.BookFragment;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.data.NewBookActivity;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.news.NewsActivity;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.news.NewsTitleFragment;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.util.SetMyInfoActivity;
import com.bumptech.glide.Glide;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

public class MainActivity extends BasicActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView header_username;
    private TextView header_email;
    private ImageView header_image;
    private Boolean isPause = false;

    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newEvent();
            }
        });*/



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        BookFragment fragment = new BookFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_main,fragment);
        transaction.commit();

        View headerlayout = navigationView.getHeaderView(0);
        header_email = (TextView) headerlayout.findViewById(R.id.nav_header_email);
        header_username = (TextView) headerlayout.findViewById(R.id.nav_header_username);
        header_image = (ImageView) headerlayout.findViewById(R.id.nav_header_imageView);

        setNavHeader();

    }


    public void setNavHeader(){
        User user = BmobUser.getCurrentUser(User.class);
        String s1 = user.getEmail();
        header_email.setText(s1);
        header_username.setText(user.getUsername());
        BmobFile avatar = user.getPath();
        if (avatar==null){
            Glide.with(MainActivity.this).load(R.drawable.apple_pic).into(header_image);
        }
        else{
            String avatarUrl = avatar.getFileUrl();
            Glide.with(MainActivity.this).load(avatarUrl).into(header_image);
        }


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
            newEvent();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            main();
        } else if (id == R.id.nav_gallery) {
            news();
        } else if (id == R.id.nav_share) {
            share();
        } else if (id == R.id.nav_setting) {
            settings();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void news(){
        NewsTitleFragment fragment = new NewsTitleFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_main,fragment);
        transaction.commit();
    }
    public void main(){
        BookFragment fragment = new BookFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_main,fragment);
        transaction.commit();
    }

    public void share(){

    }

    public void settings(){
        Intent intent = new Intent(MainActivity.this, SetMyInfoActivity.class);
        startActivity(intent);
    }

    public void newEvent(){
        Intent intent = new Intent(MainActivity.this, NewBookActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPause =true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isPause){
            isPause = false;
            main();
        }
    }
}
