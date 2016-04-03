// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.com.google.gson.internal.bind;

import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonNull;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;
import com.newrelic.com.google.gson.stream.JsonReader;
import com.newrelic.com.google.gson.stream.JsonToken;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.newrelic.com.google.gson.internal.bind:
//            d

public final class JsonTreeReader extends JsonReader
{

    private static final Reader a = new d();
    private static final Object b = new Object();
    private final List c = new ArrayList();

    public JsonTreeReader(JsonElement jsonelement)
    {
        super(a);
        c.add(jsonelement);
    }

    private Object a()
    {
        return c.get(-1 + c.size());
    }

    private void a(JsonToken jsontoken)
    {
        if (peek() != jsontoken)
        {
            throw new IllegalStateException((new StringBuilder("Expected ")).append(jsontoken).append(" but was ").append(peek()).toString());
        } else
        {
            return;
        }
    }

    private Object b()
    {
        return c.remove(-1 + c.size());
    }

    public final void beginArray()
    {
        a(JsonToken.BEGIN_ARRAY);
        JsonArray jsonarray = (JsonArray)a();
        c.add(jsonarray.iterator());
    }

    public final void beginObject()
    {
        a(JsonToken.BEGIN_OBJECT);
        JsonObject jsonobject = (JsonObject)a();
        c.add(jsonobject.entrySet().iterator());
    }

    public final void close()
    {
        c.clear();
        c.add(b);
    }

    public final void endArray()
    {
        a(JsonToken.END_ARRAY);
        b();
        b();
    }

    public final void endObject()
    {
        a(JsonToken.END_OBJECT);
        b();
        b();
    }

    public final boolean hasNext()
    {
        JsonToken jsontoken = peek();
        return jsontoken != JsonToken.END_OBJECT && jsontoken != JsonToken.END_ARRAY;
    }

    public final boolean nextBoolean()
    {
        a(JsonToken.BOOLEAN);
        return ((JsonPrimitive)b()).getAsBoolean();
    }

    public final double nextDouble()
    {
        JsonToken jsontoken = peek();
        if (jsontoken != JsonToken.NUMBER && jsontoken != JsonToken.STRING)
        {
            throw new IllegalStateException((new StringBuilder("Expected ")).append(JsonToken.NUMBER).append(" but was ").append(jsontoken).toString());
        }
        double d1 = ((JsonPrimitive)a()).getAsDouble();
        if (!isLenient() && (Double.isNaN(d1) || Double.isInfinite(d1)))
        {
            throw new NumberFormatException((new StringBuilder("JSON forbids NaN and infinities: ")).append(d1).toString());
        } else
        {
            b();
            return d1;
        }
    }

    public final int nextInt()
    {
        JsonToken jsontoken = peek();
        if (jsontoken != JsonToken.NUMBER && jsontoken != JsonToken.STRING)
        {
            throw new IllegalStateException((new StringBuilder("Expected ")).append(JsonToken.NUMBER).append(" but was ").append(jsontoken).toString());
        } else
        {
            int i = ((JsonPrimitive)a()).getAsInt();
            b();
            return i;
        }
    }

    public final long nextLong()
    {
        JsonToken jsontoken = peek();
        if (jsontoken != JsonToken.NUMBER && jsontoken != JsonToken.STRING)
        {
            throw new IllegalStateException((new StringBuilder("Expected ")).append(JsonToken.NUMBER).append(" but was ").append(jsontoken).toString());
        } else
        {
            long l = ((JsonPrimitive)a()).getAsLong();
            b();
            return l;
        }
    }

    public final String nextName()
    {
        a(JsonToken.NAME);
        java.util.Map.Entry entry = (java.util.Map.Entry)((Iterator)a()).next();
        c.add(entry.getValue());
        return (String)entry.getKey();
    }

    public final void nextNull()
    {
        a(JsonToken.NULL);
        b();
    }

    public final String nextString()
    {
        JsonToken jsontoken = peek();
        if (jsontoken != JsonToken.STRING && jsontoken != JsonToken.NUMBER)
        {
            throw new IllegalStateException((new StringBuilder("Expected ")).append(JsonToken.STRING).append(" but was ").append(jsontoken).toString());
        } else
        {
            return ((JsonPrimitive)b()).getAsString();
        }
    }

    public final JsonToken peek()
    {
        Object obj;
        do
        {
            if (c.isEmpty())
            {
                return JsonToken.END_DOCUMENT;
            }
            obj = a();
            if (!(obj instanceof Iterator))
            {
                break;
            }
            boolean flag = c.get(-2 + c.size()) instanceof JsonObject;
            Iterator iterator = (Iterator)obj;
            if (iterator.hasNext())
            {
                if (flag)
                {
                    return JsonToken.NAME;
                }
                c.add(iterator.next());
            } else
            if (flag)
            {
                return JsonToken.END_OBJECT;
            } else
            {
                return JsonToken.END_ARRAY;
            }
        } while (true);
        if (obj instanceof JsonObject)
        {
            return JsonToken.BEGIN_OBJECT;
        }
        if (obj instanceof JsonArray)
        {
            return JsonToken.BEGIN_ARRAY;
        }
        if (obj instanceof JsonPrimitive)
        {
            JsonPrimitive jsonprimitive = (JsonPrimitive)obj;
            if (jsonprimitive.isString())
            {
                return JsonToken.STRING;
            }
            if (jsonprimitive.isBoolean())
            {
                return JsonToken.BOOLEAN;
            }
            if (jsonprimitive.isNumber())
            {
                return JsonToken.NUMBER;
            } else
            {
                throw new AssertionError();
            }
        }
        if (obj instanceof JsonNull)
        {
            return JsonToken.NULL;
        }
        if (obj == b)
        {
            throw new IllegalStateException("JsonReader is closed");
        } else
        {
            throw new AssertionError();
        }
    }

    public final void promoteNameToValue()
    {
        a(JsonToken.NAME);
        java.util.Map.Entry entry = (java.util.Map.Entry)((Iterator)a()).next();
        c.add(entry.getValue());
        c.add(new JsonPrimitive((String)entry.getKey()));
    }

    public final void skipValue()
    {
        if (peek() == JsonToken.NAME)
        {
            nextName();
            return;
        } else
        {
            b();
            return;
        }
    }

    public final String toString()
    {
        return getClass().getSimpleName();
    }

}
