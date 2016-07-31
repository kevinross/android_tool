package name.kevinross.debugtestapp;

import android.os.RemoteException;

import name.kevinross.sudo.RootService;

public class DemoService extends RootService<IDemoService, IDemoService.Stub>
{
    // called from a su context, do no init here
    public DemoService(String superuser) {

    }
    protected IDemoService.Stub getImplementation() {
        return new IDemoService.Stub() {
            @Override
            public String echo(String value) throws RemoteException {
                return String.format("Value: %s", value);
            }

            @Override
            public int getUid() throws RemoteException {
                return android.os.Process.myUid();
            }
        };
    }
    protected Class<IDemoService> getInterfaceClass() {
        return IDemoService.class;
    }

}
