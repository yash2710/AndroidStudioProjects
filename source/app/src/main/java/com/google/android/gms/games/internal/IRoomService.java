// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.common.data.DataHolder;

// Referenced classes of package com.google.android.gms.games.internal:
//            IRoomServiceCallbacks

public interface IRoomService
    extends IInterface
{

    public abstract void G(boolean flag);

    public abstract void a(IBinder ibinder, IRoomServiceCallbacks iroomservicecallbacks);

    public abstract void a(DataHolder dataholder, boolean flag);

    public abstract void a(byte abyte0[], String s1, int i);

    public abstract void a(byte abyte0[], String as[]);

    public abstract void be(String s1);

    public abstract void bf(String s1);

    public abstract void c(String s1, String s2, String s3);

    public abstract void hF();

    public abstract void hG();

    public abstract void hH();

    public abstract void hI();

    public abstract void r(String s1, int i);

    public abstract void s(String s1, int i);
}
