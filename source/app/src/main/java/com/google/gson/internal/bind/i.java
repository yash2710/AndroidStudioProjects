// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.lang.reflect.Field;

// Referenced classes of package com.google.gson.internal.bind:
//            j, ReflectiveTypeAdapterFactory, m

final class i extends j
{

    private TypeAdapter d;
    private Gson e;
    private Field f;
    private TypeToken g;
    private boolean h;
    private ReflectiveTypeAdapterFactory i;

    i(ReflectiveTypeAdapterFactory reflectivetypeadapterfactory, String s, boolean flag, boolean flag1, Gson gson, Field field, TypeToken typetoken, 
            boolean flag2)
    {
        i = reflectivetypeadapterfactory;
        e = gson;
        f = field;
        g = typetoken;
        h = flag2;
        super(s, flag, flag1);
        d = ReflectiveTypeAdapterFactory.a(i, e, f, g);
    }

    final void a(JsonReader jsonreader, Object obj)
    {
        Object obj1 = d.read(jsonreader);
        if (obj1 != null || !h)
        {
            f.set(obj, obj1);
        }
    }

    final void a(JsonWriter jsonwriter, Object obj)
    {
        Object obj1 = f.get(obj);
        (new m(e, d, g.getType())).write(jsonwriter, obj1);
    }
}
