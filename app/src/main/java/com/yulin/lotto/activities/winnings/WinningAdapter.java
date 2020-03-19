package com.yulin.lotto.activities.winnings;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.opencsv.CSVReader;
import com.yulin.lotto.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yulin. I on 19,March,2020
 */
class WinningAdapter extends Adapter<WinViewHolder> {
    List<WinObject> winList = new ArrayList<>();

    public WinningAdapter(Context context) {
        updateWinList(context);
    }

    private void updateWinList(Context context) {
        try {
            String csvLine;
            InputStream inputStream = context.getResources().openRawResource(R.raw.lotto);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((csvLine = reader.readLine()) != null) {
                String[] splitted = csvLine.split(",");
                winList.add(new WinObject(splitted));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public WinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.winning_item, parent, false);
        return new WinViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WinViewHolder holder, int position) {
        holder.updateUI(winList.get(position));
    }

    @Override
    public int getItemCount() {
        return winList.size();
    }
}
