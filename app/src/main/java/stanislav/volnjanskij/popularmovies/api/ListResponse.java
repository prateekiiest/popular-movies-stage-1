package stanislav.volnjanskij.popularmovies.api;

import java.util.List;

/**
 * Created by Stas on 19.07.15.
 */
public class ListResponse {
    List<MovieModel> results;
    int page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieModel> getResults() {
        return results;
    }

    public void setResults(List<MovieModel> results) {
        this.results = results;
    }
}
