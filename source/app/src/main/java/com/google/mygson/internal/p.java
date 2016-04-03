// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson.internal;

import com.google.mygson.Gson;
import com.google.mygson.TypeAdapter;
import com.google.mygson.reflect.TypeToken;
import com.google.mygson.stream.JsonReader;
import com.google.mygson.stream.JsonWriter;

// Referenced classes of package com.google.mygson.internal:
//            Excluder

final class p extends TypeAdapter
{

    private TypeAdapter a;
    private boolean b;
    private boolean c;
    private Gson d;
    private TypeToken e;
    private Excluder f;

    p(Excluder excluder, boolean flag, boolean flag1, Gson gson, TypeToken typetoken)
    {
        f = excluder;
        b = flag;
        c = flag1;
        d = gson;
        e = typetoken;
        super();
    }

    private TypeAdapter a()
    {
        TypeAdapter typeadapter = a;
        if (typeadapter != null)
        {
            return typeadapter;
        } else
        {
            TypeAdapter typeadapter1 = d.getDelegateAdapter(f, e);
            a = typeadapter1;
            return typeadapter1;
        }
    }

    public final Object read(JsonReader jsonreader)
    {
        if (b)
        {
            jsonreader.skipValue();
            return null;
        } else
        {
            return a().read(jsonreader);
        }
    }

    public final void write(JsonWriter jsonwriter, Object obj)
    {
        if (c)
        {
            jsonwriter.nullValue();
            return;
        } else
        {
            a().write(jsonwriter, obj);
            return;
        }
    }
}
