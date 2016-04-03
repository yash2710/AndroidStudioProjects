// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IInterface;
import com.google.android.gms.cast.LaunchOptions;

public interface gl
    extends IInterface
{

    public abstract void a(double d, double d1, boolean flag);

    public abstract void a(String s, LaunchOptions launchoptions);

    public abstract void a(String s, String s1, long l);

    public abstract void a(String s, byte abyte0[], long l);

    public abstract void a(boolean flag, double d, boolean flag1);

    public abstract void am(String s);

    public abstract void an(String s);

    public abstract void ao(String s);

    public abstract void disconnect();

    public abstract void e(String s, boolean flag);

    public abstract void eg();

    public abstract void ep();

    public abstract void h(String s, String s1);
}
