// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.appstate;

import com.google.android.gms.common.api.Status;

class yJ
    implements eDeletedResult
{

    final Status yJ;
    final yJ yM;

    public int getStateKey()
    {
        return yM.;
    }

    public Status getStatus()
    {
        return yJ;
    }

    eDeletedResult(eDeletedResult edeletedresult, Status status)
    {
        yM = edeletedresult;
        yJ = status;
        super();
    }
}
