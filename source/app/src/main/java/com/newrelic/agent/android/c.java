// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android;

import com.newrelic.agent.android.util.Encoder;

// Referenced classes of package com.newrelic.agent.android:
//            NullAgentImpl

final class c
    implements Encoder
{

    c(NullAgentImpl nullagentimpl)
    {
    }

    public final String encode(byte abyte0[])
    {
        return new String(abyte0);
    }
}
