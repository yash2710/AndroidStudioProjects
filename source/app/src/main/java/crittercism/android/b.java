// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package crittercism.android;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Debug;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.crittercism.app.Crittercism;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package crittercism.android:
//            d, c, n, f, 
//            h, a

public class b
{

    private final String a = crittercism.android.d.b();
    private c b;
    private Context c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private int i;
    private boolean j;
    private String k;
    private JSONObject l;
    private Object m;

    public b(Context context, String s, String s1, String s2, int i1, String s3)
    {
        b = null;
        c = null;
        d = "";
        e = "";
        f = "";
        g = "";
        h = null;
        i = 0;
        j = false;
        k = "";
        l = new JSONObject();
        m = new Object();
        c = context;
        b = new c(a, c);
        d = s;
        e = null;
        f = s1;
        g = s2;
        i = i1;
        h = s3;
    }

    private static String b(String s)
    {
        if (s != null && !s.equals(""))
        {
            try
            {
                s = new String((new BigInteger(1, MessageDigest.getInstance("SHA-256").digest(s.getBytes()))).toString(16));
            }
            catch (NoSuchAlgorithmException nosuchalgorithmexception)
            {
                s = null;
            }
        }
        if (s.equals(""))
        {
            return null;
        } else
        {
            return s;
        }
    }

    private int c(String s)
    {
        int i1;
        try
        {
            i1 = c.getPackageManager().checkPermission(s, c.getPackageName());
        }
        catch (Exception exception)
        {
            return -1;
        }
        return i1;
    }

    private double j()
    {
        double d1 = 1.0D;
        int i1;
        int j1;
        double d2;
        try
        {
            Intent intent = c.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            i1 = intent.getIntExtra("level", -1);
            j1 = intent.getIntExtra("scale", -1);
        }
        catch (Exception exception)
        {
            return d1;
        }
        d2 = j1;
        if (i1 >= 0 && d2 > 0.0D)
        {
            d1 = (double)i1 / d2;
        }
        return d1;
    }

    private static long k()
    {
        int i1 = -1;
        int j1;
        int k1;
        android.os.Debug.MemoryInfo memoryinfo = new android.os.Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryinfo);
        j1 = memoryinfo.dalvikPss + memoryinfo.nativePss;
        k1 = memoryinfo.otherPss;
        i1 = k1 + j1 << 10;
_L2:
        return (long)i1;
        Exception exception;
        exception;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private String l()
    {
        String s;
        try
        {
            s = ((TelephonyManager)c.getSystemService("phone")).getNetworkOperatorName();
        }
        catch (Exception exception)
        {
            return Build.BRAND;
        }
        return s;
    }

