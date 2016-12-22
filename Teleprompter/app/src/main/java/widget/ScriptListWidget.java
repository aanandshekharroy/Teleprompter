package widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.theseus.teleprompter.R;

/**
 * Implementation of App Widget functionality.
 */
public class ScriptListWidget extends AppWidgetProvider {
    private final static String LOG_TAG=ScriptListWidget.class.getSimpleName();
//private

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        context.startService(new Intent(context,WidgetUpdateService.class));
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d(LOG_TAG,"intent recieved");
        context.startService(new Intent(context,WidgetUpdateService.class));
    }
}

