// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IInterface;
import com.google.android.gms.cast.ApplicationMetadata;

// Referenced classes of package com.google.android.gms.internal:
//            ge, gj

public interface gm
    extends IInterface
{

    public abstract void T(int i);

    public abstract void U(int i);

    public abstract void V(int i);

    public abstract void W(int i);

    public abstract void a(ApplicationMetadata applicationmetadata, String s, String s1, boolean flag);

    public abstract void a(String s, double d, boolean flag);

    public abstract void a(String s, long l);

    public abstract void a(String s, long l, int i);

    public abstract void b(ge ge);

    public abstract void b(gj gj);

    public abstract void b(String s, byte abyte0[]);

    public abstract void g(String s, String s1);

    public abstract void onApplicationDisconnected(int i);
}
