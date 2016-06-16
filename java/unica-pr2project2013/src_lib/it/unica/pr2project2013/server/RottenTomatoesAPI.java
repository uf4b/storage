package it.unica.pr2project2013.server;

import it.unica.pr2project2013.tipi.RTMovie;
import it.unica.pr2project2013.tipi.RTRatings;

import java.io.InputStream;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Servizio web RottenTomatoes.com
 */
public class RottenTomatoesAPI extends ServerAPI<RTMovie> {
	
	/**
	 * Costruttore URL
	 */
	public RottenTomatoesAPI() {
		super("api.rottentomatoes.com/api/public/v1.0/%s.json?apikey=wc5c57m5nuyx2yv3tvwt9wbs");
	}

	/**
	 * @see {@link ServerAPI#computaDati(InputStream)}
	 */
	@Override
	protected Set<RTMovie> computaDati(InputStream inputStream) throws Exception {
		
	    JSONTokener tokener = new JSONTokener(inputStream);
	    JSONArray array = new JSONObject(tokener).getJSONArray("movies");
	    
	    Set<RTMovie> movies = new HashSet<RTMovie>();
	    for (int i = 0; i < array.length(); i++) {
	    	JSONObject element = array.getJSONObject(i);
	    	RTMovie movie = new RTMovie();
	    	
	    	movie.setTitle(element.getString("title"));
	    	movie.setYear(element.getInt("year"));
	    	movie.setMpaa_rating(element.getString("mpaa_rating"));
	    	movie.setOverview(element.getString("synopsis"));
	    	try {
	    		movie.setRuntime(element.getInt("runtime"));
	    	} catch (JSONException e) {
	    		movie.setRuntime(0);
	    	}
	    	JSONObject ratings = element.getJSONObject("ratings");
	    	int critics_score = ratings.getInt("critics_score");
	    	int audience_score = ratings.getInt("audience_score");
	    	movie.setRatings(new RTRatings(critics_score, audience_score));
	    	
	    	movies.add(movie);
	    }
	    return movies;
	}
}

