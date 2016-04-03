// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.adms.measurement;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import java.io.File;
import java.util.Locale;
import java.util.UUID;

// Referenced classes of package com.adobe.adms.measurement:
//            ADMS_MeasurementBase, ADMS_DefaultValues, ADMS_Churn, ADMS_Worker, 
//            c

public final class ADMS_Measurement extends ADMS_MeasurementBase
{

    static SharedPreferences a;
    static android.content.SharedPreferences.Editor b;
    private static ADMS_Worker c = null;
    private static ADMS_Churn i = null;
    protected Context context;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;

    private ADMS_Measurement()
    {
        context = null;
        d = null;
        e = null;
        f = null;
        g = null;
        h = "UTF-8";
    }

    ADMS_Measurement(byte byte0)
    {
        this();
    }

    private String a()
    {
        String s;
        TelephonyManager telephonymanager;
        String s1;
        String s2;
        try
        {
            telephonymanager = (TelephonyManager)context.getSystemService("phone");
        }
        catch (Exception exception)
        {
            ADMS_DefaultValues.exceptionLog(exception);
            s = null;
            continue; /* Loop/switch isn't completed */
        }
        if (telephonymanager == null) goto _L2; else goto _L1
_L1:
        s1 = telephonymanager.getSubscriberId();
        if (s1 != null) goto _L4; else goto _L3
_L3:
        s2 = telephonymanager.getDeviceId();
        s = s2;
_L6:
        if (s == null || s.length() <= 0)
        {
            s = UUID.randomUUID().toString().replace("-", "");
        }
        return s;
_L4:
        s = s1;
        continue; /* Loop/switch isn't completed */
_L2:
        s = null;
        if (true) goto _L6; else goto _L5
_L5:
    }

