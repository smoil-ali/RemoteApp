package tambowskip.com.free.remoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.jmdns.ServiceTypeListener;


public class DiscoverAcitivity extends AppCompatActivity    {

    public final String SERVICE_TYPE="_http._tcp.";
    NsdManager nsdManager;
    public final String TAG="values";
    RecyclerView recyclerView;
    ServerList mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_acitivity);
        init();

        new task().execute();
    }

    public void init(){
        recyclerView=findViewById(R.id.serverRecyclerview);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter=new ServerList();
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.discover_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.discoverMe:
                Toast.makeText(this, "add me", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    NsdManager.DiscoveryListener discoveryListener=new NsdManager.DiscoveryListener() {


        @Override
        public void onDiscoveryStarted(String s) {
            Log.i(TAG,"starting");

        }

        @Override
        public void onStartDiscoveryFailed(String s, int i) {
            Log.i(TAG,"Failed at start");
        }

        @Override
        public void onStopDiscoveryFailed(String s, int i) {
            Log.i(TAG,"Failed");
        }



        @Override
        public void onDiscoveryStopped(String s) {

        }

        @Override
        public void onServiceFound(NsdServiceInfo nsdServiceInfo) {
            Log.i(TAG,nsdServiceInfo.toString());
            nsdManager.resolveService(nsdServiceInfo,resolveListener);
        }

        @Override
        public void onServiceLost(NsdServiceInfo nsdServiceInfo) {

        }
    };

    NsdManager.ResolveListener resolveListener=new NsdManager.ResolveListener() {
        @Override
        public void onResolveFailed(NsdServiceInfo nsdServiceInfo, int i) {

        }

        @Override
        public void onServiceResolved(NsdServiceInfo nsdServiceInfo) {
            Log.i(TAG,"Connected"+nsdServiceInfo.getHost());
        }
    };

    public class task extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            nsdManager=(NsdManager)getApplicationContext().getSystemService(getApplicationContext().NSD_SERVICE);
            nsdManager.discoverServices(SERVICE_TYPE,NsdManager.PROTOCOL_DNS_SD,discoveryListener);

            return null;
        }
    }


}
