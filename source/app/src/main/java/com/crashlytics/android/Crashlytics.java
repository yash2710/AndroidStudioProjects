// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.crashlytics.android.internal.A;
import com.crashlytics.android.internal.D;
import com.crashlytics.android.internal.aM;
import com.crashlytics.android.internal.aP;
import com.crashlytics.android.internal.aQ;
import com.crashlytics.android.internal.aS;
import com.crashlytics.android.internal.aX;
import com.crashlytics.android.internal.ab;
import com.crashlytics.android.internal.af;
import com.crashlytics.android.internal.ag;
import com.crashlytics.android.internal.ai;
import com.crashlytics.android.internal.ao;
import com.crashlytics.android.internal.av;
import com.crashlytics.android.internal.ax;
import com.crashlytics.android.internal.ay;
import com.crashlytics.android.internal.q;
import com.crashlytics.android.internal.r;
import com.crashlytics.android.internal.u;
import com.crashlytics.android.internal.v;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HttpsURLConnection;

// Referenced classes of package com.crashlytics.android:
//            F, Z, G, X, 
//            y, t, B, E, 
//            x, Y, T, O, 
//            CrashlyticsMissingDependencyException, CrashTest, P, R, 
//            v, PinningInfoProvider, CrashlyticsListener

public final class Crashlytics extends u
{

    public static final String TAG = "Crashlytics";
    private static ContextWrapper j;
    private static String k;
    private static String l;
    private static String m;
    private static String n;
    private static String o;
    private static String p;
    private static String q;
    private static PinningInfoProvider r = null;
    private static av s;
    private static float t;
    private static Crashlytics u;
    private final long a = System.currentTimeMillis();
    private final ConcurrentHashMap b = new ConcurrentHashMap();
    private CrashlyticsListener c;
    private Z d;
    private ao e;
    private String f;
    private String g;
    private String h;
    private String i;

    public Crashlytics()
    {
        e = null;
        f = null;
        g = null;
        h = null;
    }

    static int a(float f1, int i1)
    {
        return (int)(f1 * (float)i1);
    }

    private F a(y y1)
    {
        String as[] = new String[1];
        as[0] = i;
        String s1 = ab.a(as);
        int i1 = ai.a(l).a();
        return new F(p, k, o, n, s1, m, i1, q, "0", y1);
    }

    static Z a(Crashlytics crashlytics)
    {
        return crashlytics.d;
    }

    private static void a(int i1, String s1, String s2)
    {
        Crashlytics crashlytics = getInstance();
        if (crashlytics == null || crashlytics.d == null)
        {
            v.a().b().a(s1, "Crashlytics must be initialized by calling Crashlytics.start(Context) prior to logging messages.", null);
            return;
        } else
        {
            long l1 = System.currentTimeMillis() - crashlytics.a;
            crashlytics.d.a(l1, (new StringBuilder()).append(ab.b(i1)).append("/").append(s1).append(" ").append(s2).toString());
            return;
        }
    }

    static void a(String s1)
    {
        D d1 = (D)v.a().a(com/crashlytics/android/internal/D);
        if (d1 != null)
        {
            d1.a(new ag(s1));
        }
    }

