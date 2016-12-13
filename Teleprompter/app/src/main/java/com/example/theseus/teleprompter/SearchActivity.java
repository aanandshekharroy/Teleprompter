package com.example.theseus.teleprompter;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.app.LoaderManager;
import android.content.Loader;
import android.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.theseus.teleprompter.adapter.SearchAdapter;
import com.example.theseus.teleprompter.data.ScriptContract;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,LoaderManager.LoaderCallbacks<Cursor> {
    int LOADER_ID=1;
    private static final String LOG_TAG=SearchActivity.class.getSimpleName();
    private Context mContext;
    private String mQuery="";
    private SearchAdapter mSearchAdapter;
    public static String[] SCRIPT_POJECTION=new String[]{
            ScriptContract.ScriptEntry._ID,
            ScriptContract.ScriptEntry.COLUMN_TITLE,
            ScriptContract.ScriptEntry.COLUMN_CONTENT
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mContext=this;
        mSearchAdapter=new SearchAdapter(mContext);
        getLoaderManager().initLoader(LOADER_ID,null, this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.search_menu:
                Toast.makeText(mContext,"clicked search",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    protected void onNewIntent(Intent intent) {
//        ...
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
        }
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
//        Toast.makeText(mContext,query,Toast.LENGTH_SHORT).show();
        mQuery=query;
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
//        Log.d(LOG)
        mQuery=newText;
        getLoaderManager().restartLoader(LOADER_ID,null,this);
        Toast.makeText(mContext,newText,Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(LOG_TAG,"uri for search: "+ ScriptContract.ScriptEntry.buildUriFromKey(mQuery));
        return new CursorLoader(mContext, ScriptContract.ScriptEntry.buildUriFromKey(mQuery),SCRIPT_POJECTION,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(LOG_TAG,"data count: "+data);
        if(data!=null){
            Log.d(LOG_TAG,"data count: "+data.getCount());
//            mSearchAdapter.swapCursor(data);
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
//        mSearchAdapter.swapCursor(null);
    }
}
