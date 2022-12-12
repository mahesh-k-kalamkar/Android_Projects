package com.mystore.Fragments;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.mystore.R;

import org.w3c.dom.Text;

public class SearchFragment extends Fragment {

    TextView btn_search;
    EditText searchTxt;
    View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_search, container, false);

        searchTxt = v.findViewById(R.id.searchTxt);
        btn_search = v.findViewById(R.id.btn_search);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                String term = searchTxt.getText().toString();
                Log.d("searchTxt", term);
                intent.putExtra(SearchManager.QUERY, term);
                startActivity(intent);

            }
        });

        return v;
    }
}
