// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.cast;

import com.google.android.gms.internal.gh;

class zX extends zX
{

    final N zW;
    final String zX;

    protected volatile void a(com.google.android.gms.common.api. )
    {
        a((gh));
    }

    protected void a(gh gh1)
    {
        try
        {
            gh1.b(zX, null, this);
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
        zX = s;
        super(null);
    }
}
