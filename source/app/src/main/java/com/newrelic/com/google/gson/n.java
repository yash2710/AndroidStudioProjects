// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.com.google.gson;


// Referenced classes of package com.newrelic.com.google.gson:
//            LongSerializationPolicy, JsonPrimitive, JsonElement

final class n extends LongSerializationPolicy
{

    n(String s, int i)
    {
        super(s, 1, (byte)0);
    }

    public final JsonElement serialize(Long long1)
    {
        return new JsonPrimitive(String.valueOf(long1));
    }
}
