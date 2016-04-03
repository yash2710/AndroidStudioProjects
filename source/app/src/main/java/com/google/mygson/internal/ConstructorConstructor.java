// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson.internal;

import com.google.mygson.InstanceCreator;
import com.google.mygson.reflect.TypeToken;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

// Referenced classes of package com.google.mygson.internal:
//            i, d, h, j, 
//            g, k, l, m, 
//            n, o, e, f, 
//            ObjectConstructor

public final class ConstructorConstructor
{

    private final Map a;

    public ConstructorConstructor(Map map)
    {
        a = map;
    }

    private ObjectConstructor a(Class class1)
    {
        i i1;
        try
        {
            Constructor constructor = class1.getDeclaredConstructor(new Class[0]);
            if (!constructor.isAccessible())
            {
                constructor.setAccessible(true);
            }
            i1 = new i(this, constructor);
        }
        catch (NoSuchMethodException nosuchmethodexception)
        {
            return null;
        }
        return i1;
    }

    public final ObjectConstructor get(TypeToken typetoken)
    {
        java.lang.reflect.Type type = typetoken.getType();
        Class class1 = typetoken.getRawType();
        InstanceCreator instancecreator = (InstanceCreator)a.get(type);
        Object obj;
        if (instancecreator != null)
        {
            obj = new d(this, instancecreator, type);
        } else
        {
            InstanceCreator instancecreator1 = (InstanceCreator)a.get(class1);
            if (instancecreator1 != null)
            {
                return new h(this, instancecreator1, type);
            }
            obj = a(class1);
            if (obj == null)
            {
                if (java/util/Collection.isAssignableFrom(class1))
                {
                    if (java/util/SortedSet.isAssignableFrom(class1))
                    {
                        obj = new j(this);
                    } else
                    if (java/util/EnumSet.isAssignableFrom(class1))
                    {
                        obj = new k(this, type);
                    } else
                    if (java/util/Set.isAssignableFrom(class1))
                    {
                        obj = new l(this);
                    } else
                    if (java/util/Queue.isAssignableFrom(class1))
                    {
                        obj = new m(this);
                    } else
                    {
                        obj = new n(this);
                    }
                } else
                if (java/util/Map.isAssignableFrom(class1))
                {
                    if (java/util/SortedMap.isAssignableFrom(class1))
                    {
                        obj = new o(this);
                    } else
                    if ((type instanceof ParameterizedType) && !java/lang/String.isAssignableFrom(TypeToken.get(((ParameterizedType)type).getActualTypeArguments()[0]).getRawType()))
                    {
                        obj = new e(this);
                    } else
                    {
                        obj = new f(this);
                    }
                } else
                {
                    obj = null;
                }
                if (obj == null)
                {
                    return new g(this, class1, type);
                }
            }
        }
        return ((ObjectConstructor) (obj));
    }

    public final String toString()
    {
        return a.toString();
    }
}