    private JSONObject m()
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            NetworkInfo networkinfo = ((ConnectivityManager)c.getSystemService("connectivity")).getNetworkInfo(1);
            jsonobject.put("available", networkinfo.isAvailable());
            jsonobject.put("connected", networkinfo.isConnected());
            if (!networkinfo.isConnected())
            {
                jsonobject.put("connecting", networkinfo.isConnectedOrConnecting());
            }
            jsonobject.put("failover", networkinfo.isFailover());
        }
        catch (Exception exception)
        {
            crittercism/android/b.getCanonicalName();
            exception.toString();
            return jsonobject;
        }
        return jsonobject;
    }

    private JSONObject n()
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            NetworkInfo networkinfo = ((ConnectivityManager)c.getSystemService("connectivity")).getNetworkInfo(0);
            jsonobject.put("available", networkinfo.isAvailable());
            jsonobject.put("connected", networkinfo.isConnected());
            if (!networkinfo.isConnected())
            {
                jsonobject.put("connecting", networkinfo.isConnectedOrConnecting());
            }
            jsonobject.put("failover", networkinfo.isFailover());
            jsonobject.put("roaming", networkinfo.isRoaming());
        }
        catch (Exception exception)
        {
            crittercism/android/b.getCanonicalName();
            (new StringBuilder()).append(exception.toString()).append(" in getMobileNetworkStatus");
            return jsonobject;
        }
        return jsonobject;
    }

    private String o()
    {
        String s;
        if (c("android.permission.GET_TASKS") != 0)
        {
            break MISSING_BLOCK_LABEL_86;
        }
        List list = ((ActivityManager)c.getSystemService("activity")).getRunningTasks(1);
        (new StringBuilder("CURRENT Activity ::")).append(((android.app.ActivityManager.RunningTaskInfo)list.get(0)).topActivity.getClassName());
        s = ((android.app.ActivityManager.RunningTaskInfo)list.get(0)).topActivity.flattenToShortString().replace("/", "");
        return s;
        Exception exception;
        exception;
        return "";
    }

    private JSONArray p()
    {
        JSONArray jsonarray;
        StringBuilder stringbuilder;
        jsonarray = new JSONArray();
        stringbuilder = new StringBuilder();
        Object obj = m;
        obj;
        JVM INSTR monitorenter ;
        ExecutorService executorservice;
        n n1;
        Future future;
        executorservice = Executors.newCachedThreadPool();
        n1 = new n(m, Thread.currentThread());
        future = executorservice.submit(n1);
        if (crittercism.android.n.a()) goto _L2; else goto _L1
_L1:
        StringBuilder stringbuilder1 = (StringBuilder)future.get(5L, TimeUnit.SECONDS);
_L5:
        future.cancel(true);
        executorservice.shutdownNow();
_L3:
        obj;
        JVM INSTR monitorexit ;
        if (stringbuilder1.toString().length() > 0)
        {
            String as[] = stringbuilder1.toString().split("\n");
            for (int k1 = 0; k1 < as.length; k1++)
            {
                jsonarray.put(as[k1]);
            }

        }
        break MISSING_BLOCK_LABEL_217;
        Exception exception2;
        exception2;
        crittercism.android.n.b();
        n1.c();
        Exception exception;
        Exception exception1;
        try
        {
            Thread.sleep(200L);
        }
        catch (InterruptedException interruptedexception) { }
        future.cancel(true);
        executorservice.shutdownNow();
        stringbuilder1 = stringbuilder;
          goto _L3
        exception1;
        future.cancel(true);
        executorservice.shutdownNow();
        throw exception1;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        int i1 = jsonarray.length();
        JSONArray jsonarray1;
        if (i1 > 100)
        {
            jsonarray1 = new JSONArray();
            int j1 = i1 - 100;
            while (j1 < i1) 
            {
                try
                {
                    jsonarray1.put(jsonarray.getString(j1));
                }
                catch (JSONException jsonexception)
                {
                    (new StringBuilder("Caught exception in second try-catch of getLogCat():")).append(jsonexception.getClass().getName());
                }
                j1++;
            }
        } else
        {
            jsonarray1 = jsonarray;
        }
        return jsonarray1;
_L2:
        stringbuilder1 = stringbuilder;
        if (true) goto _L5; else goto _L4
_L4:
    }

    private float q()
    {
        float f1;
        try
        {
            f1 = c.getResources().getDisplayMetrics().ydpi;
        }
        catch (Exception exception)
        {
            return 0.0F;
        }
        return f1;
    }

    private boolean r()
    {
        int i1 = 30;
_L1:
        if (j || i1 <= 0)
        {
            break MISSING_BLOCK_LABEL_27;
        }
        i1--;
        Thread.sleep(1000L);
          goto _L1
        Exception exception;
        exception;
        return j;
    }

    public final String a()
    {
        return d;
    }

    public final JSONObject a(JSONObject jsonobject)
    {
        String s;
        JSONObject jsonobject1;
        JSONObject jsonobject2;
        s = new String();
        jsonobject1 = new JSONObject();
        jsonobject2 = new JSONObject();
        new String();
        JSONObject jsonobject6;
        jsonobject2.put("success", 0);
        s = jsonobject.getString("requestUrl");
        jsonobject6 = jsonobject.getJSONObject("requestData");
        JSONObject jsonobject3;
        String s1;
        jsonobject3 = jsonobject6;
        s1 = s;
_L2:
        String as[];
        JSONArray jsonarray;
        if (!jsonobject3.has("filenames") || !jsonobject3.has("filename_prefix"))
        {
            break; /* Loop/switch isn't completed */
        }
        new JSONArray();
        jsonarray = jsonobject3.getJSONArray("filenames");
        as = new String[jsonarray.length()];
        String s2;
        String s3;
        JSONObject jsonobject5;
        String s4;
        String s6;
        int i1 = 0;
        do
        {
            Exception exception;
            StringBuilder stringbuilder;
            StringBuilder stringbuilder1;
            JSONException jsonexception;
            try
            {
                if (i1 >= jsonarray.length())
                {
                    break;
                }
                as[i1] = jsonarray.getString(i1);
            }
            catch (Exception exception1)
            {
                exception1.printStackTrace();
                return jsonobject2;
            }
            i1++;
        } while (true);
        break MISSING_BLOCK_LABEL_180;
        jsonexception;
        jsonexception.printStackTrace();
        jsonobject3 = jsonobject1;
        s1 = s;
        continue; /* Loop/switch isn't completed */
        exception;
        exception.printStackTrace();
        jsonobject3 = jsonobject1;
        s1 = s;
        if (true) goto _L2; else goto _L1
        s2 = jsonobject3.getString("filename_prefix");
        jsonobject3.remove("filenames");
        jsonobject3.remove("filename_prefix");
_L12:
        if (as == null || s2 == null) goto _L4; else goto _L3
_L3:
        String s5;
        try
        {
            s3 = b.a(s1, jsonobject3, as, s2);
        }
        catch (f f1)
        {
            throw f1;
        }
        catch (IOException ioexception)
        {
            throw ioexception;
        }
        catch (Exception exception2)
        {
            (new StringBuilder("Exception in postJsonDataNew: ")).append(exception2.getClass().getName());
            JSONObject jsonobject4 = new JSONObject();
            try
            {
                jsonobject4.put("success", 0);
            }
            catch (Exception exception3)
            {
                return jsonobject4;
            }
            return jsonobject4;
        }
        if (s3 == null)
        {
            break MISSING_BLOCK_LABEL_462;
        }
        if (s3.equals(""))
        {
            break MISSING_BLOCK_LABEL_462;
        }
        JVM INSTR new #60  <Class JSONObject>;
        jsonobject5 = JSONObjectInstrumentation.init(s3);
        stringbuilder = (new StringBuilder("POSTING JSON DATA: url = ")).append(s1).append("\tdata = ");
        if (jsonobject3 instanceof JSONObject) goto _L6; else goto _L5
_L5:
        s4 = jsonobject3.toString();
_L9:
        stringbuilder.append(s4);
        stringbuilder1 = new StringBuilder("POSTING JSON DATA: response = ");
        if (jsonobject5 instanceof JSONObject) goto _L8; else goto _L7
_L7:
        s6 = jsonobject5.toString();
_L10:
        stringbuilder1.append(s6);
        return jsonobject5;
_L4:
        s3 = b.a(s1, jsonobject3);
        break MISSING_BLOCK_LABEL_235;
_L6:
        s4 = JSONObjectInstrumentation.toString((JSONObject)jsonobject3);
          goto _L9
_L8:
        s5 = JSONObjectInstrumentation.toString((JSONObject)jsonobject5);
        s6 = s5;
          goto _L10
        return jsonobject2;
_L1:
        s2 = null;
        as = null;
        if (true) goto _L12; else goto _L11
_L11:
    }

    public final transient JSONObject a(boolean aflag[])
    {
        JSONObject jsonobject2;
        int i1 = 1;
        int j1;
        boolean flag;
        JSONObject jsonobject;
        Exception exception;
        Exception exception1;
        JSONObject jsonobject1;
        Exception exception2;
        ActivityManager activitymanager;
        android.app.ActivityManager.MemoryInfo memoryinfo;
        boolean flag1;
        int k1;
        int l1;
        Display display;
        JSONArray jsonarray;
        int i2;
        Exception exception3;
        JSONArray jsonarray1;
        int j2;
        Exception exception4;
        if (aflag.length > 0)
        {
            j1 = aflag[0];
        } else
        {
            j1 = i1;
        }
        if (aflag.length > i1)
        {
            flag = aflag[i1];
        } else
        {
            flag = false;
        }
        jsonobject = new JSONObject();
        jsonobject2 = d();
        jsonobject2.put("battery_level", j());
        jsonobject2.put("memory_usage", k());
        activitymanager = (ActivityManager)c.getSystemService("activity");
        memoryinfo = new android.app.ActivityManager.MemoryInfo();
        activitymanager.getMemoryInfo(memoryinfo);
        flag1 = memoryinfo.lowMemory;
        k1 = 0;
        if (flag1)
        {
            k1 = i1;
        }
        jsonobject2.put("low_memory", k1);
        if (c("android.permission.ACCESS_NETWORK_STATE") == 0)
        {
            jsonobject2.put("wifi", m());
            jsonobject2.put("mobile_network", n());
        }
        jsonobject2.put("disk_space_free", crittercism.android.h.a().toString());
        jsonobject2.put("disk_space_total", crittercism.android.h.b().toString());
        jsonobject2.put("sd_space_free", crittercism.android.h.c().toString());
        jsonobject2.put("sd_space_total", crittercism.android.h.d().toString());
        l1 = c.getResources().getConfiguration().orientation;
        if (l1 != 0) goto _L2; else goto _L1
_L1:
        display = ((WindowManager)c.getSystemService("window")).getDefaultDisplay();
        if (display.getWidth() != display.getHeight()) goto _L4; else goto _L3
_L3:
        i1 = 3;
_L14:
        jsonobject2.put("orientation", i1);
        jsonobject2.put("activity", o());
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_312;
        }
        jsonobject2.put("metadata", l);
        if (j1 == 0) goto _L6; else goto _L5
