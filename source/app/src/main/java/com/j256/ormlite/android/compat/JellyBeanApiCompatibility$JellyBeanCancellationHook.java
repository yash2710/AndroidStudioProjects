// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.android.compat;

import android.os.CancellationSignal;

public class Y
    implements Y
{

    private final CancellationSignal cancellationSignal = new CancellationSignal();

    public void cancel()
    {
        cancellationSignal.cancel();
    }


    public Y()
    {
    }
}
