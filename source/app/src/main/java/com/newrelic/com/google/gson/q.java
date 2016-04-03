// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.com.google.gson;

import com.newrelic.com.google.gson.stream.JsonReader;
import com.newrelic.com.google.gson.stream.JsonToken;
import com.newrelic.com.google.gson.stream.JsonWriter;

// Referenced classes of package com.newrelic.com.google.gson:
//            TypeAdapter

final class q extends TypeAdapter
{

    private TypeAdapter a;

    q(TypeAdapter typeadapter)
    {
        a = typeadapter;
        super();
    }

    public final Object read(JsonReader jsonreader)
    {
        if (jsonreader.peek() == JsonToken.NULL)
        {
            jsonreader.nextNull();
            return null;
        } else
        {
            return a.read(jsonreader);
        }
    }

    public final void write(JsonWriter jsonwriter, Object obj)
    {
        if (obj == null)
        {
            jsonwriter.nullValue();
            return;
        } else
        {
            a.write(jsonwriter, obj);
            return;
        }
    }
}