_L5:
        if (c("android.permission.READ_LOGS") != 0) goto _L8; else goto _L7
_L7:
        jsonarray1 = p();
        j2 = jsonarray1.length();
        if (j2 <= 0)
        {
            break; /* Loop/switch isn't completed */
        }
        jsonobject2.put("logcat", jsonarray1);
        return jsonobject2;
_L4:
        if (display.getWidth() > display.getHeight())
        {
            i1 = 2;
        }
        continue; /* Loop/switch isn't completed */
        exception4;
        try
        {
            (new StringBuilder("put logcat EXCEPTION: ")).append(exception4.getClass().getName());
            break; /* Loop/switch isn't completed */
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception2)
        {
            exception1 = exception2;
            jsonobject1 = jsonobject2;
        }
          goto _L9
_L8:
        if (android.os.Build.VERSION.SDK_INT < 16 || !Crittercism.x()) goto _L6; else goto _L10
_L10:
        jsonarray = p();
        i2 = jsonarray.length();
        if (i2 <= 0) goto _L6; else goto _L11
_L11:
        jsonobject2.put("logcat", jsonarray);
        return jsonobject2;
        exception3;
        (new StringBuilder("put logcat EXCEPTION: ")).append(exception3.getClass().getName());
_L6:
        return jsonobject2;
        exception;
        exception1 = exception;
        jsonobject1 = jsonobject;
