// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson.internal.bind;

import com.google.mygson.Gson;
import com.google.mygson.TypeAdapter;
import com.google.mygson.TypeAdapterFactory;
import com.google.mygson.reflect.TypeToken;

final class J
    implements TypeAdapterFactory
{

    private Class a;
    private Class b;
    private TypeAdapter c;

    J(Class class1, Class class2, TypeAdapter typeadapter)
    {
        a = class1;
        b = class2;
        c = typeadapter;
        super();
    }

    public final TypeAdapter create(Gson gson, TypeToken typetoken)
    {
        Class class1 = typetoken.getRawType();
        if (class1 == a || class1 == b)
        {
            return c;
        } else
        {
            return null;
        }
    }

    public final String toString()
    {
        return (new StringBuilder("Factory[type=")).append(b.getName()).append("+").append(a.getName()).append(",adapter=").append(c).append("]").toString();
    }
}
