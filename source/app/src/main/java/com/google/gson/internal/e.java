// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.gson.internal;

import java.util.LinkedHashMap;

// Referenced classes of package com.google.gson.internal:
//            ObjectConstructor, ConstructorConstructor

final class e
    implements ObjectConstructor
{

    e(ConstructorConstructor constructorconstructor)
    {
    }

    public final Object construct()
    {
        return new LinkedHashMap();
    }
}
