// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson.internal.bind;

import com.google.mygson.Gson;
import com.google.mygson.TypeAdapter;
import com.google.mygson.reflect.TypeToken;
import com.google.mygson.stream.JsonReader;
import com.google.mygson.stream.JsonWriter;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class m extends TypeAdapter
{

    private final Gson a;
    private final TypeAdapter b;
    private final Type c;

    m(Gson gson, TypeAdapter typeadapter, Type type)
    {
        a = gson;
        b = typeadapter;
        c = type;
    }

    public final Object read(JsonReader jsonreader)
    {
        return b.read(jsonreader);
    }

    public final void write(JsonWriter jsonwriter, Object obj)
    {
        TypeAdapter typeadapter = b;
        Object obj1 = c;
        if (obj != null && (obj1 == java/lang/Object || (obj1 instanceof TypeVariable) || (obj1 instanceof Class)))
        {
            obj1 = obj.getClass();
        }
        TypeAdapter typeadapter1;
        if (obj1 != c)
        {
            typeadapter1 = a.getAdapter(TypeToken.get(((Type) (obj1))));
            if ((typeadapter1 instanceof ReflectiveTypeAdapterFactory.Adapter) && !(b instanceof ReflectiveTypeAdapterFactory.Adapter))
            {
                typeadapter1 = b;
            }
        } else
        {
            typeadapter1 = typeadapter;
        }
        typeadapter1.write(jsonwriter, obj);
    }
}
