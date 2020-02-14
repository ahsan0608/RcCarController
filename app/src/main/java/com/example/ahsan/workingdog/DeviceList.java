package com.example.ahsan.workingdog;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by ahsan on 8/5/2017.
 */

public class DeviceList extends AppCompatActivity {

    Button btnPaired;
    ListView deviceList;

    private BluetoothAdapter myBluetooth = null;
    private Set<BluetoothDevice> pairedDevices;
    public static String EXTRA_ADDRESS = "device_address";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);

        btnPaired = (Button) findViewById(R.id.button);
        deviceList = (ListView) findViewById(R.id.listview);

        myBluetooth = BluetoothAdapter.getDefaultAdapter();

        if (myBluetooth == null){
            Toast.makeText(getApplicationContext(),"Bluetooth not Found",Toast.LENGTH_LONG).show();
            finish();
        }
        else if(!myBluetooth.isEnabled()){
            Intent turnBon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnBon,1);
        }

        btnPaired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pairedDevicesList();
            }
        });
    }

    private void pairedDevicesList() {
        pairedDevices = myBluetooth.getBondedDevices();
        ArrayList list = new ArrayList();

        if (pairedDevices.size()>0)
        {
            for (BluetoothDevice bt : pairedDevices)
            {
                list.add(bt.getName() + "\n" +bt.getAddress());
            }
        }else
        {
            Toast.makeText(getApplicationContext(),"No paired Device Found",Toast.LENGTH_LONG).show();

        }

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        deviceList.setAdapter(adapter);
        deviceList.setOnItemClickListener(myListClickListener);
    }

    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String info = ((TextView) view).getText().toString();
            String address = info.substring(info.length() - 17);

            Intent i = new Intent(DeviceList.this, LedControl.class);
            i.putExtra(EXTRA_ADDRESS,address);
            startActivity(i);
            //Toast.makeText(getApplicationContext(),"Yooooo",Toast.LENGTH_LONG).show();

        }
    };

}
