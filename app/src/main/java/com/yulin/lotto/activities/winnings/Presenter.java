package com.yulin.lotto.activities.winnings;

import androidx.recyclerview.widget.RecyclerView;

import com.opencsv.CSVReader;
import com.yulin.lotto.rest.GetWinsListService;
import com.yulin.lotto.rest.RetrofitInstance;

import android.os.Handler;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Yulin. I on 19,March,2020
 */
public class Presenter {
    private final IWinningsView mView;
    private final String winningsFileName = "raw/lotto.csv";
    private WinningAdapter winningAdapter;

    public Presenter(IWinningsView view) {
        this.mView = view;
    }

    public void setViews() {
        setWinningRecyclerView();
    }

    private void setWinningRecyclerView() {
        RecyclerView recyclerView = mView.getWinningRecyclerView();
        winningAdapter = new WinningAdapter(mView.getContext());
        recyclerView.setAdapter(winningAdapter);

        GetWinsListService service = RetrofitInstance.getInstance().create(GetWinsListService.class);
        Call<ResponseBody> call = service.getWinsCSV();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                CSVReader reader = new CSVReader(response.body().charStream());
                    winningAdapter.updateWinListFromCall(reader);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("remove");
            }
        });
    }
}
