// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.util;

import android.util.Base64;

// Referenced classes of package com.newrelic.agent.android.util:
//            Encoder

public class AndroidEncoder
    implements Encoder
{

    public AndroidEncoder()
    {
    }

    public String encode(byte abyte0[])
    {
        return Base64.encodeToString(abyte0, 0);
    }
}
