// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson.internal.bind;

import com.google.mygson.TypeAdapter;
import com.google.mygson.stream.JsonReader;
import com.google.mygson.stream.JsonToken;
import com.google.mygson.stream.JsonWriter;
import java.net.InetAddress;

final class z extends TypeAdapter
{

    z()
    {
    }

    public final volatile Object read(JsonReader jsonreader)
    {
        return read(jsonreader);
    }

    public final InetAddress read(JsonReader jsonreader)
    {
        if (jsonreader.peek() == JsonToken.NULL)
        {
            jsonreader.nextNull();
            return null;
        } else
        {
            return InetAddress.getByName(jsonreader.nextString());
        }
    }

    public final volatile void write(JsonWriter jsonwriter, Object obj)
    {
        write(jsonwriter, (InetAddress)obj);
    }

    public final void write(JsonWriter jsonwriter, InetAddress inetaddress)
    {
        String s;
        if (inetaddress == null)
        {
            s = null;
        } else
        {
            s = inetaddress.getHostAddress();
        }
        jsonwriter.value(s);
    }
}