_L12:
        (new StringBuilder("Exception with getStateInfo(): ")).append(exception1.getClass().getName());
        return jsonobject1;
_L9:
        if (true) goto _L12; else goto _L2
_L2:
        i1 = l1;
        if (true) goto _L14; else goto _L13
_L13:
        return jsonobject2;
    }

    public final void a(String s)
    {
        e = s;
    }

    public final String b()
    {
        String s;
        s = android.provider.Settings.Secure.getString(c.getContentResolver(), "android_id");
        if (s == null || s.equals("") || "9774d56d682e549c".equals(s))
        {
            break MISSING_BLOCK_LABEL_143;
        }
        UUID uuid = UUID.nameUUIDFromBytes(s.getBytes("utf8"));
        if (uuid == null) goto _L2; else goto _L1
_L1:
        String s2 = uuid.toString();
        String s1 = s2;
_L3:
        if (s1 != null && s1.equals(""))
        {
            s1 = null;
        }
_L4:
        if (s1 == null && c.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", Crittercism.a().u()) == 0)
        {
            s1 = b(((TelephonyManager)c.getSystemService("phone")).getDeviceId());
        }
        if (s1 == null)
        {
            s1 = UUID.randomUUID().toString();
        }
        return s1;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
_L2:
        s1 = null;
          goto _L3
        s1 = null;
          goto _L4
    }

    public final boolean b(JSONObject jsonobject)
    {
        boolean flag = true;
        this;
        JVM INSTR monitorenter ;
        if (!jsonobject.has("username")) goto _L2; else goto _L1
_L1:
        k = jsonobject.getString("username");
_L4:
        int i1;
        if (!r())
        {
            break MISSING_BLOCK_LABEL_178;
        }
        JSONObject jsonobject1 = new JSONObject();
        new JSONObject();
        JSONObject jsonobject2 = c();
        jsonobject2.put("metadata", jsonobject);
        jsonobject1.put("requestUrl", a.c.e);
        jsonobject1.put("requestData", jsonobject2);
        JSONObject jsonobject3 = a(jsonobject1);
        if (!jsonobject3.has("success"))
        {
            break MISSING_BLOCK_LABEL_178;
        }
        i1 = jsonobject3.getInt("success");
        if (i1 != flag)
        {
            break MISSING_BLOCK_LABEL_178;
        }
_L5:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        if (k.equals("")) goto _L4; else goto _L3
_L3:
        jsonobject.put("username", k);
          goto _L4
        f f1;
        f1;
        throw f1;
        Exception exception1;
        exception1;
        this;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        crittercism/android/b.getCanonicalName();
        exception.toString();
        flag = false;
          goto _L5
    }

    public final JSONObject c()
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("app_id", d);
            if (e == null)
            {
                e = b();
            }
            jsonobject.put("hashed_device_id", e);
            jsonobject.put("device_name", "android");
            jsonobject.put("library_version", f);
        }
        catch (JSONException jsonexception)
        {
            return jsonobject;
        }
        catch (Exception exception)
        {
            (new StringBuilder("Exception in getRequiredParams(): ")).append(exception.getClass().getName());
            return jsonobject;
        }
        return jsonobject;
    }

    public final void c(JSONObject jsonobject)
    {
        l = jsonobject;
    }

    public final JSONObject d()
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("arch", System.getProperty("os.arch"));
            jsonobject.put("locale", crittercism.android.a.b(c));
            jsonobject.put("dpi", e());
            jsonobject.put("xdpi", f());
            jsonobject.put("ydpi", q());
            jsonobject.put("name", "");
            jsonobject.put("system", "android");
            jsonobject.put("model", Build.MODEL);
            jsonobject.put("carrier", l());
            jsonobject.put("app_version", g);
            jsonobject.put("system_version", android.os.Build.VERSION.RELEASE);
            jsonobject.put("app_version_code", i);
            if (h != null && h.length() > 0)
            {
                jsonobject.put("app_version", h);
            }
        }
        catch (Exception exception)
        {
            return new JSONObject();
        }
        return jsonobject;
    }

    public final float e()
    {
        float f1;
        try
        {
            f1 = c.getResources().getDisplayMetrics().density;
        }
        catch (Exception exception)
        {
            return 1.0F;
        }
        return f1;
    }

    public final float f()
    {
        float f1;
        try
        {
            f1 = c.getResources().getDisplayMetrics().xdpi;
        }
        catch (Exception exception)
        {
            return 0.0F;
        }
        return f1;
    }

    public final String g()
    {
        String s;
        new JSONObject();
        new JSONObject();
        s = new String();
        JSONObject jsonobject = c();
        jsonobject.put("pkg", c.getPackageName());
        s = b.a(a.c.f, jsonobject);
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_235;
        }
        if (s.equals(""))
        {
            break MISSING_BLOCK_LABEL_235;
        }
        JVM INSTR new #60  <Class JSONObject>;
        JSONObject jsonobject1 = JSONObjectInstrumentation.init(s);
        if (!jsonobject1.has("success"))
        {
            break MISSING_BLOCK_LABEL_173;
        }
        if (jsonobject1.getInt("success") != 1)
        {
            break MISSING_BLOCK_LABEL_235;
        }
        (new StringBuilder("app_id: ")).append(jsonobject1.getString("app_id"));
        (new StringBuilder("package name: ")).append(jsonobject1.getString("pkg"));
        (new StringBuilder("updated settings: ")).append(jsonobject1.getJSONObject("updated_settings"));
        return s;
        (new StringBuilder()).append(a.c.f).append(" response: ").append(s);
        return s;
        f f1;
        f1;
        return s;
        Exception exception;
        exception;
        (new StringBuilder("sendPackageName: Exception! ")).append(exception.getClass().getName());
        return s;
        JSONException jsonexception;
        jsonexception;
        return s;
        IOException ioexception;
        ioexception;
        return s;
    }

    public final void h()
    {
        j = true;
    }

    public final JSONObject i()
    {
        return l;
    }
}
