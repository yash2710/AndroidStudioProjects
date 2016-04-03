// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.encode;

import android.telephony.PhoneNumberUtils;

// Referenced classes of package com.google.zxing.client.android.encode:
//            b, h

final class j
    implements b
{

    j(h h)
    {
    }

    public final String format(String s)
    {
        return PhoneNumberUtils.formatNumber(s);
    }
}
