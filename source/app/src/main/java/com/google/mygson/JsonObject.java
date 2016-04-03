// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson;

import com.google.mygson.internal.LinkedTreeMap;
import java.util.Set;

// Referenced classes of package com.google.mygson:
//            JsonElement, JsonNull, JsonPrimitive, JsonArray

public final class JsonObject extends JsonElement
{

    private final LinkedTreeMap a = new LinkedTreeMap();

    public JsonObject()
    {
    }

    private static JsonElement a(Object obj)
    {
        if (obj == null)
        {
            return JsonNull.INSTANCE;
        } else
        {
            return new JsonPrimitive(obj);
        }
    }

    public final void add(String s, JsonElement jsonelement)
    {
        if (jsonelement == null)
        {
            jsonelement = JsonNull.INSTANCE;
        }
        a.put(s, jsonelement);
    }

    public final void addProperty(String s, Boolean boolean1)
    {
        add(s, a(boolean1));
    }

    public final void addProperty(String s, Character character)
    {
        add(s, a(character));
    }

    public final void addProperty(String s, Number number)
    {
        add(s, a(number));
    }

    public final void addProperty(String s, String s1)
    {
        add(s, a(s1));
    }

    public final Set entrySet()
    {
        return a.entrySet();
    }

    public final boolean equals(Object obj)
    {
        return obj == this || (obj instanceof JsonObject) && ((JsonObject)obj).a.equals(a);
    }

    public final JsonElement get(String s)
    {
        return (JsonElement)a.get(s);
    }

    public final JsonArray getAsJsonArray(String s)
    {
        return (JsonArray)a.get(s);
    }

    public final JsonObject getAsJsonObject(String s)
    {
        return (JsonObject)a.get(s);
    }

    public final JsonPrimitive getAsJsonPrimitive(String s)
    {
        return (JsonPrimitive)a.get(s);
    }

    public final boolean has(String s)
    {
        return a.containsKey(s);
    }

    public final int hashCode()
    {
        return a.hashCode();
    }

    public final JsonElement remove(String s)
    {
        return (JsonElement)a.remove(s);
    }
}
