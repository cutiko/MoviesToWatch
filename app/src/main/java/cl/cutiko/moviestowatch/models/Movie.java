package cl.cutiko.moviestowatch.models;

import com.orm.SugarRecord;

/**
 * Created by cutiko on 24-04-17.
 */

public class Movie extends SugarRecord {

    private String name;
    private boolean watched;

    public Movie() {
    }

    public Movie(String name, boolean watched) {
        this.name = name;
        this.watched = watched;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }
}
