package name.kevinross.debugtestapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import name.kevinross.sudo.RootService;
import name.kevinross.tool.ReflectionUtil;

public class MainActivity extends AppCompatActivity {

    IDemoService demoService = null;
    TextView helloWorld = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helloWorld = (TextView)findViewById(R.id.helloWorld);
        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                demoService = IDemoService.Stub.asInterface(service);
                try {
                    demoService.echo("hello");
                    helloWorld.setText(demoService.echo("hello"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                unbindService(this);
            }
        };
        bindService(new Intent(this, DemoService.class), serviceConnection, Context.BIND_AUTO_CREATE);
        if (demoService != null) {
            try {
                Log.d("Testing", demoService.echo("hello"));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
