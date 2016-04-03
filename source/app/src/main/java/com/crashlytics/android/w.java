// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android;

import com.crashlytics.android.internal.Z;
import com.crashlytics.android.internal.av;
import com.crashlytics.android.internal.ax;
import com.crashlytics.android.internal.ay;
import com.crashlytics.android.internal.q;
import com.crashlytics.android.internal.r;
import com.crashlytics.android.internal.v;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.crashlytics.android:
//            v, u, Crashlytics, z

final class w extends Z
    implements com.crashlytics.android.v
{

    public w(String s, String s1, av av)
    {
        super(s, s1, av, ax.b);
    }

    public final boolean a(u u1)
    {
        ay ay1 = b().a("X-CRASHLYTICS-API-KEY", u1.a).a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").a("X-CRASHLYTICS-API-CLIENT-VERSION", Crashlytics.getInstance().getVersion());
        Iterator iterator = u1.b.e().entrySet().iterator();
        ay ay2;
        for (ay2 = ay1; iterator.hasNext(); ay2 = ay2.a((java.util.Map.Entry)iterator.next())) { }
        z z1 = u1.b;
        ay ay3 = ay2.a("report[file]", z1.b(), "application/octet-stream", z1.d()).b("report[identifier]", z1.c());
        v.a().b().a("Crashlytics", (new StringBuilder("Sending report to: ")).append(a()).toString());
        int i = ay3.b();
        v.a().b().a("Crashlytics", (new StringBuilder("Create report request ID: ")).append(ay3.a("X-REQUEST-ID")).toString());
        v.a().b().a("Crashlytics", (new StringBuilder("Result was: ")).append(i).toString());
        return r.a(i) == 0;
    }
}
