package stanislav.volnjanskij.popularmovies.ui.movies_list;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import stanislav.volnjanskij.popularmovies.R;

import stanislav.volnjanskij.popularmovies.api.APIClient;
import stanislav.volnjanskij.popularmovies.api.MovieModel;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * interface.
 */
public class MoviesListFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<List<MovieModel>>{

    MovieModel[] data;


    /**
     * The fragment's ListView/GridView.
     */
    @Bind(R.id.grid)
    GridView gridView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private MoviesListAdapter mAdapter;
    private View rootView;
    private Callback callback;
    private String currentOrder;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MoviesListFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);



    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String order = prefs.getString("sort_order", "");
        if (currentOrder==null) currentOrder=order;
        // reload list if settings are changed
        if (!currentOrder.equals(order)){
            getLoaderManager().initLoader(0, null, MoviesListFragment.this).forceLoad();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.movie_list_fragment, container, false);
        ButterKnife.bind(this,rootView);
        mAdapter=new MoviesListAdapter(getActivity(),R.layout.movie_list_item);
        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (callback != null) callback.movieSelected(mAdapter.getItem(position));
            }
        });

        ViewTreeObserver vto = rootView.getViewTreeObserver();
        //wait for layout rendiring to obtain grid view width in pixels
        if (savedInstanceState!=null){
            data= (MovieModel[]) savedInstanceState.getParcelableArray("movies");
        }

        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rootView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int width = gridView.getMeasuredWidth()/ gridView.getNumColumns();
                mAdapter.setImageWidth(width);
                mAdapter.setImageHeight((int) (width * 1.5837));
                if (data==null){
                    getLoaderManager().initLoader(0, null, MoviesListFragment.this).forceLoad();
                }else{
                    mAdapter.addAll(data);
                    mAdapter.notifyDataSetChanged();
                }


            }
        });


        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArray("movies", data);
    }

    @Override
    public Loader<List<MovieModel>> onCreateLoader(int id, Bundle args) {
        Loader<List<MovieModel>> loader=new MovieLoader(getActivity());
        return  loader;
    }

    @Override
    public void onLoadFinished(Loader<List<MovieModel>> loader, List<MovieModel> loadedData) {
        if (loadedData.size()==0) {
            Toast.makeText(getActivity(),getResources().getString(R.string.network_error),Toast.LENGTH_LONG).show();
            return;
        }
        mAdapter.clear();
        mAdapter.addAll(loadedData);
        this.data=new MovieModel[loadedData.size()];
        loadedData.toArray(this.data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<MovieModel>> loader) {

    }

    public interface Callback {
        // TODO: Update argument type and name
        public void movieSelected(MovieModel movie);
    }
    public static class MovieLoader extends AsyncTaskLoader<List<MovieModel>> {

        public MovieLoader(Context context) {
            super(context);
        }

        @Override
        public List<MovieModel> loadInBackground() {
            try {

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                String order = prefs.getString("sort_order", "");
                if (order.equals("top_rated")) {
                    return APIClient.getInstance().getTopRated();
                } else {
                    return APIClient.getInstance().getPopular();
                }
            }catch (Exception ex){

                return new ArrayList<MovieModel>();
            }
        }

    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
