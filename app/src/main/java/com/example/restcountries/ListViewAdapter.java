package com.example.restcountries;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private Activity context;
    ArrayList<Model> countriesObj;

    public ListViewAdapter(Activity context, ArrayList<Model> countriesObj) {

        Log.i("Countries Obj Size", Integer.toString(countriesObj.size()));
        this.context = context;
        this.countriesObj = countriesObj;

    }

    public static class ViewHolder {

        TextView textViewCountry;

    }

    @Override
    public int getCount() {

        //Log.i("SIZE", Integer.toString(countriesObj.size()));
        if(countriesObj.size()<=0) return 1;

        return countriesObj.size();

    }

    @Override
    public Object getItem(int position) {

        return position;

    }

    @Override
    public long getItemId(int position) {

        return position;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=convertView;

        LayoutInflater inflater = context.getLayoutInflater();
        ViewHolder vh;

        if(convertView == null) {

            vh=new ViewHolder();
            row = inflater.inflate(R.layout.row_item, null, true);
            vh.textViewCountry = (TextView) row.findViewById(R.id.textViewCountry);
            row.setTag(vh);

        } else {

            vh = (ViewHolder) convertView.getTag();

        }

        vh.textViewCountry.setText(countriesObj.get(position).getCountryName());
        return row;

    }
}
