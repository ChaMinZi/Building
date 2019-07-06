package com.example.building;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

public class InfoFragment extends Fragment {
    private Context mContext;
    private List<ARPoint> list;
    private FloatingActionButton mButton;
    private EditText name;
    private ListView listView;
    private ListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        mContext = container.getContext();
        mButton = (FloatingActionButton)view.findViewById(R.id.mButton);
        name = (EditText)view.findViewById(R.id.name);
        listView = (ListView)view.findViewById(R.id.data);
        listView.setAdapter(adapter);

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((MainActivity)mContext).displayFragment(false);
            }
        });
        return view;
    }
}
