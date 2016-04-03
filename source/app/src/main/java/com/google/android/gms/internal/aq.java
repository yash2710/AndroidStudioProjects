// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IInterface;
import com.google.android.gms.dynamic.d;

// Referenced classes of package com.google.android.gms.internal:
//            al, ap, as, dc, 
//            dg, ai

public interface aq
    extends IInterface
{

    public abstract d U();

    public abstract al V();

    public abstract void a(al al);

    public abstract void a(ap ap);

    public abstract void a(as as);

    public abstract void a(dc dc);

    public abstract void a(dg dg, String s);

    public abstract boolean a(ai ai);

    public abstract void ag();

    public abstract void destroy();

    public abstract boolean isReady();

    public abstract void pause();

    public abstract void resume();

    public abstract void showInterstitial();

    public abstract void stopLoading();
}
