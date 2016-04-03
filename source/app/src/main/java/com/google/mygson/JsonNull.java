// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson;


// Referenced classes of package com.google.mygson:
//            JsonElement

public final class JsonNull extends JsonElement
{

    public static final JsonNull INSTANCE = new JsonNull();

    public JsonNull()
    {
    }

    public final boolean equals(Object obj)
    {
        return this == obj || (obj instanceof JsonNull);
    }

    public final int hashCode()
    {
        return com/google/mygson/JsonNull.hashCode();
    }

}
