package com.bullova.mc.hithere;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal on 1/15/2015.
 */

public class IndexRoom extends Activity{
    private TextView tHiScreen;
    private TextView PeerCount;
    private String sName;
    final private String HI_TAG = "Hi there ";

    IndexRoom mIndexRoom = this;
    WifiP2pManager mManager;
    WifiP2pManager.Channel mChannel;
    BroadcastReceiver mReceiver;
    IntentFilter mIntentFilter;
    ListView PeersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.room);
        PeersList = (ListView) findViewById(R.id.listView);
        PeerCount = (TextView) findViewById(R.id.PeerCountText);
        tHiScreen = (TextView) findViewById(R.id.HelloTextView);

        //inicializacia WifiP2P
        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(this, getMainLooper(), null);
        mReceiver = new WiFiDirectBroadcastReceiver(mManager, mChannel, this);

        //nejake intenty :D
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);


        Intent i = getIntent();
        Bundle extras = i.getExtras();

        sName = extras.getString("loginName");
        tHiScreen.setText(HI_TAG + sName + "!");

        //nájdenie dostupných wifi zariadení
        mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(mIndexRoom, "Peer Discovery: Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int reasonCode) {
                Toast.makeText(mIndexRoom, "Peer Discovery: Failure", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void updatePeerCount(int count){
        PeerCount.setText("Number of available peers: "+count);
    }

    /* register the broadcast receiver with the intent values to be matched */
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
    }
    /* unregister the broadcast receiver */
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

}
