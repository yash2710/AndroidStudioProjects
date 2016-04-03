// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final class as
{

    final Object a;
    private final Method b;
    private final int c;
    private boolean d;

    as(Object obj, Method method)
    {
        d = true;
        if (obj == null)
        {
            throw new NullPointerException("EventProducer target cannot be null.");
        }
        if (method == null)
        {
            throw new NullPointerException("EventProducer method cannot be null.");
        } else
        {
            a = obj;
            b = method;
            method.setAccessible(true);
            c = 31 * (31 + method.hashCode()) + obj.hashCode();
            return;
        }
    }

    public final boolean a()
    {
        return d;
    }

    public final void b()
    {
        d = false;
    }

    public final Object c()
    {
        if (!d)
        {
            throw new IllegalStateException((new StringBuilder()).append(toString()).append(" has been invalidated and can no longer produce events.").toString());
        }
        Object obj;
        try
        {
            obj = b.invoke(a, new Object[0]);
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw new AssertionError(illegalaccessexception);
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            if (invocationtargetexception.getCause() instanceof Error)
            {
                throw (Error)invocationtargetexception.getCause();
            } else
            {
                throw invocationtargetexception;
            }
        }
        return obj;
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null)
            {
                return false;
            }
            if (getClass() != obj.getClass())
            {
                return false;
            }
            as as1 = (as)obj;
            if (!b.equals(as1.b) || a != as1.a)
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        return c;
    }

    public final String toString()
    {
        return (new StringBuilder("[EventProducer ")).append(b).append("]").toString();
    }
}
