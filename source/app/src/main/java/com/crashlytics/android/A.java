// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android;

import android.content.Context;
import android.content.res.Resources;
import com.crashlytics.android.internal.Z;
import com.crashlytics.android.internal.ab;
import com.crashlytics.android.internal.av;
import com.crashlytics.android.internal.ax;
import com.crashlytics.android.internal.ay;
import com.crashlytics.android.internal.q;
import com.crashlytics.android.internal.r;
import com.crashlytics.android.internal.v;

// Referenced classes of package com.crashlytics.android:
//            F, y

abstract class A extends Z
{

    public A(String s, String s1, av av, ax ax)
    {
        super(s, s1, av, ax);
    }

    private static ay a(ay ay1, F f)
    {
        ay ay2;
        java.io.InputStream inputstream;
        ay2 = ay1.b("app[identifier]", f.b).b("app[name]", f.f).b("app[display_version]", f.c).b("app[build_version]", f.d).a("app[source]", Integer.valueOf(f.g)).b("app[minimum_sdk_version]", f.h).b("app[built_sdk_version]", f.i);
        if (!ab.e(f.e))
        {
            ay2.b("app[instance_identifier]", f.e);
        }
        if (f.j == null)
        {
            break MISSING_BLOCK_LABEL_178;
        }
        inputstream = null;
        inputstream = v.a().getContext().getResources().openRawResource(f.j.b);
        ay2.b("app[icon][hash]", f.j.a).a("app[icon][data]", "icon.png", "application/octet-stream", inputstream).a("app[icon][width]", Integer.valueOf(f.j.c)).a("app[icon][height]", Integer.valueOf(f.j.d));
        ab.a(inputstream, "Failed to close app icon InputStream.");
        return ay2;
        android.content.res.Resources.NotFoundException notfoundexception;
        notfoundexception;
        v.a().b().a("Crashlytics", (new StringBuilder("Failed to find app icon with resource ID: ")).append(f.j.b).toString(), notfoundexception);
        ab.a(inputstream, "Failed to close app icon InputStream.");
        return ay2;
        Exception exception;
        exception;
        ab.a(inputstream, "Failed to close app icon InputStream.");
        throw exception;
    }

    public final boolean a(F f)
    {
        ay ay1 = a(b().a("X-CRASHLYTICS-API-KEY", f.a).a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").a("X-CRASHLYTICS-API-CLIENT-VERSION", v.a().getVersion()), f);
        v.a().b().a("Crashlytics", (new StringBuilder("Sending app info to ")).append(a()).toString());
        if (f.j != null)
        {
            v.a().b().a("Crashlytics", (new StringBuilder("App icon hash is ")).append(f.j.a).toString());
            v.a().b().a("Crashlytics", (new StringBuilder("App icon size is ")).append(f.j.c).append("x").append(f.j.d).toString());
        }
        int i = ay1.b();
        String s;
        if ("POST".equals(ay1.d()))
        {
            s = "Create";
        } else
        {
            s = "Update";
        }
        v.a().b().a("Crashlytics", (new StringBuilder()).append(s).append(" app request ID: ").append(ay1.a("X-REQUEST-ID")).toString());
        v.a().b().a("Crashlytics", (new StringBuilder("Result was ")).append(i).toString());
        return r.a(i) == 0;
    }
}
