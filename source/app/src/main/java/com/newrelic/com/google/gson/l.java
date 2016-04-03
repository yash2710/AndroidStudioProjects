// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.com.google.gson;

import com.newrelic.com.google.gson.stream.JsonReader;
import com.newrelic.com.google.gson.stream.JsonWriter;

// Referenced classes of package com.newrelic.com.google.gson:
//            TypeAdapter

final class l extends TypeAdapter
{

    private TypeAdapter a;

    l()
    {
    }

    public final Object read(JsonReader jsonreader)
    {
        if (a == null)
        {
            throw new IllegalStateException();
        } else
        {
            return a.read(jsonreader);
        }
    }

    public final void setDelegate(TypeAdapter typeadapter)
    {
        if (a != null)
        {
            throw new AssertionError();
        } else
        {
            a = typeadapter;
            return;
        }
    }

    public final void write(JsonWriter jsonwriter, Object obj)
    {
        if (a == null)
        {
            throw new IllegalStateException();
        } else
        {
            a.write(jsonwriter, obj);
            return;
        }
    }
}
