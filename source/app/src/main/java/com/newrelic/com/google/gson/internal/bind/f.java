// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.com.google.gson.internal.bind;

import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonPrimitive;
import com.newrelic.com.google.gson.JsonSyntaxException;
import com.newrelic.com.google.gson.TypeAdapter;
import com.newrelic.com.google.gson.internal.JsonReaderInternalAccess;
import com.newrelic.com.google.gson.internal.ObjectConstructor;
import com.newrelic.com.google.gson.internal.Streams;
import com.newrelic.com.google.gson.stream.JsonReader;
import com.newrelic.com.google.gson.stream.JsonToken;
import com.newrelic.com.google.gson.stream.JsonWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.newrelic.com.google.gson.internal.bind:
//            m, MapTypeAdapterFactory

final class f extends TypeAdapter
{

    private final TypeAdapter a;
    private final TypeAdapter b;
    private final ObjectConstructor c;
    private MapTypeAdapterFactory d;

    public f(MapTypeAdapterFactory maptypeadapterfactory, Gson gson, Type type, TypeAdapter typeadapter, Type type1, TypeAdapter typeadapter1, ObjectConstructor objectconstructor)
    {
        d = maptypeadapterfactory;
        super();
        a = new m(gson, typeadapter, type);
        b = new m(gson, typeadapter1, type1);
        c = objectconstructor;
    }

    public final volatile Object read(JsonReader jsonreader)
    {
        return read(jsonreader);
    }

    public final Map read(JsonReader jsonreader)
    {
        JsonToken jsontoken = jsonreader.peek();
        if (jsontoken == JsonToken.NULL)
        {
            jsonreader.nextNull();
            return null;
        }
        Map map = (Map)c.construct();
        if (jsontoken == JsonToken.BEGIN_ARRAY)
        {
            jsonreader.beginArray();
            for (; jsonreader.hasNext(); jsonreader.endArray())
            {
                jsonreader.beginArray();
                Object obj1 = a.read(jsonreader);
                if (map.put(obj1, b.read(jsonreader)) != null)
                {
                    throw new JsonSyntaxException((new StringBuilder("duplicate key: ")).append(obj1).toString());
                }
            }

            jsonreader.endArray();
            return map;
        }
        jsonreader.beginObject();
        while (jsonreader.hasNext()) 
        {
            JsonReaderInternalAccess.INSTANCE.promoteNameToValue(jsonreader);
            Object obj = a.read(jsonreader);
            if (map.put(obj, b.read(jsonreader)) != null)
            {
                throw new JsonSyntaxException((new StringBuilder("duplicate key: ")).append(obj).toString());
            }
        }
        jsonreader.endObject();
        return map;
    }

    public final volatile void write(JsonWriter jsonwriter, Object obj)
    {
        write(jsonwriter, (Map)obj);
    }

    public final void write(JsonWriter jsonwriter, Map map)
    {
        int i = 0;
        if (map == null)
        {
            jsonwriter.nullValue();
            return;
        }
        if (!MapTypeAdapterFactory.a(d))
        {
            jsonwriter.beginObject();
            java.util.Map.Entry entry1;
            for (Iterator iterator1 = map.entrySet().iterator(); iterator1.hasNext(); b.write(jsonwriter, entry1.getValue()))
            {
                entry1 = (java.util.Map.Entry)iterator1.next();
                jsonwriter.name(String.valueOf(entry1.getKey()));
            }

            jsonwriter.endObject();
            return;
        }
        ArrayList arraylist = new ArrayList(map.size());
        ArrayList arraylist1 = new ArrayList(map.size());
        Iterator iterator = map.entrySet().iterator();
        boolean flag = false;
        while (iterator.hasNext()) 
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            JsonElement jsonelement1 = a.toJsonTree(entry.getKey());
            arraylist.add(jsonelement1);
            arraylist1.add(entry.getValue());
            boolean flag1;
            if (jsonelement1.isJsonArray() || jsonelement1.isJsonObject())
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            flag = flag1 | flag;
        }
        if (flag)
        {
            jsonwriter.beginArray();
            for (; i < arraylist.size(); i++)
            {
                jsonwriter.beginArray();
                Streams.write((JsonElement)arraylist.get(i), jsonwriter);
                b.write(jsonwriter, arraylist1.get(i));
                jsonwriter.endArray();
            }

            jsonwriter.endArray();
            return;
        }
        jsonwriter.beginObject();
        while (i < arraylist.size()) 
        {
            JsonElement jsonelement = (JsonElement)arraylist.get(i);
            String s;
            if (jsonelement.isJsonPrimitive())
            {
                JsonPrimitive jsonprimitive = jsonelement.getAsJsonPrimitive();
                if (jsonprimitive.isNumber())
                {
                    s = String.valueOf(jsonprimitive.getAsNumber());
                } else
                if (jsonprimitive.isBoolean())
                {
                    s = Boolean.toString(jsonprimitive.getAsBoolean());
                } else
                if (jsonprimitive.isString())
                {
                    s = jsonprimitive.getAsString();
                } else
                {
                    throw new AssertionError();
                }
            } else
            if (jsonelement.isJsonNull())
            {
                s = "null";
            } else
            {
                throw new AssertionError();
            }
            jsonwriter.name(s);
            b.write(jsonwriter, arraylist1.get(i));
            i++;
        }
        jsonwriter.endObject();
    }
}
