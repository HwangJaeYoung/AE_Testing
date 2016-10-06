package onem2m.seslab.sejong.ae_testing.testing;

import onem2m.seslab.sejong.ae_testing.reuse.oneM2M.AECreation;

public class oneM2MStimulator implements oneM2MTester.oneM2MOperation {

    private AECreation aeCreation;

    public oneM2MStimulator( ) {
        this.aeCreation = new AECreation();
    }

    @Override
    public void Create() {
        // User has to classify the resource to use the different resource such as container, subscription, etc.
        aeCreation.Mca( );
    }

    @Override
    public void Retrieve() {

    }

    @Override
    public void Update() {

    }

    @Override
    public void Delete() {

    }

    @Override
    public void Notify() {

    }
}