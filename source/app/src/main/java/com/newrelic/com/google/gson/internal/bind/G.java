// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.com.google.gson.internal.bind;

import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.TypeAdapter;
import com.newrelic.com.google.gson.TypeAdapterFactory;
import com.newrelic.com.google.gson.reflect.TypeToken;

// Referenced classes of package com.newrelic.com.google.gson.internal.bind:
//            U

final class G
    implements TypeAdapterFactory
{

    G()
    {
    }

    public final TypeAdapter create(Gson gson, TypeToken typetoken)
    {
        Class class1 = typetoken.getRawType();
        if (!java/lang/Enum.isAssignableFrom(class1) || class1 == java/lang/Enum)
        {
            return null;
        }
        if (!class1.isEnum())
        {
            class1 = class1.getSuperclass();
        }
        return new U(class1);
    }
}