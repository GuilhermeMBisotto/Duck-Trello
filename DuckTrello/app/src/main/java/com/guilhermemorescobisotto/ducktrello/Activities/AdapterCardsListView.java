package com.guilhermemorescobisotto.ducktrello.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.guilhermemorescobisotto.ducktrello.Models.Card;
import com.guilhermemorescobisotto.ducktrello.R;

import java.util.Date;
import java.util.List;

/**
 * Created by guilhermemorescobisotto on 4/26/16.
 */
public class AdapterCardsListView extends BaseAdapter {

    private List<CardItem> items;
    private Context context;

    public AdapterCardsListView(Context context, List<CardItem> items) {
        this.items = items;
        this.context = context;
     }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public CardItem getItem(int position) {
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
            convertView = vi.inflate(R.layout.cards_list_view, null);
        }

        try {
            CardItem item = items.get(position);

            ((TextView) convertView.findViewById(R.id.tv_cardName)).setText(item.getName());
            ((TextView) convertView.findViewById(R.id.tv_dateLastActivity)).setText(String.format("Última atividade: %s", item.getCard().dateLastActivity.toString()));
            ((TextView) convertView.findViewById(R.id.tv_due)).setText(String.format("Limite: %s", (item.getCard().due == null ? "" : item.getCard().due.toString())));
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
