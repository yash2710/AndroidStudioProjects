// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson.internal.bind;

import com.google.mygson.TypeAdapter;
import com.google.mygson.stream.JsonReader;
import com.google.mygson.stream.JsonToken;
import com.google.mygson.stream.JsonWriter;
import java.net.URL;

final class w extends TypeAdapter
{

    w()
    {
    }

    public final volatile Object read(JsonReader jsonreader)
    {
        return read(jsonreader);
    }

    public final URL read(JsonReader jsonreader)
    {
        if (jsonreader.peek() == JsonToken.NULL)
        {
            jsonreader.nextNull();
        } else
        {
            String s = jsonreader.nextString();
            if (!"null".equals(s))
            {
                return new URL(s);
            }
        }
        return null;
    }

    public final volatile void write(JsonWriter jsonwriter, Object obj)
    {
        write(jsonwriter, (URL)obj);
    }

    public final void write(JsonWriter jsonwriter, URL url)
    {
        String s;
        if (url == null)
        {
            s = null;
        } else
        {
            s = url.toExternalForm();
        }
        jsonwriter.value(s);
    }
}
