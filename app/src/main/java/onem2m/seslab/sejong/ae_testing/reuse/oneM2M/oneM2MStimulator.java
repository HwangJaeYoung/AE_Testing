package onem2m.seslab.sejong.ae_testing.reuse.oneM2M;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
import onem2m.seslab.sejong.ae_testing.domain.AE;
import onem2m.seslab.sejong.ae_testing.domain.oneM2M;
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

        /**** For checking the header lists
         Iterator<String> keys = header.keySet().iterator();
         while (keys.hasNext()) {
         String key = keys.next();
         Log.i("ValueTest", key);
         } ****/

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
                oneM2M AE = new AE(session);
                Mca(AE, Accept, method);
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
    public void Mca(oneM2M resource, String Accept, NanoHTTPD.Method method) {

        oneM2MRequest oneM2MRequestor = new oneM2MRequest();

        if(Accept.equals("application/xml")) {
            oneM2MRequestor.XML(context, XMLResponseListener, method, resource);
        } else if(Accept.equals("application/json")) {
            try {
                oneM2MRequestor.JSON(context, JSONResponseListener, method, resource);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    HttpRequester.NetworkResponseListenerXML XMLResponseListener = new HttpRequester.NetworkResponseListenerXML() {

        // Creating the specific domain.

        @Override
        public void onSuccess(DefaultHandler jsonObject) {
            Log.i("Testing", "XML Success");
        }

        @Override
        public void onFail(DefaultHandler jsonObject, int errorCode) {
            Log.i("Testing", "XML Fail");
        }
    };

    HttpRequester.NetworkResponseListenerJSON JSONResponseListener = new HttpRequester.NetworkResponseListenerJSON() {

        // Creating the specific domain.
        @Override
        public void onSuccess(JSONObject jsonObject) {
            Log.i("Testing", jsonObject.toString());
        }

        @Override
        public void onFail(JSONObject jsonObject) {
            Log.i("Testing", jsonObject.toString());

        }
    };
}