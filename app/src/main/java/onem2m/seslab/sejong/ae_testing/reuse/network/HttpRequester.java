package onem2m.seslab.sejong.ae_testing.reuse.network;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;
import org.xml.sax.helpers.DefaultHandler;

public class HttpRequester {
    private static final String BASE_URL = "http://203.253.128.151:7579";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void postJSON(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
    	requestJSON(url, params, responseHandler, true);
    }

    public static void getJSON(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
    	requestJSON(url, params, responseHandler, false);
    }

    public static void postXML(String url, RequestParams params, XMLResponseHandler responseHandler) {
        requestXML(url, params, responseHandler, true);
    }

    public static void getXML(String url, RequestParams params, XMLResponseHandler responseHandler) {
        requestXML(url, params, responseHandler, false);
    }
    
    public static void requestJSON(String url, RequestParams params, JsonHttpResponseHandler responseHandler, boolean anIsPost) {
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

    public static void requestXML(String url, RequestParams params, XMLResponseHandler responseHandler, boolean anIsPost) {
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
    public interface NetworkResponseListenerJSON {
        void onSuccess(JSONObject jsonObject);
        void onFail(JSONObject jsonObject, int errorCode);
    }

    public static abstract class NetworkResponseListenerXML extends DefaultHandler {
        public abstract void onSuccess(DefaultHandler jsonObject);
        public abstract void onFail(DefaultHandler jsonObject, int errorCode);
    }
}