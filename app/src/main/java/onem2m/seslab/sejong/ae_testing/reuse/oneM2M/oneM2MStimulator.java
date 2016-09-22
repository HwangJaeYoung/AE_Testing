package onem2m.seslab.sejong.ae_testing.reuse.oneM2M;

import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
import onem2m.seslab.sejong.ae_testing.domain.AE;
import onem2m.seslab.sejong.ae_testing.domain.oneM2M;

/**
 * Created by Blossom on 2016-09-21.
 */

public class oneM2MStimulator {

    private String uri;
    private NanoHTTPD.Method method;
    private Map<String, String> header;
    private Map<String, String> parameters;
    private Map<String, String> files;

    public oneM2MStimulator(String uri, NanoHTTPD.Method method, Map<String, String> header, Map<String, String> parameters, Map<String, String> files) {
        this.uri = uri;
        this.method = method;
        this.header = header;
        this.parameters = parameters;
        this.files = files;
    }

    public void startTesting( ) {
        String contentType = null, resourceType = "2";

        // Parsing the resourceType from Content_Type header





        switch(resourceType) {
            case "1" : // accessControlPolicy
                break;

            case "2" : // AE
                oneM2M AE = new AE(uri, method, header, parameters, files);





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

        // Networking...

    }
}
