package com.example.building;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdpater extends BaseAdapter {
    private TextView title;
    private TextView data;

    private ArrayList<ListItem> listViewItemList = new ArrayList<ListItem>();

    public void addItem (String title, String data) {
        ListItem item = new ListItem();

        item.setTitle(title);
        item.setData(data);

        listViewItemList.add(item);
    }
    @Override
    public int getCount() {
        return listViewItemList.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        title = (TextView)convertView.findViewById(R.id.title);
        data = (TextView)convertView.findViewById(R.id.info);

        ListItem listItem = listViewItemList .get(position);

        title.setText(listItem.getTitle());
        data.setText(listItem.getData());

        return convertView;
    }
}
