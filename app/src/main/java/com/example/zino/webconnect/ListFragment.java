package com.example.zino.webconnect;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class ListFragment extends Fragment implements View.OnClickListener {
    String TAG = this.getClass().getName();
    Context context;
    ListView listView;
    MyListAdapter myListAdapter;
    Button bt_load;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_fragment, container, false);

        bt_load = (Button) view.findViewById(R.id.bt_load);
        listView = (ListView) view.findViewById(R.id.listView);
        myListAdapter = new MyListAdapter(getContext());
        listView.setAdapter(myListAdapter);

        bt_load.setOnClickListener(this);
        Log.d(TAG, "onCreateView 호출"+this);

        return view;
    }


    @Override
    public void onClick(View v) {

        myListAdapter.parseJson();
        myListAdapter.notifyDataSetChanged();

    }

}
