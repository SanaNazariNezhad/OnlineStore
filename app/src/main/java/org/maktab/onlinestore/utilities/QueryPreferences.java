package org.maktab.onlinestore.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class QueryPreferences {
    private static final String PREF_SEARCH_QUERY = "searchQuery";
    private static final String PREF_FILTER_COLOR = "filterColor";
    private static final String PREF_TOTAL_ITEMS = "totalItems";

    public static String getSearchQuery(Context context) {
        return getSharedPreferences(context).getString(PREF_SEARCH_QUERY, null);
    }

    public static void setSearchQuery(Context context, String query) {
        getSharedPreferences(context)
                .edit()
                .putString(PREF_SEARCH_QUERY, query)
                .apply();
    }

    public static String getFilterColor(Context context) {
        return getSharedPreferences(context).getString(PREF_FILTER_COLOR, null);
    }

    public static void setFilterColor(Context context, String color) {
        getSharedPreferences(context)
                .edit()
                .putString(PREF_FILTER_COLOR, color)
                .apply();
    }

    public static int getTotalItems(Context context) {
        return getSharedPreferences(context).getInt(PREF_TOTAL_ITEMS,0);
    }

    public static void setTotalItems(Context context, int totalItems) {
        getSharedPreferences(context)
                .edit()
                .putInt(PREF_TOTAL_ITEMS, totalItems)
                .apply();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }
}
