// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.gson;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

// Referenced classes of package com.google.gson:
//            TypeAdapter, Gson

final class j extends TypeAdapter
{

    private Gson a;

    j(Gson gson)
    {
        a = gson;
        super();
    }

    public final Float read(JsonReader jsonreader)
    {
        if (jsonreader.peek() == JsonToken.NULL)
        {
            jsonreader.nextNull();
            return null;
        } else
        {
            return Float.valueOf((float)jsonreader.nextDouble());
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
            float f = number.floatValue();
            Gson.a(a, f);
            jsonwriter.value(number);
            return;
        }
    }

    public final volatile void write(JsonWriter jsonwriter, Object obj)
    {
        write(jsonwriter, (Number)obj);
    }
}
