// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.cast;

import android.text.TextUtils;
import com.google.android.gms.internal.gh;

class zZ extends zZ
{

    final N zW;
    final String zZ;

    protected volatile void a(com.google.android.gms.common.api. )
    {
        a((gh));
    }

    protected void a(gh gh1)
    {
        if (TextUtils.isEmpty(zZ))
        {
            c(2001, "IllegalArgument: sessionId cannot be null or empty");
            return;
        }
        try
        {
            gh1.a(zZ, this);
            return;
        }
        catch (IllegalStateException illegalstateexception)
        {
            N(2001);
        }
    }

    ( , String s)
    {
        zW = ;
        zZ = s;
        super(null);
    }
}
