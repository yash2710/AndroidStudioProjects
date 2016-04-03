// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.com.google.gson.internal.bind;

import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.TypeAdapter;
import com.newrelic.com.google.gson.internal.ObjectConstructor;
import com.newrelic.com.google.gson.stream.JsonReader;
import com.newrelic.com.google.gson.stream.JsonToken;
import com.newrelic.com.google.gson.stream.JsonWriter;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.newrelic.com.google.gson.internal.bind:
//            m

final class b extends TypeAdapter
{

    private final TypeAdapter a;
    private final ObjectConstructor b;

    public b(Gson gson, Type type, TypeAdapter typeadapter, ObjectConstructor objectconstructor)
    {
        a = new m(gson, typeadapter, type);
        b = objectconstructor;
    }

    public final volatile Object read(JsonReader jsonreader)
    {
        return read(jsonreader);
    }

    public final Collection read(JsonReader jsonreader)
    {
        if (jsonreader.peek() == JsonToken.NULL)
        {
            jsonreader.nextNull();
            return null;
        }
        Collection collection = (Collection)b.construct();
        jsonreader.beginArray();
        for (; jsonreader.hasNext(); collection.add(a.read(jsonreader))) { }
        jsonreader.endArray();
        return collection;
    }

    public final volatile void write(JsonWriter jsonwriter, Object obj)
    {
        write(jsonwriter, (Collection)obj);
    }

    public final void write(JsonWriter jsonwriter, Collection collection)
    {
        if (collection == null)
        {
            jsonwriter.nullValue();
            return;
        }
        jsonwriter.beginArray();
        Object obj;
        for (Iterator iterator = collection.iterator(); iterator.hasNext(); a.write(jsonwriter, obj))
        {
            obj = iterator.next();
        }

        jsonwriter.endArray();
    }
}
