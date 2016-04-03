// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android;

import com.crashlytics.android.internal.q;
import com.crashlytics.android.internal.v;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.crashlytics.android:
//            B

final class z
{

    private final File a;
    private final Map b;

    public z(File file)
    {
        this(file, Collections.emptyMap());
    }

    public z(File file, Map map)
    {
        a = file;
        b = new HashMap(map);
        if (a.length() == 0L)
        {
            b.putAll(B.a);
        }
    }

    public final boolean a()
    {
        v.a().b().a("Crashlytics", (new StringBuilder("Removing report at ")).append(a.getPath()).toString());
        return a.delete();
    }

    public final String b()
    {
        return d().getName();
    }

    public final String c()
    {
        String s = b();
        return s.substring(0, s.lastIndexOf('.'));
    }

    public final File d()
    {
        return a;
    }

    public final Map e()
    {
        return Collections.unmodifiableMap(b);
    }
}
