// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson;


// Referenced classes of package com.google.mygson:
//            LongSerializationPolicy, JsonPrimitive, JsonElement

final class m extends LongSerializationPolicy
{

    m(String s, int i)
    {
        super(s, 0, (byte)0);
    }

    public final JsonElement serialize(Long long1)
    {
        return new JsonPrimitive(long1);
    }
}