    private static void a(Context context1)
    {
        ADMS_Measurement adms_measurement = sharedInstance();
        if (context1 == null || adms_measurement.context == context1) goto _L2; else goto _L1
_L1:
        adms_measurement;
        JVM INSTR monitorenter ;
        adms_measurement.context = context1;
        if (i == null)
        {
            i = new ADMS_Churn(adms_measurement);
        }
        if (c == null)
        {
            c = new ADMS_Worker();
        }
        SharedPreferences sharedpreferences = adms_measurement.context.getSharedPreferences("APP_MEASUREMENT_CACHE", 0);
        a = sharedpreferences;
        b = sharedpreferences.edit();
        c.cacheFilename = (new File(adms_measurement.context.getCacheDir(), "ADMS_OfflineCache.offline")).getPath();
        c.readFromDisk();
        c.setOnline(true);
        adms_measurement.setOfflineHitLimit(1000);
        adms_measurement;
        JVM INSTR monitorexit ;
_L2:
        return;
        Exception exception;
        exception;
        adms_measurement;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected static String getAndroidVersion()
    {
        return android.os.Build.VERSION.RELEASE;
    }

    protected static boolean isOnline()
    {
        if (sharedInstance().context == null)
        {
            return false;
        }
        NetworkInfo networkinfo = ((ConnectivityManager)sharedInstance().context.getSystemService("connectivity")).getActiveNetworkInfo();
        return networkinfo != null && networkinfo.isConnectedOrConnecting();
    }

    protected static String loadPref(Context context1)
    {
        return a.getString("APP_MEASUREMENT_VISITOR_ID", null);
    }

    protected static void savePref(Context context1, String s)
    {
        b.putString("APP_MEASUREMENT_VISITOR_ID", s);
        b.commit();
    }

    public static ADMS_Measurement sharedInstance()
    {
        return c.a;
    }

    public static ADMS_Measurement sharedInstance(Context context1)
    {
        a(context1);
        return c.a;
    }

    public final void clearTrackingQueue()
    {
        c.clearTrackingQueue();
    }

    public final Object clone()
    {
        throw new CloneNotSupportedException();
    }

    protected final void debugLog(String s)
    {
        if (debugLogging)
        {
            Log.d("ADMS_MEASUREMENT", s);
        }
    }

    protected final String getApplicationID()
    {
        if (d != null) goto _L2; else goto _L1
_L1:
        if (context != null) goto _L4; else goto _L3
_L3:
        d = "";
_L2:
        return d;
_L4:
        String s;
        String s1;
        PackageManager packagemanager = context.getPackageManager();
        android.content.pm.ApplicationInfo applicationinfo = packagemanager.getApplicationInfo(context.getPackageName(), 0);
        PackageInfo packageinfo = packagemanager.getPackageInfo(context.getPackageName(), 0);
        s = (String)packagemanager.getApplicationLabel(applicationinfo);
        s1 = packageinfo.versionName;
        if (!isSet(s)) goto _L2; else goto _L5
_L5:
        StringBuilder stringbuilder;
        String s2;
        stringbuilder = (new StringBuilder()).append(s);
        if (!isSet(s1))
        {
            break MISSING_BLOCK_LABEL_172;
        }
        s2 = (new StringBuilder("/")).append(s1).toString();
_L6:
        d = stringbuilder.append(s2).toString();
          goto _L2
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        debugLog(namenotfoundexception.toString());
        debugLog(namenotfoundexception.getMessage());
        d = "";
          goto _L2
        s2 = "";
          goto _L6
    }

    protected final String getCarrierString()
    {
        if (context == null)
        {
            return "";
        } else
        {
            return ((TelephonyManager)context.getSystemService("phone")).getNetworkOperatorName();
        }
    }

    protected final String getDefaultAcceptLanguage()
    {
        if (g == null)
        {
            if (context == null)
            {
                g = "en-US";
            } else
            {
                g = context.getResources().getConfiguration().locale.toString().replace('_', '-');
            }
        }
        return g;
    }

    protected final String getDefaultCharSet()
    {
        return h;
    }

    protected final String getDefaultUserAgent()
    {
        return (new StringBuilder("Mozilla/5.0 (Linux; U; Android ")).append(getAndroidVersion()).append("; ").append(getDefaultAcceptLanguage()).append("; ").append(getPlatformString()).append(" Build/").append(Build.ID).append(") ").append(getApplicationID()).toString();
    }

    protected final String getDefaultVisitorID()
    {
        if (f == null)
        {
            if (loadPref(context) == null)
            {
                savePref(context, a());
            }
            f = loadPref(context);
        }
        if (f == null && debugLogging)
        {
            debugLog("Unable to generate Visitor ID");
            f = "";
        }
        return f;
    }

    public final int getLifecycleSessionTimeout()
    {
        if (i != null)
        {
            return i.lifecycleSessionTimeout;
        } else
        {
            return 0;
        }
    }

    protected final String getNetworkConnectionString()
    {
        if (context == null)
        {
            return "";
        }
        if (((ConnectivityManager)context.getSystemService("connectivity")).getNetworkInfo(1).isConnected())
        {
            return "WiFi";
        }
        if (((ConnectivityManager)context.getSystemService("connectivity")).getNetworkInfo(0).isConnected())
        {
            return "Cell";
        } else
        {
            return "";
        }
    }

    protected final String getOperatingSystemString()
    {
        return (new StringBuilder("Android ")).append(getAndroidVersion()).toString();
    }

    protected final String getPlatformString()
    {
        return Build.MODEL;
    }

    protected final String getResolutionString()
    {
        if (e == null)
        {
            if (context == null)
            {
                e = "";
            } else
            {
                DisplayMetrics displaymetrics = context.getResources().getDisplayMetrics();
                e = (new StringBuilder()).append(displaymetrics.widthPixels).append("x").append(displaymetrics.heightPixels).toString();
            }
        }
        return e;
    }

    public final int getTrackingQueueSize()
    {
        return c.getTrackingQueueSize();
    }

    protected final void sendRequest(String s)
    {
        debugLog((new StringBuilder("Hit Request String : ")).append(s).toString());
        if (!isSet(userAgent))
        {
            userAgent = getDefaultUserAgent();
        }
        userAgent = userAgent.replace("\t", " ");
        userAgent = userAgent.replace("\n", " ");
        userAgent = userAgent.replace("\r", " ");
        String s1 = (new StringBuilder()).append(s).append("\tUser-Agent\t").append(userAgent).append("\tAccept-Language\t").append(getDefaultAcceptLanguage()).toString();
        c.queue(s1);
    }

    public final void setContext(Context context1)
    {
        if (context1 != null)
        {
            a(context1);
        }
    }

    public final void setLifecycleSessionTimeout(int j)
    {
        if (i != null)
        {
            i.lifecycleSessionTimeout = j;
        }
    }

    public final void setOffline()
    {
        isOffline = true;
        setWorkerOffline(isOffline);
    }

    public final void setOfflineHitLimit(int j)
    {
        if (j < 0)
        {
            j = 0;
        }
        offlineHitLimit = j;
        synchronized (c)
        {
            c.offlineLimit = j;
        }
    }

    public final void setOfflineThrottleDelay(int j)
    {
    }

    public final void setOfflineTrackingEnabled(boolean flag)
    {
        offlineTrackingEnabled = flag;
        synchronized (c)
        {
            c.trackOffline = flag;
        }
    }

    public final void setOnline()
    {
        isOffline = false;
        setWorkerOffline(isOffline);
    }

    protected final void setWorkerOffline(boolean flag)
    {
        ADMS_Worker adms_worker = c;
        adms_worker;
        JVM INSTR monitorenter ;
        ADMS_Worker adms_worker1 = c;
        boolean flag1;
        if (!flag)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        adms_worker1.setOnline(flag1);
        adms_worker;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void startActivity(Activity activity)
    {
        a(activity.getApplicationContext());
        i.startActivity(context);
    }

    public final void stopActivity()
    {
        i.stopActivity(context);
    }

}
