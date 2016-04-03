// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.encode;

import android.telephony.PhoneNumberUtils;

// Referenced classes of package com.google.zxing.client.android.encode:
//            b, c

final class f
    implements b
{

    f(c c1)
    {
    }

    public final String format(String s)
    {
        return c.a(PhoneNumberUtils.formatNumber(s));
    }
}
