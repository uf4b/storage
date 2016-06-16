package it.unica.pr2project2013.server;

import it.unica.pr2project2013.tipi.CalendarioShow;
import it.unica.pr2project2013.tipi.TRMovie;
import it.unica.pr2project2013.tipi.TRShow;

import java.io.InputStream;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


/**
 * Servizio web Trakt.tv (Serie TV)
 */
public class TraktAPIShow extends TraktAPI {
	
	/**
	 * Override TraktAPI.computaDati()
	 */
	@Override
	protected Set<? extends TRMovie> computaDati(InputStream inputStream) throws Exception {
		
	    JSONTokener tokener = new JSONTokener(inputStream);
	    JSONArray array = (JSONArray)tokener.nextValue();
	    
	    Set<TRShow> shows = new HashSet<TRShow>();
	   
	    for (int i = 0; i < array.length(); i++) {
	    	JSONObject element = array.getJSONObject(i);
	    	TRShow show = new TRShow();
	    	
	    	show.setTitle(element.getString("title"));
	    	show.setGenres(getGenres(element.getJSONArray("genres")));
	    	show.setYear(element.getInt("year"));
	    	show.setNetwork(element.getString("network"));
	    	show.setCertification(element.getString("certification"));
	    	String day = element.getString("air_day");
	    	String time = element.getString("air_time");
	    	show.setCal(new CalendarioShow(day, time));
	    	try {
	    		show.setRuntime(element.getInt("runtime"));
	    	} catch (JSONException e) {
	    		show.setRuntime(0);
	    	}
	    	show.setWatchers(element.getInt("watchers"));
	    	show.setRatings(getRatings(element.getJSONObject("ratings")));
	    	shows.add(show);
	    }
	    return shows;
	}
}

