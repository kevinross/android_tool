package name.kevinross.debugtestapp;

import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import name.kevinross.sudo.RootService;

public class DemoService extends RootService<IDemoService.Stub>
{
    public IBinder onBind(Intent intent) {
        return super.onBind(intent,
            new IDemoService.Stub() {
                int i = 0;
                @Override
                public String echo(String value) throws RemoteException {
                    return String.format("Value: %s", value);
                }

                @Override
                public int getUid() throws RemoteException {
                    return android.os.Process.myUid();
                }

                @Override
                public int nextInt() throws RemoteException {
                    return i++;
                }
            });
    }
}
