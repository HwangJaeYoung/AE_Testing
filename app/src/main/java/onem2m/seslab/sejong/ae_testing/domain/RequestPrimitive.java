package onem2m.seslab.sejong.ae_testing.domain;

import java.util.Map;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;
import fi.iki.elonen.NanoHTTPD;

/**
 * Created by Blossom on 2016-09-25.
 */

public class RequestPrimitive {

    // Header Key
    private static final String KEY_HEADER_TARGT_ADDRESSS = "target-address";
    private static final String KEY_HEADER_ACCEPT = "accept";
    private static final String KEY_HEADER_CONTENT_TYPE = "content-type";
    private static final String KEY_HEADER_X_M2M_ORIGIN = "x-m2m-origin";
    private static final String KEY_HEADER_X_M2M_RI = "x-m2m-ri";

    // Header Value
    private NanoHTTPD.Method Method;
    private String targetAddress;
    private String ACCEPT;
    private String Content_Type;
    private String X_M2M_Origin;
    private String X_M2M_RI;

    public RequestPrimitive(Map<String, String> header, NanoHTTPD.Method method) {
        Method = method;
        targetAddress = header.get(KEY_HEADER_TARGT_ADDRESSS);
        ACCEPT = header.get(KEY_HEADER_ACCEPT);
        X_M2M_Origin = header.get(KEY_HEADER_X_M2M_ORIGIN);
        X_M2M_RI = header.get(KEY_HEADER_X_M2M_RI);
        Content_Type = header.get(KEY_HEADER_CONTENT_TYPE);

    }

    public NanoHTTPD.Method getMethod(){
        return Method;
    }

    public String getTargetAddress() { return targetAddress; }

    public String getX_M2M_RI() {
        return X_M2M_RI;
    }

    public String getACCEPT() {
        return ACCEPT;
    }

    public String getContent_Type() {
        return Content_Type;
    }

    public String getX_M2M_Origin() {
        return X_M2M_Origin;
    }

    public int getHeaderLength() {
        int length = 0;

        if(ACCEPT != null); length++; if(X_M2M_Origin != null); length++;
        if(X_M2M_RI != null); length++; if(Content_Type != null) length++;

        return length;
    }

    public Header[] getHeaderList( ) {
        Header[] headers = new Header[getHeaderLength()]; int i = 0;

        if(X_M2M_RI != null)
            headers[i++] = new BasicHeader("X-M2M-RI", X_M2M_RI);
        if(ACCEPT != null)
            headers[i++] = new BasicHeader("Accept", ACCEPT);
        if(X_M2M_Origin != null)
            headers[i++] = new BasicHeader("X-M2M-Origin", X_M2M_Origin);
        if(Content_Type != null)
            headers[i++] = new BasicHeader("Content-Type", Content_Type);

        return headers;
    }
}
