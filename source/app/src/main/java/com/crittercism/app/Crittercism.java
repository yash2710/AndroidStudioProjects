// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crittercism.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import crittercism.android.a;
import crittercism.android.b;
import crittercism.android.d;
import crittercism.android.e;
import crittercism.android.i;
import crittercism.android.k;
import crittercism.android.l;
import crittercism.android.m;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.crittercism.app:
//            f, CrittercismNDK, c, d, 
//            a, m, j, g, 
//            e, o, h, i, 
//            k, b

public class Crittercism
{

    public static final String a = crittercism.android.d.b();
    private static Crittercism b = new Crittercism();
    private b c;
    private d d;
    private e e;
    private boolean f;
    private Context g;
    private i h;
    private m i;
    private k j;
    private l k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private String q;
    private int r;
    private String s;
    private String t;
    private String u;
    private String v;
    private boolean w;
    private Thread x;
    private Handler y;

    private Crittercism()
    {
        e = null;
        f = false;
        g = null;
        h = new i();
        i = new m();
        j = new k();
        k = new l();
        l = false;
        m = false;
        n = false;
        o = false;
        p = false;
        q = "";
        r = 0;
        s = null;
        t = "";
        u = "Developer Reply";
        v = "com.crittercism/dumps";
        w = false;
        x = null;
        y = new f(this);
    }

    static void A()
    {
        if (b != null)
        {
            try
            {
                android.content.SharedPreferences.Editor editor = b.g.getSharedPreferences("com.crittercism.prefs", 0).edit();
                editor.remove("crashedOnLastAppLoad");
                editor.putBoolean("crashedOnLastAppLoad", true);
                if (!editor.commit())
                {
                    throw new Exception();
                }
            }
            catch (Exception exception) { }
        }
    }

    private String B()
    {
        String s1;
        try
        {
            s1 = g.getPackageManager().getPackageInfo(g.getPackageName(), 0).versionName;
        }
        catch (Exception exception)
        {
            return "1.0";
        }
        return s1;
    }

    private int C()
    {
        int i1;
        try
        {
            i1 = g.getPackageManager().getPackageInfo(g.getPackageName(), 0).versionCode;
        }
        catch (Exception exception)
        {
            return 0;
        }
        return i1;
    }

    private static long a(Date date)
    {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.s", Locale.US);
        simpledateformat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String s1 = simpledateformat.format(date);
        long l1 = 0L;
        try
        {
            l1 = simpledateformat.parse(s1).getTime();
            (new StringBuilder("longFormat = ")).append(Long.toString(l1)).append("\tdate = ").append(date.toString());
        }
        catch (Exception exception)
        {
            return l1;
        }
        return l1;
    }

    public static Crittercism a()
    {
        return b;
    }

