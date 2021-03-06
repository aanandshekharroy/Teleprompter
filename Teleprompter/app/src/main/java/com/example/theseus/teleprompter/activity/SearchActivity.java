package com.example.theseus.teleprompter.activity;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.app.LoaderManager;
import android.content.Loader;
import android.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.theseus.teleprompter.R;
import com.example.theseus.teleprompter.SimpleDividerItemDecoration;
import com.example.theseus.teleprompter.adapter.SearchAdapter;
import com.example.theseus.teleprompter.data.ScriptContract;
import com.example.theseus.teleprompter.fragment.MainActivityFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, LoaderManager.LoaderCallbacks<Cursor> {
    int LOADER_ID = 1;
    private static final String LOG_TAG = SearchActivity.class.getSimpleName();
    private Context mContext;
    private String mQuery = "";
    private SearchAdapter mSearchAdapter;
    private RecyclerView mRecyclerView;
    private AdView mAdView;
    public static String[] SCRIPT_POJECTION = new String[]{
            ScriptContract.ScriptEntry._ID,
            ScriptContract.ScriptEntry.COLUMN_TITLE,
            ScriptContract.ScriptEntry.COLUMN_CONTENT
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mContext = this;
        mSearchAdapter = new SearchAdapter(mContext);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mSearchAdapter);
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));

        getLoaderManager().initLoader(LOADER_ID, null, this);
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
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
        searchView.requestFocus();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mQuery = query;
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mQuery = newText;
        getLoaderManager().restartLoader(LOADER_ID, null, this);
        return false;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(mContext, ScriptContract.ScriptEntry.buildUriFromKey(mQuery), SCRIPT_POJECTION, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null) {
            if (mQuery.length() > 0) {
                mSearchAdapter.swapCursor(data);
            } else {
                mSearchAdapter.swapCursor(null);
            }

        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mSearchAdapter.swapCursor(null);
    }
}
