package fr.iut.ptitbiscuit.whatthefilm;

import android.app.Application;
import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * <p><b>Créé :</b> 29/03/2017</p>
 *
 * @author Pacôme Lehoux
 * @version 1.0
 * @since 1.0
 */

public class WTFApplication extends Application {
	private static Context context;
	private static RequestQueue requestQueue;


	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		requestQueue = Volley.newRequestQueue(WTFApplication.getAppContext());

	}

	public static Context getAppContext() {
		return WTFApplication.context;
	}

	public static RequestQueue getAppRequestQueue() {
		return WTFApplication.requestQueue;
	}
}
