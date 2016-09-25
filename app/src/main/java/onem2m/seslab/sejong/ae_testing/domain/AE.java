package onem2m.seslab.sejong.ae_testing.domain;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

public class AE implements oneM2M {

    // Session Value
    private NanoHTTPD.Method method;
    private Map<String, String> parameters;
    private Map<String, String> files;

    // Body Value
    private String AEBody;

    public AE(NanoHTTPD.IHTTPSession session) {
        this.method = session.getMethod();
        this.parameters = session.getParms();
        this.files = new HashMap<String, String>();

        try {
            session.parseBody(files);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NanoHTTPD.ResponseException e) {
            e.printStackTrace();
        }

        if (NanoHTTPD.Method.PUT.equals(method) || NanoHTTPD.Method.POST.equals(method))
            AEBody = files.get("postData");
    }

    @Override
    public String getOneM2MBody() {
        return AEBody;
    }
}