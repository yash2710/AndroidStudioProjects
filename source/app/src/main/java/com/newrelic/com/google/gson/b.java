// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.com.google.gson;

import java.lang.reflect.Field;

// Referenced classes of package com.newrelic.com.google.gson:
//            FieldNamingPolicy

final class b extends FieldNamingPolicy
{

    b(String s, int i)
    {
        super(s, 0, (byte)0);
    }

    public final String translateName(Field field)
    {
        return field.getName();
    }
}
