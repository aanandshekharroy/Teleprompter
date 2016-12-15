package com.example.theseus.teleprompter;

import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.theseus.teleprompter.adapter.ScriptListAdapter;
import com.example.theseus.teleprompter.data.ScriptContract;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private String LOG_TAG=MainActivityFragment.class.getSimpleName();
    int LOADER_ID=1;
    private ScriptListAdapter mScriptListAdapter;
    private RecyclerView mRecyclerView;
    private TextView mEmptyView;
    public MainActivityFragment() {
    }
    public static String[] SCRIPT_POJECTION=new String[]{
            ScriptContract.ScriptEntry._ID,
            ScriptContract.ScriptEntry.COLUMN_TITLE,
            ScriptContract.ScriptEntry.COLUMN_CONTENT
    };
    public static final int COLUMN_ID=0;
    public static final int COLUMN_TITLE=1;
    public static final int COLUMN_CONTENT=2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        mScriptListAdapter=new ScriptListAdapter(getActivity());

        View rootView=inflater.inflate(R.layout.fragment_main, container, false);
        mEmptyView=(TextView)rootView.findViewById(R.id.empty_view);
        mRecyclerView=(RecyclerView)rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mScriptListAdapter);
        mScriptListAdapter=new ScriptListAdapter(getActivity(), new ScriptListAdapter.ScriptListAdapterOnClickHandler() {
            @Override
            public void onClick(ScriptListAdapter.ScriptListAdapterViewHolder vh) {
                int pos=vh.getAdapterPosition();
            }
        },mEmptyView);
        mRecyclerView.setAdapter(mScriptListAdapter);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(LOADER_ID,null,this);
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(LOG_TAG,"onCreateLoader");
        return new CursorLoader(getActivity(),
                ScriptContract.ScriptEntry.CONTENT_URI,SCRIPT_POJECTION,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data.getCount()>0){
            Log.d(LOG_TAG,"data count: "+data.getCount());
//            +", title=  "+data.getString(COLUMN_TITLE));
        }

        mScriptListAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mScriptListAdapter.swapCursor(null);
    }

    @Override
    public void onResume() {
        super.onResume();
        getLoaderManager().restartLoader(LOADER_ID,null,this);
    }
}
