// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson;

import com.google.mygson.stream.JsonReader;
import com.google.mygson.stream.JsonToken;
import com.google.mygson.stream.JsonWriter;

// Referenced classes of package com.google.mygson:
//            TypeAdapter, Gson

final class k extends TypeAdapter
{

    k(Gson gson)
    {
    }

    public final Number read(JsonReader jsonreader)
    {
        if (jsonreader.peek() == JsonToken.NULL)
        {
            jsonreader.nextNull();
            return null;
        } else
        {
            return Long.valueOf(jsonreader.nextLong());
        }
    }

    public final volatile Object read(JsonReader jsonreader)
    {
        return read(jsonreader);
    }

    public final void write(JsonWriter jsonwriter, Number number)
    {
        if (number == null)
        {
            jsonwriter.nullValue();
            return;
        } else
        {
            jsonwriter.value(number.toString());
            return;
        }
    }

    public final volatile void write(JsonWriter jsonwriter, Object obj)
    {
        write(jsonwriter, (Number)obj);
    }
}