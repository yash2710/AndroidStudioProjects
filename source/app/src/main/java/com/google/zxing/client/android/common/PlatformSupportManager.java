// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.common;

import com.flipkart.logging.FkLogger;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class PlatformSupportManager
{

    private static final String a = com/google/zxing/client/android/common/PlatformSupportManager.getSimpleName();
    private final Class b;
    private final Object c;
    private final SortedMap d;

    protected PlatformSupportManager(Class class1, Object obj)
    {
        if (!class1.isInterface())
        {
            throw new IllegalArgumentException();
        }
        if (!class1.isInstance(obj))
        {
            throw new IllegalArgumentException();
        } else
        {
            b = class1;
            c = obj;
            d = new TreeMap(Collections.reverseOrder());
            return;
        }
    }

    protected final void addImplementationClass(int i, String s)
    {
        d.put(Integer.valueOf(i), s);
    }

    public final Object build()
    {
        Iterator iterator = d.keySet().iterator();
_L2:
        Integer integer;
        String s;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        integer = (Integer)iterator.next();
        if (android.os.Build.VERSION.SDK_INT < integer.intValue())
        {
            continue; /* Loop/switch isn't completed */
        }
        s = (String)d.get(integer);
        Object obj;
        Class class1 = Class.forName(s).asSubclass(b);
        FkLogger.info(a, (new StringBuilder("Using implementation ")).append(class1).append(" of ").append(b).append(" for SDK ").append(integer).toString());
        obj = class1.getConstructor(new Class[0]).newInstance(new Object[0]);
        return obj;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        FkLogger.warn(a, classnotfoundexception);
        continue; /* Loop/switch isn't completed */
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        FkLogger.warn(a, illegalaccessexception);
        continue; /* Loop/switch isn't completed */
        InstantiationException instantiationexception;
        instantiationexception;
        FkLogger.warn(a, instantiationexception);
        continue; /* Loop/switch isn't completed */
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
        FkLogger.warn(a, nosuchmethodexception);
        continue; /* Loop/switch isn't completed */
        InvocationTargetException invocationtargetexception;
        invocationtargetexception;
        FkLogger.warn(a, invocationtargetexception);
        if (true) goto _L2; else goto _L1
_L1:
        FkLogger.info(a, (new StringBuilder("Using default implementation ")).append(c.getClass()).append(" of ").append(b).toString());
        return c;
    }

}
