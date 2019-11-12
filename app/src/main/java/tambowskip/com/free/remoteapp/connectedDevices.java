package tambowskip.com.free.remoteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ForwardingListener;

import android.content.Context;
import android.net.DhcpInfo;

import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.BidiFormatter;
import android.text.format.Formatter;
import android.widget.TextView;



public class connectedDevices extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected_devices);





    }

    public static String intToIp(int i) {
        return (i & 0xFF) + "." +
                ((i >> 8 ) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                ((i >> 24) & 0xFF);
    }


}
