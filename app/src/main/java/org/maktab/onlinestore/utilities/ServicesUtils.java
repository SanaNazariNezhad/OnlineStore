package org.maktab.onlinestore.utilities;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import org.greenrobot.eventbus.EventBus;
import org.maktab.onlinestore.R;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.data.model.SalesReport;
import org.maktab.onlinestore.data.repository.OnlineStoreRepository;
import org.maktab.onlinestore.data.repository.SplashDBRepository;
import org.maktab.onlinestore.event.NotificationEvent;
import org.maktab.onlinestore.view.activity.HomeActivity;
import org.maktab.onlinestore.view.activity.ProductDetailActivity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ServicesUtils {

    private static final int NOTIFICATION_ID = 1;

    public static void pollAndShowNotification(Context context, String tag) {

        SplashDBRepository repository = SplashDBRepository.getInstance(context);
       Product product = repository.getHighestScoreProduct().get(0);


        if (product == null) {
            Log.d(tag, "Items from server not fetched");
            return;
        }

        int productId = product.getId();
        String serverId = String.valueOf(productId);
//        String serverId = "3";
        String lastSavedId = QueryPreferences.getNumberOfProduct(context);
        if (!serverId.equals(lastSavedId)) {
            Log.d(tag, "show notification");

            sendNotificationEvent(context,product);
        } else {
            Log.d(tag, "do nothing");
        }

        QueryPreferences.setNumberOfProduct(context, serverId);
    }

    private static void sendNotificationEvent(Context context,Product product) {
        int productId = product.getId();
        URL url;
        Bitmap image = null;
        try {
            if (product.getImages().size() != 0) {
                url = new URL(product.getImages().get(0).getSrc());
                image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (image == null) {
            image = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_image);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                ProductDetailActivity.newIntent(context,productId),
                0);

        String channelId = context.getResources().getString(R.string.channel_id);
        Notification notification = new NotificationCompat.Builder(context, channelId)
                .setContentTitle(context.getResources().getString(R.string.new_product_title))
                .setContentText(product.getTitle())
                .setColor(context.getResources().getColor(R.color.purple_500))
                .setSmallIcon(R.drawable.ic_store)
                .setContentIntent(pendingIntent)
                .setLargeIcon(image)
                .setAutoCancel(true)
                .build();

        NotificationEvent notificationEvent = new NotificationEvent(NOTIFICATION_ID, notification);
        EventBus.getDefault().post(notificationEvent);
    }
}
