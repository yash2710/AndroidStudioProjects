// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.gson.internal;

import java.lang.reflect.Method;

// Referenced classes of package com.google.gson.internal:
//            UnsafeAllocator

final class J extends UnsafeAllocator
{

    private Method a;
    private int b;

    J(Method method, int i)
    {
        a = method;
        b = i;
        super();
    }

    public final Object newInstance(Class class1)
    {
        Method method = a;
        Object aobj[] = new Object[2];
        aobj[0] = class1;
        aobj[1] = Integer.valueOf(b);
        return method.invoke(null, aobj);
    }
}
