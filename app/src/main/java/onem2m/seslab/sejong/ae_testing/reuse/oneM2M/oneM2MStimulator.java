package onem2m.seslab.sejong.ae_testing.reuse.oneM2M;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import onem2m.seslab.sejong.ae_testing.domain.AE;
import onem2m.seslab.sejong.ae_testing.domain.DummyDataForAE;
import onem2m.seslab.sejong.ae_testing.domain.RequestPrimitive;
import onem2m.seslab.sejong.ae_testing.reuse.network.HttpRequester;
import onem2m.seslab.sejong.ae_testing.reuse.network.oneM2MRequest;
import onem2m.seslab.sejong.ae_testing.testing.oneM2MTester;

public class oneM2MStimulator extends oneM2MTester.oneM2MOperation {

    private Context context;

    public oneM2MStimulator(Context context) { this.context = context; }

    // AE send the specific header value and body to CSE
    public void Mca( ) {
        oneM2MRequest oneM2MRequestor = new oneM2MRequest();

        RequestPrimitive requestPrimitive = new RequestPrimitive(new DummyDataForAE());
        AE resource = new AE(new DummyDataForAE());

        String accept = requestPrimitive.getACCEPT();
        String operation = requestPrimitive.getOperation();

        if(accept.equals("application/xml")) {
            oneM2MRequestor.XML(context, XMLResponseListener, operation, requestPrimitive, resource);
        } else if(accept.equals("application/json")) {
            try {
                oneM2MRequestor.JSON(context, JSONResponseListener, operation, requestPrimitive, resource);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    HttpRequester.NetworkResponseListenerXML XMLResponseListener = new HttpRequester.NetworkResponseListenerXML() {

        // Creating the specific domain.

        @Override
        public void onSuccess(int statusCode, Header[] headers, HttpRequester.NetworkResponseListenerXML networkResponseListenerXML) {
            Log.i("Testing", "XML onSuccess");

            Map<String, String> map = networkResponseListenerXML.getXmlResponse();

            Iterator<String> keys = map.keySet().iterator();

            while (keys.hasNext()) {
                String key = keys.next();
                Log.i("Testing", "Key : " + key + ", " + "Value : " + map.get(key));
            }
        }

        @Override
        public void onFail(int statusCode, Header[] headers, HttpRequester.NetworkResponseListenerXML networkResponseListenerXML) {
            Log.i("Testing", "XML onFail");
            Log.i("Testing", ""+ statusCode);
            Map<String, String> map = networkResponseListenerXML.getXmlResponse();

            Iterator<String> keys = map.keySet().iterator();

            while (keys.hasNext()) {
                String key = keys.next();
                Log.i("Testing", "Key : " + key + ", " + "Value : " + map.get(key));
            }
        }
    };

    HttpRequester.NetworkResponseListenerJSON JSONResponseListener = new HttpRequester.NetworkResponseListenerJSON() {

        // Creating the responsePrimitive domain.

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {
            Log.i("Testing", "JSON onSuccess");
            Log.i("Testing", jsonObject.toString());
        }

        @Override
        public void onFail(int statusCode, Header[] headers, JSONObject jsonObject) {
            Log.i("Testing", "JSON onFail");
            Log.i("Testing", jsonObject.toString());
        }
    };

    @Override
    public void Create() {
        Mca( );
    }

    @Override
    public void Retrieve() {

    }

    @Override
    public void Update() {

    }

    @Override
    public void Delete() {

    }

    @Override
    public void Notify() {

    }
}