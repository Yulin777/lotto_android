package com.yulin.lotto.activities.winnings;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.opencsv.CSVReader;
import com.yulin.lotto.R;

import java.io.BufferedReader;
import java.io.IOException;
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
        updateWinListFromLocal(context); //load local file
    }

    private void updateWinListFromLocal(Context context) {
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

    public void updateWinListFromCall(CSVReader reader) {
        try {
            List<String[]> rawList = new AsyncReadAll().execute(reader).get();
            rawList.remove(0);  //remove giberish headers
            List<WinObject> winList = new ArrayList<>();

            for (String[] line : rawList) {
                winList.add(new WinObject(line));
            }

            this.winList = winList;
            notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class AsyncReadAll extends AsyncTask<CSVReader, Void, List<String[]>> {

        @Override
        protected List<String[]> doInBackground(CSVReader... csvReaders) {
            try {
                return csvReaders[0].readAll();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
