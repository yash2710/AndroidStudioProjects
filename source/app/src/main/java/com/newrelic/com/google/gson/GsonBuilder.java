// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.com.google.gson;

import com.newrelic.com.google.gson.internal.Excluder;
import com.newrelic.com.google.gson.internal.bind.TypeAdapters;
import com.newrelic.com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.newrelic.com.google.gson:
//            LongSerializationPolicy, FieldNamingPolicy, a, o, 
//            Gson, JsonSerializer, JsonDeserializer, InstanceCreator, 
//            TypeAdapter, FieldNamingStrategy, ExclusionStrategy, TypeAdapterFactory

public final class GsonBuilder
{

    private Excluder a;
    private LongSerializationPolicy b;
    private FieldNamingStrategy c;
    private final Map d = new HashMap();
    private final List e = new ArrayList();
    private final List f = new ArrayList();
    private boolean g;
    private String h;
    private int i;
    private int j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;

    public GsonBuilder()
    {
        a = Excluder.DEFAULT;
        b = LongSerializationPolicy.DEFAULT;
        c = FieldNamingPolicy.IDENTITY;
        i = 2;
        j = 2;
        m = true;
    }

    public final GsonBuilder addDeserializationExclusionStrategy(ExclusionStrategy exclusionstrategy)
    {
        a = a.withExclusionStrategy(exclusionstrategy, false, true);
        return this;
    }

    public final GsonBuilder addSerializationExclusionStrategy(ExclusionStrategy exclusionstrategy)
    {
        a = a.withExclusionStrategy(exclusionstrategy, true, false);
        return this;
    }

    public final Gson create()
    {
        ArrayList arraylist;
        String s;
        int i1;
        int j1;
        arraylist = new ArrayList();
        arraylist.addAll(e);
        Collections.reverse(arraylist);
        arraylist.addAll(f);
        s = h;
        i1 = i;
        j1 = j;
        if (s == null || "".equals(s.trim())) goto _L2; else goto _L1
_L1:
        a a1 = new a(s);
_L6:
        arraylist.add(com.newrelic.com.google.gson.o.newFactory(TypeToken.get(java/util/Date), a1));
        arraylist.add(com.newrelic.com.google.gson.o.newFactory(TypeToken.get(java/sql/Timestamp), a1));
        arraylist.add(com.newrelic.com.google.gson.o.newFactory(TypeToken.get(java/sql/Date), a1));
_L4:
        return new Gson(a, c, d, g, k, o, m, n, l, b, arraylist);
_L2:
        if (i1 == 2 || j1 == 2) goto _L4; else goto _L3
_L3:
        a1 = new a(i1, j1);
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final GsonBuilder disableHtmlEscaping()
    {
        m = false;
        return this;
    }

    public final GsonBuilder disableInnerClassSerialization()
    {
        a = a.disableInnerClassSerialization();
        return this;
    }

    public final GsonBuilder enableComplexMapKeySerialization()
    {
        k = true;
        return this;
    }

    public final transient GsonBuilder excludeFieldsWithModifiers(int ai[])
    {
        a = a.withModifiers(ai);
        return this;
    }

    public final GsonBuilder excludeFieldsWithoutExposeAnnotation()
    {
        a = a.excludeFieldsWithoutExposeAnnotation();
        return this;
    }

    public final GsonBuilder generateNonExecutableJson()
    {
        o = true;
        return this;
    }

    public final GsonBuilder registerTypeAdapter(Type type, Object obj)
    {
        boolean flag;
        if ((obj instanceof JsonSerializer) || (obj instanceof JsonDeserializer) || (obj instanceof InstanceCreator) || (obj instanceof TypeAdapter))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        com.newrelic.com.google.gson.internal..Gson.Preconditions.checkArgument(flag);
        if (obj instanceof InstanceCreator)
        {
            d.put(type, (InstanceCreator)obj);
        }
        if ((obj instanceof JsonSerializer) || (obj instanceof JsonDeserializer))
        {
            TypeToken typetoken = TypeToken.get(type);
            e.add(com.newrelic.com.google.gson.o.newFactoryWithMatchRawType(typetoken, obj));
        }
        if (obj instanceof TypeAdapter)
        {
            e.add(TypeAdapters.newFactory(TypeToken.get(type), (TypeAdapter)obj));
        }
        return this;
    }

    public final GsonBuilder registerTypeAdapterFactory(TypeAdapterFactory typeadapterfactory)
    {
        e.add(typeadapterfactory);
        return this;
    }

    public final GsonBuilder registerTypeHierarchyAdapter(Class class1, Object obj)
    {
        boolean flag;
        if ((obj instanceof JsonSerializer) || (obj instanceof JsonDeserializer) || (obj instanceof TypeAdapter))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        com.newrelic.com.google.gson.internal..Gson.Preconditions.checkArgument(flag);
        if ((obj instanceof JsonDeserializer) || (obj instanceof JsonSerializer))
        {
            f.add(0, com.newrelic.com.google.gson.o.newTypeHierarchyFactory(class1, obj));
        }
        if (obj instanceof TypeAdapter)
        {
            e.add(TypeAdapters.newTypeHierarchyFactory(class1, (TypeAdapter)obj));
        }
        return this;
    }

    public final GsonBuilder serializeNulls()
    {
        g = true;
        return this;
    }

    public final GsonBuilder serializeSpecialFloatingPointValues()
    {
        l = true;
        return this;
    }

    public final GsonBuilder setDateFormat(int i1)
    {
        i = i1;
        h = null;
        return this;
    }

    public final GsonBuilder setDateFormat(int i1, int j1)
    {
        i = i1;
        j = j1;
        h = null;
        return this;
    }

    public final GsonBuilder setDateFormat(String s)
    {
        h = s;
        return this;
    }

    public final transient GsonBuilder setExclusionStrategies(ExclusionStrategy aexclusionstrategy[])
    {
        int i1 = aexclusionstrategy.length;
        for (int j1 = 0; j1 < i1; j1++)
        {
            ExclusionStrategy exclusionstrategy = aexclusionstrategy[j1];
            a = a.withExclusionStrategy(exclusionstrategy, true, true);
        }

        return this;
    }

    public final GsonBuilder setFieldNamingPolicy(FieldNamingPolicy fieldnamingpolicy)
    {
        c = fieldnamingpolicy;
        return this;
    }

    public final GsonBuilder setFieldNamingStrategy(FieldNamingStrategy fieldnamingstrategy)
    {
        c = fieldnamingstrategy;
        return this;
    }

    public final GsonBuilder setLongSerializationPolicy(LongSerializationPolicy longserializationpolicy)
    {
        b = longserializationpolicy;
        return this;
    }

    public final GsonBuilder setPrettyPrinting()
    {
        n = true;
        return this;
    }

    public final GsonBuilder setVersion(double d1)
    {
        a = a.withVersion(d1);
        return this;
    }
}
