// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.gson.internal;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.gson.internal:
//            p

public final class Excluder
    implements TypeAdapterFactory, Cloneable
{

    public static final Excluder DEFAULT = new Excluder();
    private double a;
    private int b;
    private boolean c;
    private boolean d;
    private List e;
    private List f;

    public Excluder()
    {
        a = -1D;
        b = 136;
        c = true;
        e = Collections.emptyList();
        f = Collections.emptyList();
    }

    private boolean a(Since since, Until until)
    {
        boolean flag;
        if (since != null && since.value() > a)
        {
            flag = false;
        } else
        {
            flag = true;
        }
        if (flag)
        {
            boolean flag1;
            if (until != null && until.value() <= a)
            {
                flag1 = false;
            } else
            {
                flag1 = true;
            }
            if (flag1)
            {
                return true;
            }
        }
        return false;
    }

    private static boolean a(Class class1)
    {
        return !java/lang/Enum.isAssignableFrom(class1) && (class1.isAnonymousClass() || class1.isLocalClass());
    }

    private boolean b(Class class1)
    {
        if (class1.isMemberClass())
        {
            boolean flag;
            if ((8 & class1.getModifiers()) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                return true;
            }
        }
        return false;
    }

    protected final Excluder clone()
    {
        Excluder excluder;
        try
        {
            excluder = (Excluder)super.clone();
        }
        catch (CloneNotSupportedException clonenotsupportedexception)
        {
            throw new AssertionError();
        }
        return excluder;
    }

    protected final volatile Object clone()
    {
        return clone();
    }

    public final TypeAdapter create(Gson gson, TypeToken typetoken)
    {
        Class class1 = typetoken.getRawType();
        boolean flag = excludeClass(class1, true);
        boolean flag1 = excludeClass(class1, false);
        if (!flag && !flag1)
        {
            return null;
        } else
        {
            return new p(this, flag1, flag, gson, typetoken);
        }
    }

    public final Excluder disableInnerClassSerialization()
    {
        Excluder excluder = clone();
        excluder.c = false;
        return excluder;
    }

    public final boolean excludeClass(Class class1, boolean flag)
    {
        if (a != -1D && !a((Since)class1.getAnnotation(com/google/gson/annotations/Since), (Until)class1.getAnnotation(com/google/gson/annotations/Until)))
        {
            return true;
        }
        if (!c && b(class1))
        {
            return true;
        }
        if (a(class1))
        {
            return true;
        }
        List list;
        Iterator iterator;
        if (flag)
        {
            list = e;
        } else
        {
            list = f;
        }
        for (iterator = list.iterator(); iterator.hasNext();)
        {
            if (((ExclusionStrategy)iterator.next()).shouldSkipClass(class1))
            {
                return true;
            }
        }

        return false;
    }

    public final boolean excludeField(Field field, boolean flag)
    {
label0:
        {
            if ((b & field.getModifiers()) != 0)
            {
                return true;
            }
            if (a != -1D && !a((Since)field.getAnnotation(com/google/gson/annotations/Since), (Until)field.getAnnotation(com/google/gson/annotations/Until)))
            {
                return true;
            }
            if (field.isSynthetic())
            {
                return true;
            }
            if (d)
            {
                Expose expose = (Expose)field.getAnnotation(com/google/gson/annotations/Expose);
                if (expose == null || (flag ? !expose.serialize() : !expose.deserialize()))
                {
                    return true;
                }
            }
            if (!c && b(field.getType()))
            {
                return true;
            }
            if (a(field.getType()))
            {
                return true;
            }
            List list;
            FieldAttributes fieldattributes;
            Iterator iterator;
            if (flag)
            {
                list = e;
            } else
            {
                list = f;
            }
            if (list.isEmpty())
            {
                break label0;
            }
            fieldattributes = new FieldAttributes(field);
            iterator = list.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break label0;
                }
            } while (!((ExclusionStrategy)iterator.next()).shouldSkipField(fieldattributes));
            return true;
        }
        return false;
    }

    public final Excluder excludeFieldsWithoutExposeAnnotation()
    {
        Excluder excluder = clone();
        excluder.d = true;
        return excluder;
    }

    public final Excluder withExclusionStrategy(ExclusionStrategy exclusionstrategy, boolean flag, boolean flag1)
    {
        Excluder excluder = clone();
        if (flag)
        {
            excluder.e = new ArrayList(e);
            excluder.e.add(exclusionstrategy);
        }
        if (flag1)
        {
            excluder.f = new ArrayList(f);
            excluder.f.add(exclusionstrategy);
        }
        return excluder;
    }

    public final transient Excluder withModifiers(int ai[])
    {
        int i = 0;
        Excluder excluder = clone();
        excluder.b = 0;
        for (int j = ai.length; i < j; i++)
        {
            excluder.b = ai[i] | excluder.b;
        }

        return excluder;
    }

    public final Excluder withVersion(double d1)
    {
        Excluder excluder = clone();
        excluder.a = d1;
        return excluder;
    }

}
