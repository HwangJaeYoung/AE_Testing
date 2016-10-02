package onem2m.seslab.sejong.ae_testing.reuse.network;

import android.content.Context;

import org.json.JSONException;

import com.loopj.android.http.RequestParams;
import fi.iki.elonen.NanoHTTPD;
import onem2m.seslab.sejong.ae_testing.domain.RequestPrimitive;
import onem2m.seslab.sejong.ae_testing.domain.oneM2M;

public class oneM2MRequest {

	//private static final String URL_BASE = "/testURI";
	private static final String URL_BASE = "/mobius-yt";

	public oneM2MRequest( ) { }

	public void JSON(Context context, final HttpRequester.NetworkResponseListenerJSON aNetworkListener, String method, RequestPrimitive requestPrimitive, oneM2M resource) throws JSONException {
		RequestParams requestParams = new RequestParams( );

		if(method.equals("POST") || method.equals("PUT"))
			HttpRequester.postJSON(context, URL_BASE, requestParams, new JsonResponseHandler(aNetworkListener), requestPrimitive, resource);
		else if(method.equals("GET") || method.equals("DELETE"))
			HttpRequester.getJSON(context, URL_BASE, requestParams, new JsonResponseHandler(aNetworkListener), requestPrimitive, resource);
	}

	public void XML(Context context, final HttpRequester.NetworkResponseListenerXML aNetworkListener, String method, RequestPrimitive requestPrimitive, oneM2M resource) {
		RequestParams requestParams = new RequestParams( );

		if(method.equals("POST") || method.equals("PUT"))
			HttpRequester.postXML(context, URL_BASE, requestParams, new XMLResponseHandler(aNetworkListener), requestPrimitive, resource);
		else if(method.equals("GET") || method.equals("DELETE"))
			HttpRequester.getXML(context, URL_BASE, requestParams, new XMLResponseHandler(aNetworkListener), requestPrimitive, resource);
	}
}