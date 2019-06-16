package com.SkyNet.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.SkyNet.R;

import java.util.ArrayList;

public class WeatherAdapter extends BaseAdapter {
    ArrayList<WeatherInfo> weatherList;
    LayoutInflater inflater;

    public WeatherAdapter(Context context, ArrayList<WeatherInfo> weatherList) {
        inflater = LayoutInflater.from(context);
        this.weatherList = weatherList;
    }

    @Override
    public int getCount() {
        return weatherList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.weather_list_item, null);
            holder = new ViewHolder();
            holder.temp = (TextView) view.findViewById(R.id.temp);
            holder.weather = (TextView) view.findViewById(R.id.weather);
            holder.wind = (TextView) view.findViewById(R.id.wind);
            holder.week = (TextView) view.findViewById(R.id.week);
            holder.date = (TextView) view.findViewById(R.id.date);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.temp.setText(weatherList.get(position).getTemp());
        holder.weather.setText(weatherList.get(position).getWeather());
        holder.wind.setText(weatherList.get(position).getWind());
        holder.week.setText(weatherList.get(position).getWeek());
        holder.date.setText(weatherList.get(position).getDate());

        return view;
    }

    class ViewHolder {
        TextView temp;
        TextView weather;
        TextView wind;
        TextView week;
        TextView date;
    }
}
