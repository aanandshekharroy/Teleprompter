package widget;

import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.theseus.teleprompter.R;
import com.example.theseus.teleprompter.activity.MainActivity;
import com.example.theseus.teleprompter.data.ScriptContract;
import com.example.theseus.teleprompter.fragment.MainActivityFragment;

import static android.R.attr.data;

/**
 * Created by theseus on 21/12/16.
 */

public class ScriptWidgetService extends RemoteViewsService {
    private static final String LOG_TAG=ScriptWidgetService.class.getSimpleName();
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            private Cursor mcursor=null;
            @Override
            public void onCreate() {
                mcursor=getContentResolver().query(ScriptContract.ScriptEntry.CONTENT_URI,MainActivityFragment.SCRIPT_POJECTION,null,null,null);
                Log.d(LOG_TAG,"service cursor count "+mcursor.getCount());
            }

            @Override
            public void onDataSetChanged() {
                if(mcursor!=null){
                    mcursor.close();
                }else {
                    mcursor=getContentResolver().query(ScriptContract.ScriptEntry.CONTENT_URI,null,null,null,null);
                }
            }

            @Override
            public void onDestroy() {
                if(mcursor!=null){
                    mcursor.close();
                }
            }

            @Override
            public int getCount() {
                if(mcursor!=null){
                    return mcursor.getCount();
                }
                return 0;
            }

            @Override
            public RemoteViews getViewAt(int position) {
                if(position== AdapterView.INVALID_POSITION||mcursor==null||mcursor.isClosed()){
                    return null;
                }

                mcursor.moveToFirst();
                mcursor.moveToPosition(position);
                RemoteViews views=new RemoteViews(getPackageName(), R.layout.script_search_item);
                views.setTextViewText(R.id.title,mcursor.getString(MainActivityFragment.COLUMN_TITLE));
                views.setTextViewText(R.id.content,mcursor.getString(MainActivityFragment.COLUMN_CONTENT));
                return views;
            }

            @Override
            public RemoteViews getLoadingView() {
                return null;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                return mcursor.getInt(mcursor.getColumnIndex(ScriptContract.ScriptEntry.COLUMN__ID));
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }
}
