/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/r0ssar00/Development/AndroidStudioProjects/DebuggableTool/debugtestapp/src/main/aidl/name/kevinross/debugtestapp/IDemoService.aidl
 */
package name.kevinross.debugtestapp;
// Declare any non-default types here with import statements

public interface IDemoService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements name.kevinross.debugtestapp.IDemoService
{
private static final java.lang.String DESCRIPTOR = "name.kevinross.debugtestapp.IDemoService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an name.kevinross.debugtestapp.IDemoService interface,
 * generating a proxy if needed.
 */
public static name.kevinross.debugtestapp.IDemoService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof name.kevinross.debugtestapp.IDemoService))) {
return ((name.kevinross.debugtestapp.IDemoService)iin);
}
return new name.kevinross.debugtestapp.IDemoService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_echo:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _result = this.echo(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getUid:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getUid();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements name.kevinross.debugtestapp.IDemoService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public java.lang.String echo(java.lang.String value) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(value);
mRemote.transact(Stub.TRANSACTION_echo, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getUid() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getUid, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_echo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getUid = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public java.lang.String echo(java.lang.String value) throws android.os.RemoteException;
public int getUid() throws android.os.RemoteException;
}
