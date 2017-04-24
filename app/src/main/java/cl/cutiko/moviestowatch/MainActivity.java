package cl.cutiko.moviestowatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cl.cutiko.moviestowatch.models.Movie;

public class MainActivity extends AppCompatActivity {

    public static final String MOVIE_ID = "cl.cutiko.moviestowatch.KEY.MOVIE_ID";
    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText) findViewById(R.id.movieEt);
        Button save = (Button) findViewById(R.id.saveBtn);
        Button last = (Button) findViewById(R.id.lastBtn);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                if (name.trim().length() > 0) {
                    Movie movie = new Movie(name, false);
                    movie.save();
                    movies = getMovies();
                    editText.setText("");
                    Toast.makeText(MainActivity.this, "Película guardada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Escriba un nombre por favor", Toast.LENGTH_SHORT).show();
                }
            }
        });

        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = movies.size();
                if (size > 0) {
                    Movie lastMovie = movies.get(size-1);
                    Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                    intent.putExtra(MOVIE_ID, lastMovie.getId());
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "No hay películas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        movies = getMovies();
    }

    private List<Movie> getMovies(){
        return Movie.find(Movie.class, "watched = 0");
    }
}
