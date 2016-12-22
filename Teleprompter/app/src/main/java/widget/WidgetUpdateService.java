package widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.theseus.teleprompter.R;

/**
 * Created by theseus on 22/12/16.
 */

public class WidgetUpdateService extends IntentService {
    private static final String LOG_TAG=WidgetUpdateService.class.getSimpleName();
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public WidgetUpdateService() {
        super("WidgetUpdateService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(LOG_TAG,"intent recieved");
        AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this,
                ScriptListWidget.class));
        for (int appWidgetId : appWidgetIds) {
            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(getPackageName(), R.layout.script_list_widget);
            views.setTextViewText(R.id.appwidget_text, getString(R.string.app_name));
            views.setRemoteAdapter(R.id.list_view_widget,new Intent(this,ScriptWidgetService.class));
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
