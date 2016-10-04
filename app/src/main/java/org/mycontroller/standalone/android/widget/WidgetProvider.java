package org.mycontroller.standalone.android.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import org.mycontroller.standalone.android.bl.services.MQTTService;
import org.mycontroller.standalone.android.R;
import org.mycontroller.standalone.android.util.LOG;

/**
 * Created by tibi on 27/09/16.
 */
public class WidgetProvider extends AppWidgetProvider {

    private static final String ACTION_UP_CLICK =
            "com.example.myapp.action.UP_CLICK";
    private static final String ACTION_STOP_CLICK =
            "com.example.myapp.action.STOP_CLICK";
    private static final String ACTION_DOWN_CLICK =
            "com.example.myapp.action.DOWN_CLICK";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetID : appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
            remoteViews.setOnClickPendingIntent(R.id.btn_up, getPendingSelfIntent(context, ACTION_UP_CLICK));
            remoteViews.setOnClickPendingIntent(R.id.btn_stop, getPendingSelfIntent(context, ACTION_STOP_CLICK));
            remoteViews.setOnClickPendingIntent(R.id.btn_down, getPendingSelfIntent(context, ACTION_DOWN_CLICK));

            appWidgetManager.updateAppWidget(appWidgetID, remoteViews);
        }

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (ACTION_UP_CLICK.equals(intent.getAction())) {
            if (MQTTService.getInstance()!=null) {
                MQTTService.getInstance().publishMessage("mymqtt/2/1/1/0/29", new byte[1]);
                MQTTService.getInstance().publishMessage("mymqtt/2/2/1/0/29", new byte[1]);
            }
            LOG.debug("UP");
        } else if (ACTION_STOP_CLICK.equals(intent.getAction())) {
            if (MQTTService.getInstance()!=null) {
                MQTTService.getInstance().publishMessage("mymqtt/2/1/1/0/31", new byte[1]);
                MQTTService.getInstance().publishMessage("mymqtt/2/2/1/0/31", new byte[1]);
            }
            LOG.debug("STOP");
        } else if (ACTION_DOWN_CLICK.equals(intent.getAction())) {
            if (MQTTService.getInstance()!=null) {
                MQTTService.getInstance().publishMessage("mymqtt/2/1/1/0/30", new byte[1]);
                MQTTService.getInstance().publishMessage("mymqtt/2/2/1/0/30", new byte[1]);
            }
            LOG.debug("DOWN");
        }
    }

    private PendingIntent getPendingSelfIntent(Context context, String action) {
        // An explicit intent directed at the current class (the "self").
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

}
