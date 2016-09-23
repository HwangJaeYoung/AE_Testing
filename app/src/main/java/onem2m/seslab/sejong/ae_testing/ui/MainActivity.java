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

import fi.iki.elonen.NanoHTTPD;
import onem2m.seslab.sejong.ae_testing.reuse.oneM2M.oneM2MStimulator;

public class MainActivity extends Activity implements ViewForMainActivity.Controller {

	private static final int PORT = 8080;

	private ViewForMainActivity view;
	private WebServer server;
	private String ipAddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new ViewForMainActivity(getApplicationContext(), this);
		setContentView(view.getRoot());

		ipAddress = getLocalIpAddress();
		view.setDeviceIPAddress(ipAddress, PORT);

		server = new WebServer();

		try {
			server.start();
		} catch(IOException ioe) {
			Log.w("Httpd", "The server could not start.");
		}

		Log.w("Httpd", "Web server initialized.");
	}

	// DON'T FORGET to stop the server
	@Override
	public void onDestroy()  {
		super.onDestroy();
		if (server != null)
			server.stop();
	}

	public String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();

				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();

					if(!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
						String ipAddr = inetAddress.getHostAddress();
						return ipAddr;
					}
				}
			}
		} catch (SocketException ex) {
			Log.d("Server", ex.toString());
		}
		return null;
	}

	@Override
	public void getMobiusInformation() { }

	private class WebServer extends NanoHTTPD {
		public WebServer() {
			super(PORT);
		}

		@Override
		public Response serve(IHTTPSession session) {

			// Calling the oneM2M Stimulator
			oneM2MStimulator oneM2MTest = new oneM2MStimulator(session);
			oneM2MTest.startTesting();

			return new NanoHTTPD.Response("Android Response");
		}
	}
}