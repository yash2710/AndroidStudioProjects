// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson.internal;

import com.google.mygson.InstanceCreator;
import java.lang.reflect.Type;

// Referenced classes of package com.google.mygson.internal:
//            ObjectConstructor, ConstructorConstructor

final class h
    implements ObjectConstructor
{

    private InstanceCreator a;
    private Type b;

    h(ConstructorConstructor constructorconstructor, InstanceCreator instancecreator, Type type)
    {
        a = instancecreator;
        b = type;
        super();
    }

    public final Object construct()
    {
        return a.createInstance(b);
    }
}
