package onem2m.seslab.sejong.ae_testing.reuse.network;

import com.loopj.android.http.SaxAsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class XMLResponseHandler extends SaxAsyncHttpResponseHandler<HttpRequester.NetworkResponseListenerXML> {
    private HttpRequester.NetworkResponseListenerXML networkResponseListener;

    public XMLResponseHandler(HttpRequester.NetworkResponseListenerXML aNetworkResponseListener) {
        super(aNetworkResponseListener);
        this.networkResponseListener = aNetworkResponseListener;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, HttpRequester.NetworkResponseListenerXML networkResponseListenerXML) {

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, HttpRequester.NetworkResponseListenerXML networkResponseListenerXML) {

    }
}