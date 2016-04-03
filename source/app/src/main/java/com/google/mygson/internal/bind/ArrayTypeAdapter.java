// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson.internal.bind;

import com.google.mygson.Gson;
import com.google.mygson.TypeAdapter;
import com.google.mygson.TypeAdapterFactory;
import com.google.mygson.stream.JsonReader;
import com.google.mygson.stream.JsonToken;
import com.google.mygson.stream.JsonWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.mygson.internal.bind:
//            a, m

public final class ArrayTypeAdapter extends TypeAdapter
{

    public static final TypeAdapterFactory FACTORY = new a();
    private final Class a;
    private final TypeAdapter b;

    public ArrayTypeAdapter(Gson gson, TypeAdapter typeadapter, Class class1)
    {
        b = new m(gson, typeadapter, class1);
        a = class1;
    }

    public final Object read(JsonReader jsonreader)
    {
        if (jsonreader.peek() == JsonToken.NULL)
        {
            jsonreader.nextNull();
            return null;
        }
        ArrayList arraylist = new ArrayList();
        jsonreader.beginArray();
        for (; jsonreader.hasNext(); arraylist.add(b.read(jsonreader))) { }
        jsonreader.endArray();
        Object obj = Array.newInstance(a, arraylist.size());
        for (int i = 0; i < arraylist.size(); i++)
        {
            Array.set(obj, i, arraylist.get(i));
        }

        return obj;
    }

    public final void write(JsonWriter jsonwriter, Object obj)
    {
        if (obj == null)
        {
            jsonwriter.nullValue();
            return;
        }
        jsonwriter.beginArray();
        int i = 0;
        for (int j = Array.getLength(obj); i < j; i++)
        {
            Object obj1 = Array.get(obj, i);
            b.write(jsonwriter, obj1);
        }

        jsonwriter.endArray();
    }

}
