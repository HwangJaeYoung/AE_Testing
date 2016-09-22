package onem2m.seslab.sejong.ae_testing.reuse.network;

import org.json.JSONObject;
import android.content.Context;
import android.util.Log;

import com.loopj.android.http.*;

public class HttpRequester {
    private static final String BASE_URL = "http://203.253.128.151:7579";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void post(String url, RequestParams params, JsonHttpResponseHandler responseHandler, Context aContext) {
    	request(url, params, responseHandler, aContext, true);
    }

    public static void get(String url, RequestParams params, JsonHttpResponseHandler responseHandler, Context aContext) {
    	request(url, params, responseHandler, aContext, false);
    }
    
    public static void request(String url, RequestParams params, JsonHttpResponseHandler responseHandler, Context aContext, boolean anIsPost) {
    	Log.i("request", "Url: "+url);
        Log.i("request", "Parms: " + params.toString());
        
        client.addHeader("Accept", "application/json");
        client.addHeader("X-M2M-RI", "12345") ;
		client.addHeader("X-M2M-Origin", "S0.2.481.1.1.232466");

		if (anIsPost)
			client.post(getAbsoluteUrl(url), params, responseHandler);
		else
			client.get(getAbsoluteUrl(url), params, responseHandler);
	}
    
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
  
    // 처리를 위해 공통적인 규약을 준것이다.
    public static interface NetworkResponseListener {
        public void onSuccess(JSONObject jsonObject);
        public void onFail(JSONObject jsonObject, int errorCode);
    }
}