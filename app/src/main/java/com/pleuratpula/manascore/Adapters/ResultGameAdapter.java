package com.pleuratpula.manascore.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pleuratpula.manascore.Models.ResultGame;
import com.pleuratpula.manascore.R;

import java.util.ArrayList;

import javax.xml.transform.Result;

public class ResultGameAdapter extends ArrayAdapter<ResultGame> {
    public ResultGameAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ResultGame> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        TempItemHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater =
                    ((Activity)getContext()).getLayoutInflater();
            row = inflater.inflate(R.layout.layout_result_game, null);

            holder = new TempItemHolder();
            holder.resultGamePlayers = row.findViewById(R.id.txtresultGamePoints);
            holder.resultGamePoints = row.findViewById(R.id.txtresultPoints);
            row.setTag(holder);
        }
        else
        {
            holder = (TempItemHolder)row.getTag();
        }

        ResultGame transaction = getItem(position);
        holder.resultGamePoints.setText(transaction.getPlayersGame());
        holder.resultGamePlayers.setText(transaction.getId());
        return row;
    }
    private class TempItemHolder
    {
        public TextView resultGamePoints;
        public TextView resultGamePlayers;
    }
}
