package onem2m.seslab.sejong.ae_testing.reuse.network;

import org.json.JSONException;

import com.loopj.android.http.RequestParams;

import android.content.Context;

public class MobiusRequest {
	
	private Context context;
	private static final String URL_BASE = "/mobius-yt";
	
	public MobiusRequest(Context aContext) {
		this.context = aContext;
	}
	
	public void mobius(final HttpRequester.NetworkResponseListener aNetworkListener) throws JSONException {
		RequestParams requestParams = new RequestParams( );
		
		if(context != null)
			HttpRequester.get(URL_BASE, requestParams, new JsonResponseHandler(aNetworkListener), context);
	}
}