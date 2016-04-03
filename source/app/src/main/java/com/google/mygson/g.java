// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson;

import java.lang.reflect.Type;

// Referenced classes of package com.google.mygson:
//            JsonDeserializationContext, Gson, JsonElement

final class g
    implements JsonDeserializationContext
{

    private Gson a;

    g(Gson gson)
    {
        a = gson;
        super();
    }

    public final Object deserialize(JsonElement jsonelement, Type type)
    {
        return a.fromJson(jsonelement, type);
    }
}
