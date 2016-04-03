// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import android.os.IInterface;

// Referenced classes of package com.google.android.gms.games.internal:
//            IGamesSignInCallbacks

public interface IGamesSignInService
    extends IInterface
{

    public abstract void a(IGamesSignInCallbacks igamessignincallbacks, String s, String s1);

    public abstract void a(IGamesSignInCallbacks igamessignincallbacks, String s, String s1, String s2);

    public abstract void a(IGamesSignInCallbacks igamessignincallbacks, String s, String s1, String as[]);

    public abstract void a(IGamesSignInCallbacks igamessignincallbacks, String s, String s1, String as[], String s2);

    public abstract void b(IGamesSignInCallbacks igamessignincallbacks, String s, String s1, String s2);

    public abstract void b(IGamesSignInCallbacks igamessignincallbacks, String s, String s1, String as[]);

    public abstract String bc(String s);

    public abstract String bd(String s);

    public abstract String f(String s, boolean flag);

    public abstract void o(String s, String s1);
}
