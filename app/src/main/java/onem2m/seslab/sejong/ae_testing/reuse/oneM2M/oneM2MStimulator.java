package onem2m.seslab.sejong.ae_testing.reuse.oneM2M;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Iterator;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import fi.iki.elonen.NanoHTTPD;
import onem2m.seslab.sejong.ae_testing.domain.AE;
import onem2m.seslab.sejong.ae_testing.domain.oneM2M;
import onem2m.seslab.sejong.ae_testing.domain.RequestPrimitive;
import onem2m.seslab.sejong.ae_testing.reuse.network.HttpRequester;
import onem2m.seslab.sejong.ae_testing.reuse.network.oneM2MRequest;

public class oneM2MStimulator {

    private NanoHTTPD.IHTTPSession session;
    private Context context;

    public oneM2MStimulator(NanoHTTPD.IHTTPSession session, Context context) {
        this.session = session;
        this.context = context;
    }

    public void startTesting( ) {
        // Parsing the resourceType from Content_Type header
        // TS-0009 : 6.4.3 Content-Type
        // TS-0004 : 6.3.4.2.1 m2m:resourceType

        String contentTypeHeaderValue, resourceType, Accept;
        NanoHTTPD.Method method;

        Map<String, String> header = session.getHeaders();

        // Accept
        Accept = header.get("accept");

        // Content-Type
        contentTypeHeaderValue = header.get("content-type");
        contentTypeHeaderValue = contentTypeHeaderValue.replaceAll("\\p{Space}", "");
        String splitedString[] = contentTypeHeaderValue.split(";");
        resourceType = splitedString[1].split("=")[1];

        // Method
        method = session.getMethod();

        switch(resourceType) {
            case "1" : // accessControlPolicy
                break;

            case "2" : // AE

                RequestPrimitive reqPrimitive = new RequestPrimitive(header, method);
                oneM2M AE = new AE(session);
                Mca(reqPrimitive, AE, Accept, method);
                break;

            case "3" : // container
                break;

            case "4" : // conentInstance
                break;

            case "5" : // CSEBase
                break;

            case "9" : // Group
                break;

            case "23" : // Subscription
                break;

            default:

        }
    }

    // AE send the specific header value and body to CSE
    public void Mca(RequestPrimitive requestPrimitive, oneM2M resource, String Accept, NanoHTTPD.Method method) {

        oneM2MRequest oneM2MRequestor = new oneM2MRequest();

        if(Accept.equals("application/xml")) {
            oneM2MRequestor.XML(context, XMLResponseListener, method, requestPrimitive, resource);
        } else if(Accept.equals("application/json")) {
            try {
                oneM2MRequestor.JSON(context, JSONResponseListener, method, requestPrimitive, resource);
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
}