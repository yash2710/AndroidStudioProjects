// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.fk_android_batchnetworking;


public final class A extends Enum
{

    public static final a CSTATE_CACHED;
    public static final a CSTATE_DONOT_CACHE;
    public static final a CSTATE_NOT_CACHED;
    private static final a a[];

    public static A valueOf(String s)
    {
        return (A)Enum.valueOf(com/flipkart/fk_android_batchnetworking/Data$DataCacheState, s);
    }

    public static A[] values()
    {
        return (A[])a.clone();
    }

    static 
    {
        CSTATE_NOT_CACHED = new <init>("CSTATE_NOT_CACHED", 0);
        CSTATE_CACHED = new <init>("CSTATE_CACHED", 1);
        CSTATE_DONOT_CACHE = new <init>("CSTATE_DONOT_CACHE", 2);
        e_3B_.clone aclone[] = new <init>[3];
        aclone[0] = CSTATE_NOT_CACHED;
        aclone[1] = CSTATE_CACHED;
        aclone[2] = CSTATE_DONOT_CACHE;
        a = aclone;
    }

    private A(String s, int i)
    {
        super(s, i);
    }
}
