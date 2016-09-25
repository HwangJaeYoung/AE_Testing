package onem2m.seslab.sejong.ae_testing.reuse.network;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONObject;
import org.xml.sax.helpers.DefaultHandler;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;
import onem2m.seslab.sejong.ae_testing.domain.RequestPrimitive;
import onem2m.seslab.sejong.ae_testing.domain.oneM2M;

public class HttpRequester {
    //private static final String BASE_URL = "http://192.168.35.135:62590";
    private static final String BASE_URL = "http://203.253.128.151:7579";

    public static AsyncHttpClient syncHttpClient= new SyncHttpClient();
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void postJSON(Context context, String url, RequestParams params, JsonHttpResponseHandler responseHandler, RequestPrimitive requestPrimitive, oneM2M resource) {
        requestJSON(context, url, params, responseHandler, requestPrimitive, resource, true);
    }

    public static void getJSON(Context context, String url, RequestParams params, JsonHttpResponseHandler responseHandler, RequestPrimitive requestPrimitive, oneM2M resource) {
        requestJSON(context, url, params, responseHandler, requestPrimitive, resource, false);
    }

    public static void postXML(Context context, String url, RequestParams params, XMLResponseHandler responseHandler, RequestPrimitive requestPrimitive, oneM2M resource) {
        requestXML(context, url, params, responseHandler, requestPrimitive, resource, true);
    }

    public static void getXML(Context context, String url, RequestParams params, XMLResponseHandler responseHandler, RequestPrimitive requestPrimitive, oneM2M resource) {
        requestXML(context, url, params, responseHandler, requestPrimitive, resource,  false);
    }

    public static void requestJSON(Context context, String url, RequestParams params, JsonHttpResponseHandler responseHandler, RequestPrimitive requestPrimitive, oneM2M resource, boolean anIsPost) {
        Log.i("request", "JSON");
        Log.i("request", "Url: "+url);
        Log.i("request", "Parms: " + params.toString());

        if (anIsPost) {
            StringEntity oneM2MBody = null;

            try {
                oneM2MBody = new StringEntity(resource.getOneM2MBody());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            getClient().post(context, getAbsoluteUrl(url), requestPrimitive.getHeaderList(), oneM2MBody, requestPrimitive.getContent_Type(), responseHandler);
        }
        else
            getClient().get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void requestXML(Context context, String url, RequestParams params, XMLResponseHandler responseHandler, RequestPrimitive requestPrimitive, oneM2M resource, boolean anIsPost) {
        Log.i("request", "XML");
        Log.i("request", "Url: "+url);
        Log.i("request", "Parms: " + params.toString());

        getClient().addHeader("Accept", "application/json");
        getClient().addHeader("X-M2M-RI", "12345") ;
        getClient().addHeader("X-M2M-Origin", "S0.2.481.1.1.232466");

        if (anIsPost)
            getClient().post(getAbsoluteUrl(url), params, responseHandler);
        else
            getClient().get(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    private static AsyncHttpClient getClient()
    {
        // Return the synchronous HTTP client when the thread is not prepared
        if (Looper.myLooper() == null)
            return syncHttpClient;
        return client;
    }

    // 泥섎━瑜??꾪빐 怨듯넻?곸씤 洹쒖빟??以寃껋씠??
    public interface NetworkResponseListenerJSON {
        void onSuccess(JSONObject jsonObject);
        void onFail(JSONObject jsonObject);
    }

    public static abstract class NetworkResponseListenerXML extends DefaultHandler {
        public abstract void onSuccess(DefaultHandler jsonObject);
        public abstract void onFail(DefaultHandler jsonObject, int errorCode);
    }
}