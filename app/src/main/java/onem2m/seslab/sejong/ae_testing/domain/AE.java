package onem2m.seslab.sejong.ae_testing.domain;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

public class AE implements oneM2M {

    // Header Key
    private static final String KEY_HEADER_ACCEPT = "accept";
    private static final String KEY_HEADER_CONTENT_TYPE = "content-type";
    private static final String KEY_HEADER_X_M2M_Origin = "x-m2m-origin";
    private static final String KEY_HEADER_X_M2M_RI = "x-m2m-ri";

    // Session Value
    private String uri;
    private NanoHTTPD.Method method;
    private Map<String, String> header;
    private Map<String, String> parameters;
    private Map<String, String> files;

    // Header Value
    private String ACCEPT;
    private String Content_Type;
    private String X_M2M_Origin;
    private String X_M2M_RI;

    // Body Value
    private String AEBody;

    public AE(NanoHTTPD.IHTTPSession session) {
        this.uri = session.getUri();
        this.method = session.getMethod();
        this.header = session.getHeaders();
        this.parameters = session.getParms();
        this.files = new HashMap<String, String>();

        try {
            session.parseBody(files);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NanoHTTPD.ResponseException e) {
            e.printStackTrace();
        }

        ACCEPT = header.get(KEY_HEADER_ACCEPT);
        X_M2M_Origin = header.get(KEY_HEADER_X_M2M_Origin);
        X_M2M_RI = header.get(KEY_HEADER_X_M2M_RI);
        Content_Type = header.get(KEY_HEADER_CONTENT_TYPE);

        if (NanoHTTPD.Method.PUT.equals(method) || NanoHTTPD.Method.POST.equals(method))
            AEBody = files.get("postData");
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public String getX_M2M_RI() {
        return X_M2M_RI;
    }

    @Override
    public String getACCEPT() {
        return ACCEPT;
    }

    @Override
    public String getContent_Type() {
        return Content_Type;
    }

    @Override
    public String getX_M2M_Origin() {
        return X_M2M_Origin;
    }

    @Override
    public int getHeaderLength() {
        int length = 0;

        if(ACCEPT != null); length++; if(X_M2M_Origin != null); length++;
        if(X_M2M_RI != null); length++; if(Content_Type != null) length++;

        return length;
    }

    @Override
    public String getOneM2MBody() {
        return AEBody;
    }
}