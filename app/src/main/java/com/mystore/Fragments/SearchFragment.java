package com.mystore.Fragments;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.mystore.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SearchFragment extends Fragment {

    static float temp;
    TextView btn_search, temperature;
    EditText searchTxt;
    View v;

    public static float cpuTemperature() {
        Process process;

        try {
            process = Runtime.getRuntime().exec("cat sys/class/thermal_zone0/temp");
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            if (line != null) {
                temp = Float.parseFloat(line);
                return temp / 1000.0f;
            } else {
                return 51.0f;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_search, container, false);


        searchTxt = v.findViewById(R.id.searchTxt);
        temperature = v.findViewById(R.id.TvTemperature);
        btn_search = v.findViewById(R.id.btn_search);


        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                String term = searchTxt.getText().toString();
                Log.d("searchTxt", term);
                intent.putExtra(SearchManager.QUERY, term);
                startActivity(intent);

                cpuTemperature();
                temperature.setText((int) temp);

            }
        });

        return v;
    }


}