    static void a(Crittercism crittercism)
    {
        String s1;
        JSONArray jsonarray1;
        JSONObject jsonobject;
        JSONArray jsonarray2;
        crittercism.d;
        s1 = crittercism.android.d.a(crittercism.g, "breadcrumbsFileString");
        JSONArray jsonarray = new JSONArray();
        jsonarray.put("session_start");
        jsonarray.put(z());
        jsonarray1 = new JSONArray();
        jsonarray1.put(jsonarray);
        jsonobject = new JSONObject();
        jsonarray2 = new JSONArray();
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_301;
        }
        JSONObject jsonobject3;
        JVM INSTR new #268 <Class JSONObject>;
        jsonobject3 = JSONObjectInstrumentation.init(s1);
        JSONObject jsonobject2 = jsonobject3;
_L3:
        if (!jsonobject2.has("current_session")) goto _L2; else goto _L1
_L1:
        JSONArray jsonarray4 = jsonobject2.getJSONArray("current_session");
        JSONObject jsonobject1;
        JSONArray jsonarray3;
        jsonobject1 = jsonobject2;
        jsonarray3 = jsonarray4;
_L4:
        StringBuilder stringbuilder;
        String s2;
        Context context;
        String s3;
        JSONException jsonexception1;
        JSONException jsonexception2;
        try
        {
            jsonobject1.put("previous_session", jsonarray3);
            jsonobject1.put("current_session", jsonarray1);
        }
        catch (JSONException jsonexception)
        {
            jsonobject1 = new JSONObject();
        }
        stringbuilder = new StringBuilder("Breadcrumbs: ");
        if (!(jsonobject1 instanceof JSONObject))
        {
            s2 = jsonobject1.toString();
        } else
        {
            s2 = JSONObjectInstrumentation.toString((JSONObject)jsonobject1);
        }
        stringbuilder.append(s2);
        crittercism.d;
        context = crittercism.g;
        if (!(jsonobject1 instanceof JSONObject))
        {
            s3 = jsonobject1.toString();
        } else
        {
            s3 = JSONObjectInstrumentation.toString((JSONObject)jsonobject1);
        }
        crittercism.android.d.a(context, "breadcrumbsFileString", s3);
        return;
        jsonexception1;
        jsonobject2 = new JSONObject();
          goto _L3
        jsonexception2;
        jsonobject1 = new JSONObject();
        jsonarray3 = new JSONArray();
          goto _L4
_L2:
        jsonobject1 = jsonobject2;
        jsonarray3 = jsonarray2;
          goto _L4
        jsonobject1 = jsonobject;
        jsonarray3 = jsonarray2;
          goto _L4
    }

    static void a(Crittercism crittercism, String s1)
    {
        if (crittercism.l) goto _L2; else goto _L1
_L1:
        String s2;
        JSONArray jsonarray;
        JSONObject jsonobject;
        JSONArray jsonarray1;
        crittercism.d;
        s2 = crittercism.android.d.a(crittercism.g, "breadcrumbsFileString");
        jsonarray = new JSONArray();
        jsonarray.put(s1);
        jsonarray.put(z());
        jsonobject = new JSONObject();
        jsonarray1 = new JSONArray();
        if (s2 == null)
        {
            break MISSING_BLOCK_LABEL_394;
        }
        JSONObject jsonobject3;
        JVM INSTR new #268 <Class JSONObject>;
        jsonobject3 = JSONObjectInstrumentation.init(s2);
        JSONObject jsonobject2 = jsonobject3;
_L9:
        if (!jsonobject2.has("current_session")) goto _L4; else goto _L3
_L3:
        JSONArray jsonarray7 = jsonobject2.getJSONArray("current_session");
        JSONObject jsonobject1;
        JSONArray jsonarray2;
        jsonobject1 = jsonobject2;
        jsonarray2 = jsonarray7;
_L10:
        jsonarray2.put(jsonarray);
        if (jsonarray2.length() <= 50) goto _L6; else goto _L5
_L5:
        JSONArray jsonarray3 = new JSONArray();
        jsonarray3.put(jsonarray2.getJSONArray(0));
        int i1 = 2;
_L8:
        if (i1 >= jsonarray2.length())
        {
            break; /* Loop/switch isn't completed */
        }
        jsonarray3.put(jsonarray2.getJSONArray(i1));
        i1++;
        if (true) goto _L8; else goto _L7
        JSONException jsonexception2;
        jsonexception2;
        jsonobject2 = new JSONObject();
          goto _L9
        JSONException jsonexception3;
        jsonexception3;
        JSONArray jsonarray6 = new JSONArray();
        jsonobject1 = jsonobject2;
        jsonarray2 = jsonarray6;
          goto _L10
_L7:
        JSONArray jsonarray4 = jsonarray3;
_L11:
        JSONArray jsonarray5 = jsonarray4;
_L12:
        JSONException jsonexception;
        StringBuilder stringbuilder;
        String s3;
        Context context;
        String s4;
        try
        {
            jsonobject1.put("current_session", jsonarray5);
        }
        catch (JSONException jsonexception1) { }
        stringbuilder = new StringBuilder("Breadcrumbs: ");
        if (!(jsonobject1 instanceof JSONObject))
        {
            s3 = jsonobject1.toString();
        } else
        {
            s3 = JSONObjectInstrumentation.toString((JSONObject)jsonobject1);
        }
        stringbuilder.append(s3);
        (new StringBuilder("currentSessionJsonArray size: ")).append(Integer.toString(jsonarray5.length()));
        crittercism.d;
        context = crittercism.g;
        if (!(jsonobject1 instanceof JSONObject))
        {
            s4 = jsonobject1.toString();
        } else
        {
            s4 = JSONObjectInstrumentation.toString((JSONObject)jsonobject1);
        }
        crittercism.android.d.a(context, "breadcrumbsFileString", s4);
_L2:
        return;
        jsonexception;
        jsonarray4 = new JSONArray();
          goto _L11
_L6:
        jsonarray5 = jsonarray2;
          goto _L12
_L4:
        jsonobject1 = jsonobject2;
        jsonarray2 = jsonarray1;
          goto _L10
        jsonobject1 = jsonobject;
        jsonarray2 = jsonarray1;
          goto _L10
    }

    static void a(Crittercism crittercism, boolean flag)
    {
        crittercism.l = flag;
    }

    static boolean a(Context context)
    {
        return c(context);
    }

    static void b(Crittercism crittercism, boolean flag)
    {
        crittercism.m = flag;
    }

    static boolean b(Context context)
    {
        return d(context);
    }

    static boolean b(Crittercism crittercism)
    {
        return crittercism.o;
    }

    static Context c(Crittercism crittercism)
    {
        return crittercism.g;
    }

    static void c(Crittercism crittercism, boolean flag)
    {
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_15;
        }
        CrittercismNDK.installNdkLib(crittercism.g, crittercism.v);
        return;
        Throwable throwable;
        throwable;
        (new StringBuilder("Exception installing ndk library: ")).append(throwable.getClass().getName());
        return;
    }

    public static boolean c()
    {
        return b.p;
    }

    private static boolean c(Context context)
    {
        boolean flag;
        try
        {
            flag = context.getSharedPreferences("com.crittercism.prefs", 0).getBoolean("optOutStatus", false);
        }
        catch (Exception exception)
        {
            return false;
        }
        return flag;
    }

    static Thread d(Crittercism crittercism)
    {
        return crittercism.x;
    }

    private static boolean d(Context context)
    {
        boolean flag;
        try
        {
            SharedPreferences sharedpreferences = context.getSharedPreferences("com.crittercism.prefs", 0);
            flag = sharedpreferences.getBoolean("crashedOnLastAppLoad", false);
            android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.remove("crashedOnLastAppLoad");
            if (!editor.commit())
            {
                throw new Exception();
            }
        }
        catch (Exception exception)
        {
            (new StringBuilder("Exception when trying to retrieve crashedOnLastAppLoad from SharedPreferences! ")).append(exception.getClass().getName());
            flag = false;
        }
        StringBuilder stringbuilder = new StringBuilder("read crashedOnLastAppLoad as: ");
        String s1;
        if (flag)
        {
            s1 = "true";
        } else
        {
            s1 = "false";
        }
        stringbuilder.append(s1);
        return flag;
    }

    public static boolean didCrashOnLastAppLoad()
    {
        if (b == null)
        {
            Log.w("Crittercism", "Call to didCrashOnLastAppLoad() failed.  Please contact us at support@crittercism.com.");
            return false;
        }
        boolean flag;
        try
        {
            flag = b.m;
        }
        catch (Exception exception)
        {
            return false;
        }
        return flag;
    }

    static b e(Crittercism crittercism)
    {
        return crittercism.c;
    }

    public static String getNotificationTitle()
    {
        if (b == null)
        {
            Log.w("Crittercism", "Call to getNotificationTitle failed.  Please contact us at support@crittercism.com.");
            return "Developer Reply";
        } else
        {
            return b.u;
        }
    }

    public static boolean getOptOutStatus()
    {
        FutureTask futuretask;
        ExecutorService executorservice;
        if (b == null)
        {
            Log.w("Crittercism", "Call to getOptOutStatus() failed.  Please contact us at support@crittercism.com.");
            return false;
        }
        futuretask = new FutureTask(new c());
        executorservice = Executors.newFixedThreadPool(10);
        executorservice.execute(futuretask);
        boolean flag = false;
_L2:
        boolean flag1;
        if (futuretask.isDone())
        {
            break; /* Loop/switch isn't completed */
        }
        flag1 = ((Boolean)futuretask.get(2500L, TimeUnit.MILLISECONDS)).booleanValue();
        flag = flag1;
        if (true) goto _L2; else goto _L1
        TimeoutException timeoutexception;
        timeoutexception;
        flag = false;
_L1:
        return flag;
        Exception exception;
        exception;
        (new StringBuilder("Exception in getOptOutStatus: ")).append(exception.getClass().getName());
        flag = false;
        if (true) goto _L1; else goto _L3
_L3:
    }

    public static String getUserUUID()
    {
        FutureTask futuretask;
        ExecutorService executorservice;
        if (b == null)
        {
            Log.w("Crittercism", "Call to getUserUUID failed.  Please contact us at support@crittercism.com.");
            return null;
        }
        futuretask = new FutureTask(new com.crittercism.app.d());
        executorservice = Executors.newFixedThreadPool(10);
        executorservice.execute(futuretask);
        String s1 = null;
        try
        {
            while (!futuretask.isDone()) 
            {
                s1 = (String)futuretask.get(2500L, TimeUnit.MILLISECONDS);
            }
        }
        catch (TimeoutException timeoutexception)
        {
            s1 = null;
        }
        catch (Exception exception)
        {
            (new StringBuilder("Exception in getUserUUID: ")).append(exception.getClass().getName());
            s1 = null;
        }
        return s1;
    }

    public static transient boolean init(Context context, String s1, JSONObject ajsonobject[])
    {
        com/crittercism/app/Crittercism;
        JVM INSTR monitorenter ;
        if (!s1.contains("CRITTERCISM_APP_ID")) goto _L2; else goto _L1
_L1:
        Log.e("Crittercism", "ERROR: Crittercism will not work unless you enter a valid Crittercism App ID. Check your settings page to find the ID.");
        boolean flag = false;
_L10:
        com/crittercism/app/Crittercism;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        JSONObject jsonobject = new JSONObject();
        if (ajsonobject.length <= 0) goto _L4; else goto _L3
_L3:
        JSONObject jsonobject1 = ajsonobject[0];
_L16:
        if (b == null) goto _L6; else goto _L5
_L5:
        boolean flag1 = b.f;
        if (flag1) goto _L6; else goto _L7
_L7:
        Crittercism crittercism;
        boolean flag2;
        crittercism = b;
        crittercism.f = true;
        crittercism.g = context;
        crittercism.q = crittercism.B();
        crittercism.r = crittercism.C();
        flag2 = jsonobject1.has("customVersionName");
        if (!flag2)
        {
            break MISSING_BLOCK_LABEL_135;
        }
        crittercism.s = jsonobject1.getString("customVersionName");
_L11:
        boolean flag3;
        crittercism.c = new b(context, s1, a, crittercism.q, crittercism.r, crittercism.s);
        crittercism.c.e();
        if (crittercism.d == null)
        {
            crittercism.d = new d();
        }
        crittercism.android.a.a(context);
        flag3 = jsonobject1.has("installNdk");
        if (!flag3)
        {
            break MISSING_BLOCK_LABEL_228;
        }
        crittercism.o = jsonobject1.getBoolean("installNdk");
_L12:
        boolean flag4 = jsonobject1.has("nativeDumpPath");
        if (!flag4)
        {
            break MISSING_BLOCK_LABEL_256;
        }
        Exception exception;
        boolean flag5;
        boolean flag6;
        boolean flag7;
        boolean flag8;
        Exception exception3;
        Exception exception4;
        Exception exception7;
        JSONException jsonexception;
        Exception exception8;
        try
        {
            crittercism.v = jsonobject1.getString("nativeDumpPath");
        }
        catch (Exception exception6) { }
        flag5 = jsonobject1.has("delaySendingAppLoad");
        if (!flag5) goto _L9; else goto _L8
_L8:
        crittercism.w = jsonobject1.getBoolean("delaySendingAppLoad");
_L13:
        flag6 = jsonobject1.has("shouldCollectLogcat");
        if (!flag6)
        {
            break MISSING_BLOCK_LABEL_312;
        }
        crittercism.n = jsonobject1.getBoolean("shouldCollectLogcat");
_L14:
        flag7 = jsonobject1.has("notificationTitle");
        if (!flag7)
        {
            break MISSING_BLOCK_LABEL_340;
        }
        crittercism.u = jsonobject1.getString("notificationTitle");
_L15:
        crittercism.u = getNotificationTitle();
        (new StringBuilder("initialize: notification title is ")).append(crittercism.u);
        flag8 = jsonobject1.has("shouldUseAmazonMarket");
        if (!flag8)
        {
            break MISSING_BLOCK_LABEL_391;
        }
        try
        {
            jsonobject1.getBoolean("shouldUseAmazonMarket");
        }
        catch (Exception exception2) { }
        crittercism.x = new Thread(new com.crittercism.app.a(crittercism));
        crittercism.x.start();
        Log.i("Crittercism", "Crittercism Initialized.");
        flag = didCrashOnLastAppLoad();
          goto _L10
        exception8;
        crittercism.s = null;
          goto _L11
        jsonexception;
        crittercism.o = false;
          goto _L12
        exception;
        throw exception;
        exception7;
        crittercism.o = false;
          goto _L12
_L9:
        crittercism.w = false;
          goto _L13
        exception4;
        crittercism.n = false;
          goto _L14
        exception3;
        crittercism.u = "Developer Reply";
          goto _L15
_L4:
        jsonobject1 = jsonobject;
          goto _L16
        Exception exception1;
        exception1;
_L6:
        flag = false;
          goto _L10
        Exception exception5;
        exception5;
          goto _L9
    }

    public static void leaveBreadcrumb(String s1)
    {
        com/crittercism/app/Crittercism;
        JVM INSTR monitorenter ;
        if (b != null) goto _L2; else goto _L1
_L1:
        Log.w("Crittercism", "Call to leaveBreadcrumb() failed.  Please contact us at support@crittercism.com.");
_L3:
        com/crittercism/app/Crittercism;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (s1 != null)
        {
            break MISSING_BLOCK_LABEL_46;
        }
        Log.w("Crittercism", "Cannot leave null breadcrumb");
          goto _L3
        Exception exception;
        exception;
        throw exception;
        if (s1.length() > 140)
        {
            s1 = s1.substring(0, 140);
        }
        (new Thread(new com.crittercism.app.m(s1))).start();
          goto _L3
    }

    public static void logHandledException(Throwable throwable)
    {
        com/crittercism/app/Crittercism;
        JVM INSTR monitorenter ;
        if (b != null) goto _L2; else goto _L1
_L1:
        Log.w("Crittercism", "Call to logHandledException() failed.  Please contact us at support@crittercism.com.");
_L4:
        com/crittercism/app/Crittercism;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (getOptOutStatus()) goto _L4; else goto _L3
_L3:
        Crittercism crittercism = b;
        if (crittercism.j.h >= 50 || crittercism.j.e().size() >= 5 || crittercism.j.e().size() + crittercism.j.h >= 50) goto _L4; else goto _L5
_L5:
        k k1;
        JSONObject jsonobject;
        k1 = crittercism.j;
        jsonobject = new JSONObject();
        new String();
        String s1 = "";
        if (throwable.getMessage() != null)
        {
            s1 = throwable.getMessage();
        }
        k1.a(throwable);
        k1.d();
        k1.g();
_L12:
        jsonobject.put("app_state", b.c.a(new boolean[] {
            1, 1
        }));
        jsonobject.put("breadcrumbs", k1.f);
        jsonobject.put("current_thread_id", Thread.currentThread().getId());
        jsonobject.put("exception_name", k1.c);
        jsonobject.put("exception_reason", s1);
        jsonobject.put("platform", "android");
        jsonobject.put("threads", k1.e);
        jsonobject.put("ts", z());
        if (Thread.currentThread().getId() != 1L) goto _L7; else goto _L6
_L6:
        jsonobject.put("type", crittercism.android.a.a.c);
_L13:
        jsonobject.put("unsymbolized_stacktrace", k1.d);
_L14:
        k1.a(jsonobject);
        Date date = crittercism.j.g;
        if (date == null) goto _L9; else goto _L8
_L8:
        if (date == null) goto _L4; else goto _L10
_L10:
        if (a(new Date()) - a(date) <= 60000L) goto _L4; else goto _L9
_L9:
        (new Thread(new j(crittercism))).start();
          goto _L4
        Exception exception4;
        exception4;
        Exception exception;
        Exception exception2;
        Exception exception3;
        JSONException jsonexception;
        try
        {
            (new StringBuilder("Exception in logHandledExceptionInstanceMethod: ")).append(exception4.getClass().getName());
        }
        catch (Exception exception1) { }
        finally
        {
            com/crittercism/app/Crittercism;
        }
          goto _L4
        exception2;
        (new StringBuilder("Exception in addThrowableToVector: ")).append(exception2.getClass().getName());
        s1 = new String();
        k1.c = new String();
        k1.d = new JSONArray();
        k1.e = new JSONArray();
        k1.f = new JSONObject();
        if (true) goto _L12; else goto _L11
_L11:
        JVM INSTR monitorexit ;
        throw exception;
_L7:
        jsonobject.put("type", crittercism.android.a.a.d);
          goto _L13
        jsonexception;
        (new StringBuilder("JSONException in addThrowableToVector: ")).append(jsonexception.getClass().getName());
          goto _L14
        exception3;
        (new StringBuilder("Exception in addThrowableToVector: ")).append(exception3.getClass().getName());
          goto _L14
    }

    public static void sendAppLoadData()
    {
        if (b != null) goto _L2; else goto _L1
_L1:
        Log.w("Crittercism", "Failed to send app load data.  Please contact us at support@crittercism.com");
_L4:
        return;
_L2:
        if (!b.w)
        {
            break; /* Loop/switch isn't completed */
        }
        if (!getOptOutStatus())
        {
            (new Thread(new g())).start();
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
        try
        {
            Log.i("Crittercism", "sendAppLoadData() will only send data to Crittercism if \"delaySendingAppLoad\" is set to true in the configuration settings you include in the init call.");
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    public static void setMetadata(JSONObject jsonobject)
    {
        if (b == null)
        {
            Log.w("Crittercism", "Call to setMetadata() failed.  Please contact us at support@crittercism.com.");
            return;
        }
        try
        {
            b.c.c(jsonobject);
            if (b.f)
            {
                (new Thread(new com.crittercism.app.e(b, jsonobject))).start();
                return;
            }
        }
        catch (Exception exception)
        {
            return;
        }
        Log.e("Crittercism", "Initialize the Crittercism library before using its methods.");
        return;
    }

    public static void setOptOutStatus(boolean flag)
    {
        if (b == null)
        {
            Log.w("Crittercism", "Call to setOptOutStatus() failed.  Please contact us at support@crittercism.com.");
            return;
        } else
        {
            (new Thread(new o(flag))).start();
            return;
        }
    }

    public static void setUsername(String s1)
    {
        if (b == null)
        {
            Log.w("Crittercism", "Call to setUsername() failed.  Please contact us at support@crittercism.com.");
            return;
        }
        try
        {
            if (b.f)
            {
                if (b.e == null)
                {
                    b.e = new e();
                }
                b.e.b = s1;
                JSONObject jsonobject = b.c.i();
                jsonobject.put("username", s1);
                setMetadata(jsonobject);
                return;
            }
        }
        catch (Exception exception)
        {
            return;
        }
        Log.e("Crittercism", "Initialize the Crittercism library before using its methods.");
        return;
    }

    public static boolean x()
    {
        if (b == null)
        {
            return false;
        } else
        {
            return b.n;
        }
    }

    public static String z()
    {
        new String();
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sZ", Locale.US);
        simpledateformat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpledateformat.format(new Date());
    }

    public final void a(i i1)
    {
        h = i1;
    }

    public final void a(k k1)
    {
        j = k1;
    }

    public final void a(l l1)
    {
        k = l1;
    }

    public final void a(m m1)
    {
        i = m1;
    }

    public final boolean a(Throwable throwable)
    {
        this;
        JVM INSTR monitorenter ;
        m m1;
        JSONObject jsonobject;
        m1 = i;
        jsonobject = new JSONObject();
        new String();
        String s1 = "";
        if (throwable.getMessage() != null)
        {
            s1 = throwable.getMessage();
        }
        m1.a(throwable);
        m1.d();
        m1.g();
_L3:
        jsonobject.put("app_state", b.c.a(new boolean[] {
            1, 1
        }));
        jsonobject.put("breadcrumbs", m1.f);
        jsonobject.put("current_thread_id", Thread.currentThread().getId());
        jsonobject.put("exception_name", m1.c);
        jsonobject.put("exception_reason", s1);
        jsonobject.put("platform", "android");
        jsonobject.put("threads", m1.e);
        jsonobject.put("ts", z());
        if (Thread.currentThread().getId() != 1L) goto _L2; else goto _L1
_L1:
        jsonobject.put("type", crittercism.android.a.a.a);
_L4:
        jsonobject.put("unsymbolized_stacktrace", m1.d);
_L5:
        boolean flag;
        m1.a(jsonobject);
        flag = g();
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception1;
        exception1;
        (new StringBuilder("Exception in addThrowableToVector: ")).append(exception1.getClass().getName());
        s1 = new String();
        m1.c = new String();
        m1.d = new JSONArray();
        m1.e = new JSONArray();
        m1.f = new JSONObject();
          goto _L3
        Exception exception;
        exception;
        throw exception;
_L2:
        jsonobject.put("type", crittercism.android.a.a.b);
          goto _L4
        JSONException jsonexception;
        jsonexception;
        (new StringBuilder("JSONException in addThrowableToVector: ")).append(jsonexception.getClass().getName());
          goto _L5
        Exception exception2;
        exception2;
        (new StringBuilder("Exception in addThrowableToVector: ")).append(exception2.getClass().getName());
          goto _L5
    }

    public final void b()
    {
        h.b();
        if (w)
        {
            break MISSING_BLOCK_LABEL_44;
        }
        if (getOptOutStatus())
        {
            return;
        }
        try
        {
            e();
            g();
            i();
            k();
            return;
        }
        catch (Exception exception) { }
    }

    public final void d()
    {
        p = true;
    }

    public final boolean e()
    {
        boolean flag;
        boolean flag1;
        FutureTask futuretask;
        ExecutorService executorservice;
        flag = true;
        flag1 = false;
        futuretask = new FutureTask(new h(this));
        executorservice = Executors.newFixedThreadPool(10);
        executorservice.execute(futuretask);
        boolean flag2 = false;
_L2:
        boolean flag3;
        if (futuretask.isDone())
        {
            break; /* Loop/switch isn't completed */
        }
        flag3 = ((Boolean)futuretask.get(2500L, TimeUnit.MILLISECONDS)).booleanValue();
        flag2 = flag3;
        if (true) goto _L2; else goto _L1
_L1:
        flag = flag2;
_L3:
        Exception exception;
        StringBuilder stringbuilder;
        String s1;
        TimeoutException timeoutexception;
        if (flag1)
        {
            try
            {
                c.h();
                h.f();
            }
            catch (Exception exception1)
            {
                (new StringBuilder("Exception in startAppLoadsThreads when attempting to flush pending appLoads: ")).append(exception1.getClass().getName());
            }
        }
        stringbuilder = new StringBuilder("sentAppLoads = ");
        if (flag)
        {
            s1 = "TRUE";
        } else
        {
            s1 = "FALSE";
        }
        stringbuilder.append(s1);
        return flag;
        timeoutexception;
        flag1 = flag;
          goto _L3
        exception;
        flag = false;
        flag1 = false;
          goto _L3
    }

    public final boolean f()
    {
        JSONObject jsonobject;
        JSONObject jsonobject1;
        jsonobject = new JSONObject();
        jsonobject1 = new JSONObject();
        JSONObject jsonobject8 = h.c();
        jsonobject = jsonobject8;
_L6:
        JSONObject jsonobject7 = c.a(jsonobject);
        JSONObject jsonobject4 = jsonobject7;
        boolean flag3 = jsonobject4.has("success");
        boolean flag4;
        flag4 = false;
        if (!flag3)
        {
            break MISSING_BLOCK_LABEL_96;
        }
        int i1 = jsonobject4.getInt("success");
        flag4 = false;
        if (i1 != 1)
        {
            break MISSING_BLOCK_LABEL_96;
        }
        c.h();
        h.f();
        flag4 = true;
        boolean flag1 = flag4;
_L1:
        e e4;
        try
        {
            new JSONObject();
        }
        catch (Exception exception4)
        {
            return flag1;
        }
        if (!jsonobject4.has("me"))
        {
            break MISSING_BLOCK_LABEL_640;
        }
        e4 = crittercism.android.e.a(jsonobject4.getJSONObject("me"));
        Exception exception;
        Exception exception1;
        JSONObject jsonobject2;
        boolean flag;
        Exception exception2;
        JSONObject jsonobject3;
        Exception exception3;
        Exception exception5;
        Exception exception6;
        e e2;
        String s1;
        Message message;
        Bundle bundle;
        JSONObject jsonobject5;
        JSONObject jsonobject6;
        StringBuilder stringbuilder;
        String s2;
        Exception exception7;
        e e3;
        String s3;
        boolean flag2;
        Exception exception8;
        for (e e1 = e4; e1 == null; e1 = null)
        {
            break MISSING_BLOCK_LABEL_206;
        }

        e3 = e;
        s3 = null;
        if (e3 == null)
        {
            break MISSING_BLOCK_LABEL_186;
        }
        flag2 = e.b.equals("");
        s3 = null;
        if (flag2)
        {
            break MISSING_BLOCK_LABEL_186;
        }
        s3 = e.b;
        e = e1;
        if (s3 == null)
        {
            break MISSING_BLOCK_LABEL_206;
        }
        e.b = s3;
_L2:
        if (!jsonobject4.has("app_settings"))
        {
            break MISSING_BLOCK_LABEL_316;
        }
        jsonobject5 = jsonobject4.getJSONObject("app_settings");
        if (!jsonobject5.has("settings"))
        {
            break MISSING_BLOCK_LABEL_316;
        }
        jsonobject6 = jsonobject5.getJSONObject("settings");
        if (!jsonobject6.has("need_pkg"))
        {
            break MISSING_BLOCK_LABEL_316;
        }
        stringbuilder = new StringBuilder("settings need_pkg = ");
        if (jsonobject6.getInt("need_pkg") == 1)
        {
            s2 = "true";
        } else
        {
            s2 = "false";
        }
        stringbuilder.append(s2);
        if (jsonobject6.getInt("need_pkg") == 1)
        {
            c.g();
        }
_L3:
        if (e == null)
        {
            break MISSING_BLOCK_LABEL_435;
        }
        e2 = e;
        s1 = e2.a;
        e2.a = "";
        (new StringBuilder("pop notification: ")).append(s1);
        (new StringBuilder("username: ")).append(e.b);
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_435;
        }
        if (!s1.equals(""))
        {
            message = Message.obtain(y);
            bundle = new Bundle();
            bundle.putString("notification", s1);
            message.setData(bundle);
            message.sendToTarget();
        }
        return flag1;
        exception1;
        jsonobject2 = jsonobject1;
        flag = false;
        exception2 = exception1;
_L4:
        (new StringBuilder("Exception obtaining or handling response object or clearing pending app loads vector in attemptToSendAppLoads ")).append(exception2.getClass().getName());
        jsonobject3 = jsonobject2;
        flag1 = flag;
        jsonobject4 = jsonobject3;
          goto _L1
        exception3;
        (new StringBuilder("Exception getting user object in handleAppLoadResponse: ")).append(exception3.getClass().getName());
        break MISSING_BLOCK_LABEL_640;
        exception7;
        (new StringBuilder("Exception setting user object in handleAppLoadResponse: ")).append(exception7.getClass().getName());
          goto _L2
        exception5;
        (new StringBuilder("Exception setting app settings in handleAppLoadResponse: ")).append(exception5.getClass().getName());
          goto _L3
        exception6;
        (new StringBuilder("Exception with user pop notification! ")).append(exception6.getClass().getName());
        return flag1;
        exception8;
        jsonobject2 = jsonobject4;
        exception2 = exception8;
        flag = false;
          goto _L4
        exception2;
        jsonobject2 = jsonobject4;
        flag = true;
          goto _L4
        exception;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final boolean g()
    {
        boolean flag;
        boolean flag1;
        FutureTask futuretask;
        ExecutorService executorservice;
        flag = true;
        flag1 = false;
        futuretask = new FutureTask(new com.crittercism.app.i(this));
        executorservice = Executors.newFixedThreadPool(10);
        executorservice.execute(futuretask);
        boolean flag2 = false;
_L2:
        boolean flag3;
        if (futuretask.isDone())
        {
            break; /* Loop/switch isn't completed */
        }
        flag3 = ((Boolean)futuretask.get(2500L, TimeUnit.MILLISECONDS)).booleanValue();
        flag2 = flag3;
        if (true) goto _L2; else goto _L1
_L1:
        flag = flag2;
_L3:
        Exception exception;
        StringBuilder stringbuilder;
        String s1;
        TimeoutException timeoutexception;
        if (flag1)
        {
            try
            {
                i.f();
            }
            catch (Exception exception1)
            {
                (new StringBuilder("Exception in startCrashSendingThreads when attempting to flush pending crashes: ")).append(exception1.getClass().getName());
            }
        }
        stringbuilder = new StringBuilder("sentCrashes = ");
        if (flag)
        {
            s1 = "TRUE";
        } else
        {
            s1 = "FALSE";
        }
        stringbuilder.append(s1);
        return flag;
        timeoutexception;
        flag1 = flag;
          goto _L3
        exception;
        flag = false;
        flag1 = false;
          goto _L3
    }

    public final boolean h()
    {
        boolean flag;
        JSONObject jsonobject;
        flag = true;
        jsonobject = new JSONObject();
        new JSONObject();
        JSONObject jsonobject2 = i.b();
        jsonobject = jsonobject2;
_L4:
        int i1;
        JSONObject jsonobject1 = c.a(jsonobject);
        if (!jsonobject1.has("success"))
        {
            break MISSING_BLOCK_LABEL_118;
        }
        i1 = jsonobject1.getInt("success");
        if (i1 != flag)
        {
            break MISSING_BLOCK_LABEL_118;
        }
        i.f();
        return flag;
        Exception exception1;
        exception1;
        Exception exception2;
        flag = false;
        exception2 = exception1;
_L2:
        (new StringBuilder("Exception obtaining or handling response object or clearing pending crashes vector in attemptToSendCrashes ")).append(exception2.getClass().getName());
        return flag;
        exception2;
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        if (true) goto _L4; else goto _L3
_L3:
        return false;
    }

    public final boolean i()
    {
        boolean flag;
        boolean flag1;
        FutureTask futuretask;
        ExecutorService executorservice;
        flag = true;
        flag1 = false;
        futuretask = new FutureTask(new com.crittercism.app.k(this));
        executorservice = Executors.newFixedThreadPool(10);
        executorservice.execute(futuretask);
        boolean flag2 = false;
_L2:
        boolean flag3;
        if (futuretask.isDone())
        {
            break; /* Loop/switch isn't completed */
        }
        flag3 = ((Boolean)futuretask.get(2500L, TimeUnit.MILLISECONDS)).booleanValue();
        flag2 = flag3;
        if (true) goto _L2; else goto _L1
_L1:
        flag = flag2;
_L3:
        Exception exception;
        StringBuilder stringbuilder;
        String s1;
        TimeoutException timeoutexception;
        if (flag1)
        {
            try
            {
                j.a(j.e().size());
                j.f();
            }
            catch (Exception exception1)
            {
                (new StringBuilder("Exception in startExceptionSendingThreads when attempting to flush pending exceptions: ")).append(exception1.getClass().getName());
            }
        }
        stringbuilder = new StringBuilder("sentExceptions = ");
        if (flag)
        {
            s1 = "TRUE";
        } else
        {
            s1 = "FALSE";
        }
        stringbuilder.append(s1);
        return flag;
        timeoutexception;
        flag1 = flag;
          goto _L3
        exception;
        flag = false;
        flag1 = false;
          goto _L3
    }

    public final boolean j()
    {
        JSONObject jsonobject;
        jsonobject = new JSONObject();
        new JSONObject();
        JSONObject jsonobject2 = j.b();
        jsonobject = jsonobject2;
_L3:
        int i1;
        JSONObject jsonobject1 = c.a(jsonobject);
        if (!jsonobject1.has("success"))
        {
            break MISSING_BLOCK_LABEL_164;
        }
        i1 = jsonobject1.getInt("success");
        Exception exception2;
        boolean flag;
        if (i1 != 1)
        {
            break MISSING_BLOCK_LABEL_164;
        }
        Exception exception1;
        try
        {
            j.a(j.e().size());
            j.f();
        }
        catch (Exception exception3)
        {
            flag = true;
            exception2 = exception3;
            continue; /* Loop/switch isn't completed */
        }
        flag = true;
_L4:
        j.g = new Date();
        return flag;
        exception1;
        exception2 = exception1;
        flag = false;
_L2:
        (new StringBuilder("Exception obtaining or handling response object or clearing pending exceptions vector in attemptToSendHandledExceptions ")).append(exception2.getClass().getName());
        return flag;
        exception2;
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
          goto _L3
        flag = false;
          goto _L4
    }

    public final boolean k()
    {
        boolean flag;
        boolean flag1;
        FutureTask futuretask;
        ExecutorService executorservice;
        flag = true;
        flag1 = false;
        futuretask = new FutureTask(new com.crittercism.app.b(this));
        executorservice = Executors.newFixedThreadPool(10);
        executorservice.execute(futuretask);
        boolean flag2 = false;
_L2:
        boolean flag3;
        if (futuretask.isDone())
        {
            break; /* Loop/switch isn't completed */
        }
        flag3 = ((Boolean)futuretask.get(8000L, TimeUnit.MILLISECONDS)).booleanValue();
        flag2 = flag3;
        if (true) goto _L2; else goto _L1
_L1:
        flag = flag2;
_L3:
        Exception exception;
        StringBuilder stringbuilder;
        String s1;
        TimeoutException timeoutexception;
        if (flag1)
        {
            try
            {
                k.b();
                k.f();
            }
            catch (Exception exception1)
            {
                (new StringBuilder("Exception in startNdkSendingThreads when attempting to flush pending ndk crashes: ")).append(exception1.getClass().getName());
            }
        }
        stringbuilder = new StringBuilder("sentNdkCrashes = ");
        if (flag)
        {
            s1 = "TRUE";
        } else
        {
            s1 = "FALSE";
        }
        stringbuilder.append(s1);
        return flag;
        timeoutexception;
        flag1 = flag;
          goto _L3
        exception;
        flag = false;
        flag1 = false;
          goto _L3
    }

    public final boolean l()
    {
        int i1;
        JSONObject jsonobject;
        i1 = 1;
        jsonobject = new JSONObject();
        new JSONObject();
        JSONObject jsonobject2 = k.a();
        jsonobject = jsonobject2;
_L3:
        int j1;
        JSONObject jsonobject1 = c.a(jsonobject);
        if (!jsonobject1.has("success"))
        {
            break MISSING_BLOCK_LABEL_154;
        }
        j1 = jsonobject1.getInt("success");
        if (j1 != i1)
        {
            break MISSING_BLOCK_LABEL_154;
        }
_L4:
        boolean flag = i1;
_L2:
        Exception exception1;
        if (flag)
        {
            try
            {
                k.b();
                k.f();
            }
            catch (Exception exception2)
            {
                (new StringBuilder("Exception removing ndk dump files from disk in attemptToSendNdkCrashes: ")).append(exception2.getClass().getName());
                return flag;
            }
        }
        return flag;
        exception1;
        (new StringBuilder("Exception obtaining or handling response object or clearing pending ndk filenames vector in attemptToSendNdkCrashes: ")).append(exception1.getClass().getName());
        flag = false;
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
          goto _L3
        i1 = 0;
          goto _L4
    }

    public final String m()
    {
        if (c == null)
        {
            Log.w("Crittercism", "Failed to get app id.  Please contact us at support@crittercism.com.");
            return new String();
        } else
        {
            return c.a();
        }
    }

    public final Context n()
    {
        return g;
    }

    public final b o()
    {
        return c;
    }

    public final d p()
    {
        return d;
    }

    public final i q()
    {
        return h;
    }

    public final m r()
    {
        return i;
    }

    public final k s()
    {
        return j;
    }

    public final l t()
    {
        return k;
    }

    public final String u()
    {
        try
        {
            if (t == null || t.equals(""))
            {
                t = g.getPackageName();
            }
        }
        catch (Exception exception)
        {
            Log.w("Crittercism", "Call to getPackageName() failed.  Please contact us at support@crittercism.com.");
            t = new String();
        }
        return t;
    }

    public final String v()
    {
        return v;
    }

    public final String w()
    {
        if (g == null)
        {
            return null;
        } else
        {
            d _tmp = d;
            return crittercism.android.d.a(g, "com.crittercism.prefs.did");
        }
    }

    public final int y()
    {
        float f1;
        try
        {
            f1 = c.f();
        }
        catch (Exception exception)
        {
            return -1;
        }
        return (int)((f1 * 10F) / 160F);
    }

}
