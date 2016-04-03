// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.encode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing.client.android.encode:
//            b, h

final class i
    implements b
{

    i()
    {
    }

    public final String format(String s)
    {
        return h.b().matcher(h.a().matcher(s).replaceAll("\\\\$1")).replaceAll("");
    }
}
