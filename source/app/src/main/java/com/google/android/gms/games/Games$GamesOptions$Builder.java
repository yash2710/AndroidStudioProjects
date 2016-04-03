// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games;


public final class <init>
{

    boolean MM;
    boolean MN;
    int MO;
    boolean MP;
    int MQ;
    String MR;

    public final <init> build()
    {
        return new <init>(this, null);
    }

    public final <init> setSdkVariant(int i)
    {
        MQ = i;
        return this;
    }

    public final MQ setShowConnectingPopup(boolean flag)
    {
        MN = flag;
        MO = 17;
        return this;
    }

    public final MO setShowConnectingPopup(boolean flag, int i)
    {
        MN = flag;
        MO = i;
        return this;
    }

    private ()
    {
        MM = false;
        MN = true;
        MO = 17;
        MP = false;
        MQ = 4368;
        MR = null;
    }

    MR(MR mr)
    {
        this();
    }
}
