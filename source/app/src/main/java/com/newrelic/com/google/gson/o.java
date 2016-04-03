// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.com.google.gson;

import com.newrelic.com.google.gson.internal.Streams;
import com.newrelic.com.google.gson.reflect.TypeToken;
import com.newrelic.com.google.gson.stream.JsonReader;
import com.newrelic.com.google.gson.stream.JsonWriter;

// Referenced classes of package com.newrelic.com.google.gson:
//            TypeAdapter, Gson, p, JsonElement, 
//            JsonDeserializer, JsonSerializer, TypeAdapterFactory

final class o extends TypeAdapter
{

    private final JsonSerializer a;
    private final JsonDeserializer b;
    private final Gson c;
    private final TypeToken d;
    private final TypeAdapterFactory e;
    private TypeAdapter f;

    private o(JsonSerializer jsonserializer, JsonDeserializer jsondeserializer, Gson gson, TypeToken typetoken, TypeAdapterFactory typeadapterfactory)
    {
        a = jsonserializer;
        b = jsondeserializer;
        c = gson;
        d = typetoken;
        e = typeadapterfactory;
    }

    o(JsonSerializer jsonserializer, JsonDeserializer jsondeserializer, Gson gson, TypeToken typetoken, TypeAdapterFactory typeadapterfactory, byte byte0)
    {
        this(jsonserializer, jsondeserializer, gson, typetoken, typeadapterfactory);
    }

    private TypeAdapter a()
    {
        TypeAdapter typeadapter = f;
        if (typeadapter != null)
        {
            return typeadapter;
        } else
        {
            TypeAdapter typeadapter1 = c.getDelegateAdapter(e, d);
            f = typeadapter1;
            return typeadapter1;
        }
    }

    public static TypeAdapterFactory newFactory(TypeToken typetoken, Object obj)
    {
        return new p(obj, typetoken, false, null, (byte)0);
    }

    public static TypeAdapterFactory newFactoryWithMatchRawType(TypeToken typetoken, Object obj)
    {
        boolean flag;
        if (typetoken.getType() == typetoken.getRawType())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return new p(obj, typetoken, flag, null, (byte)0);
    }

    public static TypeAdapterFactory newTypeHierarchyFactory(Class class1, Object obj)
    {
        return new p(obj, null, false, class1, (byte)0);
    }

    public final Object read(JsonReader jsonreader)
    {
        if (b == null)
        {
            return a().read(jsonreader);
        }
        JsonElement jsonelement = Streams.parse(jsonreader);
        if (jsonelement.isJsonNull())
        {
            return null;
        } else
        {
            return b.deserialize(jsonelement, d.getType(), c.a);
        }
    }

    public final void write(JsonWriter jsonwriter, Object obj)
    {
        if (a == null)
        {
            a().write(jsonwriter, obj);
            return;
        }
        if (obj == null)
        {
            jsonwriter.nullValue();
            return;
        } else
        {
            Streams.write(a.serialize(obj, d.getType(), c.b), jsonwriter);
            return;
        }
    }
}
