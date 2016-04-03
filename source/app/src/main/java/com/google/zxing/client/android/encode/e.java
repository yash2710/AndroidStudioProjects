// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.encode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing.client.android.encode:
//            b, c

final class e
    implements b
{

    e(c c1)
    {
    }

    public final String format(String s)
    {
        if (s == null)
        {
            return null;
        } else
        {
            return c.c().matcher(s).replaceAll("");
        }
    }
}
