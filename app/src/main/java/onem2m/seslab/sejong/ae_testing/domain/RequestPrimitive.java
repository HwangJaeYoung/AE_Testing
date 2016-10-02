package onem2m.seslab.sejong.ae_testing.domain;

import java.util.Map;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;
import fi.iki.elonen.NanoHTTPD;

/**
 * Created by JaeYoung Hwang on 2016-09-25.
 */

public class RequestPrimitive {

    // Header Key
    private static final String KEY_HEADER_TO = "To";
    private static final String KEY_HEADER_OPERATION = "Operation";
    private static final String KEY_HEADER_ACCEPT = "Accept";
    private static final String KEY_HEADER_CONTENT_TYPE = "Content-Type";
    private static final String KEY_HEADER_X_M2M_ORIGIN = "X-M2M-Origin";
    private static final String KEY_HEADER_X_M2M_RI = "X-M2M-RI";

    // Header Value
    private String To;
    private String Operation;
    private String ACCEPT;
    private String Content_Type;
    private String X_M2M_Origin;
    private String X_M2M_RI;

    // DummyData
    private Map<String, String> headerSet;

    public RequestPrimitive(DummyDataForAE dummyDataForAE) {
        headerSet = dummyDataForAE.getHeaderSet();

        To = headerSet.get(KEY_HEADER_TO);
        Operation = headerSet.get(KEY_HEADER_OPERATION);
        ACCEPT = headerSet.get(KEY_HEADER_ACCEPT);
        X_M2M_Origin = headerSet.get(KEY_HEADER_X_M2M_ORIGIN);
        X_M2M_RI = headerSet.get(KEY_HEADER_X_M2M_RI);
        Content_Type = headerSet.get(KEY_HEADER_CONTENT_TYPE);
    }

    public String getTo() { return To; }

    public String getOperation() { return Operation; }

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