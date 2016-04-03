// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.tracing;


public final class  extends Enum
{

    public static final a CPU;
    public static final a MEMORY;
    private static final a a[];

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/newrelic/agent/android/tracing/Sample$SampleType, s);
    }

    public static [] values()
    {
        return ([])a.clone();
    }

    static 
    {
        MEMORY = new <init>("MEMORY", 0);
        CPU = new <init>("CPU", 1);
        e_3B_.clone aclone[] = new <init>[2];
        aclone[0] = MEMORY;
        aclone[1] = CPU;
        a = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
