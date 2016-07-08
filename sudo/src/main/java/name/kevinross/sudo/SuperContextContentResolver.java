package name.kevinross.sudo;

import android.app.ActivityManagerNative;
import android.app.ActivityThread;
import android.app.IActivityManager;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.content.IContentProvider;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.ArrayMap;

import name.kevinross.tool.ReflectionUtil;

/**
 * Get a content resolver for a given context. Here there be monsters.
 */
public class SuperContextContentResolver {
    private static void injectContentProviderIntoApplication(ClassLoader classLoader, ActivityManagerNative activityManagerNative, ActivityThread activityThread, Context context, String name) {
        IActivityManager.ContentProviderHolder holder = null;
        IContentProvider provider = null;
        IBinder token = new Binder();

        try {
            holder = activityManagerNative.getContentProviderExternal(name, context.getUserId(), token);
            provider = holder.provider;
        } catch (RemoteException e) {
            throw new RuntimeException("Couldn't get content provider for given name and context+userid");
        }

        Class applicationContentResolverClass = ReflectionUtil.getInnerClass(ReflectionUtil.getClassByName(classLoader, "android.app.ContextImpl"), "ApplicationContentResolver");
        ContentResolver resolver = ReflectionUtil.invokes().on(applicationContentResolverClass).
                of(Context.class, ActivityThread.class, UserHandle.class).
                using(context, ActivityThread.currentActivityThread(), UserHandle.CURRENT).nosy().swallow().<ContentResolver>getNewInstance();
        ReflectionUtil.invokes().on(context).name("mContentResolver").using(resolver).nosy().swallow().set();

        Class providerKeyClass = ReflectionUtil.getInnerClass(ActivityThread.class, "ProviderKey");
        Object providerKey = ReflectionUtil.invokes().on(providerKeyClass).of(String.class, Integer.TYPE).using("settings", -2).nosy().swallow().getNewInstance();

        Class providerRecordClass = ReflectionUtil.getInnerClass(ActivityThread.class, "ProviderClientRecord");
        Object providerRecord = ReflectionUtil.invokes().on(providerRecordClass).
                of(ActivityThread.class, new String[]{}.getClass(), IContentProvider.class,
                        ContentProvider.class,
                        IActivityManager.ContentProviderHolder.class).
                using(activityThread, new String[]{name}, provider, null, holder).nosy().swallow().getNewInstance();

        ArrayMap mProviderMap = ReflectionUtil.invokes().on(activityThread).name("mProviderMap").nosy().swallow().<ArrayMap>get();
        mProviderMap.put(providerKey, providerRecord);
    }
    private static void disposeContentProvider() {
        //ActivityManagerNative.getDefault().removeContentProviderExternal("settings", token);
    }
}
