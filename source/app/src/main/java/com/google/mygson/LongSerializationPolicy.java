// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson;


// Referenced classes of package com.google.mygson:
//            m, n, JsonElement

public abstract class LongSerializationPolicy extends Enum
{

    public static final LongSerializationPolicy DEFAULT;
    public static final LongSerializationPolicy STRING;
    private static final LongSerializationPolicy a[];

    private LongSerializationPolicy(String s, int i)
    {
        super(s, i);
    }

    LongSerializationPolicy(String s, int i, byte byte0)
    {
        this(s, i);
    }

    public static LongSerializationPolicy valueOf(String s)
    {
        return (LongSerializationPolicy)Enum.valueOf(com/google/mygson/LongSerializationPolicy, s);
    }

    public static LongSerializationPolicy[] values()
    {
        return (LongSerializationPolicy[])a.clone();
    }

    public abstract JsonElement serialize(Long long1);

    static 
    {
        DEFAULT = new m("DEFAULT", 0);
        STRING = new n("STRING", 1);
        LongSerializationPolicy alongserializationpolicy[] = new LongSerializationPolicy[2];
        alongserializationpolicy[0] = DEFAULT;
        alongserializationpolicy[1] = STRING;
        a = alongserializationpolicy;
    }
}