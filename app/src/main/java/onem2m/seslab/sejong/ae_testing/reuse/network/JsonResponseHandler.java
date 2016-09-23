package onem2m.seslab.sejong.ae_testing.reuse.network;

import com.loopj.android.http.*;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class JsonResponseHandler extends JsonHttpResponseHandler {
    private HttpRequester.NetworkResponseListenerJSON networkResponseListener;

    public JsonResponseHandler(HttpRequester.NetworkResponseListenerJSON aNetworkResponseListener) {
        this.networkResponseListener = aNetworkResponseListener;
    }

    // 여기가 콜백 메소드 부분이다.
    // Fired when a request returns successfully
    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        this.networkResponseListener.onSuccess(response);

    }

    // Returns when request failed
    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        if(statusCode == 0) {
            this.networkResponseListener.onFail(new JSONObject(), 8);
        }
        else { }
    }
}
