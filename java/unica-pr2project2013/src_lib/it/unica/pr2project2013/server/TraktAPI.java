package it.unica.pr2project2013.server;

import it.unica.pr2project2013.tipi.TRMovie;
import it.unica.pr2project2013.tipi.TRatings;

import java.io.InputStream;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Servizio web Trakt.tv (Film)
 */
public class TraktAPI extends ServerAPI<TRMovie> {
	
	/**
	 * Costruttore URL
	 */
	public TraktAPI() {
		super("api.trakt.tv/%s.json/5dba9789aa33fcf4bfca53fb58546951");
	}

	/**
	 * Implementazione ServerAPI.computaDati()
	 */
	@Override
	protected Set<? extends TRMovie> computaDati(InputStream inputStream) throws Exception {
		
	    JSONTokener tokener = new JSONTokener(inputStream);
	    JSONArray array = (JSONArray)tokener.nextValue();
	    
	    Set<TRMovie> movies = new HashSet<TRMovie>();
	   
	    for (int i = 0; i < array.length(); i++) {
	    	JSONObject element = array.getJSONObject(i);
	    	TRMovie movie = new TRMovie();
	    	movie.setTitle(element.getString("title"));
	    	movie.setGenres(getGenres(element.getJSONArray("genres")));
	    	movie.setYear(element.getInt("year"));
	    	try {
	    		movie.setRuntime(element.getInt("runtime"));
	    	} catch (JSONException e) {
	    		movie.setRuntime(0);
	    	}
	    	movie.setWatchers(element.getInt("watchers"));
	    	movie.setRatings(getRatings(element.getJSONObject("ratings")));
	    	movies.add(movie);
	    }
	    return movies;
	}
	
	/**
	 * Crea l'array dei generi
	 * @param genresArray Array json
	 * @return Array 
	 */
	protected String[] getGenres(JSONArray genresArray) {
    	String[] genres = new String[ genresArray.length() ];
    	for (int j = 0; j < genresArray.length(); j++) {
    		genres[j] = genresArray.getString(j);
    	}
    	return genres;
	}
	
	/**
	 * Crea un oggetto valutazione
	 * @param ratings Valutazione  json
	 * @return Valutazione
	 */
	protected TRatings getRatings(JSONObject ratings) {
    	return new TRatings(ratings.getInt("loved"), ratings.getInt("hated"));
	}
}

