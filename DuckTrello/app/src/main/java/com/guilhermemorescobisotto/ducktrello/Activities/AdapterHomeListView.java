package com.guilhermemorescobisotto.ducktrello.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.guilhermemorescobisotto.ducktrello.R;

import java.util.List;

/**
 * Created by guilhermemorescobisotto on 4/26/16.
 */
public class AdapterHomeListView extends BaseAdapter {

    private List<BoardItem> items;
    private Context context;

    public AdapterHomeListView(Context context, List<BoardItem> items) {
        this.items = items;
        this.context = context;
     }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public BoardItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.home_list_view, null);
        }

        try {
            BoardItem item = items.get(position);

            ((TextView) convertView.findViewById(R.id.homeName)).setText(item.getName());
            ((TextView) convertView.findViewById(R.id.homeActiveMembers)).setText("Membros ativos: " + item.getBoard().getActiveMembers().size());
            ((TextView) convertView.findViewById(R.id.homeUserType)).setText(item.getBoard().getUserType());


        } catch (IndexOutOfBoundsException e) {

        }
        return convertView;
    }
}
