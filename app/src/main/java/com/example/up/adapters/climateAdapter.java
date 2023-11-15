package com.example.up.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.up.R;
import com.example.up.database.Database;
import com.example.up.database.entities.Climate;

import java.util.List;

public class climateAdapter extends ArrayAdapter<Climate> {
    private final LayoutInflater inflater;
    private final int layout;
    private final List<Climate> items;

    Database db;

    public climateAdapter(Context context, int resourse, List<Climate> items){
        super(context,resourse,items);
        this.items = items;
        this.layout=resourse;
        this.inflater = LayoutInflater.from(context);

        db=Database.getDatabase(getContext());
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Climate item = items.get(position);

        viewHolder.ClimateName.setText(item.climate_name);
        viewHolder.ClimateHumidity.setText(String.valueOf(item.humidity));
        viewHolder.ClimateTemperature.setText(String.valueOf(item.temperature));
        return convertView;
    }

    private class ViewHolder{
        final TextView ClimateName;
        final TextView ClimateHumidity;
        final TextView ClimateTemperature;
        public ViewHolder(View view){
            ClimateName = view.findViewById(R.id.climate_name);
            ClimateHumidity = view.findViewById(R.id.climate_humidity);
            ClimateTemperature = view.findViewById(R.id.climate_temperature);
        }
    }

    @Override
    public void remove(@Nullable Climate object) {
        if (object == null) {
            return;
        }
        items.remove(object);
        super.remove(object);
        notifyDataSetChanged();
    }
}
