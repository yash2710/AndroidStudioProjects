// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IInterface;
import com.google.android.gms.dynamic.d;

// Referenced classes of package com.google.android.gms.internal:
//            ai, bv, al

public interface bu
    extends IInterface
{

    public abstract void a(d d, ai ai, String s, bv bv);

    public abstract void a(d d, ai ai, String s, String s1, bv bv);

    public abstract void a(d d, al al, ai ai, String s, bv bv);

    public abstract void a(d d, al al, ai ai, String s, String s1, bv bv);

    public abstract void destroy();

    public abstract d getView();

    public abstract void pause();

    public abstract void resume();

    public abstract void showInterstitial();
}
