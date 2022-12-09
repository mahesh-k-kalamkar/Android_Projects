package com.mystore.Fragments;

import static android.content.Context.MODE_PRIVATE;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.mystore.R;


public class ProfileFragment extends Fragment {

    View view;
    SwitchMaterial toggle_mode_switch;
    private SharedPreferences sharedPreferences;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        toggle_mode_switch = view.findViewById(R.id.toggle_mode_switch);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        sharedPreferences = getContext().getSharedPreferences("Setting_Pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean isCheck = sharedPreferences.getBoolean("isCheckMobData", false);
        toggle_mode_switch.setChecked(isCheck);
        toggle_mode_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES);
                    editor.putBoolean("isCheckMobData", b);
                    editor.commit();
                }else {
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO);
                    editor.putBoolean("isCheckMobData", false);
                    editor.commit();
                }
            }
        });
    }
}
