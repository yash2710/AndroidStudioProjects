// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.ParcelFileDescriptor;

// Referenced classes of package com.google.android.gms.games.internal:
//            ConnectionInfo

public interface IRoomServiceCallbacks
    extends IInterface
{

    public abstract void a(ParcelFileDescriptor parcelfiledescriptor, int i);

    public abstract void a(ConnectionInfo connectioninfo);

    public abstract void a(String s, byte abyte0[], int i);

    public abstract void a(String s, String as[]);

    public abstract void al(IBinder ibinder);

    public abstract void b(String s, String as[]);

    public abstract void bg(String s);

    public abstract void bh(String s);

    public abstract void bi(String s);

    public abstract void bj(String s);

    public abstract void bk(String s);

    public abstract void bl(String s);

    public abstract void bm(String s);

    public abstract void c(int i, int j, String s);

    public abstract void c(String s, String as[]);

    public abstract void ck(int i);

    public abstract void d(String s, String as[]);

    public abstract void e(String s, String as[]);

    public abstract void f(String s, String as[]);

    public abstract void g(String s, String as[]);

    public abstract void hJ();

    public abstract void hK();

    public abstract void onP2PConnected(String s);

    public abstract void onP2PDisconnected(String s);

    public abstract void t(String s, int i);
}