    private void a(String s1, Context context, float f1)
    {
        boolean flag = false;
        this;
        JVM INSTR monitorenter ;
        if (j == null) goto _L2; else goto _L1
_L1:
        v.a().b().a("Crashlytics", "Crashlytics already started, ignoring re-initialization attempt.");
_L6:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        p = s1;
        j = new ContextWrapper(context.getApplicationContext());
        s = new av(v.a().b());
        v.a().b().b("Crashlytics", (new StringBuilder("Initializing Crashlytics ")).append(getCrashlyticsVersion()).toString());
        PackageInfo packageinfo;
        k = j.getPackageName();
        PackageManager packagemanager = j.getPackageManager();
        l = packagemanager.getInstallerPackageName(k);
        v.a().b().a("Crashlytics", (new StringBuilder("Installer package name is: ")).append(l).toString());
        packageinfo = packagemanager.getPackageInfo(k, 0);
        n = Integer.toString(packageinfo.versionCode);
        if (packageinfo.versionName != null) goto _L4; else goto _L3
_L3:
        String s2 = "0.0";
_L8:
        o = s2;
        m = context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
        q = Integer.toString(context.getApplicationInfo().targetSdkVersion);
        i = ab.i(context);
_L9:
        e = new ao(j);
        e.h();
        (new G(i, ab.a(j, "com.crashlytics.RequireBuildId", true))).a(s1, k);
        v.a().b().a("Crashlytics", "Installing exception handler...");
        d = new Z(Thread.getDefaultUncaughtExceptionHandler(), c, i);
        flag = d.f();
        d.d();
        d.c();
        d.h();
        Thread.setDefaultUncaughtExceptionHandler(d);
        v.a().b().a("Crashlytics", "Successfully installed exception handler.");
_L10:
        CountDownLatch countdownlatch;
        countdownlatch = new CountDownLatch(1);
        (new Thread(new X(this, context, f1, countdownlatch), "Crashlytics Initializer")).start();
        if (!flag) goto _L6; else goto _L5
_L5:
        v.a().b().a("Crashlytics", "Crashlytics detected incomplete initialization on previous app launch. Will initialize synchronously.");
        if (countdownlatch.await(4000L, TimeUnit.MILLISECONDS)) goto _L6; else goto _L7
_L7:
        v.a().b().c("Crashlytics", "Crashlytics initialization was not completed in the allotted time.");
          goto _L6
        InterruptedException interruptedexception;
        interruptedexception;
        v.a().b().a("Crashlytics", "Crashlytics was interrupted during initialization.", interruptedexception);
          goto _L6
        Exception exception;
        exception;
        throw exception;
_L4:
        s2 = packageinfo.versionName;
          goto _L8
        Exception exception1;
        exception1;
        v.a().b().a("Crashlytics", "Error setting up app properties", exception1);
          goto _L9
        Exception exception2;
        exception2;
        v.a().b().a("Crashlytics", "There was a problem installing the exception handler.", exception2);
          goto _L10
    }

    private boolean a(Context context, float f1)
    {
        boolean flag;
        String s1;
        flag = true;
        s1 = ab.g(getContext());
        aX ax2;
        aS.a().a(context, s, n, o, i()).c();
        ax2 = aS.a().b();
        aX ax1 = ax2;
_L7:
        if (ax1 == null)
        {
            break MISSING_BLOCK_LABEL_436;
        }
        aM am = ax1.a;
        if (!"new".equals(am.a)) goto _L2; else goto _L1
_L1:
        F f3 = a(y.a(getContext(), s1));
        if (!(new t(i(), am.b, s)).a(f3)) goto _L4; else goto _L3
_L3:
        boolean flag5 = aS.a().d();
        boolean flag4 = flag5;
_L8:
        boolean flag2 = flag4;
_L9:
        boolean flag1;
        boolean flag3;
        com.crashlytics.android.v v1;
        try
        {
            flag1 = ax1.d.b;
        }
        catch (Exception exception3)
        {
            v.a().b().a("Crashlytics", "Error getting collect reports setting.", exception3);
            flag1 = false;
        }
_L11:
        if (!flag2 || !flag1) goto _L6; else goto _L5
_L5:
        flag = true & d.b();
        v1 = q();
        flag3 = false;
        Exception exception;
        Exception exception2;
        F f2;
        if (v1 != null)
        {
            try
            {
                (new B(v1)).a(f1);
            }
            catch (Exception exception1)
            {
                v.a().b().a("Crashlytics", "Error sending crash report", exception1);
                flag3 = false;
            }
        }
_L10:
        if (flag3)
        {
            v.a().b().a("Crashlytics", "Crash reporting disabled.");
        }
        return flag;
        exception;
        v.a().b().a("Crashlytics", "Error dealing with settings", exception);
        ax1 = null;
          goto _L7
_L4:
        v.a().b().a("Crashlytics", "Failed to create app with Crashlytics service.", null);
        flag4 = false;
          goto _L8
_L2:
label0:
        {
            if (!"configured".equals(am.a))
            {
                break label0;
            }
            flag4 = aS.a().d();
        }
          goto _L8
        if (am.d)
        {
            v.a().b().a("Crashlytics", "Server says an update is required - forcing a full App update.");
            f2 = a(y.a(getContext(), s1));
            (new E(i(), am.b, s)).a(f2);
        }
        flag4 = flag;
          goto _L8
        exception2;
        v.a().b().a("Crashlytics", "Error performing auto configuration.", exception2);
        flag2 = false;
          goto _L9
_L6:
        flag3 = flag;
          goto _L10
        flag1 = false;
        flag2 = false;
          goto _L11
    }

