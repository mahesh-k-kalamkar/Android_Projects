package com.mystore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mystore.Fragments.HomeFragment;
import com.mystore.Fragments.ProfileFragment;
import com.mystore.Fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 0;
    ImageView image;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ft = getSupportFragmentManager().beginTransaction();
        HomeFragment HomeFragment = new HomeFragment();
        ft.replace(R.id.main_fragment, HomeFragment);
        ft.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    public void openCamera(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            assert data != null;
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(photo);
        }
    }


    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {

        switch (item.getItemId()) {
            case R.id.homeFragment:
                Fragment fragment = new HomeFragment();
                fragment = new HomeFragment();
                switchFragment(fragment);
                return true;

            case R.id.SearchFragment:
                fragment = new SearchFragment();
                switchFragment(fragment);
                return true;

            case R.id.MyordersFragment:
                fragment = new HomeFragment();
                switchFragment(fragment);
                return true;

            case R.id.profileFragment:
                fragment = new ProfileFragment();
                switchFragment(fragment);
                return true;
        }

        return false;

    };

    private void switchFragment(Fragment fragment) {
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment, fragment);
        ft.commit();
    }

}


//https://github.com/RyanKoech/FurnitureApp_UI.git