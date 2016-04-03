// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android;

import com.crashlytics.android.internal.au;
import java.io.InputStream;

final class ac
    implements au
{

    private byte a[];
    private int b[];

    ac(byte abyte0[], int ai[])
    {
        a = abyte0;
        b = ai;
        super();
    }

    public final void a(InputStream inputstream, int i)
    {
        inputstream.read(a, b[0], i);
        int ai[] = b;
        ai[0] = i + ai[0];
        inputstream.close();
        return;
        Exception exception;
        exception;
        inputstream.close();
        throw exception;
    }
}
