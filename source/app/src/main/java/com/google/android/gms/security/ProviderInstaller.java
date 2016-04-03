// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.security;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.hm;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import java.lang.reflect.Method;

public class ProviderInstaller
{

    public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
    private static Method aea = null;
    private static final Object qp = new Object();

    public ProviderInstaller()
    {
    }

    private static void L(Context context)
    {
        aea = context.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl").getMethod("insertProvider", new Class[] {
            android/content/Context
        });
    }

    public static void installIfNeeded(Context context)
    {
        obj;
        JVM INSTR monitorenter ;
        hm.b(context, "Context must not be null");
        GooglePlayServicesUtil.z(context);
        Context context1 = GooglePlayServicesUtil.getRemoteContext(context);
        if (context1 == null)
        {
            Log.e("ProviderInstaller", "Failed to get remote context");
            throw new GooglePlayServicesNotAvailableException(8);
        }
        Exception exception1;
        synchronized (qp)
        {
            if (aea == null)
            {
                L(context1);
            }
            aea.invoke(null, new Object[] {
                context1
            });
        }
        return;
        exception1;
        Log.e("ProviderInstaller", (new StringBuilder("Failed to install provider: ")).append(exception1.getMessage()).toString());
        throw new GooglePlayServicesNotAvailableException(8);
    }

    public static void installIfNeededAsync(Context context, ProviderInstallListener providerinstalllistener)
    {
        hm.b(context, "Context must not be null");
        hm.b(providerinstalllistener, "Listener must not be null");
        hm.ay("Must be called on the UI thread");
        _cls1 _lcls1 = new _cls1(context, providerinstalllistener);
        Void avoid[] = new Void[0];
        if (!(_lcls1 instanceof AsyncTask))
        {
            _lcls1.execute(avoid);
            return;
        } else
        {
            AsyncTaskInstrumentation.execute((AsyncTask)_lcls1, avoid);
            return;
        }
    }


    private class _cls1 extends AsyncTask
        implements TraceFieldInterface
    {

        public Trace _nr_trace;
        final ProviderInstallListener aeb;
        final Context qu;

        public void _nr_setTrace(Trace trace)
        {
            try
            {
                _nr_trace = trace;
                return;
            }
            catch (Exception exception)
            {
                return;
            }
        }

        protected final transient Integer b(Void avoid[])
        {
            try
            {
                ProviderInstaller.installIfNeeded(qu);
            }
            catch (GooglePlayServicesRepairableException googleplayservicesrepairableexception)
            {
                return Integer.valueOf(googleplayservicesrepairableexception.getConnectionStatusCode());
            }
            catch (GooglePlayServicesNotAvailableException googleplayservicesnotavailableexception)
            {
                return Integer.valueOf(googleplayservicesnotavailableexception.errorCode);
            }
            return Integer.valueOf(0);
        }

        protected final void d(Integer integer)
        {
            if (integer.intValue() == 0)
            {
                aeb.onProviderInstalled();
                return;
            } else
            {
                Intent intent = GooglePlayServicesUtil.Z(integer.intValue());
                aeb.onProviderInstallFailed(integer.intValue(), intent);
                return;
            }
        }

        protected final Object doInBackground(Object aobj[])
        {
            TraceMachine.enterMethod(_nr_trace, "ProviderInstaller$1#doInBackground", null);
_L1:
            Integer integer = b((Void[])aobj);
            TraceMachine.exitMethod();
            TraceMachine.unloadTraceContext(this);
            return integer;
            NoSuchFieldError nosuchfielderror;
            nosuchfielderror;
            TraceMachine.enterMethod(null, "ProviderInstaller$1#doInBackground", null);
              goto _L1
        }

        protected final void onPostExecute(Object obj)
        {
            TraceMachine.enterMethod(_nr_trace, "ProviderInstaller$1#onPostExecute", null);
_L1:
            d((Integer)obj);
            TraceMachine.exitMethod();
            return;
            NoSuchFieldError nosuchfielderror;
            nosuchfielderror;
            TraceMachine.enterMethod(null, "ProviderInstaller$1#onPostExecute", null);
              goto _L1
        }

        _cls1(Context context, ProviderInstallListener providerinstalllistener)
        {
            qu = context;
            aeb = providerinstalllistener;
            super();
        }

        private class ProviderInstallListener
        {

            public abstract void onProviderInstallFailed(int i, Intent intent);

            public abstract void onProviderInstalled();
        }

    }

}
