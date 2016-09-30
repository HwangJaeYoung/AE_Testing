package onem2m.seslab.sejong.ae_testing.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
import onem2m.seslab.sejong.ae_testing.reuse.oneM2M.oneM2MStimulator;
import onem2m.seslab.sejong.ae_testing.testing.oneM2MTester;

public class MainActivity extends Activity implements ViewForMainActivity.Controller {

	private ViewForMainActivity view;
	private oneM2MTester tester;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new ViewForMainActivity(getApplicationContext(), this);
		setContentView(view.getRoot());

		tester = new oneM2MTester(getApplicationContext());
		view.setDeviceIPAddress(tester.getLocalIpAddress(), tester.getPortNumber());

		try {
			tester.getWebServer().start();
		} catch(IOException ioe) {
			Log.w("Httpd", "The server could not start.");
		}

		Log.w("Httpd", "Web server initialized.");
	}

	// DON'T FORGET to stop the server
	@Override
	public void onDestroy()  {
		super.onDestroy();
		if (tester.getWebServer() != null)
			tester.getWebServer().stop();
	}

	@Override
	public void getMobiusInformation() { }
}