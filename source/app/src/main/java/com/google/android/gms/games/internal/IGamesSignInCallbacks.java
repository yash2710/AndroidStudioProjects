// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import android.content.Intent;
import android.os.IInterface;
import com.google.android.gms.common.data.DataHolder;

public interface IGamesSignInCallbacks
    extends IInterface
{

    public abstract void S(DataHolder dataholder);

    public abstract void T(DataHolder dataholder);

    public abstract void b(int i, Intent intent);

    public abstract void ci(int i);

    public abstract void cj(int i);

    public abstract void g(DataHolder dataholder);
}
