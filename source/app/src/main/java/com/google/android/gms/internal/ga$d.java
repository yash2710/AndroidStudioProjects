// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.appstate.AppStateBuffer;
import com.google.android.gms.common.api.b;
import com.google.android.gms.common.data.DataHolder;

final class taHolder extends b
    implements com.google.android.gms.appstate.ateManager.StateListResult
{

    private final AppStateBuffer yT;

    public final AppStateBuffer getStateBuffer()
    {
        return yT;
    }

    public taHolder(DataHolder dataholder)
    {
        super(dataholder);
        yT = new AppStateBuffer(dataholder);
    }
}
