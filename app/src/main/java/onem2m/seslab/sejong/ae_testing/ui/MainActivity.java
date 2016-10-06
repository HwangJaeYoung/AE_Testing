package onem2m.seslab.sejong.ae_testing.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import onem2m.seslab.sejong.ae_testing.testing.oneM2MTester;

public class MainActivity extends Activity implements ViewForMainActivity.Controller {

	private ViewForMainActivity view;
	private static Context context;
	private oneM2MTester tester; /**** Testing object ****/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.context = getApplicationContext();

		view = new ViewForMainActivity(getApplicationContext(), this);
		setContentView(view.getRoot());

		/**** Testing code ****/
		tester = new oneM2MTester(getApplicationContext());
		view.setDeviceIPAddress(tester.getLocalIpAddress(), tester.getPortNumber());

		try {
			tester.getWebServer().start();
		} catch(IOException ioe) {
			Log.w("Httpd", "The server could not start.");
		}

		Log.w("Httpd", "Web server initialized.");
	}

	@Override
	public void onDestroy()  {
		super.onDestroy();

		/**** Testing code ****/
		// DON'T FORGET to stop the server
		if (tester.getWebServer() != null)
			tester.getWebServer().stop();
	}

	public static Context getAppContext() {
		return MainActivity.context;
	}

	@Override
	public void getMobiusInformation() { }
}