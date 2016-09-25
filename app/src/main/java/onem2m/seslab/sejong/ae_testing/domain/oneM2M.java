package onem2m.seslab.sejong.ae_testing.domain;

/**
 * Created by Blossom on 2016-09-21.
 */

public interface oneM2M {
    String getOneM2MBody(); // inflate
    String getUri();
    String getX_M2M_RI();
    String getACCEPT();
    String getContent_Type();
    String getX_M2M_Origin();
    int getHeaderLength();
}
