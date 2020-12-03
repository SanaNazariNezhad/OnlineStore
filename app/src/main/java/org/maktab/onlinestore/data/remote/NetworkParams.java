package org.maktab.onlinestore.data.remote;


import android.net.Uri;

import org.maktab.onlinestore.data.model.Images;

import java.util.HashMap;
import java.util.Map;

public class NetworkParams {


    public static final String BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";
    public static final String CONSUMER_KEY = "ck_671aacd21cdac7c8fafc30c6fa0bf09eba15b074";
    public static final String CONSUMER_SECRET = "cs_75d98b6177540214b3fb44fee036cf935f025616";
    public static final String RATING_COUNT = "crating_count";
    public static final String AVERAGE_RATING = "rating";
    public static final String CREATED_AT = "created_at";
    public static final String DESC = "DESC";
    public static final String FIELDS = "fields";
    public static final String  PARENT_CATEGORY = "0";
    public static final String PARENT_OF_CATEGORY = "parent";
    public static final String FILTER_ORDER = "filter[order]";
    public static final String CATEGORY = "category";

    public static final Map<String, String> BASE_OPTIONS = new HashMap<String, String>() {{
        put("consumer_key", CONSUMER_KEY);
        put("consumer_secret", CONSUMER_SECRET);
    }};

    public static Map<String, String> getMostVisitedProducts() {
        Map<String, String> products = new HashMap<>();
        products.putAll(BASE_OPTIONS);
        products.put(FILTER_ORDER, DESC);
        products.put(FIELDS, RATING_COUNT);

        return products;
    }

    public static Map<String, String> getHighestScoreProducts() {
        Map<String, String> products = new HashMap<>();
        products.putAll(BASE_OPTIONS);
        products.put(FILTER_ORDER, DESC);
        products.put(FIELDS, AVERAGE_RATING);

        return products;
    }

    public static Map<String, String> getLatestProducts() {
        Map<String, String> products = new HashMap<>();
        products.putAll(BASE_OPTIONS);
        products.put(FILTER_ORDER, DESC);
        products.put(FIELDS, CREATED_AT);

        return products;
    }

    public static Map<String, String> getProducts(String page) {
        Map<String, String> products = new HashMap<>();
        products.putAll(BASE_OPTIONS);
        products.put("page", page);

        return products;
    }

    public static Map<String, String> getProductsWithParentId(String parentId) {
        Map<String, String> products = new HashMap<>();
        products.putAll(BASE_OPTIONS);
        products.put(CATEGORY,parentId);

        return products;
    }

    public static Map<String, String> getCategories() {
        Map<String, String> products = new HashMap<>();
        products.putAll(BASE_OPTIONS);
        products.put(PARENT_OF_CATEGORY, PARENT_CATEGORY);

        return products;
    }

    public static Map<String, String> subCategories(String parentId) {
        Map<String, String> products = new HashMap<>();
        products.putAll(BASE_OPTIONS);
        products.put(PARENT_OF_CATEGORY, parentId);

        return products;
    }

    public static Uri getPhotoPageUri(Images images) {
        Uri uri = Uri.parse(images.getSrc())
                .buildUpon()
                .build();

        return uri;
    }
}
