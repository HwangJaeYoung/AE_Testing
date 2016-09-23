package onem2m.seslab.sejong.ae_testing.reuse.network;

import org.json.JSONException;

import com.loopj.android.http.RequestParams;
import fi.iki.elonen.NanoHTTPD;

public class oneM2MRequest {

	private static final String URL_BASE = "/mobius-yt";

	public oneM2MRequest( ) { }

	public void JSON(final HttpRequester.NetworkResponseListenerJSON aNetworkListener, NanoHTTPD.Method method) throws JSONException {
		RequestParams requestParams = new RequestParams( );

		if(NanoHTTPD.Method.PUT.equals(method) || NanoHTTPD.Method.POST.equals(method))
			HttpRequester.postJSON(URL_BASE, requestParams, new JsonResponseHandler(aNetworkListener));
		else if(NanoHTTPD.Method.PUT.equals(method) || NanoHTTPD.Method.POST.equals(method))
			HttpRequester.getJSON(URL_BASE, requestParams, new JsonResponseHandler(aNetworkListener));
	}

	public void XML(final HttpRequester.NetworkResponseListenerXML aNetworkListener, NanoHTTPD.Method method) {
		RequestParams requestParams = new RequestParams( );

		if(NanoHTTPD.Method.PUT.equals(method) || NanoHTTPD.Method.POST.equals(method))
			HttpRequester.postXML(URL_BASE, requestParams, new XMLResponseHandler(aNetworkListener));
		else if(NanoHTTPD.Method.PUT.equals(method) || NanoHTTPD.Method.POST.equals(method))
			HttpRequester.getXML(URL_BASE, requestParams, new XMLResponseHandler(aNetworkListener));
	}
}