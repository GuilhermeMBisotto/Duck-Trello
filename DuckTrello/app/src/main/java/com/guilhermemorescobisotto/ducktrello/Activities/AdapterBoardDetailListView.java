package com.guilhermemorescobisotto.ducktrello.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.guilhermemorescobisotto.ducktrello.R;

import java.util.List;

/**
 * Created by guilhermemorescobisotto on 4/26/16.
 */
public class AdapterBoardDetailListView extends BaseAdapter {

    private List<BoardDetailItem> items;
    private Context context;

    public AdapterBoardDetailListView(Context context, List<BoardDetailItem> items) {
        this.items = items;
        this.context = context;
     }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public BoardDetailItem getItem(int position) {
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
            convertView = vi.inflate(R.layout.board_detail_list_view, null);
        }

        try {
            BoardDetailItem item = items.get(position);

            ((TextView) convertView.findViewById(R.id.tv_listTitle)).setText(item.getListName());
            ((TextView) convertView.findViewById(R.id.tv_cardName)).setText(item.getName());
            ((TextView) convertView.findViewById(R.id.tv_checkLists)).setText(String.format("Checklists: %s/%s", item.getCard().badges.checkItemsChecked, item.getCard().badges.checkItems));
            ((TextView) convertView.findViewById(R.id.tv_membersName)).setText(String.format("Membros do Card: %s", item.getCard().getMembersNameFromCard()));
            ((TextView) convertView.findViewById(R.id.tv_comments)).setText(String.format("Comentários: %s",item.getCard().badges.comments));
            ((TextView) convertView.findViewById(R.id.tv_attachments)).setText(String.format("Anexos: %s", item.getCard().badges.attachments));

            ((CheckBox) convertView.findViewById(R.id.cb_card)).setChecked(false);

        } catch (IndexOutOfBoundsException e) {

        }
        return convertView;
    }
}
