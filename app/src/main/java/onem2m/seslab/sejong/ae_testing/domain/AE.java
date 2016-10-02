package onem2m.seslab.sejong.ae_testing.domain;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

public class AE implements oneM2M {

    // Body Value
    private String AEBody;

    public AE(DummyDataForAE dummyDataForAE) {
        AEBody = dummyDataForAE.getXmlBody();
    }

    @Override
    public String getOneM2MBody() {
        return AEBody;
    }
}