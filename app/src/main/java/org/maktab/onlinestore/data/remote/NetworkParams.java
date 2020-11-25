package org.maktab.onlinestore.data.remote;


import android.net.Uri;

import org.maktab.onlinestore.data.model.Images;

import java.util.HashMap;
import java.util.Map;

public class NetworkParams {


    public static final String BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";
    public static final String CONSUMER_KEY = "ck_671aacd21cdac7c8fafc30c6fa0bf09eba15b074";
    public static final String CONSUMER_SECRET = "cs_75d98b6177540214b3fb44fee036cf935f025616";

    public static final Map<String, String> BASE_OPTIONS = new HashMap<String, String>() {{
        put("consumer_key", CONSUMER_KEY);
        put("consumer_secret", CONSUMER_SECRET);
    }};
    public static final String RATING_COUNT_DESC = "rating_count&DESC";
    public static final String AVERAGE_RATING_DESC = "average_rating&DESC";
    public static final String CREATED_AT_DESC = "created_at&DESC";

    public static Map<String, String> getMostVisitedProducts() {
        Map<String, String> products = new HashMap<>();
        products.putAll(BASE_OPTIONS);
        products.put("filter[orderby]", RATING_COUNT_DESC);

        return products;
    }

    public static Map<String, String> getHighestScoreProducts() {
        Map<String, String> products = new HashMap<>();
        products.putAll(BASE_OPTIONS);
        products.put("filter[orderby]", AVERAGE_RATING_DESC);

        return products;
    }

    public static Map<String, String> getLatestProducts() {
        Map<String, String> products = new HashMap<>();
        products.putAll(BASE_OPTIONS);
        products.put("filter[orderby]", CREATED_AT_DESC);

        return products;
    }

    public static Map<String, String> getProducts(String page) {
        Map<String, String> products = new HashMap<>();
        products.putAll(BASE_OPTIONS);
        products.put("page", page);

        return products;
    }

    public static Uri getPhotoPageUri(Images images) {
        Uri uri = Uri.parse(images.getSrc())
                .buildUpon()
                .build();

        return uri;
    }
}
