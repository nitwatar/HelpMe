package com.atar.mani.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atar.mani.R;


/**
 * Created by atarmanipandey on 23/9/16.
 */
public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.WeatherViewHolder> {

    private static final String TAG = "MainListAdapter";

    private Context mContext;
    private String[] mDataSet;




    public MainListAdapter(Context context, String[] dataSet) {
        mContext = context;
        mDataSet = dataSet;
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v;
        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.weather_card, viewGroup, false);

        return new WeatherViewHolder(v);

    }

    @Override
    public void onBindViewHolder(WeatherViewHolder viewHolder, final int position) {
        WeatherViewHolder holder = (WeatherViewHolder) viewHolder;
        holder.temp.setText(mDataSet[position]);
        holder.itemView.setTag(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;

                switch ((Integer) v.getTag()) {
                    case 0:
                        intent = new Intent(mContext, DealerLocatorActivity.class);
                        break;



                }
                if (intent != null)
                    mContext.startActivity(intent);

            }
        });


    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView temp;

        public WeatherViewHolder(View v) {
            super(v);
            this.temp = (TextView) v.findViewById(R.id.temp);
        }
    }


}
