package com.yulin.lotto.common;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.opencsv.CSVReader;
import com.yulin.lotto.R;
import com.yulin.lotto.rest.GetWinsListService;
import com.yulin.lotto.rest.RetrofitInstance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Yulin. I on 20,March,2020
 */
public class PreviousWinners {
    private final Context context;
    private List<WinObject> winList = new ArrayList<>();
    public static List<List<Integer>> winSerials = new ArrayList<>(); //that's a copy of winList's serials numbers only

    public PreviousWinners(Context context) {
        this.context = context;
    }

    public void fetchWinList(Consumer<List<WinObject>> onListUpdated) {
        initWinListFromLocal(onListUpdated);
        updateWinListFromCall(onListUpdated);
    }

    private void updateWinListFromCall(Consumer<List<WinObject>> onListUpdated) {
        GetWinsListService service = RetrofitInstance.getInstance().create(GetWinsListService.class);
        Call<ResponseBody> call = service.getWinsCSV();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                CSVReader reader = new CSVReader(response.body().charStream());
                updateWinListFromCall(reader, onListUpdated);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //do nothing - the local file is already loaded
            }
        });
    }

    private void updateWinListFromCall(CSVReader reader, Consumer<List<WinObject>> onFinish) {
        try {
            List<String[]> rawList = new AsyncReadAll().execute(reader).get();
            rawList.remove(0);  //remove giberish headers
            List<WinObject> winList = new ArrayList<>();

            for (String[] line : rawList) {
                winList.add(new WinObject(line));

                List<Integer> temp = new ArrayList<>();
                for (String number : Arrays.asList(line).subList(2, 8)) {
                    temp.add(Integer.parseInt(number));
                }
                winSerials.add(temp);
            }

            this.winList = winList;
            onFinish.accept(winList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initWinListFromLocal(Consumer<List<WinObject>> onListUpdated) {
        try {
            String csvLine;
            InputStream inputStream = context.getResources().openRawResource(R.raw.lotto);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((csvLine = reader.readLine()) != null) {
                String[] splitted = csvLine.split(",");
                winList.add(new WinObject(splitted));
            }
            onListUpdated.accept(winList);
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
