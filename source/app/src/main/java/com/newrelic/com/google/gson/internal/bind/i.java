// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.com.google.gson.internal.bind;

import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.TypeAdapter;
import com.newrelic.com.google.gson.reflect.TypeToken;
import com.newrelic.com.google.gson.stream.JsonReader;
import com.newrelic.com.google.gson.stream.JsonWriter;
import java.lang.reflect.Field;

// Referenced classes of package com.newrelic.com.google.gson.internal.bind:
//            j, m, ReflectiveTypeAdapterFactory

final class i extends j
{

    private TypeAdapter d;
    private Gson e;
    private TypeToken f;
    private Field g;
    private boolean h;

    i(ReflectiveTypeAdapterFactory reflectivetypeadapterfactory, String s, boolean flag, boolean flag1, Gson gson, TypeToken typetoken, Field field, 
            boolean flag2)
    {
        e = gson;
        f = typetoken;
        g = field;
        h = flag2;
        super(s, flag, flag1);
        d = e.getAdapter(f);
    }

    final void a(JsonReader jsonreader, Object obj)
    {
        Object obj1 = d.read(jsonreader);
        if (obj1 != null || !h)
        {
            g.set(obj, obj1);
        }
    }

    final void a(JsonWriter jsonwriter, Object obj)
    {
        Object obj1 = g.get(obj);
        (new m(e, d, f.getType())).write(jsonwriter, obj1);
    }
}