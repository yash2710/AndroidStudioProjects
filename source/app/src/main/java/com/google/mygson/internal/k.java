// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson.internal;

import com.google.mygson.JsonIOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumSet;

// Referenced classes of package com.google.mygson.internal:
//            ObjectConstructor, ConstructorConstructor

final class k
    implements ObjectConstructor
{

    private Type a;

    k(ConstructorConstructor constructorconstructor, Type type)
    {
        a = type;
        super();
    }

    public final Object construct()
    {
        if (a instanceof ParameterizedType)
        {
            Type type = ((ParameterizedType)a).getActualTypeArguments()[0];
            if (type instanceof Class)
            {
                return EnumSet.noneOf((Class)type);
            } else
            {
                throw new JsonIOException((new StringBuilder("Invalid EnumSet type: ")).append(a.toString()).toString());
            }
        } else
        {
            throw new JsonIOException((new StringBuilder("Invalid EnumSet type: ")).append(a.toString()).toString());
        }
    }
}
