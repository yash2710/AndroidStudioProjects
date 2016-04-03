// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.crashlytics.android.internal:
//            ab, v, q, ap

public final class ao
{

    private static final Pattern a = Pattern.compile("[^\\p{Alnum}]");
    private static final String b = Pattern.quote("/");
    private final ReentrantLock c = new ReentrantLock();
    private final boolean d;
    private final boolean e;
    private final Context f;

    public ao(Context context)
    {
        if (context == null)
        {
            throw new IllegalArgumentException("appContext must not be null");
        }
        f = context;
        d = ab.a(context, "com.crashlytics.CollectDeviceIdentifiers", true);
        if (!d)
        {
            v.a().b().a("Crashlytics", (new StringBuilder("Device ID collection disabled for ")).append(context.getPackageName()).toString());
        }
        e = ab.a(context, "com.crashlytics.CollectUserIdentifiers", true);
        if (!e)
        {
            v.a().b().a("Crashlytics", (new StringBuilder("User information collection disabled for ")).append(context.getPackageName()).toString());
        }
    }

    private String a(SharedPreferences sharedpreferences)
    {
        c.lock();
        String s = sharedpreferences.getString("crashlytics.installation.id", null);
        if (s != null)
        {
            break MISSING_BLOCK_LABEL_51;
        }
        s = b(UUID.randomUUID().toString());
        sharedpreferences.edit().putString("crashlytics.installation.id", s).commit();
        c.unlock();
        return s;
        Exception exception;
        exception;
        c.unlock();
        throw exception;
    }

    private static void a(Map map, ap ap1, String s)
    {
        if (s != null)
        {
            map.put(ap1, s);
        }
    }

    private boolean a(String s)
    {
        return f.checkCallingPermission(s) == 0;
    }

    private static String b(String s)
    {
        if (s == null)
        {
            return null;
        } else
        {
            return a.matcher(s).replaceAll("").toLowerCase(Locale.US);
        }
    }

    private static String c(String s)
    {
        return s.replaceAll(b, "");
    }

    private String i()
    {
        if (!d || android.os.Build.VERSION.SDK_INT < 9)
        {
            break MISSING_BLOCK_LABEL_52;
        }
        String s = b((String)android/os/Build.getField("SERIAL").get(null));
        return s;
        Exception exception;
        exception;
        v.a().b().a("Crashlytics", "Could not retrieve android.os.Build.SERIAL value", exception);
        return null;
    }

    public final boolean a()
    {
        return e;
    }

    public final String b()
    {
        String s = v.a().i();
        if (s == null)
        {
            SharedPreferences sharedpreferences = ab.a();
            s = sharedpreferences.getString("crashlytics.installation.id", null);
            if (s == null)
            {
                s = a(sharedpreferences);
            }
        }
        return s;
    }

    public final String c()
    {
        Locale locale = Locale.US;
        Object aobj[] = new Object[2];
        aobj[0] = c(android.os.Build.VERSION.RELEASE);
        aobj[1] = c(android.os.Build.VERSION.INCREMENTAL);
        return String.format(locale, "%s/%s", aobj);
    }

    public final String d()
    {
        Locale locale = Locale.US;
        Object aobj[] = new Object[2];
        aobj[0] = c(Build.MANUFACTURER);
        aobj[1] = c(Build.MODEL);
        return String.format(locale, "%s/%s", aobj);
    }

    public final String e()
    {
        String s = "";
        if (d)
        {
            s = g();
            if (s == null)
            {
                SharedPreferences sharedpreferences = ab.a();
                s = sharedpreferences.getString("crashlytics.installation.id", null);
                if (s == null)
                {
                    s = a(sharedpreferences);
                }
            }
        }
        return s;
    }

    public final Map f()
    {
        HashMap hashmap;
        ap ap1;
        hashmap = new HashMap();
        a(hashmap, ap.c, g());
        ap1 = ap.d;
        if (!d || !a("android.permission.READ_PHONE_STATE")) goto _L2; else goto _L1
_L1:
        TelephonyManager telephonymanager = (TelephonyManager)f.getSystemService("phone");
        if (telephonymanager == null) goto _L2; else goto _L3
_L3:
        String s = b(telephonymanager.getDeviceId());
_L5:
        a(hashmap, ap1, s);
        a(hashmap, ap.e, i());
        ap ap2 = ap.a;
        boolean flag = d;
        String s1 = null;
        if (flag)
        {
            boolean flag1 = a("android.permission.ACCESS_WIFI_STATE");
            s1 = null;
            if (flag1)
            {
                WifiManager wifimanager = (WifiManager)f.getSystemService("wifi");
                s1 = null;
                if (wifimanager != null)
                {
                    WifiInfo wifiinfo = wifimanager.getConnectionInfo();
                    s1 = null;
                    if (wifiinfo != null)
                    {
                        s1 = b(wifiinfo.getMacAddress());
                    }
                }
            }
        }
        a(hashmap, ap2, s1);
        a(hashmap, ap.b, h());
        return Collections.unmodifiableMap(hashmap);
_L2:
        s = null;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public final String g()
    {
        boolean flag = d;
        String s = null;
        if (flag)
        {
            String s1 = android.provider.Settings.Secure.getString(f.getContentResolver(), "android_id");
            boolean flag1 = "9774d56d682e549c".equals(s1);
            s = null;
            if (!flag1)
            {
                s = b(s1);
            }
        }
        return s;
    }

    public final String h()
    {
        if (!d || !a("android.permission.BLUETOOTH"))
        {
            break MISSING_BLOCK_LABEL_33;
        }
        BluetoothAdapter bluetoothadapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothadapter != null)
        {
            try
            {
                b(bluetoothadapter.getAddress());
            }
            catch (Exception exception)
            {
                v.a().b().a("Crashlytics", "Utils#getBluetoothMacAddress failed, returning null. Requires prior call to BluetoothAdatpter.getDefaultAdapter() on thread that has called Looper.prepare()", exception);
            }
        }
        return null;
    }

}
