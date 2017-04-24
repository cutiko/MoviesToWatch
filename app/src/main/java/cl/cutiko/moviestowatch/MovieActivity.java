package cl.cutiko.moviestowatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;

import cl.cutiko.moviestowatch.models.Movie;

public class MovieActivity extends AppCompatActivity {

    private Movie movie;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        long id = getIntent().getLongExtra(MainActivity.MOVIE_ID, 0);

        //Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();

        movie = Movie.findById(Movie.class, id);

        checkBox = (CheckBox) findViewById(R.id.movieCb);

        getSupportActionBar().setTitle(movie.getName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        movie.setWatched(checkBox.isChecked());
        movie.save();
    }
}
