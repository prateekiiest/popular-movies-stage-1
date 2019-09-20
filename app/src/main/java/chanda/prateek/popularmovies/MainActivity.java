package chanda.prateek.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import chanda.prateek.popularmovies.api.MovieModel;
import chanda.prateek.popularmovies.ui.movie_details.DetailsActivity;
import chanda.prateek.popularmovies.ui.movies_list.MoviesListFragment;
import chanda.prateek.popularmovies.ui.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity implements MoviesListFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // when the activity is created
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MoviesListFragment fragment= (MoviesListFragment) getFragmentManager().findFragmentById(R.id.movie_list);
        fragment.setCallback(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // create an explicit intent to go for settings activity
            Intent intent=new Intent(this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void movieSelected(MovieModel movie) {
        Intent intent=new Intent(this, DetailsActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }
