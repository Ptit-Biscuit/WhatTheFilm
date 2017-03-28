package com.example.ptit_biscuit.whatthefilm;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ptit-Biscuit on 26/03/2017.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {
    private Activity context;

    static class ViewHolder {
        public TextView nameHolder;
        public TextView descHolder;
        public TextView dateHolder;
    }

    public MovieAdapter(Activity context, ArrayList<Movie> items) {
        super(context, R.layout.list_view, items);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder viewHolder;

        if (row == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(R.layout.list_view, null);

            viewHolder = new ViewHolder();
            viewHolder.nameHolder = (TextView) row.findViewById(R.id.name);
            viewHolder.descHolder = (TextView) row.findViewById(R.id.desc);
            viewHolder.dateHolder = (TextView) row.findViewById(R.id.date);
            row.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) row.getTag();

            viewHolder.nameHolder.setText(getItem(position).getName());
            viewHolder.descHolder.setText(getItem(position).getDesc());
            viewHolder.dateHolder.setText(getItem(position).getDate());
        }

        return row;
    }
}
