package SeedsTheif.utils;

import okhttp3.*;
import org.json.JSONObject;
import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.ui.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProfitTracker {
    private StopWatch timer;

    private HashMap<Integer, Integer> items;
    private HashMap<Integer, Integer> prices;

    private final OkHttpClient client = new OkHttpClient();

    public ProfitTracker() {
        timer = StopWatch.start();
        items = new HashMap<>();
        prices = new HashMap<>();
    }

    public void addItem(int itemId, int count) {
        if (items.containsKey(itemId)) {
            int total = items.get(itemId) + count;
            items.put(itemId, total);
        } else {
            items.put(itemId, count);
        }
    }

    public void setPrice(int itemId, int price) {
        prices.put(itemId, price);
    }

    public int getTotalProfit() {
        int sum = 0;
        for (Map.Entry entry : items.entrySet()) {
            int count = (int) entry.getValue();
            int price = prices.getOrDefault(entry.getKey(), 0);
            sum += price * count;
        }
        return sum;
    }

    public double getHourlyProfit() {
        return Math.floor(timer.getHourlyRate(this.getTotalProfit()));
    }

    public void getPrice(int itemId) {
        //?itemId=21143
        final HttpUrl url = HttpUrl.parse("https://api.runelite.net/runelite-1.5.6.1/osb/ge")
                .newBuilder()
                .addQueryParameter("itemId", Integer.toString(itemId))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    String jsonData = response.body().string();
                    JSONObject priceData = new JSONObject(jsonData);

                    int price = priceData.getInt("overall_average");

                    setPrice(itemId, price);
                } catch (IOException e) {
                    Log.severe(e.getMessage());
                }
            }
        });
    }
}
