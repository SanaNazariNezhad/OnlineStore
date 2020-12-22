package org.maktab.onlinestore.utilities;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LiveData;

import org.greenrobot.eventbus.EventBus;
import org.maktab.onlinestore.data.model.SalesReport;
import org.maktab.onlinestore.data.repository.OnlineStoreRepository;
import org.maktab.onlinestore.event.NotificationEvent;
import org.maktab.onlinestore.view.activity.HomeActivity;
import org.maktab.onlinestore.R;


public class ServicesUtils {

    private static final int NOTIFICATION_ID = 1;
    private static LiveData<SalesReport> sSalesReportLiveData;

    public static void pollAndShowNotification(Context context, String tag) {
//        String query = QueryPreferences.getSearchQuery(context);

        OnlineStoreRepository repository = new OnlineStoreRepository();
        SalesReport salesReport;
        int total_sales;


        salesReport = repository.fetchProductItems();


        if (salesReport == null) {
            Log.d(tag, "Items from server not fetched salesReport is null");
            return;
        }

        total_sales = salesReport.getTotalItems();

        if (total_sales == 0) {
            Log.d(tag, "Items from server not fetched");
            return;
        }

        int lastTotalItems = QueryPreferences.getTotalItems(context);
        if (total_sales != lastTotalItems) {
            Log.d(tag, "show notification");

            sendNotificationEvent(context);
        } else {
            Log.d(tag, "do nothing");
        }

        QueryPreferences.setTotalItems(context, total_sales);
    }

    private static void sendNotificationEvent(Context context) {
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                HomeActivity.newIntent(context),
                0);

        String channelId = context.getResources().getString(R.string.channel_id);
        Notification notification = new NotificationCompat.Builder(context, channelId)
                .setContentTitle(context.getResources().getString(R.string.new_products_title))
                .setContentText(context.getResources().getString(R.string.new_products_text))
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationEvent notificationEvent = new NotificationEvent(NOTIFICATION_ID, notification);
        EventBus.getDefault().post(notificationEvent);
    }
}
