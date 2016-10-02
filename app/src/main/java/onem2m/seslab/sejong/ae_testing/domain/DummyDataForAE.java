package onem2m.seslab.sejong.ae_testing.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JaeYoung Hwang on 2016-10-02.
 */

public class DummyDataForAE {
    private String xmlBody;
    private String jsonBody;
    private Map<String, String> headerSet;

    public DummyDataForAE() {
        this.headerSet = new HashMap<String, String>();

        this.headerSet.put("To", "http://203.253.128.151:7579/mobius-yt");
        this.headerSet.put("Operation", "POST");
        this.headerSet.put("Accept", "application/xml");
        this.headerSet.put("X-M2M-RI", "12345");
        this.headerSet.put("X-M2M-Origin", "Origin");
        this.headerSet.put("Content-Type", "application/vnd.onem2m-res+xml; ty=2");

        this.xmlBody = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                       "<m2m:ae xmlns:m2m=\"http://www.onem2m.org/xml/protocols\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" rn=\"seslab_ae5\">\n" +
                       "    <api>0.2.481.2.0001.001.000111</api>\n" +
                       "</m2m:ae>";

        this.jsonBody = "{\n" +

                        "    \"m2m:ae\": {\n" +
                        "        \"rn\": \"andtesting33\",\n" +
                        "        \"api\": \"0.2.481.2.0001.001.000111\"\n" +
                        "    }\n" +
                        "}";
    }

    public String getXmlBody() {
        return xmlBody;
    }

    public Map<String, String> getHeaderSet() {
        return headerSet;
    }

    public String getJsonBody() {
        return jsonBody;
    }
}