    static boolean a(Crashlytics crashlytics, Activity activity, aQ aq)
    {
        x x1 = new x(activity, aq);
        Y y1 = new Y(crashlytics);
        activity.runOnUiThread(new T(crashlytics, activity, y1, x1, aq));
        v.a().b().a("Crashlytics", "Waiting for user opt-in.");
        y1.b();
        return y1.a();
    }

    static boolean a(Crashlytics crashlytics, Context context, float f1)
    {
        return crashlytics.a(context, f1);
    }

    static void b(String s1)
    {
        D d1 = (D)v.a().a(com/crashlytics/android/internal/D);
        if (d1 != null)
        {
            d1.a(new af(s1));
        }
    }

    private static String c(String s1)
    {
        if (s1 != null)
        {
            s1 = s1.trim();
            if (s1.length() > 1024)
            {
                s1 = s1.substring(0, 1024);
            }
        }
        return s1;
    }

    static String d()
    {
        return k;
    }

    static String e()
    {
        return l;
    }

    static String f()
    {
        return o;
    }

    static String g()
    {
        return n;
    }

    public static String getCrashlyticsVersion()
    {
        return getInstance().getVersion();
    }

    public static Crashlytics getInstance()
    {
        com/crashlytics/android/Crashlytics;
        JVM INSTR monitorenter ;
        Crashlytics crashlytics = (Crashlytics)v.a().a(com/crashlytics/android/Crashlytics);
        if (crashlytics == null) goto _L2; else goto _L1
_L1:
        com/crashlytics/android/Crashlytics;
        JVM INSTR monitorexit ;
        return crashlytics;
_L2:
        if (u == null)
        {
            u = new Crashlytics();
        }
        crashlytics = u;
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public static PinningInfoProvider getPinningInfoProvider()
    {
        return r;
    }

    static String h()
    {
        return m;
    }

    static String i()
    {
        return ab.a(j, "com.crashlytics.ApiEndpoint");
    }

    static boolean k()
    {
        return ab.a().getBoolean("always_send_reports_opt_in", false);
    }

    static void l()
    {
        ab.a().edit().putBoolean("always_send_reports_opt_in", true).commit();
    }

    public static void log(int i1, String s1, String s2)
    {
        a(i1, s1, s2);
        v.a().b().a(i1, s1, s2, true);
    }

    public static void log(String s1)
    {
        a(3, "Crashlytics", s1);
    }

    public static void logException(Throwable throwable)
    {
        Crashlytics crashlytics = getInstance();
        if (crashlytics == null || crashlytics.d == null)
        {
            v.a().b().a("Crashlytics", "Crashlytics must be initialized by calling Crashlytics.start(Context) prior to logging exceptions.", null);
            return;
        }
        if (throwable == null)
        {
            v.a().b().a(5, "Crashlytics", "Crashlytics is ignoring a request to log a null exception.");
            return;
        } else
        {
            crashlytics.d.a(Thread.currentThread(), throwable);
            return;
        }
    }

    static av r()
    {
        return s;
    }

    public static void setApplicationInstallationIdentifier(String s1)
    {
        v.a().a(c(s1));
    }

    public static void setBool(String s1, boolean flag)
    {
        setString(s1, Boolean.toString(flag));
    }

    public static void setDouble(String s1, double d1)
    {
        setString(s1, Double.toString(d1));
    }

    public static void setFloat(String s1, float f1)
    {
        setString(s1, Float.toString(f1));
    }

    public static void setInt(String s1, int i1)
    {
        setString(s1, Integer.toString(i1));
    }

    public static void setLong(String s1, long l1)
    {
        setString(s1, Long.toString(l1));
    }

    public static void setPinningInfoProvider(PinningInfoProvider pinninginfoprovider)
    {
label0:
        {
            if (r != pinninginfoprovider)
            {
                r = pinninginfoprovider;
                if (s != null)
                {
                    if (pinninginfoprovider != null)
                    {
                        break label0;
                    }
                    s.a(null);
                }
            }
            return;
        }
        s.a(new O(pinninginfoprovider));
    }

    public static void setString(String s1, String s2)
    {
        if (s1 == null)
        {
            if (j != null && ab.f(j))
            {
                throw new IllegalArgumentException("Custom attribute key cannot be null.");
            } else
            {
                v.a().b().a("Crashlytics", "Attempting to set custom attribute with null key, ignoring.", null);
                return;
            }
        }
        String s3 = c(s1);
        if (getInstance().b.size() < 64 || getInstance().b.containsKey(s3))
        {
            String s4;
            if (s2 == null)
            {
                s4 = "";
            } else
            {
                s4 = c(s2);
            }
            getInstance().b.put(s3, s4);
            return;
        } else
        {
            v.a().b().a("Crashlytics", "Exceeded maximum number of custom attributes (64)");
            return;
        }
    }

    public static void setUserEmail(String s1)
    {
        getInstance().g = c(s1);
    }

    public static void setUserIdentifier(String s1)
    {
        getInstance().f = c(s1);
    }

    public static void setUserName(String s1)
    {
        getInstance().h = c(s1);
    }

    public static void start(Context context)
    {
        start(context, 1.0F);
    }

    public static void start(Context context, float f1)
    {
        t = f1;
        if (!ab.d(context))
        {
            v.a().a(new A());
        }
        u au[] = new u[2];
        au[0] = getInstance();
        au[1] = new D();
        v.a(context, au);
    }

    final Map a()
    {
        return Collections.unmodifiableMap(b);
    }

    final ao b()
    {
        return e;
    }

    protected final void c()
    {
        Context context = super.getContext();
        String s1 = com.crashlytics.android.internal.r.a(context, false);
        if (s1 == null)
        {
            return;
        }
        try
        {
            a(s1, context, t);
            return;
        }
        catch (CrashlyticsMissingDependencyException crashlyticsmissingdependencyexception)
        {
            throw crashlyticsmissingdependencyexception;
        }
        catch (Exception exception)
        {
            v.a().b().a("Crashlytics", "Crashlytics was not started due to an exception during initialization", exception);
        }
    }

    public final void crash()
    {
        (new CrashTest()).indexOutOfBounds();
    }

    public final boolean getDebugMode()
    {
        return v.a().f();
    }

    public final String getVersion()
    {
        return v.a().getVersion();
    }

    final boolean j()
    {
        return ((Boolean)aS.a().a(new P(this), Boolean.valueOf(false))).booleanValue();
    }

    final Z m()
    {
        return d;
    }

    final String n()
    {
        if (e.a())
        {
            return f;
        } else
        {
            return null;
        }
    }

    final String o()
    {
        if (e.a())
        {
            return g;
        } else
        {
            return null;
        }
    }

    final String p()
    {
        if (e.a())
        {
            return h;
        } else
        {
            return null;
        }
    }

    final com.crashlytics.android.v q()
    {
        return (com.crashlytics.android.v)aS.a().a(new R(), null);
    }

    public final void setDebugMode(boolean flag)
    {
        v.a().a(flag);
    }

    public final void setListener(CrashlyticsListener crashlyticslistener)
    {
        c = crashlyticslistener;
    }

    public final boolean verifyPinning(URL url)
    {
label0:
        {
            try
            {
                if (getPinningInfoProvider() == null)
                {
                    break label0;
                }
                ay ay1 = s.a(ax.a, url.toString());
                ((HttpsURLConnection)ay1.a()).setInstanceFollowRedirects(false);
                ay1.b();
            }
            catch (Exception exception)
            {
                v.a().b().a("Crashlytics", "Could not verify SSL pinning", exception);
                return false;
            }
            return true;
        }
        return false;
    }

}
