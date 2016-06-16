package it.unica.pr2project2013.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.Callable;

public class ControlloreConnessione implements Callable<Boolean> {

	@Override
	public Boolean call() throws IOException {
		System.out.print("Controllo connessione: ");
		try {
			URL url = new URL("http://www.trakt.tv");
			BufferedReader bufferedReader = new BufferedReader(
											new InputStreamReader(url.openStream())
										);
		} catch (Exception e) {
			System.out.println("Non connesso");
			return false;
		}
		System.out.println("Connesso");
		return true;
	}
}
