package onem2m.seslab.sejong.ae_testing.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import onem2m.seslab.sejong.ae_testing.R;
import onem2m.seslab.sejong.ae_testing.reuse.mvc.activity.AbstractViewForActivity;

public class ViewForMainActivity extends AbstractViewForActivity {

    private TextView deviceIPAddress;
    private Controller controller;

    public ViewForMainActivity(Context context, Controller aController) {
        super(context);
        controller = aController;
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_main, null);
    }

    @Override
    protected void initViews() {
        deviceIPAddress = (TextView)findViewById(R.id.deviceIPAddress);
    }

    @Override
    protected void setEvent() { }

    public void setDeviceIPAddress(String ipAddress, int PORT) {
        if(ipAddress != null)
            deviceIPAddress.setText("Device IP Address : http://" + ipAddress + ":" + PORT);
        else
            deviceIPAddress.setText("Wi-Fi Network Not Available");
    }

    public interface Controller {
        void getMobiusInformation();
    }
}