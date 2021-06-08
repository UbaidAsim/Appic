package com.example.muhammadubaidullah.appic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    public WordAdapter(@NonNull Context context, ArrayList<Word>words) {
        super(context,0, words);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }


        Word currentWord = getItem(position);


        TextView nameTextView = (TextView) listItemView.findViewById(R.id.MivokId);

        nameTextView.setText(currentWord.getMivok());


        TextView numberTextView = (TextView) listItemView.findViewById(R.id.DefaultId);

        numberTextView.setText(currentWord.getDefault());

        ImageView numberImageView=(ImageView)listItemView.findViewById(R.id.icon);
        numberImageView.setImageResource(currentWord.getIconID());

        return listItemView;
    }
}

