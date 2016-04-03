// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.ads.mediation.MediationAdRequest;
import java.util.Date;
import java.util.Set;

public final class bw
    implements MediationAdRequest
{

    private final Date d;
    private final Set f;
    private final boolean g;
    private final int ml;
    private final int nR;

    public bw(Date date, int i, Set set, boolean flag, int j)
    {
        d = date;
        ml = i;
        f = set;
        g = flag;
        nR = j;
    }

    public final Date getBirthday()
    {
        return d;
    }

    public final int getGender()
    {
        return ml;
    }

    public final Set getKeywords()
    {
        return f;
    }

    public final boolean isTesting()
    {
        return g;
    }

    public final int taggedForChildDirectedTreatment()
    {
        return nR;
    }
}
