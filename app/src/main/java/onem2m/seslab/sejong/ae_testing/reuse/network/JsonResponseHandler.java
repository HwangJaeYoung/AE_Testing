package onem2m.seslab.sejong.ae_testing.reuse.network;

import android.util.Log;

import com.loopj.android.http.*;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class JsonResponseHandler extends JsonHttpResponseHandler {
    private HttpRequester.NetworkResponseListenerJSON networkResponseListener;

    public JsonResponseHandler(HttpRequester.NetworkResponseListenerJSON aNetworkResponseListener) {
        this.networkResponseListener = aNetworkResponseListener;

    }

    // ?ш린媛 肄쒕갚 硫붿냼??遺遺꾩씠??
    // Fired when a request returns successfully
    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        this.networkResponseListener.onSuccess(response);
    }

    // Returns when request failed
    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        Log.i("testing", "" + statusCode);

        this.networkResponseListener.onFail(errorResponse);
    }
}