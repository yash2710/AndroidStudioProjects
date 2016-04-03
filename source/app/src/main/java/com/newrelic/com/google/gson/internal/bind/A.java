// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.com.google.gson.internal.bind;

import com.newrelic.com.google.gson.TypeAdapter;
import com.newrelic.com.google.gson.stream.JsonReader;
import com.newrelic.com.google.gson.stream.JsonToken;
import com.newrelic.com.google.gson.stream.JsonWriter;
import java.util.UUID;

final class A extends TypeAdapter
{

    A()
    {
    }

    public final volatile Object read(JsonReader jsonreader)
    {
        return read(jsonreader);
    }

    public final UUID read(JsonReader jsonreader)
    {
        if (jsonreader.peek() == JsonToken.NULL)
        {
            jsonreader.nextNull();
            return null;
        } else
        {
            return UUID.fromString(jsonreader.nextString());
        }
    }

    public final volatile void write(JsonWriter jsonwriter, Object obj)
    {
        write(jsonwriter, (UUID)obj);
    }

    public final void write(JsonWriter jsonwriter, UUID uuid)
    {
        String s;
        if (uuid == null)
        {
            s = null;
        } else
        {
            s = uuid.toString();
        }
        jsonwriter.value(s);
    }
}
