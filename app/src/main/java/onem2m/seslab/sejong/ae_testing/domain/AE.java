package onem2m.seslab.sejong.ae_testing.domain;

import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by Blossom on 2016-09-21.
 */

public class AE implements oneM2M {

    // Header
    private static final String JSON_HEADER_ACCEPT = null;
    private static final String JSON_HEADER_CONTENT_TYPE = null;
    private static final String JSON_HEADER_X_M2M_Origin = null;
    private static final String JSON_KEY_X_M2M_RI = null;

    // Body (Resource)
    private static final String JSON_KEY_SELLER2 = "seller";
    private static final String JSON_KEY_BUYER2 = "buyer";
    private static final String JSON_KEY_IMAGE2 = "image";
    private static final String JSON_KEY_CREATE_TIME2 = "created_date_time";

    private String uri;
    private NanoHTTPD.Method method;
    private Map<String, String> header;
    private Map<String, String> parameters;
    private Map<String, String> files;

    private String ACCEPT;
    private String Content_Type;
    private String X_M2M_Origin;
    private String X_M2M_RI;

    public AE(String uri, NanoHTTPD.Method method, Map<String, String> header, Map<String, String> parameters, Map<String, String> files) {
        this.uri = uri;
        this.method = method;
        this.header = header;
        this.parameters = parameters;
        this.files = files;
    }






}
