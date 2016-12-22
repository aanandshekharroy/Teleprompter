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
//    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
//                                int appWidgetId) {
//
//        CharSequence widgetText = context.getString(R.string.appwidget_text);
//        // Construct the RemoteViews object
//        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.script_list_widget);
//        views.setTextViewText(R.id.appwidget_text, widgetText);
//        views.setRemoteAdapter(R.id.list_view_widget,new Intent(context,ScriptWidgetService.class));
//
//        // Instruct the widget manager to update the widget
//        appWidgetManager.updateAppWidget(appWidgetId, views);
//    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
//        for (int appWidgetId : appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, appWidgetId);
//        }
        context.startService(new Intent(context,WidgetUpdateService.class));
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d(LOG_TAG,"intent recieved");
        context.startService(new Intent(context,WidgetUpdateService.class));
    }
}

