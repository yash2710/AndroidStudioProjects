// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.io.File;

// Referenced classes of package com.crashlytics.android.internal:
//            u, v, j, J, 
//            ah, aj, ao, d, 
//            aJ, q, x, aS, 
//            aX, aP, ab, af, 
//            ag, av, c

public class D extends u
{

    private String a;
    private String b;
    private String c;
    private ao d;
    private aJ e;
    private long f;
    private av g;
    private x h;

    public D()
    {
    }

    public static D a()
    {
        return (D)v.a().a(com/crashlytics/android/internal/D);
    }

    static void a(D d1)
    {
        Context context = d1.getContext();
        j j1;
        String s;
        String s1;
        String s2;
        String s3;
        android.app.Application application;
        j1 = new j(new J(), new ah(), new aj(v.a().h(), "session_analytics.tap", "session_analytics_to_send"), (byte)0);
        s = d1.d.b();
        s1 = d1.d.g();
        s2 = d1.d.c();
        s3 = d1.d.d();
        application = v.a().d();
        if (application == null) goto _L2; else goto _L1
_L1:
        if (android.os.Build.VERSION.SDK_INT < 14) goto _L2; else goto _L3
_L3:
        d1.h = new d(application, context.getPackageName(), s, s1, s2, s3, d1.b, d1.c, j1, d1.g);
_L11:
        long l = d1.f;
        if (d1.e.a().getBoolean("analytics_launched", false)) goto _L5; else goto _L4
_L4:
        aX ax;
        boolean flag;
        boolean flag1;
        if (System.currentTimeMillis() - l < 0x36ee80L)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
          goto _L6
_L12:
        if (flag)
        {
            try
            {
                v.a().b().a("Crashlytics", "First launch");
                if (d1.h != null)
                {
                    d1.h.b();
                    d1.e.a(d1.e.b().putBoolean("analytics_launched", true));
                }
            }
            catch (Exception exception)
            {
                ab.d("Crashlytics failed to initialize session analytics.");
            }
        }
        aS.a().a(context, d1.g, d1.b, d1.c, d1.b()).c();
        ax = aS.a().b();
        if (ax == null) goto _L8; else goto _L7
_L7:
        if (ax.d.c) goto _L10; else goto _L9
_L9:
        ab.c("Disabling analytics collection based on settings flag value.");
        d1.h.a();
_L8:
        return;
_L2:
        d1.h = new x(context.getPackageName(), s, s1, s2, s3, d1.b, d1.c, j1, d1.g);
          goto _L11
_L5:
        flag = false;
          goto _L12
_L10:
        try
        {
            d1.h.a(ax.e, d1.b());
            return;
        }
        catch (Exception exception1)
        {
            v.a().b().a("Crashlytics", "Error dealing with settings", exception1);
        }
        return;
_L6:
        if (!flag1) goto _L5; else goto _L13
_L13:
        flag = true;
          goto _L12
    }

    private String b()
    {
        return ab.a(getContext(), "com.crashlytics.ApiEndpoint");
    }

    public final void a(af af1)
    {
        if (h != null)
        {
            h.a(af1.a());
        }
    }

    public final void a(ag ag1)
    {
        if (h != null)
        {
            h.b(ag1.a());
        }
    }

    protected final void c()
    {
        Context context;
        PackageInfo packageinfo;
        g = new av(v.a().b());
        e = new aJ(v.a().a(com/crashlytics/android/internal/D));
        context = getContext();
        PackageManager packagemanager = context.getPackageManager();
        d = new ao(context);
        a = context.getPackageName();
        packageinfo = packagemanager.getPackageInfo(a, 0);
        b = Integer.toString(packageinfo.versionCode);
        if (packageinfo.versionName != null) goto _L2; else goto _L1
_L1:
        String s = "0.0";
_L3:
        c = s;
        if (android.os.Build.VERSION.SDK_INT < 9)
        {
            break MISSING_BLOCK_LABEL_157;
        }
        f = packageinfo.firstInstallTime;
_L4:
        (new Thread(new c(this), "Crashlytics Initializer")).start();
        return;
_L2:
        s = packageinfo.versionName;
          goto _L3
        try
        {
            f = (new File(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir)).lastModified();
        }
        catch (Exception exception)
        {
            v.a().b().a("Crashlytics", "Error setting up app properties", exception);
        }
          goto _L4
    }

    public String getVersion()
    {
        return v.a().getVersion();
    }
}
